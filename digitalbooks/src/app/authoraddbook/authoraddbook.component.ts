import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';

@Component({
  selector: 'app-authoraddbook',
  templateUrl: './authoraddbook.component.html',
  styleUrls: ['./authoraddbook.component.css']
})
export class AuthoraddbookComponent implements OnInit {
  logo!:string;
  bookForm!:FormGroup;
    constructor(private formBuilder:FormBuilder,private _route: Router,private _service: NgserviceService,private dialogRef:MatDialogRef<AuthoraddbookComponent>) { }
  
    ngOnInit(): void {
      let userId =localStorage.getItem('userDetails');
      this.bookForm=this.formBuilder.group({
        title:['',Validators.required],
        logo:['',Validators.required],
        category:['',Validators.required],
        publisheddate:['',Validators.required],
        price:['',Validators.required],
        publisher:['',Validators.required],
        active:['',Validators.required],
        author:['',Validators.required],
        authorId:[userId]
      
      })
  
    }
    addBook(){
      console.log(this.bookForm.value);
      this._service.addBookToRemote(this.bookForm.value).subscribe
(
  data =>{
    alert("Book added successfully");
    this.dialogRef.close();
    this._service.fetchBookListFromRemote();
  },
  error =>console.log("Error"+error)
)
    }
    exit(){
      console.log("its in page");
      this._route.navigateByUrl('/authordashboard');
    }
}
