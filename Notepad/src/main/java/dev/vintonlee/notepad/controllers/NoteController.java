package dev.vintonlee.notepad.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vintonlee.notepad.entities.Note;
import dev.vintonlee.notepad.services.NoteService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class NoteController {

	@Autowired
	private NoteService noteSvc;

	@GetMapping("notes")
	public List<Note> findAll(HttpServletRequest req, HttpServletResponse resp) {

//		add princapal and user name 
		List<Note> notes = noteSvc.findAllNotes("bob");

		if (notes == null) {
			resp.setStatus(404);
		}
		if (notes.size() == 0) {
			resp.setStatus(204);
		}

		return notes;
	}

}
