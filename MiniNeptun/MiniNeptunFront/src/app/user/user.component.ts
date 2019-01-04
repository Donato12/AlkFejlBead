import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Group } from '../group';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  id: number;
  profile : User = new User();
  takenGroups: Group[]

  constructor(
    private userService : UserService,
    private route: ActivatedRoute,
    private authService: AuthService,
  ) { }

  async ngOnInit() {
    if(this.route.toString().includes("profile/")) {
      this.route.params.subscribe(params => {
        this.id = +params['id'];
      });
      this.profile = await this.userService.getUser(this.id) 
    }
    else {
      this.profile = this.authService.user
      this.id = this.profile.id
    }
  }





}