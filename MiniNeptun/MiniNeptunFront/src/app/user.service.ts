import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService, httpOptions } from './auth.service';
import { Group } from './group';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
    private authService : AuthService,
  ) { }

  newUser(reqBody: User): Promise<User>{
    return this.http.post<User>("http://localhost:8080/users/register", reqBody ,httpOptions).toPromise()
  }

  getUser(id: number): Promise<User> {
    return this.http.get<User>("http://localhost:8080/users/"+ id,httpOptions).toPromise()
  }

  getGroups(id: number): Promise<Group[]>{
    return this.http.get<Group[]>("http://localhost:8080/users/"+ id + "/groups", httpOptions).toPromise()
  }
  
  modUser(id: number, reqBody: User): Promise<User>{
    return this.http.put<User>("http://localhost:8080/users/"+ id, reqBody, httpOptions).toPromise()
  }  

  delUser(id: number): Promise<any>{
    return this.http.delete("http://localhost:8080/users/"+ id, httpOptions).toPromise()
  }

  dropGroup(id: number, reqBody: Group): Promise<any>{
    return this.http.request('DELETE', "http://localhost:8080/users/"+ id + "/groups", {
      headers: httpOptions.headers,
      body: reqBody 
    }).toPromise();
  }

  takeGroup(id: number, reqBody: Group): Promise<any>{
    return this.http.post("http://localhost:8080/users/"+ id + "/groups", reqBody, httpOptions).toPromise()
  }
  
}
