import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  accessDenied = false;

  constructor(private authSvc: AuthService, private router: Router) { }

  ngOnInit() {
  }

  login(loginForm: NgForm) {
    const user: User = new User();
    user.username = loginForm.value.username;
    user.password = loginForm.value.password;

    this.authSvc.login(user.username, user.password).subscribe(
      next => {
        this.router.navigate(['/notes']);
      },
      error => {
        this.accessDenied = true;
        this.router.navigateByUrl('/login');
      }
    );
  }

}
