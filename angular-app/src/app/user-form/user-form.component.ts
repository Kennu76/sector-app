import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { SectorFlatNode } from '../sector-tree/sector-tree.component';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  title = 'Please enter your name and pick the Sectors you are currently involved in.'
  user: User;

  selected: SectorFlatNode[];

  constructor(
    private userService: UserService) {
    this.user = new User();
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.user.sectors = this.selected;
    this.userService.save(this.user).subscribe();
  }

  addSelected(selected: SectorFlatNode[]) {
    this.selected = selected;
    console.log("parent");
    console.log(selected);
  }


}
