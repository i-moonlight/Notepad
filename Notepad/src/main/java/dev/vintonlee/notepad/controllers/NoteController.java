package dev.vintonlee.notepad.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vintonlee.notepad.entities.Note;
import dev.vintonlee.notepad.services.NoteService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4209" })
public class NoteController {

	@Autowired
	private NoteService noteSvc;

	@GetMapping("notes")
	public List<Note> findAllNotesByUser(final HttpServletRequest req, final HttpServletResponse resp,
			final Principal principal) {

		final List<Note> notes = noteSvc.findAllNotesByUser(principal.getName());

		if (notes == null) {
			resp.setStatus(404);
		}
		if (notes != null && notes.isEmpty()) {
			resp.setStatus(204);
		}

		return notes;
	}

	@GetMapping("notes/{noteId}")
	public Note findAllNotesByUsernameandNoteId(@PathVariable("noteId") final Integer noteId,
			final HttpServletRequest req, final HttpServletResponse resp, final Principal principal) {

		final Note note = noteSvc.findNoteByUsernameAndId(principal.getName(), noteId);

		if (note == null) {
			resp.setStatus(404);
		}

		return note;
	}

	@GetMapping("notes/admin")
	public List<Note> findAll(final HttpServletRequest req, final HttpServletResponse resp, final Principal principal) {

		final List<Note> notes = noteSvc.findAllNotes(principal.getName());

		if (notes == null) {
			resp.setStatus(401);
		}
		if (notes != null && notes.isEmpty()) {
			resp.setStatus(204);
		}

		return notes;
	}

	@PostMapping("notes")
	public Note createNote(@RequestBody Note note, final HttpServletRequest req, final Principal principal,
			final HttpServletResponse resp) {

		try {
			note = noteSvc.createNote(note, principal.getName());
			if (note == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
			final StringBuffer url = req.getRequestURL();
			url.append("/").append(note.getId());
			resp.addHeader("Location", url.toString());
		} catch (final Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return note;
	}

	@PutMapping("notes")
	public Note updateNote(@RequestBody Note note, final HttpServletRequest req, final Principal principal,
			final HttpServletResponse resp) {

		try {
			note = noteSvc.updateNote(note, principal.getName());
			if (note == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
			final StringBuffer url = req.getRequestURL();
			url.append("/").append(note.getId());
			resp.addHeader("Location", url.toString());
		} catch (final Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return note;
	}

	@DeleteMapping("notes/{noteId}")
	public Note destroyNote(@PathVariable("noteId") final int noteId, final HttpServletRequest req,
			final Principal principal, final HttpServletResponse resp) {
		boolean note = false;

		try {
			note = noteSvc.destroyNote(noteId, principal.getName());
			if (note == false) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);

		} catch (final Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return null;
	}

}
