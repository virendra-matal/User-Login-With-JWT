import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  dataBoolean=false;
  data=''
  constructor(private user:UserService) { }

  ngOnInit(): void {
  }
  getService(){
    this.user.getsecondservice().subscribe(
      response=>{
        console.log(response);
        this.dataBoolean=true
        this.data=response;
      },
      error=>{
        console.log(error);
        
      }
    )
  }

  getwelcomeService(){
    this.user.getwelcomeservice().subscribe(
      response=>{
        console.log(response);
        this.dataBoolean=true
        this.data=response;
      },
      error=>{
        console.log(error);
        
      }
    )
  }

}
