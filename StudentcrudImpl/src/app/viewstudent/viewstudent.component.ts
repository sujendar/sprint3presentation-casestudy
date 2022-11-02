import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { Student } from '../student';

@Component({
  selector: 'app-viewstudent',
  templateUrl: './viewstudent.component.html',
  styleUrls: ['./viewstudent.component.css']
})
export class ViewstudentComponent implements OnInit {
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
  gotolist() {
    this._route.navigate(['studentlist']);
  }
}
