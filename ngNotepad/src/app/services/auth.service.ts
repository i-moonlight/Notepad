import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;

  userLoggedIn = false;

  constructor(private http: HttpClient) { }

  login(username, password) {
    this.userLoggedIn = true;

    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
  }

  generateBasicAuthCredentials(username, password) {
    return btoa(`${username}:${password}`);
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }

  register(user) {
    return this.http.post(this.baseUrl + 'register', user).pipe(
      catchError((err: any) => {
        // console.log(err);
        return throwError('AuthService.register(): error registering user.');
      })
    );
  }

  logout() {
    this.userLoggedIn = false;
    localStorage.removeItem('credentials');
    localStorage.removeItem('username');
  }

  getLoggedInUsername() {
    return localStorage.getItem('username');
  }

  getUserByUsername(username: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Headers': 'Content-Type',
        // 'Content-Type': 'application/json',
        Authorization: `Basic ` + this.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    return this.http.get<User>(this.baseUrl + 'api/users/' + username, httpOptions).pipe(
      catchError((err: any) => {
        // console.log(err);
        return throwError('AuthService.register(): error registering user.');
      })
    );
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

}
