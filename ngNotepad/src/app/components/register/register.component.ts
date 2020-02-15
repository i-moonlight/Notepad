import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AuthService } from './../../services/auth.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  newUser: User;

  constructor(private authSvc: AuthService, private router: Router) { }

  ngOnInit() {
  }

  register(registerForm: NgForm) {
    this.newUser = registerForm.value;

    this.authSvc.register(this.newUser).subscribe(
      success => {
        this.authSvc.login(this.newUser.username, this.newUser.password).subscribe(
          login => {
            this.router.navigateByUrl('/notes');
          },
          failed => { this.router.navigateByUrl('/login'); }
        );
      },
      failure => {
        this.router.navigateByUrl('/register');
      }
    );

  }
}
