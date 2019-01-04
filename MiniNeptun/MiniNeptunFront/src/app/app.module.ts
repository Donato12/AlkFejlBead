import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatToolbarModule, MatIconModule, MatButtonModule, MatFormFieldModule} from '@angular/material';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RoutingModule } from './routing/routing.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SubjectListComponent } from './subject-list/subject-list.component';
import { SubjectFormComponent } from './subject-form/subject-form.component';
import { MainPageComponent } from './main-page/main-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SubjectDetailComponent } from './subject-detail/subject-detail.component';
import { GroupDetailComponent } from './group-detail/group-detail.component';
import { UserComponent } from './user/user.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginFormComponent } from './login-form/login-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material';
import { MatDialogModule } from '@angular/material';
import { MatListModule } from '@angular/material/list';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import {MatSelectModule} from '@angular/material/select';


@NgModule({
  declarations: [
    AppComponent,
    SubjectListComponent,
    SubjectFormComponent,
    MainPageComponent,
    SubjectDetailComponent,
    GroupDetailComponent,
    UserComponent,
    LoginFormComponent,
    DeleteDialogComponent
  ],
  imports: [ 
    MatListModule,
    MatSelectModule,
    MatDialogModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserModule,    
    HttpClientModule,
    NgbModule.forRoot(),
    RoutingModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
  ],
  entryComponents: [DeleteDialogComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
