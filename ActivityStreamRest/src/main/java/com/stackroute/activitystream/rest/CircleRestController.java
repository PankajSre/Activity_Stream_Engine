package com.stackroute.activitystream.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.dao.CircleDAO;
import com.stackroute.activitystream.dao.UserCircleDAO;
import com.stackroute.activitystream.model.Circle;

@RestController
public class CircleRestController {

	@Autowired
	CircleDAO circleDAO;
	
	@Autowired
	UserCircleDAO userCircleDAO;
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<Circle> createUser(@RequestBody Circle circle) {

		if (circleDAO.addCircle(circle) == true) {
			circle.setErrorCode("200");
			circle.setErrorMessage("you have Successfully created the Circle ");
		} else {
			circle.setErrorCode("404");
			circle.setErrorMessage("Your Circle has not been created");
		}
		return new ResponseEntity<Circle>(circle, HttpStatus.OK);
	}
}
