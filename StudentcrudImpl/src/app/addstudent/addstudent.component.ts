import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { Student } from '../student';

@Component({
  selector: 'app-addstudent',
  templateUrl: './addstudent.component.html',
  styleUrls: ['./addstudent.component.css']
})
export class AddstudentComponent implements OnInit {

  student = new Student();
  constructor(private _route: Router,private _service: NgserviceService) { }

  ngOnInit(): void {
  }

  addStudentformsubmit()
{
this._service.addStudentToRemote(this.student).subscribe
(
  data =>{
    console.log("Data added successfully");
    this._route.navigate(['studentlist']);
  },
  error =>console.log("Error")
)
}


  gotolist() {
    this._route.navigate(['studentlist']);
  }

}
