import { SelectionModel } from "@angular/cdk/collections";
import { FlatTreeControl } from "@angular/cdk/tree";
import { Component, EventEmitter, Injectable, OnInit, Output } from "@angular/core";
import {
  MatTreeFlatDataSource,
  MatTreeFlattener
} from "@angular/material/tree";
import { BehaviorSubject } from "rxjs";
import { FormControl } from "@angular/forms";
import { MatInputModule } from '@angular/material/input'
import { Observable } from "rxjs";
import { map, startWith } from "rxjs/operators";
import { SectorService } from "../sector.service";

/**
 * Node for to-do item
 */
export class SectorNode {
  children?: SectorNode[];
  name: string;
  childrenId: number;
}

/** Flat to-do item node with expandable and level information */
export class SectorFlatNode {
  childrenId: number;
  name: string;
  level: number;
  expandable: boolean;
}

/**
 * The Json object for to-do list data.
 */
const TREE_DATA: SectorNode[] = [
  {
    name: "Fruit",
    childrenId: 1,
    children: [
      {
        childrenId: 2,
        name: "Apple"
      },
      {
        childrenId: 2,
        name: "Banana"
      },
      {
        childrenId: 5,
        name: "Fruit loops",
        children: [
          {
            childrenId: 5,
            name: "Cherry"
          },
          {
            childrenId: 7,
            name: "Grapes", children: [{
              childrenId: 10,
              name: "Oranges"
            }]
          }
        ]
      }
    ]
  }
];

/**
 * Checklist database, it can build a tree structured Json object.
 * Each node in Json object represents a to-do item or a category.
 * If a node is a category, it has children items and new items can be added under the category.
 */
@Injectable()
export class ChecklistDatabase {
  dataChange = new BehaviorSubject<SectorNode[]>([]);
  treeData: any[];
  sectorService: SectorService;
  sectors: string;

  get data(): SectorNode[] {
    return this.dataChange.value;
  }

  constructor(sectorService: SectorService) {
    this.sectorService = sectorService;
    this.initialize();
  }

  initialize() {
    this.sectorService.findAll().subscribe(data => {

      var jsonString = JSON.stringify(data);

      jsonString = jsonString.split('mainSector').join('children');
      jsonString = jsonString.split('levelTwoSector').join('children');
      jsonString = jsonString.split('levelThreeSector').join('children');
      jsonString = jsonString.split('levelFourSector').join('children');

      this.sectors = jsonString;

    this.treeData = JSON.parse(this.sectors);

    const dataTree = JSON.parse(this.sectors);

    // Notify the change.
    this.dataChange.next(dataTree);
    });
    // Build the tree nodes from Json object. The result is a list of `TodoItemNode` with nested
    //     file node as children.

  }


}

@Component({
  selector: "sector-tree",
  templateUrl: "sector-tree.component.html",
  styleUrls: ["sector-tree.component.css"],
  providers: [ChecklistDatabase]
})
export class SectorTreeComponent implements OnInit {


  @Output() selectedEventEmitter = new EventEmitter<SectorFlatNode[]>();


  /** Map from flat node to nested node. This helps us finding the nested node to be modified */
  flatNodeMap = new Map<SectorFlatNode, SectorNode>();

  /** Map from nested node to flattened node. This helps us to keep the same object for selection */
  nestedNodeMap = new Map<SectorNode, SectorFlatNode>();

  /** A selected parent node to be inserted */
  selectedParent: SectorFlatNode | null = null;

  /** The new item's name */
  newItemName = "";

  treeControl: FlatTreeControl<SectorFlatNode>;

  treeFlattener: MatTreeFlattener<SectorNode, SectorFlatNode>;

  dataSource: MatTreeFlatDataSource<SectorNode, SectorFlatNode>;

  /** The selection for checklist */
  checklistSelection = new SelectionModel<SectorFlatNode>(true /* multiple */);

  /// Filtering
  myControl = new FormControl();
  options: string[] = ["One", "Two", "Three"];
  filteredOptions: Observable<string[]>;

  constructor(private _database: ChecklistDatabase) {
    this.treeFlattener = new MatTreeFlattener(
      this.transformer,
      this.getLevel,
      this.isExpandable,
      this.getChildren
    );
    this.treeControl = new FlatTreeControl<SectorFlatNode>(
      this.getLevel,
      this.isExpandable
    );
    this.dataSource = new MatTreeFlatDataSource(
      this.treeControl,
      this.treeFlattener
    );

    _database.dataChange.subscribe(data => {
      this.dataSource.data = data;
    });
  }

