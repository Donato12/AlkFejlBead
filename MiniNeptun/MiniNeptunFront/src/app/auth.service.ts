import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http'; 
import { User } from './user';

export const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin' : '*',
    'Content-Type': 'application/json',
    'Authorization' : '',
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn : boolean;
  user : User;
  redirectUrl : String;
  constructor(private http: HttpClient) { }

  async login(username: string, password: string): Promise<User> {
    try {
      const token = btoa(`${username}:${password}`);
      httpOptions.headers = httpOptions.headers.set('Authorization', `Basic ${token}`);
      const user = await this.http.post<User>(`http://localhost:8080/users/login`, {} ,httpOptions).toPromise();
      this.isLoggedIn = true;
      this.user = user;
      this.redirectUrl = "/main";
      return Promise.resolve(this.user);
    } catch (e) {
      console.log(e);
      return Promise.reject();
    }
  }
  
  logout() {
    httpOptions.headers = httpOptions.headers.set('Authorization', ``);
    this.isLoggedIn = false;
    this.user = null;
  }
}
