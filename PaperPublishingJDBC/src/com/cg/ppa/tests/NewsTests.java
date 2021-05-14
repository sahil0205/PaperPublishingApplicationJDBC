package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.cg.ppa.DBConnection;
import com.cg.ppa.entity.News;
import com.cg.ppa.service.NewsService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NewsTests {

	NewsService service = new NewsService();
	
	@BeforeAll
	static void addNews() {
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
	void testViewAllNews() {
		List<News> newsList = service.viewAllNews();
		assertNotNull(newsList);
	}

	@Test
	void testViewNewsById() {
		System.out.println(service.viewNewsById(100));
		assertEquals(100, service.viewNewsById(100).getNewsId());
		assertEquals("Test Headline", service.viewNewsById(100).getHeadline());
	}

	@Test
	void testViewNewsByLocation() {
		try {
			News news = service.viewNewsByLocation("Test Location");
			assertEquals("Test Location", news.getLocation());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@AfterAll
	static void testDeleteNews() throws Exception {
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
