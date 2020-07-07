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
	public List<User> findAll(final String username) {

		final User loggedInUser = userRepo.findUserByUsername(username);

		if (loggedInUser != null && loggedInUser.getRole().equalsIgnoreCase("admin")) {
			return userRepo.findAll();
		}

		return null;
	}

	@Override
	public User findUserByUsername(final String username) {
		return userRepo.findUserByUsername(username);
	}

	@Override
	public User updateUserProfile(final String username, final User user) {

		final User loggedInUser = findUserByUsername(username);

		if (loggedInUser != null && isEmailUniqueOrCurrent(user, username)) {
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
			if (user.getEnabled() != null) {
				loggedInUser.setEnabled(user.getEnabled());
			}

			userRepo.saveAndFlush(loggedInUser);

			return loggedInUser;
		}

		return null;
	}

	@Override
	public User adminUpdateUserProfile(final String username, final User user) {

		final User admin = findUserByUsername(username);

		if (admin.getRole().equalsIgnoreCase("admin")) {
			userRepo.saveAndFlush(user);
			return user;
		}

		return null;

	}

	private boolean isEmailUniqueOrCurrent(final User user, final String username) {

		final User testEmail = userRepo.findUserByEmail(user.getEmail());

		final User loggedInUser = userRepo.findUserByUsername(username);

		if (testEmail == null) {
			return true;
		}

		return testEmail.equals(loggedInUser) && testEmail.getEmail().equals(user.getEmail());
	}

}
