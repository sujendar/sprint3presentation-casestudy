import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthoraddbookComponent } from '../authoraddbook/authoraddbook.component';
import { AuthorbookviewComponent } from '../authorbookview/authorbookview.component';
import { AuthoreditbookComponent } from '../authoreditbook/authoreditbook.component';
import { Bookdetails } from '../bookdetails';
import { NgserviceService } from '../ngservice.service';

@Component({
  selector: 'app-authordashboard',
  templateUrl: './authordashboard.component.html',
  styleUrls: ['./authordashboard.component.css']
})
export class AuthordashboardComponent implements OnInit {
  booklist: Array<Bookdetails> = [];
  constructor(private _route: Router, private _service: NgserviceService,public authoraddbook: MatDialog) { }
  ngOnInit(): void {
   
    this.getBooks();
  }

  getBooks() {
    this._service.fetchBookListFromRemote().subscribe(
      data => this.booklist = data, error => console.log("Exception occurred "+error),
    )
  }
  isEmpty()
  {
    if (this.booklist == null)
    {
      return true;
    }
    else { return false; }
  }

  goToAddBook() {
    const dialogRef = this.authoraddbook.open(AuthoraddbookComponent);
    this.getBooks();
  }

  goToUpdateBook(id: number) {
    console.log("id: "+ id);
    const dialogRef = this.authoraddbook.open(AuthoreditbookComponent,{ data: {myId:id}, disableClose: true });
    this.getBooks();
  }


  goToViewBook(id: number){
    const dialogRef = this.authoraddbook.open(AuthorbookviewComponent,{ data: {myId:id}, disableClose: true });
 

  }

/*deleteBook(id:number) {
    if (confirm('Are you sure ?'))
  return this._service.deleteStudentByIdFromRemote(id).subscribe(
    success =>{
      ("Product deleted succesfully");
      this.getStudents();
    },
    error=> {console.log("Exception occured 2"); this.getStudents()}
   )
  }*/


}
