package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.Message;

public interface MessageDAO {

	boolean sendMessage(Message message);
	boolean deleteMessage(int messageId, String emailId, int circleId);
	List<Message> getMyMessages(String emailId);//create a private method in daoimpl class getmycircleids(email id)
	
}
