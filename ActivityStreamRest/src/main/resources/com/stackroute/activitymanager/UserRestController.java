package com.stackroute.activitymanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.dao.UserDAO;
import com.stackroute.activitystream.model.User;

@RestController
public class UserRestController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {

		if (userDAO.saveUser(user) == true) {
			user.setErrorCode("200");
			user.setErrorMessage("you are Successfully registered with " + user.getUsername());
		} else {
			user.setErrorCode("400");
			user.setErrorMessage("Youa are not registered Successfully");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
