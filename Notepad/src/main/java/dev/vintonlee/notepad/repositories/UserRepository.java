package dev.vintonlee.notepad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.vintonlee.notepad.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByUsername(String username);
}