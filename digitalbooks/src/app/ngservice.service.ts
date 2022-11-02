import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Bookdetails } from './bookdetails';
import { Subscribebook } from './subscribebook';

class Userdetails{}
@Injectable({
  providedIn: 'root'
})
export class NgserviceService {


  constructor(private _http: HttpClient) { }

  fetchUserDetailsFromRemote(userName: String): Observable<any>{
    return this._http.post<any>('http://localhost:9999/api/v1/digitalbooks/signin/'+userName,null);
  }
  addUserDetailsToRemote(userDetails: Userdetails): Observable<any>{
    return this._http.post<any>('http://localhost:9999/api/v1/digitalbooks/signup',userDetails);
  }
  fetchBookListFromRemote(): Observable<any>{
    return this._http.get<any>('http://localhost:2222/api/v1/digitalbooks/search?category&title&author&price&publisher');
  }
  addBookToRemote(booksDetails :Bookdetails): Observable<any>{
    return this._http.post<any>('http://localhost:2222/api/v1/digitalbooks/author/books',booksDetails);
  }
  updateBookToRemote(bookid:number,booksDetails :Bookdetails): Observable<any>{
    return this._http.post<any>('http://localhost:2222/api/v1/digitalbooks/author/books/'+bookid,booksDetails);
  }
  fetchBookDetailsByIdFromRemote(bookid:number):Observable<any>{
    return this._http.get<any>('http://localhost:2222/api/v1/digitalbooks/books/details/'+bookid);
  }
  fetchSubdcribedBookListFromRemote(userid:string):Observable<any>{
    return this._http.get<any>('http://localhost:2222/api/v1/digitalbooks/readers/books/'+userid);
  }
  addSubdcribedBookToRemote(subcribedetails:Subscribebook):Observable<any>{
    return this._http.post<any>('http://localhost:2222/api/v1/digitalbooks/subscribe',subcribedetails);
  }
}

