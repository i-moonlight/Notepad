import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  user: User;

  constructor(private authSvc: AuthService, private router: Router) { }

  ngOnInit() {
    this.authSvc
      .getUserByUsername(this.authSvc.getLoggedInUsername())
      .subscribe(
        good => {
          this.user = good;
          console.log(this.user);
          if (this.user.role !== 'admin') {
            this.router.navigateByUrl('/notes');
          }
        },
        error => {
          this.router.navigateByUrl('/login');
        }
      );
  }

}
