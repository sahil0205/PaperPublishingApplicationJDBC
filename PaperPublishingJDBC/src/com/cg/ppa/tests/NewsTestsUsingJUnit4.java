package com.cg.ppa.tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.ppa.DBConnection;
import com.cg.ppa.configuration.AccessFile;
import com.cg.ppa.entity.News;
import com.cg.ppa.service.NewsService;

public class NewsTestsUsingJUnit4 {

	NewsService service = new NewsService();
	static AccessFile obj = new AccessFile();
	static Properties p = new Properties();
	
	@BeforeClass
	public static void addNews() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			
			String query = "INSERT INTO "+p.getProperty("news_table")+"(newsid, headline, userid, categoryid, location, newsdescription) VALUES('"
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
	
	@AfterClass
	public static void testDeleteNews() throws Exception {
		FileReader dbFile = obj.readFile();
		p.load(dbFile);
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM "+p.getProperty("news_table")+" where newsId='" + 100 + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("News Deleted ");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
