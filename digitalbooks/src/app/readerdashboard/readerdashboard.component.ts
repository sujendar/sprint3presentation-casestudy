import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Bookdetails } from '../bookdetails';
import { NgserviceService } from '../ngservice.service';

@Component({
  selector: 'app-readerdashboard',
  templateUrl: './readerdashboard.component.html',
  styleUrls: ['./readerdashboard.component.css']
})
export class ReaderdashboardComponent implements OnInit {

  booklist: Array<Bookdetails> = [];
  constructor(private _route: Router, private _service: NgserviceService,public authoraddbook: MatDialog) { }
  ngOnInit(): void {
   
    this.getBooks();
  }

  getBooks() {
    let userId =localStorage.getItem('userDetails')!;
    this._service.fetchSubdcribedBookListFromRemote(userId).subscribe(
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

 

  goToUnsubscribeBook(id: number) {
    console.log("id: "+ id);
   // const dialogRef = this.authoraddbook.open(AuthoreditbookComponent,{ data: {myId:id}, disableClose: true });
    this.getBooks();
  }
  goToSubscribebook() {
    this._route.navigateByUrl("/booklist");
  }

  goToViewBook(id: number){
    //const dialogRef = this.authoraddbook.open(AuthorbookviewComponent,{ data: {myId:id}, disableClose: true });
 

  }

}
