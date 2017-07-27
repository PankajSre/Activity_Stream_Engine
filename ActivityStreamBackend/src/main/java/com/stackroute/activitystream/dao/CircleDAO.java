package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.Circle;


public interface CircleDAO {

	boolean addCircle(Circle circle);
	boolean addUserToCircle(String emailId,int circleId);
	boolean deleteUserFromCircle(String emailId,int circleId);
	boolean deleteCircle(Circle circle,String emailId);
	List<Circle> getAllCircles();
	List<Circle> getCircleByUser(String createdBy);
	List<String> getUserByCircle(int circleId);
	public Circle getCircleById(int id);
}
