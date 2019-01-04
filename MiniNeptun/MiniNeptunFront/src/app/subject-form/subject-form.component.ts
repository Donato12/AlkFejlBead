import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Validators, FormBuilder, AbstractControl } from '@angular/forms';
import { Subject } from '../subject';
import { SubjectService } from '../subject.service';

@Component({
  selector: 'subject-form',
  templateUrl: './subject-form.component.html',
  styleUrls: ['./subject-form.component.css']
})
export class SubjectFormComponent implements OnInit {

  subjectForm = this.formBuilder.group({
    id: [''],
    name: ['', [Validators.required]],
    type: ['', [Validators.required]],
    creditValue: ['', [Validators.required,Validators.pattern('[0-9]*')]],
    gradeMethod: ['', [Validators.required]],
    gradeValue: ['', [Validators.required,Validators.pattern('[0-9]*')]],
  });

  get name() { return this.subjectForm.get('name');}
  get type() { return this.subjectForm.get('type');}
  get gradeType() { return this.subjectForm.get('gradeType');}
  get creditValue() { return this.subjectForm.get('creditValue');}
  get gradeMethod() { return this.subjectForm.get('gradeMethod');}
  get gradeValue() { return this.subjectForm.get('gradeValue');}

  id : number = null
  title: string
  subject: Subject
  types= ["EA", "EA+GY", "GY", "Egyéb"]
  gradeMethods= ["Vizsga", "Beadandó", "Egyéb"]

  @Output() save = new EventEmitter<Subject>();

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private subjectService : SubjectService,
  ) { }

  async ngOnInit() {
    if(this.route.toString().includes("edit")) {
      this.route.params.subscribe(params => {
        this.id = +params['id'];
      });
      this.subject = await this.subjectService.getSubject(this.id);
      this.title = "Szerkesztés"
      this.subjectForm.setValue(this.subject)
      this.subjectForm.get("id").setValue(this.id);
      switch(this.subject.type){
        case "TYPE_EA":
            this.type.setValue("EA")
            break;
        case "TYPE_GY":
            this.type.setValue("GY")
            break;       
        case "TYPE_MIX":
            this.type.setValue("EA+GY")
            break;           
        default:
            this.type.setValue("Egyéb")
        }
      switch(this.subject.gradeMethod){
      case "METHOD_EXAM":
          this.gradeMethod.setValue("Vizsga")
          break;
      case "METHOD_HOMEWORK":
      this.gradeMethod.setValue("Beadandó")
          break;     
      default:
        this.gradeMethod.setValue("Egyéb")      
      }
    }
    else {
      this.title = "Új Tárgy"
      this.subject = new Subject()    
    } 
  }

  async onSubmit() {
    const emittedIssue = Object.assign(this.subject, this.subjectForm.value);
    this.save.emit(emittedIssue);
    switch(this.subject.type){
      case "EA":
        this.subject.type = "TYPE_EA";
        break;
      case "GY":
        this.subject.type = "TYPE_GY";
        break;       
      case "EA+GY":
        this.subject.type = "TYPE_MIX";
        break;           
      default:
        this.subject.type = "TYPE_OTHER";
    }
    switch(this.subject.gradeMethod){
      case "Vizsga":
        this.subject.gradeMethod = "METHOD_EXAM";
        break;
      case "Beadandó":
        this.subject.gradeMethod = "METHOD_HOMEWORK";
        break;        
      default:
        this.subject.gradeMethod = "METHOD_OTHER";
    } 
    if(this.route.toString().includes("edit")) {
      await this.subjectService.editSubject(this.id, this.subject)
    }
    else {
      await this.subjectService.newSubject(this.subject)
    }
    this.router.navigate(['/subjects'])
  }
}
