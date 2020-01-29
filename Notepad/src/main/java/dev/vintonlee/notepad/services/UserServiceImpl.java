package dev.vintonlee.notepad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.vintonlee.notepad.entities.User;
import dev.vintonlee.notepad.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> findAll(String name) {

		User loggedInUser = userRepo.findUserByUsername(name);

		if (loggedInUser != null && loggedInUser.getRole().equalsIgnoreCase("admin")) {
			return userRepo.findAll();
		}

		return null;
	}

	@Override
	public User updateUser(User user, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserProfile(String name, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
