import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from './subject';
import { AuthService, httpOptions } from './auth.service';
import 'rxjs/add/operator/map';
import { Group } from './group';

@Injectable({
  providedIn: 'root'
})

export class SubjectService {

  constructor(
    private http: HttpClient,
    private authService : AuthService,
  ) { }
  getSubject(id: number): Promise<Subject> {
    return this.http.get<Subject>("http://localhost:8080/subjects/"+ id,httpOptions).toPromise()
  }

  getSubjects(): Promise<Subject[]> {
    return this.http.get<Subject[]>("http://localhost:8080/subjects", httpOptions).toPromise()
  }

  getGroups(id: number): Promise<Group[]> {
    return this.http.get<Group[]>("http://localhost:8080/subjects/"+ id + "/groups", httpOptions).toPromise()
  }

  newSubject(reqBody: Subject): Promise<any> {
    return this.http.post("http://localhost:8080/subjects", reqBody, httpOptions).toPromise()
  }  

  delSubject(id: number): Promise<any>{
    return this.http.delete("http://localhost:8080/subjects/"+ id, httpOptions).toPromise()
  }
  
  editSubject(id: number, reqBody: Subject): Promise<any> {
    return this.http.put("http://localhost:8080/subjects/"+ id, reqBody, httpOptions).toPromise()
  }
}
