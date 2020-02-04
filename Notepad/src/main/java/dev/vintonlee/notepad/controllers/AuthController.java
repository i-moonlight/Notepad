package dev.vintonlee.notepad.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.vintonlee.notepad.entities.User;
import dev.vintonlee.notepad.services.AuthService;

@RestController
@CrossOrigin({ "*", "http://localhost:4209" })
public class AuthController {

	@Autowired
	private AuthService authSrv;

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public User register(@RequestBody User user, HttpServletResponse res) {

		if (user == null) {
			res.setStatus(400);
		}

		user = authSrv.register(user);

		if (user == null) {
			res.setStatus(406); // User is not a unique user
		}

		return user;
	}

	@RequestMapping(path = "/authenticate", method = RequestMethod.GET)
	public Principal authenticate(Principal principal) {
		return principal;
	}

}
