import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GroupService } from '../group.service';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { AuthService } from '../auth.service';
import { User } from '../user';

@Component({
  selector: 'group-detail',
  templateUrl: './group-detail.component.html',
  styleUrls: ['./group-detail.component.css']
})
export class GroupDetailComponent implements OnInit {

  id: number;
  members : User[];

  constructor(
    private groupService : GroupService,
    private route: ActivatedRoute,
    private authService: AuthService,
  ) { }

  async ngOnInit() {
    this.route.params.subscribe(params => {
       this.id = +params['id'];
    });
    this.members = await this.groupService.getMembers(this.id)
  }




}