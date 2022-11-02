import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { NgserviceService } from '../ngservice.service';
import { Responseparams } from '../responseparams';
import { Userdetails } from '../userdetails';
@Component({
  selector: 'app-authorbookview',
  templateUrl: './authorbookview.component.html',
  styleUrls: ['./authorbookview.component.css']
})
export class AuthorbookviewComponent implements OnInit {
  userDetails=new Userdetails();
  responseparams=new Responseparams();
  bookid!: number;
  activevalue!:string;
  bookForm!:FormGroup;
  constructor(private formBuilder:FormBuilder,private _route:Router, private _service: NgserviceService, private _activatedRouter: ActivatedRoute,@Inject(MAT_DIALOG_DATA) public data: any) {
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
    this._service.fetchBookDetailsByIdFromRemote(this.data.myId).subscribe(
      data=>{
        if(data.active==1){
          this.activevalue="1";
        }else{
          this.activevalue="0";
        }
        console.log("data.title"+data.title);
        this.bookForm=this.formBuilder.group({
          title:[data.title,Validators.required],
          logo:[data.logo,Validators.required],
          category:[data.category,Validators.required],
          publisheddate:[data.publisheddate,Validators.required],
          price:[data.price,Validators.required],
          publisher:[data.publisher,Validators.required],
          active:[this.activevalue,Validators.required],
          author:[data.author,Validators.required]
        })
      },
        error => console.log("Exception Occured")
      )
    }
  }


