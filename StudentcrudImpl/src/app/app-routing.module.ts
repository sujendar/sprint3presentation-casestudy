import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddstudentComponent } from './addstudent/addstudent.component';
import { EditstudentComponent } from './editstudent/editstudent.component';
import { RegisterComponent } from './register/register.component';
import { StudentlistComponent } from './studentlist/studentlist.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { ViewstudentComponent } from './viewstudent/viewstudent.component';

const routes: Routes = [
  {path: 'studentlist', component: StudentlistComponent},
  {path: 'addstudent', component: AddstudentComponent},
  {path: 'studentlist', component: StudentlistComponent},
  {path: 'updatestudent/:id', component: EditstudentComponent},
  {path: 'viewstudent/:id', component:ViewstudentComponent},
  {path: '', component: UserLoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: UserLoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
