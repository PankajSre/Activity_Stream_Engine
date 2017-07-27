package com.stackroute.activitystream.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stackroute.activitystream.dao.CircleDAO;
import com.stackroute.activitystream.model.Circle;

public class CircleJUnitTest {
    @Autowired
	static AnnotationConfigApplicationContext context;
    @Autowired
	static private Circle circle;
    @Autowired
	static private CircleDAO circleDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.stackroute.activitystream");
		context.refresh();
		
		circle=(Circle)context.getBean("circle");
		circleDAO=(CircleDAO) context.getBean("circleDAO");
	}
    @Ignore
	@Test
	public void testToAddCircle()
	{
		circle.setCircleName("Stackroute");
		circle.setCreatedBy("pankaj@gmail.com");
		circle.setCreationDate(new Date());
		circle.setStatus(true);
		assertEquals(true, circleDAO.addCircle(circle));
	}
	@Ignore
	@Test
	public void testToAddUserToCircle()
	{	
		assertEquals(true, circleDAO.addUserToCircle("krishna@gmail.com", 7));
	}
	@Ignore
	@Test
	public void testToDeleteUserFromCircle()
	{	
		assertEquals(true, circleDAO.deleteUserFromCircle("advik@gmail.com", 7));
	}
	
	@Ignore
	@Test
	public void testToDeleteCircle()
	{	Circle circle=circleDAO.getCircleById(10);
		assertEquals(true, circleDAO.deleteCircle(circle,"pankaj@gmail.com"));
	}
	@Ignore
	@Test
	public void testToGetAllActiveCircle()
	{
		List<Circle> circles=circleDAO.getAllCircles();
		assertNotNull(circles);
		circles.forEach(System.out::println);
	}
	@Ignore
	@Test
	public void testToGetActiveCircleByUser()
	{
		List<Circle> circles=circleDAO.getCircleByUser("advik@gmail.com");
		assertNotNull(circles);
		circles.forEach(System.out::println);
	}
	@Test
	public void testToGetAllActiveUsersByCircle()
	{
		List<String> usersOfCircle=circleDAO.getUserByCircle(7);
		assertNotNull(usersOfCircle);
		usersOfCircle.forEach(System.out::println);
	}
	
}
