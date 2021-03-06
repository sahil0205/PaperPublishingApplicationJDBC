package com.cg.ppa.service;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.cg.ppa.DBConnection;
import com.cg.ppa.configuration.AccessFile;
import com.cg.ppa.entity.User;

public class LoginService {
	Scanner sc = new Scanner(System.in);
	AccessFile obj = new AccessFile();
	Properties p = new Properties();

	public User addUser() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			User userData = enterDetails();
			String query = "INSERT INTO "+p.getProperty("user_table")+"(userid, username, role, contactnumber, emailid, password) VALUES('"
					+ userData.getUserId() + "','" + userData.getUserName() + "','" + userData.getRole() + "','"
					+ userData.getContactNumber() + "','" + userData.getEmailId() + "','" + userData.getPassword()
					+ "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("User added succesfully");
			return userData;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<User> viewAllUsers() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+p.getProperty("user_table"));

			List<User> userList = new ArrayList<>();

			while (rs.next()) {
				User user = extractUser(rs);
				userList.add(user);
			}
			return userList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}

	public <T> User viewUserById(T id) {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			User user = null;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+p.getProperty("user_table")+" where userId='" + id + "'");
			while (rs.next()) {
				user = extractUser(rs);
			}
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public <T> void deleteUser(T id) throws Exception {
		FileReader dbFile = obj.readFile();
		p.load(dbFile);
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM "+p.getProperty("user_table")+"where userId='" + id + "'";
			statement.executeUpdate(query);
			System.out.println("User Deleted ");
		} catch (Exception e) {
			System.out.println("Cannot delete user");
		}
	}

	public <T> User updateUser(T id) {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			User user = updateDetails(viewUserById(id));

			String query = "update "+p.getProperty("user_table")+"set role='" + user.getRole() + "',contactnumber='"
					+ user.getContactNumber() + "' where userid='" + id + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);

			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public <T,U> User loginUser(T email, U password) {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			statement = connection.createStatement();
			User user = null;
			String query = "select * from "+p.getProperty("user_table")+" where emailid='" + email + "'" + "AND password='" + password
					+ "'";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				user = extractUser(rs);
			}
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setUserId(rs.getInt(1));
		user.setUserName(rs.getString(2));
		user.setRole(rs.getString(3));
		user.setContactNumber(rs.getString(4));
		user.setEmailId(rs.getString(5));
		user.setPassword(rs.getString(6));

		return user;
	}

	private User enterDetails() {
		System.out.print("Enter user ID: ");
		int userId = sc.nextInt();

		System.out.print("Enter User Name: ");
		String userName = sc.next();

		System.out.print("Enter Role: ");
		String role = sc.next();

		System.out.print("Enter Contact Number: ");
		String contactNumber = sc.next();

		System.out.print("Enter Email: ");
		String emailId = sc.next();

		System.out.print("Enter Password: ");
		String password = sc.next();

		return new User(userId, userName, role, contactNumber, emailId, password);

	}

	private User updateDetails(User user) {
		System.out.println("Enter updated role");
		String role = sc.next();
		System.out.println("Enter updated contact Number");
		String contactNumber = sc.next();
		user.setRole(role);
		user.setContactNumber(contactNumber);

		return user;
	}
}
