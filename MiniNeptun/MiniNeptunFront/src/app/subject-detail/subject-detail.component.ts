import { Component, OnInit } from '@angular/core';
import { Subject } from '../subject';
import { SubjectService } from '../subject.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Group } from '../group';
import { MatDialog, MatDialogRef } from '@angular/material';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';

@Component({
  selector: 'subject-detail',
  templateUrl: './subject-detail.component.html',
  styleUrls: ['./subject-detail.component.css']
})
export class SubjectDetailComponent implements OnInit {

  id: number;
  subject : Subject;
  groups : Group[];
  takenGroupIds : number[];
  takenSubject : boolean;

  deleteDialogRef: MatDialogRef<DeleteDialogComponent>;

  constructor(
    private subjectService : SubjectService,
    private userService : UserService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private dialog: MatDialog
  ) { }

  async ngOnInit() {
    this.route.params.subscribe(params => {
       this.id = +params['id'];
    });
    this.subject = await this.subjectService.getSubject(this.id);
    this.groups = await this.subjectService.getGroups(this.id);
    this.updateGroups()
  }

  deleteDialog() {
    this.deleteDialogRef = this.dialog.open(DeleteDialogComponent);
    this.deleteDialogRef
        .afterClosed().subscribe(val => this.confirmDelete(val));
  }

  async confirmDelete(confirm: boolean) {
    if(confirm == true) {
      await this.subjectService.delSubject(this.id)
      this.router.navigate(['/subjects'])
    }
  }

  async dropGroup(groupId: number) {
    var toDrop = new Group()
    toDrop.id = groupId
    await this.userService.dropGroup(this.authService.user.id, toDrop)
    this.updateGroups()   
  }

  async takeGroup(groupId: number) {
    var toTake = new Group()
    toTake.id = groupId
    await this.userService.takeGroup(this.authService.user.id, toTake)
    this.updateGroups()
  }

  async updateGroups() {
    this.takenGroupIds = []
    var takenGroups = await this.userService.getGroups(this.authService.user.id)
    takenGroups.forEach(element => {
      this.takenGroupIds.push(element.id)
    });
    this.takenSubject = false
    this.groups.forEach(element => {
      if(this.takenGroupIds.includes(element.id)) {
        this.takenSubject = true;
        return
      }
    })

  }

}
