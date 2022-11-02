import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { Responseparams } from '../responseparams';
import { Userdetails } from '../userdetails';

@Component({
  selector: 'app-authoreditbook',
  templateUrl: './authoreditbook.component.html',
  styleUrls: ['./authoreditbook.component.css']
})
export class AuthoreditbookComponent implements OnInit {
  userDetails=new Userdetails();
  responseparams=new Responseparams();
  bookid!: number;
  activevalue!:string;
  bookForm!:FormGroup;
  constructor(private formBuilder:FormBuilder,private _route:Router, private _service: NgserviceService, private _activatedRouter: ActivatedRoute,@Inject(MAT_DIALOG_DATA) public data: any,private dialogRef:MatDialogRef<AuthoreditbookComponent>) {
    this.bookForm=this.formBuilder.group({
      title:['',Validators.required],
      logo:['',Validators.required],
      category:['',Validators.required],
      publisheddate:['',Validators.required],
      price:['',Validators.required],
      publisher:['',Validators.required],
      active:['',Validators.required],
      author:['',Validators.required]
    
    })
   }

  ngOnInit(): void {
   // this.bookid = this._activatedRouter.snapshot.params['id'];
   let userId =localStorage.getItem('userDetails');
    console.log("in editbook"+this.data.myId);
   this._service.fetchBookDetailsByIdFromRemote(this.data.myId).subscribe(
    data=>{
      if(data.active==1){
        this.activevalue="1";
      }else{
        this.activevalue="0";
      }
      this.bookForm=this.formBuilder.group({
        title:[data.title,Validators.required],
        logo:[data.logo,Validators.required],
        category:[data.category,Validators.required],
        publisheddate:[data.publisheddate,Validators.required],
        price:[data.price,Validators.required],
        publisher:[data.publisher,Validators.required],
        active:[this.activevalue,Validators.required],
        author:[data.author,Validators.required],
        authorId:[userId]
      })
    },
      error => console.log("Exception Occured")
    )
  }
  editBook(){

    console.log(this.bookForm.value);
    this._service.updateBookToRemote(this.data.myId,this.bookForm.value).subscribe
(
data =>{
  this.responseparams=data;
  if(this.responseparams.errorcode=="200"){
  alert("Book updated successfully");
  }else{
    alert(this.responseparams.errormessage);
  }
  this.dialogRef.close();
  this._service.fetchBookListFromRemote();
},

error =>{
  alert("Error to modify this book");
  console.log("Error"+error);
  this._route.navigate(['/authordashboard']);
}
)
  }
  exit(){
    this._route.navigate(['/authordashboard']);
  }
}
