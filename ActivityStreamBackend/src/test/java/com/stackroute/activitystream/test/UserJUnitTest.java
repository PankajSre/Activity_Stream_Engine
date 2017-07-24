package com.stackroute.activitystream.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stackroute.activitystream.dao.UserDAO;
import com.stackroute.activitystream.model.User;


public class UserJUnitTest {

	@Autowired
	private static User user;
	@Autowired
	private static UserDAO userDAO;
	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.stackroute.activitystream");
		context.refresh();
		user=(User) context.getBean("user");
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	@Ignore
	@Test
	public void testSaveUser()
	{
		user.setUsername("pankaj");
		user.setPassword("pankaj@123");
		user.setEmailId("pankaj@gmail.com");
		user.setMobileNumber(9897240728L);
		assertEquals(true, userDAO.saveUser(user));
	}
	@Ignore
    @Test
	public void testValidateUser()
	{
		User validatedUser=userDAO.validateUser("pankaj", "pankaj@123");
		assertNotNull(validatedUser);
	}
	@Ignore
	@Test
	public void testGetUserById()
	{
		User userById=userDAO.getUserByUsername("pankaj");
		assertNotNull(userById);
		System.out.println("User Email ID :"+user.getEmailId());		
	}
	@Ignore
	@Test
	public void testDeleteUser()
	{
		User deletedUser=userDAO.getUserByUsername("pankaj");
		assertNotNull(deletedUser);
	}
	@Ignore
	@Test
	public void testUpdateUser()
	{
		User updatedUser=userDAO.getUserByUsername("pankaj");
		assertNotNull(updatedUser);
		updatedUser.setEmailId("Pankaj.saini85@gmail.com");
		assertEquals(true, userDAO.updateUser(updatedUser));
	}
   @Ignore
	@Test
	public void testForGetAllUsers()
	{
		List<User> userList=userDAO.getAllUsers();
		assertNotNull(userList);
		for(User userData : userList)
		{
			System.out.println(userData.getUsername()+"  :  "+userData.getMobileNumber());
		}
	}

}