  ngOnInit() { }

  getLevel = (node: SectorFlatNode) => node.level;

  isExpandable = (node: SectorFlatNode) => node.expandable;

  getChildren = (node: SectorNode): SectorNode[] => node.children;

  hasChild = (_: number, _nodeData: SectorFlatNode) => _nodeData.expandable;

  hasNoContent = (_: number, _nodeData: SectorFlatNode) => _nodeData.name === "";

  /**
   * Transformer to convert nested node to flat node. Record the nodes in maps for later use.
   */
  transformer = (node: SectorNode, level: number) => {
    const existingNode = this.nestedNodeMap.get(node);
    const flatNode =
      existingNode && existingNode.name === node.name
        ? existingNode
        : new SectorFlatNode();
    flatNode.name = node.name;
    flatNode.childrenId = node.childrenId;
    flatNode.level = level;
    flatNode.expandable = !(node.children === undefined || node.children.length == 0)
    this.flatNodeMap.set(flatNode, node);
    this.nestedNodeMap.set(node, flatNode);
    return flatNode;
  };

  /** Whether all the descendants of the node are selected. */
  descendantsAllSelected(node: SectorFlatNode): boolean {
    const descendants = this.treeControl.getDescendants(node);
    const descAllSelected = descendants.every(child =>
      this.checklistSelection.isSelected(child)
    );
    return descAllSelected;
  }

  /** Whether part of the descendants are selected */
  descendantsPartiallySelected(node: SectorFlatNode): boolean {
    const descendants = this.treeControl.getDescendants(node);
    const result = descendants.some(child =>
      this.checklistSelection.isSelected(child)
    );
    return result && !this.descendantsAllSelected(node);
  }

  /** Toggle the to-do item selection. Select/deselect all the descendants node */
  todoItemSelectionToggle(node: SectorFlatNode): void {
    this.checklistSelection.toggle(node);
    const descendants = this.treeControl.getDescendants(node);
    this.checklistSelection.isSelected(node)
      ? this.checklistSelection.select(...descendants)
      : this.checklistSelection.deselect(...descendants);

    // Force update for the parent
    descendants.every(child => this.checklistSelection.isSelected(child));
    this.checkAllParentsSelection(node);
  }

  /** Toggle a leaf to-do item selection. Check all the parents to see if they changed */
  todoLeafItemSelectionToggle(node: SectorFlatNode): void {
    this.checklistSelection.toggle(node);
    this.checkAllParentsSelection(node);
  }

  /* Checks all the parents when a leaf node is selected/unselected */
  checkAllParentsSelection(node: SectorFlatNode): void {
    let parent: SectorFlatNode | null = this.getParentNode(node);
    while (parent) {
      this.checkRootNodeSelection(parent);
      parent = this.getParentNode(parent);
    }
  }

  /** Check root node checked state and change it accordingly */
  checkRootNodeSelection(node: SectorFlatNode): void {
    const nodeSelected = this.checklistSelection.isSelected(node);
    const descendants = this.treeControl.getDescendants(node);
    const descAllSelected = descendants.every(child =>
      this.checklistSelection.isSelected(child)
    );
    if (nodeSelected && !descAllSelected) {
      this.checklistSelection.deselect(node);
    } else if (!nodeSelected && descAllSelected) {
      this.checklistSelection.select(node);
    }
  }

  /* Get the parent node of a node */
  getParentNode(node: SectorFlatNode): SectorFlatNode | null {
    console.log(this.checklistSelection.selected);
    this.selectedEventEmitter.emit(this.checklistSelection.selected)
    const currentLevel = this.getLevel(node);

    if (currentLevel < 1) {
      return null;
    }

    const startIndex = this.treeControl.dataNodes.indexOf(node) - 1;

    for (let i = startIndex; i >= 0; i--) {
      const currentNode = this.treeControl.dataNodes[i];

      if (this.getLevel(currentNode) < currentLevel) {
        return currentNode;
      }
    }
    return null;
  }

  getSelectedItems(): string {
    if (!this.checklistSelection.selected.length) return "Sectors you are involved in";
    return this.checklistSelection.selected.map(s => s.name).join(", ");
  }


}
