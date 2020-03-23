package dev.vintonlee.notepad.services;

import java.util.List;

import dev.vintonlee.notepad.entities.User;

public interface UserService {

	List<User> findAll(String name);

	User findUserByUsername(String username);

	User updateUserProfile(String name, User user);

	User adminUpdateUserProfile(String username, User user);

}
