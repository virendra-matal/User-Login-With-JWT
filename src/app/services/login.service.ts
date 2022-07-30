import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url='http://localhost:8585'

  constructor(private http:HttpClient) { }




  dologin(auth:any){
    return this.http.post(`${this.url}/token`,auth);
  }



  generateToken(token:any){
    localStorage.setItem('token',token);

    return true;
  }

  IsloggedIn(){
    let userToken=localStorage.getItem('token');
    if (userToken==undefined|| userToken==="" || userToken==null) {
      return false
    } else {
      return true
    }
  }

  logout(){
    localStorage.removeItem('token');
    return true
  }

  getToken(){
    return localStorage.getItem('token');
  }

}
