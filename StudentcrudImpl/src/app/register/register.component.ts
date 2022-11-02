import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { User } from '../user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user:User=new User();
  constructor(private _route: Router,private userservices:NgserviceService) { }

  ngOnInit(): void {
  }
  userSignup(){
    console.log(this.user);
    this.userservices.saveUser(this.user).subscribe(data=>{
      alert("user registered Sucessfully");
      this._route.navigate(['/login']);
    },error=>alert("user has not been saved"));
    
  }

}
