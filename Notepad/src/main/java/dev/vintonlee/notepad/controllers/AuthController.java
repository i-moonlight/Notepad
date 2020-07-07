package dev.vintonlee.notepad.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.vintonlee.notepad.entities.User;
import dev.vintonlee.notepad.services.AuthService;

@RestController
@CrossOrigin({ "*", "http://localhost:4209" })
public class AuthController {

	@Autowired
	private AuthService authSrv;

	@PostMapping(path = "/register")
	public User register(@RequestBody User user, final HttpServletResponse res) {

		if (user == null) {
			res.setStatus(400);
		}

		user = authSrv.register(user);

		if (user == null) {
			res.setStatus(406); // User is not a unique user
		}

		return user;
	}

	@GetMapping(path = "/authenticate")
	public Principal authenticate(final Principal principal) {
		return principal;
	}

}
