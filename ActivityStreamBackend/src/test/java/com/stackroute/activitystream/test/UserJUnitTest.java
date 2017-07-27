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
		user.setUsername("krishna");
		user.setPassword("krishna");
		user.setEmailId("krishna@gmail.com");
		user.setMobileNumber(800724072L);
		user.setActive(true);
		assertEquals(true, userDAO.saveUser(user));
	}
	@Ignore
	@Test
	public void testDuplicateSaveUser()
	{
		user.setUsername("arun");
		user.setPassword("arun@123");
		user.setEmailId("arun@gmail.com");
		user.setMobileNumber(9897240728L);
		assertEquals(false, userDAO.saveUser(user));
	}
//*****************************User Login Test cases*****************************
   @Ignore
	@Test
	public void testValidateUser()
	{
		User validatedUser=userDAO.validateUser("pankaj", "pankaj");
		assertNotNull(validatedUser);
	}
   @Ignore
    @Test
   	public void testValidateUserByWrongCredentials()
   	{
   		User validatedUser=userDAO.validateUser("krishna", "pankaj");
   		assertNull(validatedUser);
   	}
   @Ignore
    @Test
   	public void testValidateUserByNullUsername()
   	{
   		User validatedUser=userDAO.validateUser("", "pankaj");
   		assertNull(validatedUser);
   	}
   @Ignore
    @Test
   	public void testValidateUserByNullPassword()
   	{
   		User validatedUser=userDAO.validateUser("pankaj", "");
   		assertNull(validatedUser);
   	}
   @Ignore
    @Test
   	public void testValidateUserByUppercaseUsername()
   	{
   		User validatedUser=userDAO.validateUser("PANKAJ", "pankaj");
   		assertNotNull(validatedUser);
   	}
   @Ignore
    @Test
   	public void testValidateUserByUppercasePassword()
   	{
   		User validatedUser=userDAO.validateUser("pankaj", "PANKAJ");
   		assertNotNull(validatedUser);
   	}
  //*****************************User Login Test cases End*****************************
  
	@Test
	public void testGetUserByEmailId()
	{
		
		User userById=userDAO.getUserByEmailId("advik@gmail.com");
		assertNotNull(userById);
		System.out.println("User Email ID :"+userById.getEmailId());		
	}
	@Ignore
	@Test
	public void testDeleteUser()
	{
		User deletedUser=userDAO.getUserByEmailId("advik@gmail.com");
		assertNotNull(deletedUser);
	}
	@Ignore
	@Test
	public void testUpdateUser()
	{
		User updatedUser=userDAO.getUserByEmailId("advik@gmail.com");
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
