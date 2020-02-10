package dev.vintonlee.notepad.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.vintonlee.notepad.entities.Note;
import dev.vintonlee.notepad.entities.User;
import dev.vintonlee.notepad.repositories.NoteRepository;
import dev.vintonlee.notepad.repositories.UserRepository;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private NoteRepository noteRepo;

	@Override
	public List<Note> findAllNotes(String username) {

		User loggedInUser = userRepo.findUserByUsername(username);

		if (loggedInUser != null && loggedInUser.getRole().equalsIgnoreCase("admin")) {
			return noteRepo.findAll();
		}

		return null;
	}

	@Override
	public List<Note> findAllNotesByUser(String username) {

		User loggedInUser = userRepo.findUserByUsername(username);

		if (loggedInUser != null) {
			return noteRepo.findAllByUserOrderByUpdatedAt(loggedInUser);
		}

		return null;
	}

	@Override
	public Note findNoteByUsernameAndId(String username, int noteId) {
		User loggedInUser = userRepo.findUserByUsername(username);
		Note note = new Note();

		if (loggedInUser != null) {
			Optional<Note> noteOpt = noteRepo.findById(noteId);
			if (noteOpt.isPresent()) {
				note = noteOpt.get();
				if (note.getUser().getId() == loggedInUser.getId()) {
					return note;
				} else if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
					return note;
				}
			}
		}

		return note;
	}

	@Override
	public Note createNote(Note note, String username) {

		if (note.getTitle().length() <= 0 || note.getText().length() <= 0) {
			return null;
		}

		User loggedInUser = userRepo.findUserByUsername(username);

		if (loggedInUser != null) {
			note.setUser(loggedInUser);
			return noteRepo.saveAndFlush(note);
		}

		return null;
	}

	@Override
	public Note updateNote(Note note, String username) {
		User loggedInUser = userRepo.findUserByUsername(username);
		Note managedNote = new Note();

		if (loggedInUser != null) {
			Optional<Note> noteOpt = noteRepo.findById(note.getId());
			if (noteOpt.isPresent()) {
				managedNote = noteOpt.get();
			}
			if (managedNote != null) {
				if (loggedInUser.getId() == managedNote.getUser().getId()) {

					if (note.getTitle() != null) {
						managedNote.setTitle(note.getTitle());
					}

					if (note.getText() != null) {
						managedNote.setText(note.getText());
					}

					if (note.getStarred() != null) {
						managedNote.setStarred(note.getStarred());
					}
					noteRepo.saveAndFlush(managedNote);
					return managedNote;
				}
			}
		}

		return null;
	}

	@Override
	public boolean destroyNote(int noteId, String username) {
		User loggedInUser = userRepo.findUserByUsername(username);

		Note managedNote = new Note();
		if (loggedInUser != null) {
			managedNote = noteRepo.getOne(noteId);
			if (managedNote != null) {
				if (loggedInUser.getId() == managedNote.getUser().getId()
						|| loggedInUser.getRole().equalsIgnoreCase("admin")) {
					noteRepo.delete(managedNote);
					return true;
				}
			}
		}

		return false;
	}

}
