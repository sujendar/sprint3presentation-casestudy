import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { User } from '../user';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  user:User=new User();
  constructor(private _route: Router,private userservices:NgserviceService) { }

  ngOnInit(): void {
  }
 userLogin(){
   console.log(this.user);
   this.userservices.loginUser(this.user).subscribe(data=>{
     alert("Login Sucessfully");
     this._route.navigate(['/studentlist']);
   },error=>alert("Please enterd correct username and password"));
   
 }

}
