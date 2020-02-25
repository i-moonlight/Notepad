import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authSvc: AuthService) { }

  ngOnInit() {
    console.log('Welcome! This was built by Vinton Lee, III');
    console.log('Want to build something great?!');
    console.log('Email me at vinton.e.lee@gmail.com');
    console.log('vintonlee.dev');
  }

  userLogInCheck() {
    return this.authSvc.getCredentials();
  }

}
