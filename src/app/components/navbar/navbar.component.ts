import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  islogged=false;

  constructor(private login:LoginService) { }

  ngOnInit(): void {
    this.islogged=this.login.IsloggedIn();
  }

  loggedOut(){
    this.login.logout();
    location.reload();
  }

}
