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
	public User register(User user) {

		if (isUserUnique(user)) {
			String encodedPW = encoder.encode(user.getPassword());
			user.setPassword(encodedPW); // only persist encoded password

			user.setEnabled(true);
			user.setRole("standard");

			userRepo.saveAndFlush(user);
			return user;
		}

		return null;
	}

	private boolean isUserUnique(User user) {

		User uniqueUsername = userRepo.findUserByUsername(user.getUsername());

		if (uniqueUsername != null && uniqueUsername.getUsername().equalsIgnoreCase(user.getUsername())) {
			return false;
		}
		User uniqueEmail = userRepo.findUserByEmail(user.getEmail());

		if (uniqueEmail != null && uniqueEmail.getEmail().equalsIgnoreCase(user.getEmail())) {
			return false;
		}

		return true;
	}

}