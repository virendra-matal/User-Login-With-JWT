import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  Error=''
  Errorboolean=false;

  auth={
    username:'',
    password:''
  }

  hide=true;
  constructor(private login:LoginService) { }

  ngOnInit(): void {
  }

  sendData(){
    console.log('in the function');

    if ((this.auth.username!='' && this.auth.username!=null ) &&  (this.auth.password!=''&& this.auth.password!=null)) {
    
      this.login.dologin(this.auth).subscribe(
        (response:any)=>{
          console.log(response.token);
          this.login.generateToken(response.token);
          location.href='/dashboard'
        },
        error=>{
          console.log(error);
          this.Errorboolean=true
          this.Error=error.error
          
        }
      )

    }else{
      console.log("values are Empty");
      
    }
    
  }
}
