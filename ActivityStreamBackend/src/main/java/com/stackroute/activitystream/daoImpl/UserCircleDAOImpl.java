package com.stackroute.activitystream.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.stackroute.activitystream.dao.UserCircleDAO;
import com.stackroute.activitystream.model.Circle;
import com.stackroute.activitystream.model.UserCircle;

@Repository(value="userCircleDAO")
@Transactional
public class UserCircleDAOImpl implements UserCircleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addCircle(UserCircle circle) {
		
		try {
			sessionFactory.getCurrentSession().save(circle);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addUserToCircle(String emailId, int circleId) {
		
		try {
			UserCircle userCircle = new UserCircle();
			userCircle.setCreatedBy(emailId);
			userCircle.setCircleId(circleId);
			sessionFactory.getCurrentSession().save(userCircle);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUserFromCircle(String emailId, int circleId) {
		try {
			UserCircle userCircle = new UserCircle();
			userCircle.setCreatedBy(emailId);
			userCircle.setCircleId(circleId);
			sessionFactory.getCurrentSession().delete(userCircle);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCircle(UserCircle circle,String emailId) {//Just to Change the Status of Circle to false
	
		try {
			if(circle.getCreatedBy().equals(emailId))
			{
			circle.setStatus(false);
			sessionFactory.getCurrentSession().update(circle);
			return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Circle> getAllCircles() {
		String hql="from Circle where status=true";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public List<Circle> getCircleByUser(String createdBy) {
		String sql="from Circle where createdBy='"+createdBy+"'";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public List<String> getUserByCircle(int circleId) {
		String sql="from UserCircle where circleId=circleId";
		List<UserCircle> userCircle=sessionFactory.getCurrentSession().createQuery(sql).list();
		List<String> users=new ArrayList<>();
		for(UserCircle s : userCircle)users.add(s.getCreatedBy());
		return users;
	}

	@Override
	public Circle getCircleById(int id) {
		
		return sessionFactory.getCurrentSession().get(Circle.class, id);
	}

}
