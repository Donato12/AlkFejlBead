import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubjectListComponent } from "../subject-list/subject-list.component";
import { SubjectFormComponent } from "../subject-form/subject-form.component";
import { SubjectDetailComponent } from "../subject-detail/subject-detail.component";
import { GroupDetailComponent } from "../group-detail/group-detail.component";
import { MainPageComponent } from "../main-page/main-page.component";
import { UserComponent } from "../user/user.component";
import { AuthGuard } from '../auth.guard';
import { LoginFormComponent } from '../login-form/login-form.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginFormComponent,
  },  
  {
    path: 'main',
    component: MainPageComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_STUDENT','ROLE_TEACHER','ROLE_ADMIN']
    }
  },  
  {
    path: 'subjects',
    component: SubjectListComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_STUDENT','ROLE_TEACHER','ROLE_ADMIN']
    }
  },  
  {
    path: 'subjects/new',
    component: SubjectFormComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN']
    }
  },
  {
    path: 'subjects/:id',
    component: SubjectDetailComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_STUDENT','ROLE_TEACHER','ROLE_ADMIN']
    }
  },  
  {
    path: 'subjects/:id/edit',
    component: SubjectFormComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN']
    }
  },   
  {
    path: 'groups/:id',
    component: GroupDetailComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_TEACHER','ROLE_ADMIN']
    }
  },
  {
    path: 'profile',
    component: UserComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_STUDENT','ROLE_TEACHER','ROLE_ADMIN']
    }
  }, 
  {
    path: 'profile/:id',
    component: UserComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_STUDENT','ROLE_TEACHER','ROLE_ADMIN']
    }
  },     

];

@NgModule({
  imports: [ RouterModule.forRoot(routes)  ],
  exports: [ RouterModule ],
  declarations: []
})
export class RoutingModule { }