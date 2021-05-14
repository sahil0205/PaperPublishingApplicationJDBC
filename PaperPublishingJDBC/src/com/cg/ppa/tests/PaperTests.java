package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.cg.ppa.DBConnection;
import com.cg.ppa.entity.Paper;
import com.cg.ppa.service.PaperService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaperTests {

	PaperService service = new PaperService();
	
	@BeforeAll
    static void createPaper() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			
			String query = "INSERT INTO paper_master(paperid, publishdate, userid, price) VALUES('" + 100 + "','"
					+ "2021-05-11" + "','" + 1 + "','" + 3 + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Paper Added Successfully");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void viewById() {
		Paper paper = service.viewPaperById(100);
		assertEquals(100, paper.getPaperId());
		assertEquals(Date.valueOf("2021-05-11"), paper.getPublishDate());
	}
	
	@Test
	public void viewByPublishDate() {
		Paper paper = service.viewPaperByPublishDate(Date.valueOf("2021-05-11"));
		assertEquals(Date.valueOf("2021-05-11"), paper.getPublishDate());
	}
	
	@Test
	public void viewAll() {
		List<Paper> paperList = service.viewAllPaper();
		assertNotEquals(0, paperList.size());
	}
	
	@AfterAll
	static void deletePaper() throws Exception {
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM paper_master where paperId='" + 100 + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Paper Deleted ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}
