package dev.vintonlee.notepad.services;

import java.util.List;

import dev.vintonlee.notepad.entities.User;

public interface UserService {

	List<User> findAll(String name);

	User updateUser(User user, String name);

	User findUserByUsername(String username);

	User updateUserProfile(String name, User user);

	List<User> findAll();

}
