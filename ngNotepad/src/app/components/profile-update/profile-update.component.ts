import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile-update',
  templateUrl: './profile-update.component.html',
  styleUrls: ['./profile-update.component.css']
})
export class ProfileUpdateComponent implements OnInit {
  updateFailed = false;
  user: User = new User();
  updateUser: User = new User();

  constructor(private authSvc: AuthService, private router: Router, private userSvc: UserService) { }

  ngOnInit() {
    if (!this.authSvc.getCredentials()) {
      this.router.navigateByUrl('/home');
    }
    this.loadUser();
  }

  loadUser() {
    this.authSvc.getUserByUsername(this.authSvc.getLoggedInUsername()).subscribe(
      success => {
        this.user = Object.assign(this.user, success);

      },
      failure => { }
    );
  }


  editUserInfo(updateForm: NgForm) {
    this.updateUser.id = this.user.id;
    this.updateUser.username = this.user.username;
    this.updateUser.firstName = updateForm.value.firstName;
    this.updateUser.lastName = updateForm.value.lastName;
    this.updateUser.email = updateForm.value.email;

    this.userSvc.updateUserAsUser(this.updateUser).subscribe(
      good => {
        this.router.navigateByUrl('/users');
      },
      error => {
        this.updateFailed = true;
      }
    );
  }
}
