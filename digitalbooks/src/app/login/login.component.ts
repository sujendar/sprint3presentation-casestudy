import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { Userdetails } from '../userdetails';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userName!:string;
  userDetails=new Userdetails();
  constructor(private _route: Router, private _service: NgserviceService) {}

  ngOnInit(): void {}


  logIn(){
    console.log(this.userName);
    this._service.fetchUserDetailsFromRemote(this.userName).subscribe(
      (resp) => {
        if(resp==null){
          alert("INVALID CRDENTAILS");
        }else{
        this.userDetails = resp;
        localStorage.setItem('userDetails', JSON.stringify(this.userDetails.userId));
        if(this.userDetails.role=="READER"){
          this._route.navigateByUrl('/readerdashboard');
        }else if(this.userDetails.role=="AUTHOR"){
          this._route.navigateByUrl('/authordashboard');
        }else{
          this._route.navigateByUrl('/guestdashboard');
        }
        }
      }, (error) => console.log("Exception occurred "+error),
    )
    
  }

}
