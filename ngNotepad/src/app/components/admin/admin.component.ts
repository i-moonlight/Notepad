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
  countNotes = 0;
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

}
