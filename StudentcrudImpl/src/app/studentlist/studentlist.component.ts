import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { Student } from '../student';

@Component({
  selector: 'app-studentlist',
  templateUrl: './studentlist.component.html',
  styleUrls: ['./studentlist.component.css']
})
export class StudentlistComponent implements OnInit {
  students: Array<Student> = [];
  constructor(private _route: Router, private _service: NgserviceService) { }
  ngOnInit(): void {
   
    this.getStudents();
  }

  getStudents() {
    this._service.fetchStudentListFromRemote().subscribe(
      data => this.students = data, error => console.log("Exception occurred 1"),
    )
  }
  isEmpty()
  {
    if (this.students == null)
    {
      return true;
    }
    else { return false; }
  }

  goToAddCourse() {
    this._route.navigate(['/addstudent']);
  }

  goToUpdateStudent(id: number) {
    console.log("id: "+ id);
    this._route.navigate(['/updatestudent', id]);
  }


  goToViewStudent(id: number){
    this._route.navigate(['/viewstudent', id]);

  }

deleteStudent(id:number) {
    if (confirm('Are you sure ?'))
  return this._service.deleteStudentByIdFromRemote(id).subscribe(
    success =>{
      ("Product deleted succesfully");
      this.getStudents();
    },
    error=> {console.log("Exception occured 2"); this.getStudents()}
   )
  }

}
