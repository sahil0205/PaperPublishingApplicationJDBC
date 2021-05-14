package com.cg.ppa.tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.ppa.DBConnection;
import com.cg.ppa.configuration.AccessFile;
import com.cg.ppa.entity.Paper;
import com.cg.ppa.service.PaperService;

public class PaperTestsUsingJUnit4 {

	PaperService service = new PaperService();
	static AccessFile obj = new AccessFile();
	static Properties p = new Properties();
	
	@BeforeClass
	public static void createPaper() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			
			String query = "INSERT INTO "+p.getProperty("paper_table")+"(paperid, publishdate, userid, price) VALUES('" + 100 + "','"
					+ "2021-05-11" + "','" + 1 + "','" + 3 + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Paper Added Successfully");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testViewPaperById() {
		Paper paper = service.viewPaperById(100);
		assertEquals(100, paper.getPaperId());
		assertEquals(Date.valueOf("2021-05-11"), paper.getPublishDate());
	}

	@Test
	public void testViewAllPaper() {
		List<Paper> paperList = service.viewAllPaper();
		assertNotEquals(0, paperList.size());
	}

	@Test
	public void testViewPaperByPublishDate() {
		Paper paper = service.viewPaperByPublishDate(Date.valueOf("2021-05-11"));
		assertEquals(Date.valueOf("2021-05-11"), paper.getPublishDate());
	}
	
	@AfterClass
	public static void deletePaper() throws Exception {
		FileReader dbFile = obj.readFile();
		p.load(dbFile);
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM "+p.getProperty("paper_table")+" where paperId='" + 100 + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Paper Deleted ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
