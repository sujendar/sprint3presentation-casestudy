import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthoraddbookComponent } from './authoraddbook/authoraddbook.component';
import { AuthorbookviewComponent } from './authorbookview/authorbookview.component';
import { AuthordashboardComponent } from './authordashboard/authordashboard.component';
import { AuthoreditbookComponent } from './authoreditbook/authoreditbook.component';
import { BooklistComponent } from './booklist/booklist.component';
import { GuestdashboardComponent } from './guestdashboard/guestdashboard.component';
import { LoginComponent } from './login/login.component';
import { ReaderdashboardComponent } from './readerdashboard/readerdashboard.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: "", component: LoginComponent },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: "readerdashboard", component: ReaderdashboardComponent },
  { path: "authordashboard", component: AuthordashboardComponent },
  { path: "guestdashboard", component: GuestdashboardComponent },
  { path: "addbook", component: AuthoraddbookComponent },
  { path: "editbook/:id", component: AuthoreditbookComponent },
  { path: "viewbook/:id", component: AuthorbookviewComponent },
  { path: "booklist", component: BooklistComponent }
  
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
