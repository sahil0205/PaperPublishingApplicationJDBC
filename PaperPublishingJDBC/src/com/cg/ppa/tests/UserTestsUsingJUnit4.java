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
import com.cg.ppa.entity.User;
import com.cg.ppa.service.LoginService;

public class UserTestsUsingJUnit4 {
	
	LoginService service = new LoginService();
	static AccessFile obj = new AccessFile();
	static Properties p = new Properties();
	
	@BeforeClass
	public static void testAddUser() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			
			String query = "INSERT INTO "+p.getProperty("user_table")+"(userid, username, role, contactnumber, emailid, password) VALUES('"
					+ 100 + "','" + "TestABC" + "','" + "Editor" + "','" + "123456789" + "','" + "TestEmail" + "','"
					+ "TestPass" + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("User added succesfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testViewAllUsers() {
		try {
			List<User> userList = service.viewAllUsers();
			assertNotNull(userList);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testViewUserById() {
		User user = service.viewUserById(100);
		assertEquals("TestABC", user.getUserName());
	}

	@Test
	public void testLoginUser() {
		try {
			User user = service.loginUser("TestEmail", "TestPass");
			assertEquals(100, user.getUserId());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@AfterClass
	public static void testDeleteUser() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			
			statement = connection.createStatement();
			String query = "DELETE FROM "+p.getProperty("user_table")+" where userId='" + 100 + "'";
			statement.executeUpdate(query);
			System.out.println("User Deleted ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
