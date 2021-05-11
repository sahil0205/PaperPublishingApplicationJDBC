package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

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
					+ 10 + "','" + "Test Headline" + "','" + 2 + "','" + 1 + "','" + "Test Location" + "','"
					+ "Test Description" + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("News Added succesfully");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Order(1)
	void testViewAllNews() {
		List<News> newsList = service.viewAllNews();
		assertNotNull(newsList);
	}

	@Test
	@Order(2)
	void testViewNewsById() {
		System.out.println(service.viewNewsById(10));
		assertEquals(10, service.viewNewsById(10).getNewsId());
		assertEquals("Test Headline", service.viewNewsById(10).getHeadline());
	}

	@Test
	@Order(3)
	void testViewNewsByLocation() {
		try {
			News news = service.viewNewsByLocation("Test Location");
			assertEquals("Test Location", news.getLocation());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Order(4)
	void testDeleteNews() {
		try {
			service.deleteNews(10);
			News news = service.viewNewsById(10);
			assertNull(news);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
