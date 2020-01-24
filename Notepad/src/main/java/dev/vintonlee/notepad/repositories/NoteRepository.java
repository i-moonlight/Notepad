package dev.vintonlee.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.vintonlee.notepad.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

}