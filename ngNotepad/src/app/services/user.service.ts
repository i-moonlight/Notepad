import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = environment.baseUrl;
  private url = environment.baseUrl + 'api/users';

  constructor(
    private http: HttpClient,
    private router: Router,
    private authService: AuthService
  ) { }

  // LIST of USERS for ADMIN **********

  public index() {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ` + this.authService.getCredentials(), // Space after `Basic ` + is key due to concatenation.
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<User[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        // console.log(err);
        return throwError('user.service.ts Error: Index Method');
      })
    );
  }

  // USER BY ID **********

  public findById(id: number) {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    return this.http.get<User>(this.url + '/' + id, httpOptions).pipe(
      catchError((err: any) => {
        // console.log(err);
        return throwError('user.service.ts Error: FindById Method');
      })
    );
  }

  // CREATE USER **********

  public create(user: User) {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ` + this.authService.getCredentials(),
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http
      .post(environment.baseUrl + '/register', user, httpOptions)
      .pipe(
        catchError((err: any) => {
          // console.log(err);
          return throwError('user.service.ts Error: Create Method');
        })
      );
  }

  // UPDATE USER **********

  public updateUserAsAdmin(user: User) {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ` + this.authService.getCredentials(),
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.put(this.url + '/admin', user, httpOptions).pipe(
      catchError((err: any) => {
        // console.log(err);
        // console.log('update method User Service');
        return throwError('user.service.ts Error: Update Method');
      })
    );
  }

  public updateUserAsUser(user) {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ` + this.authService.getCredentials(),
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.put(this.url, user, httpOptions).pipe(
      catchError((err: any) => {
        // console.log(err);
        // console.log('update method User Service');
        return throwError('user.service.ts Error: Update Method');
      })
    );
  }

}
