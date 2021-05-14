package com.cg.ppa.tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.ppa.DBConnection;
import com.cg.ppa.entity.News;
import com.cg.ppa.service.NewsService;

public class NewsTestsUsingJUnit4 {

	NewsService service = new NewsService();
	
	@Before
	public void addNews() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			
			String query = "INSERT INTO news_master(newsid, headline, userid, categoryid, location, newsdescription) VALUES('"
					+ 100 + "','" + "Test Headline" + "','" + 2 + "','" + 1 + "','" + "Test Location" + "','"
					+ "Test Description" + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("News Added succesfully");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testViewAllNews() {
		List<News> newsList = service.viewAllNews();
		assertNotNull(newsList);	
		}

	@Test
	public void testViewNewsById() {
		System.out.println(service.viewNewsById(100));
		assertEquals(100, service.viewNewsById(100).getNewsId());
		assertEquals("Test Headline", service.viewNewsById(100).getHeadline());	
		}

	@Test
	public void testViewNewsByLocation() {
		try {
			News news = service.viewNewsByLocation("Test Location");
			assertEquals("Test Location", news.getLocation());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@After
	public void testDeleteNews() throws Exception {
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM news_master where newsId='" + 100 + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("News Deleted ");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
