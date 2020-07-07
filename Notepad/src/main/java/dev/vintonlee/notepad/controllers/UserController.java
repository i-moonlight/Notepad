package dev.vintonlee.notepad.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vintonlee.notepad.entities.User;
import dev.vintonlee.notepad.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4209" })
public class UserController {

	@Autowired
	private UserService userSvc;

	@GetMapping("users")
	public List<User> findAll(final HttpServletRequest req, final HttpServletResponse resp, final Principal principal) {

		final List<User> users = userSvc.findAll(principal.getName());

		if (users == null) {
			resp.setStatus(403);
		}

		if (users != null && users.isEmpty()) {
			resp.setStatus(204);
		}

		return users;
	}

	@GetMapping("users/{username}")
	public User getExistingUserByUsername(@PathVariable final String username, final HttpServletRequest req,
			final Principal principal, final HttpServletResponse resp) {
		User user = null;

		try {
			user = userSvc.findUserByUsername(username);
			if (user == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
		} catch (final Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return user;
	}

	@PutMapping("users")
	public User userUpdateExistingUser(@RequestBody User user, final HttpServletRequest req, final Principal principal,
			final HttpServletResponse resp) {

		try {
			user = userSvc.updateUserProfile(principal.getName(), user);
			if (user == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
			final StringBuffer url = req.getRequestURL();
			url.append("/").append(user.getId());
			resp.addHeader("Location", url.toString());
		} catch (final Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return user;
	}

	@PutMapping("users/admin")
	public User adminUpdateExistingUser(@RequestBody User user, final HttpServletRequest req, final Principal principal,
			final HttpServletResponse resp) {

		try {
			user = userSvc.adminUpdateUserProfile(principal.getName(), user);
			if (user == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
			final StringBuffer url = req.getRequestURL();
			url.append("/").append(user.getId());
			resp.addHeader("Location", url.toString());
		} catch (final Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return user;
	}
}
