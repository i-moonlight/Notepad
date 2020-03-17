package dev.vintonlee.notepad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.vintonlee.notepad.entities.Image;
import dev.vintonlee.notepad.entities.User;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	List<Image> findAllByUser(User loggedInUser);

}