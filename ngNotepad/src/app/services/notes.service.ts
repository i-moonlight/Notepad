import { AuthService } from 'src/app/services/auth.service';
import { Note } from './../models/note';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NotesService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/notes/';

  constructor(private authService: AuthService, private router: Router, private http: HttpClient) { }

  // Create

  createNote(newNote: Note) {
    console.log('in create  - Note Service' + newNote.title);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.post<Note>(this.url, newNote, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('NoteService.createNote(): Error');
      })
    );
  }

  // Read

  indexUserNotes(username) {
    console.log('in get note by username note service');
    console.log(username);

    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Note[]>(this.url + '/users/' + username, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error not service - indexUserNotes()');
      })
    );
  }

  // Update

  update(updatedNote: Note) {
    console.log('in update service ' + updatedNote.title + ' ' + updatedNote.id);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.put<Note>(this.url, updatedNote, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('NoteService.update(): Error');
      })
    );
  }

  // Delete

  destroyNote(id: number) {
    console.log('id for delete');
    console.log(id);

    if (!this.authService.checkLogin()) {
      return null;
    }

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.delete<Note>(this.url + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error deleting note in NoteService');
      })
    );
  }

}
