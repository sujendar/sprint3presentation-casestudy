import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { Student } from '../student';

@Component({
  selector: 'app-editstudent',
  templateUrl: './editstudent.component.html',
  styleUrls: ['./editstudent.component.css']
})
export class EditstudentComponent implements OnInit {
  id!: number;
 student =new Student();
  constructor(private _route:Router, private _service: NgserviceService, private _activatedRouter: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this._activatedRouter.snapshot.params['id'];
    console.log(this.id);
   this._service.fetchStudentByIdFromRemote(this.id).subscribe(
    data=>{
      console.log("data received");
      this.student = data;
    },
      error => console.log("Exception Occured")
    )
  }

  updateStudentformsubmit()
{
this._service.updateStudentToRemote(this.id,this.student).subscribe
(
  data =>{
    console.log("Data updated successfully");
    this._route.navigate(['studentlist']);
  },
  error =>console.log("Error")
)
}


  gotolist() {
    this._route.navigate(['studentlist']);
  }


}
