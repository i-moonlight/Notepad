import { NotesService } from './../../services/notes.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  user: User;
  countUsers = 0;
  users: User[] = [];

  constructor(private authSvc: AuthService, private router: Router, private noteSvc: NotesService, private userSvc: UserService) { }

  ngOnInit() {
    this.authSvc
      .getUserByUsername(this.authSvc.getLoggedInUsername())
      .subscribe(
        good => {
          this.user = good;
          if (this.user.role !== 'admin') {
            this.router.navigateByUrl('/notes');
          }
        },
        error => {
          this.router.navigateByUrl('/login');
        }
      );
    this.indexUsers();
  }

  indexUsers() {
    this.users = [];
    this.countUsers = 0;

    this.userSvc.indexAdmin().subscribe(
      success => {
        success.forEach(userSuccess => {
          this.users.push(userSuccess);
          this.countUsers++;
        });
      },
      failure => { }
    );
  }

  disableUser(userIn: User) {
    const userToDisable: User = userIn;

    userToDisable.enabled = false;

    this.userSvc.updateUserAsAdmin(userToDisable).subscribe(
      success => {
        this.indexUsers();
      },
      fail => { }
    );
  }

  enableUser(userIn: User) {
    const userToEnable: User = userIn;

    userToEnable.enabled = true;

    this.userSvc.updateUserAsAdmin(userToEnable).subscribe(
      success => {
        this.indexUsers();
      },
      fail => { }
    );
  }

}
