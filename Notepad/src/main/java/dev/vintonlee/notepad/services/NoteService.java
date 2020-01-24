package dev.vintonlee.notepad.services;

import java.util.List;

import dev.vintonlee.notepad.entities.Note;

public interface NoteService {

	List<Note> findAllNotes(String name);

	Note findNoteByUsernameAndId(String username, int noteId);

	Note createNote(Note note, String name);

	Note updateNote(Note note, String name);

	Note destroyNote(int noteId, String name);

}