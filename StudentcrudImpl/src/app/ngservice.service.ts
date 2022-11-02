import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

class Student{}
class User{}
@Injectable({
  providedIn: 'root'
})
export class NgserviceService {

  constructor(private _http: HttpClient) { }



  fetchStudentListFromRemote(): Observable<any>{
    return this._http.get<any>('http://localhost:2222/student');
  }
  addStudentToRemote(student: Student): Observable<any>{
    return this._http.post<any>('http://localhost:2222/student',student);
  }
  updateStudentToRemote(id: number,student: Student): Observable<any>{
    return this._http.put<any>('http://localhost:2222/student/'+id,student);
  }
  deleteStudentByIdFromRemote(id: number): Observable<any> {
    return this._http.delete<any>('http://localhost:2222/student/' + id);
  }
  fetchStudentByIdFromRemote(id: number): Observable<any> {
    return this._http.get<any>('http://localhost:2222/student/' + id);
  }

  loginUser(user:User):Observable<object>{
    console.log(user);
    return this._http.post('http://localhost:2222/User/login',user);
  }
  saveUser(user:User):Observable<object>{
    console.log(user);
    return this._http.post('http://localhost:2222/User/save',user);
  }
}
