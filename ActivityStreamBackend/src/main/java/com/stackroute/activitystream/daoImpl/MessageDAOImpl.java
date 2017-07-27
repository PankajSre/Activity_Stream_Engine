package com.stackroute.activitystream.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.dao.MessageDAO;
import com.stackroute.activitystream.model.Message;
@Repository(value="messageDAO")
@Transactional
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean sendMessage(Message message) {
		
		return false;
	}

	@Override
	public boolean deleteMessage(int messageId, String emailId, int circleId) {
		
		return false;
	}

	@Override
	public List<Message> getMyMessages(String emailId) {
		
		return null;
	}

}
