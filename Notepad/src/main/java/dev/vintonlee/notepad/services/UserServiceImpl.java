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
	public List<User> findAll(String username) {

		User loggedInUser = userRepo.findUserByUsername(username);

		if (loggedInUser != null && loggedInUser.getRole().equalsIgnoreCase("admin")) {
			return userRepo.findAll();
		}

		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepo.findUserByUsername(username);
	}

	@Override
	public User updateUserProfile(String username, User user) {

		User loggedInUser = findUserByUsername(username);

		if (loggedInUser != null) {
			if (isEmailUniqueOrCurrent(user, username)) {

				if (user.getEmail() != null) {
					loggedInUser.setEmail(user.getEmail());
				}

				if (user.getFirstName() != null) {
					loggedInUser.setFirstName(user.getFirstName());
				}

				if (user.getLastName() != null) {
					loggedInUser.setLastName(user.getLastName());
				}

				if (user.getRole() != null) {
					loggedInUser.setRole(user.getRole());
				}

				userRepo.saveAndFlush(loggedInUser);

				return loggedInUser;
			}
		}

		return null;
	}

	private boolean isEmailUniqueOrCurrent(User user, String username) {

		User testEmail = userRepo.findUserByEmail(user.getEmail());

		User loggedInUser = userRepo.findUserByUsername(username);

		if (testEmail == null) {
			return true;
		}

		if (testEmail.equals(loggedInUser)) {
			if (testEmail.getEmail().equals(user.getEmail())) {
				return true;
			}
		}

		return false;
	}

}
