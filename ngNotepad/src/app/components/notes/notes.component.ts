import { NotesService } from './../../services/notes.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
  user: User;
  notes = [];

  constructor(private authSvc: AuthService, private router: Router, private notesSvc: NotesService) { }

  ngOnInit() {
    this.authSvc.getUserByUsername(this.authSvc.getLoggedInUsername()).subscribe(
      good => {
        this.user = good;
      },
      error => {
        this.router.navigateByUrl('/login');
      }
    );
    this.loadNotes();
  }

  loadNotes() {
    this.notes = [];

    this.authSvc.getUserByUsername(this.authSvc.getLoggedInUsername()).subscribe(
      good => {
        this.user = good;
        this.notesSvc.indexUserNotes(this.user.username).subscribe(
          goodNotes => {
            goodNotes.forEach(note => {
              this.notes.unshift(note);
            }
            );
          },
          error => {
            this.notes = [];
          }
        );
      },
      error => {
        this.router.navigateByUrl('/login');
      }
    );
  }

  createNote() {
    this.router.navigateByUrl('/notepad');
  }

}
