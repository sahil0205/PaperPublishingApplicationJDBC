package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.cg.ppa.DBConnection;
import com.cg.ppa.entity.User;
import com.cg.ppa.service.LoginService;

class UserTest {
	
	LoginService service = new LoginService();

	@BeforeAll
	static void testAddUser() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			
			String query = "INSERT INTO user_master(userid, username, role, contactnumber, emailid, password) VALUES('"
					+ 10 + "','" + "TestABC" + "','" + "Editor" + "','" + "123456789" + "','" + "TestEmail" + "','"
					+ "TestPass" + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("User added succesfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Order(1)
	void testViewUserById() {
		User user = service.viewUserById(10);
		assertEquals("TestABC", user.getUserName());
	}

	@Test
	@Order(3)
	void testDeleteUser() {
		try {
			service.deleteUser(10);
			User user = service.viewUserById(10);
			assertNull(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Order(2)
	void testLoginUser() {
		try {
			User user = service.loginUser("TestEmail", "TestPass");
			assertEquals(10, user.getUserId());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
