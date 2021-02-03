import { SelectionModel } from "@angular/cdk/collections";
import { FlatTreeControl } from "@angular/cdk/tree";
import { Component, EventEmitter, Injectable, Input, OnInit, Output } from "@angular/core";
import { FormControl } from "@angular/forms";
import {
  MatTreeFlatDataSource,
  MatTreeFlattener
} from "@angular/material/tree";
import { BehaviorSubject, Observable } from "rxjs";
import { SectorService } from "../sector.service";

export class SectorNode {
  children?: SectorNode[];
  name: string;
  childrenId: number;
}

export class SectorFlatNode {
  childrenId: number;
  name: string;
  level: number;
  expandable: boolean;
}


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
      this.dataChange.next(dataTree);
    });
  }
}

@Component({
  selector: "sector-tree",
  templateUrl: "sector-tree.component.html",
  styleUrls: ["sector-tree.component.css"],
  providers: [ChecklistDatabase]
})
export class SectorTreeComponent implements OnInit {

  @Input() selection: SectorFlatNode[];
  @Output() selectedEventEmitter = new EventEmitter<SectorFlatNode[]>();


  flatNodeMap = new Map<SectorFlatNode, SectorNode>();

  nestedNodeMap = new Map<SectorNode, SectorFlatNode>();

  selectedParent: SectorFlatNode | null = null;

  newItemName = "";

  treeControl: FlatTreeControl<SectorFlatNode>;

  treeFlattener: MatTreeFlattener<SectorNode, SectorFlatNode>;

  dataSource: MatTreeFlatDataSource<SectorNode, SectorFlatNode>;

  checklistSelection = new SelectionModel<SectorFlatNode>(true /* multiple */);

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

  ngOnChanges() {
    this.treeControl.dataNodes.forEach(node => {
      this.selection.forEach(element => {
        if (element.name == node.name && element.childrenId == node.childrenId) {
          this.checklistSelection.select(node);
        }
      });
    });
    this.selectedEventEmitter.emit(this.checklistSelection.selected);
  }

  getLevel = (node: SectorFlatNode) => node.level;

  isExpandable = (node: SectorFlatNode) => node.expandable;

  getChildren = (node: SectorNode): SectorNode[] => node.children;

  hasChild = (_: number, _nodeData: SectorFlatNode) => _nodeData.expandable;

  hasNoContent = (_: number, _nodeData: SectorFlatNode) => _nodeData.name === "";

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

  descendantsAllSelected(node: SectorFlatNode): boolean {
    const descendants = this.treeControl.getDescendants(node);
    const descAllSelected = descendants.every(child =>
      this.checklistSelection.isSelected(child)
    );
    return descAllSelected;
  }

  descendantsPartiallySelected(node: SectorFlatNode): boolean {
    const descendants = this.treeControl.getDescendants(node);
    const result = descendants.some(child =>
      this.checklistSelection.isSelected(child)
    );
    return result && !this.descendantsAllSelected(node);
  }

  todoItemSelectionToggle(node: SectorFlatNode): void {
    this.checklistSelection.toggle(node);
    const descendants = this.treeControl.getDescendants(node);
    this.checklistSelection.isSelected(node)
      ? this.checklistSelection.select(...descendants)
      : this.checklistSelection.deselect(...descendants);

    descendants.every(child => this.checklistSelection.isSelected(child));
    this.checkAllParentsSelection(node);
  }

  todoLeafItemSelectionToggle(node: SectorFlatNode): void {
    this.checklistSelection.toggle(node);
    this.checkAllParentsSelection(node);
  }

  checkAllParentsSelection(node: SectorFlatNode): void {
    let parent: SectorFlatNode | null = this.getParentNode(node);
    while (parent) {
      this.checkRootNodeSelection(parent);
      parent = this.getParentNode(parent);
    }
  }

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
