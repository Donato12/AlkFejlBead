import { Component, OnInit } from '@angular/core';
import { Subject } from '../subject';
import { SubjectService } from '../subject.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})
export class SubjectListComponent implements OnInit {

  subjects: Subject[];
  showSubjects: Subject[];

  constructor(
    private authService: AuthService,
    private subjectService : SubjectService,
  ) { }

  async ngOnInit() {
    this.subjects = await this.subjectService.getSubjects();
    this.showSubjects = this.subjects;
  }

  onSubmit() {
  }

}
