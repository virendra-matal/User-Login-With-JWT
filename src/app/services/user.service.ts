import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseurl='http://localhost:8585';
  constructor(private http:HttpClient) { }

  getsecondservice(){
    return this.http.get(`${this.baseurl}/second`,{responseType:'text'});
  }
  getwelcomeservice(){
    return this.http.get(`${this.baseurl}/welcome`,{responseType:'text'});
  }

}
