import { NotesService } from './../../services/notes.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Note } from 'src/app/models/note';

@Component({
  selector: 'app-notepad',
  templateUrl: './notepad.component.html',
  styleUrls: ['./notepad.component.css']
})
export class NotepadComponent implements OnInit {
  user: User;
  newNote: Note;
  editedNote: Note;
  selectedNote: Note;

  constructor(private authSvc: AuthService, private router: Router, private noteSvc: NotesService) { }

  ngOnInit() {
    this.authSvc.getUserByUsername(this.authSvc.getLoggedInUsername()).subscribe(
      good => {
        this.user = good;
      },
      error => {
        this.router.navigateByUrl('/login');
      }
    );

    if (this.noteSvc.selectedNote != null) {
      this.selectedNote = this.noteSvc.selectedNote;

    }
  }

  createNewNote(newNoteForm: NgForm) {
    this.newNote = newNoteForm.value;

    this.noteSvc.createNote(this.newNote).subscribe(
      success => {
        this.router.navigateByUrl('/notes');
      },
      failure => {

      }
    );
  }

  updateNote(updateNoteForm: NgForm) {
    this.newNote = updateNoteForm.value;

    this.noteSvc.update(this.newNote).subscribe(
      success => {
        this.noteSvc.selectedNote = null;
        this.router.navigateByUrl('/notes');
      },
      failure => {

      }
    );

  }


}
