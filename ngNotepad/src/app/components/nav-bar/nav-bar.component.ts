import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  public isCollapsed = true;
  title = 'NOTEPAD';

  constructor(private authSvc: AuthService, private router: Router) { }

  ngOnInit() {
  }

  userLogInCheck() {
    return this.authSvc.getCredentials();
  }

  logout() {
    this.authSvc.logout();
    this.router.navigateByUrl('/home');
  }

}
