package dev.vintonlee.notepad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.vintonlee.notepad.entities.User;
import dev.vintonlee.notepad.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(final User user) {

		if (isUserUnique(user)) {
			final String encodedPW = encoder.encode(user.getPassword());
			user.setPassword(encodedPW); // only persist encoded password

			user.setEnabled(true);
			user.setRole("standard");

			userRepo.saveAndFlush(user);
			return user;
		}

		return null;
	}

	private boolean isUserUnique(final User user) {

		final User uniqueUsername = userRepo.findUserByUsername(user.getUsername());

		if (uniqueUsername == null) {
			return false;
		}
		final User uniqueEmail = userRepo.findUserByEmail(user.getEmail());

		if (uniqueEmail == null) {
			return false;
		}

		return true;
	}

}