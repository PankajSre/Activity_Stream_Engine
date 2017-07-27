package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.Circle;
import com.stackroute.activitystream.model.UserCircle;

public interface UserCircleDAO {

	boolean addCircle(UserCircle circle);
	boolean addUserToCircle(String emailId,int circleId);
	boolean deleteUserFromCircle(String emailId,int circleId);
	boolean deleteCircle(UserCircle circle,String emailId);
	List<Circle> getAllCircles();
	List<Circle> getCircleByUser(String createdBy);
	List<String> getUserByCircle(int circleId);
	public Circle getCircleById(int id);
}
