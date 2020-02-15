import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User = new User();

  constructor(private authSvc: AuthService, private router: Router) { }

  ngOnInit() {
    if (!this.authSvc.getCredentials()) {
      this.router.navigateByUrl('/home');
    }

    this.loadUser();
  }

  loadUser() {
    this.authSvc.getUserByUsername(this.authSvc.getLoggedInUsername()).subscribe(
      success => {
        this.user = success;
        console.log(this.user.username);

      },
      failure => { }
    );
  }

  editUserInfo() {

  }
}
