import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService, httpOptions } from './auth.service';
import { Group } from './group';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor(
    private http: HttpClient,
    private authService : AuthService,
  ) { }

  getGroup(id: number): Promise<Group> {
    return this.http.get<Group>("http://localhost:8080/groups/"+ id,httpOptions).toPromise()
  }
  
  newGroup(id: number, reqBody: Group): Promise<any> {
    return this.http.post("http://localhost:8080/groups/subject/"+ id, reqBody, httpOptions).toPromise()
  }  
    
  editGroup(id: number, reqBody: Group): Promise<any> {
    return this.http.put("http://localhost:8080/groups/"+ id, reqBody, httpOptions).toPromise()
  }
  
  delGroup(id: number): Promise<any>{
    return this.http.delete("http://localhost:8080/groups/"+ id, httpOptions).toPromise()
  }

  getMembers(id: number): Promise<User[]>{
    return this.http.get<User[]>("http://localhost:8080/groups/"+ id+ "/members",httpOptions).toPromise()
  }
}
