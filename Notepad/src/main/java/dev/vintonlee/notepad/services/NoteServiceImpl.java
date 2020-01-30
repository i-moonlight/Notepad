package dev.vintonlee.notepad.services;

import java.util.List;

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
	public Note findNoteByUsernameAndId(String username, int noteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note createNote(Note note, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note updateNote(Note note, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note destroyNote(int noteId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
