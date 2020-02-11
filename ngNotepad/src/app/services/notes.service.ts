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

  public selectedNote: Note;

  constructor(private authService: AuthService, private router: Router, private http: HttpClient) { }

  // Create

  createNote(newNote: Note) {

    const httpOptions = this.setHttpOptions();

    return this.http.post<Note>(this.url, newNote, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('NoteService.createNote(): Error');
      })
    );
  }

  // Read

  indexUserNotes(username: string) {
    this.checkUserInLocalStorage();

    const httpOptions = this.setHttpOptions();

    return this.http.get<Note[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error not service - indexUserNotes()');
      })
    );
  }

  // Update

  update(updatedNote: Note) {
    this.checkUserInLocalStorage();

    const httpOptions = this.setHttpOptions();

    return this.http.put<Note>(this.url, updatedNote, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('NoteService.update(): Error');
      })
    );
  }

  // Delete

  destroyNote(id: number) {
    this.checkUserInLocalStorage();

    const httpOptions = this.setHttpOptions();

    return this.http.delete<Note>(this.url + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error deleting note in NoteService');
      })
    );
  }

  private checkUserInLocalStorage() {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    }
  }

  private setHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
  }
}
