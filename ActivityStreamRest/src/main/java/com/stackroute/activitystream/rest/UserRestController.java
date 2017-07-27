package com.stackroute.activitystream.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.dao.UserDAO;
import com.stackroute.activitystream.model.User;

@RestController
public class UserRestController {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private User user;

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {

		if (userDAO.saveUser(user) == true) {
			user.setErrorCode("200");
			user.setErrorMessage("you are Successfully registered with " + user.getUsername());
		} else {
			user.setErrorCode("404");
			user.setErrorMessage("Youa are not registered Successfully");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public ResponseEntity<User> loginUser(@RequestBody User user, HttpSession session) {
		user = userDAO.validateUser(user.getUsername(), user.getPassword());

		if (user == null) {
			user = new User();
			user.setErrorCode("403");
			user.setErrorMessage("Invalid Credentials.....  Please try Again");
		} else

		{
			user.setErrorCode("200");
			user.setErrorMessage("You have successfully logged in.");
			session.setAttribute("loggedInUser", user.getUsername());
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<User> logout(HttpSession session) {
		String username=(String)session.getAttribute("loggedInUser");
		if(username !=null)
		{
		session.invalidate();
		session.setMaxInactiveInterval(0);
		user.setErrorCode("200");
		user.setErrorMessage("You have successfully logged out");
		}
		else
		{
			user.setErrorCode("405");
			user.setErrorMessage("You are not loggedIn ...Please Login");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userById/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("emailId") String emailId) {
		User user = userDAO.getUserByEmailId(emailId);
		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@RequestMapping(value = "/deleteUserById/{username}", method = RequestMethod.POST)
	public ResponseEntity<User> deleteUserById(@PathVariable("emailId") String emailId) {
		User user = userDAO.getUserByEmailId(emailId);
		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist with this username :"+emailId);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		userDAO.deleteUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUserById(@RequestBody User user) {
		
		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		userDAO.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.PUT)
	public ResponseEntity<List<User>> getAllUsers() {
		
		List<User> users = userDAO.getAllUsers();
		if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("There is No user to show....");
			users.add(user);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}
