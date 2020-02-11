package dev.vintonlee.notepad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.vintonlee.notepad.entities.Note;
import dev.vintonlee.notepad.entities.User;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	List<Note> findAllByUserOrderByUpdatedAtAsc(User loggedInUser);

}