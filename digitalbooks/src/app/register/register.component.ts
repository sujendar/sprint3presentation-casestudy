import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { Userdetails } from '../userdetails';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userdetails=new Userdetails();
  constructor(private _route: Router, private _service: NgserviceService) {}

  ngOnInit(): void {}

  addUserformsubmit()
  {
  this._service.addUserDetailsToRemote(this.userdetails).subscribe
  (
    (resp) =>{
      console.log("Data added successfully");
      alert("User Registerd succesfully");
      this._route.navigate(['/login']);
    },
    (error) =>{
      console.log(error);

    }
  )
  }

}
