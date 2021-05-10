package com.cg.ppa.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.ppa.DBConnection;
import com.cg.ppa.entity.User;

public class LoginService {
	Scanner sc = new Scanner(System.in);

	public User addUser() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

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
			String query = "INSERT INTO user_master(userid, username, role, contactnumber, emailid, password) VALUES('"
					+ userId + "','" + userName + "','" + role + "','" + contactNumber + "','" + emailId + "','"
					+ password + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("User added succesfully");
			return new User(userId, userName, role, contactNumber, emailId, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<User> viewAllUsers() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM user_master");

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

	public User viewUserById(int id) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			User user = null;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM user_master where userId='" + id + "'");
			while (rs.next()) {
				user = extractUser(rs);
			}
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deleteUser(int id) {
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM user_master where userId='" + id + "'";
			statement.executeUpdate(query);
			System.out.println("User Deleted ");
		} catch (Exception e) {
			System.out.println("Cannot delete user");
		}
	}

	public User updateUser(int id) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			User user = viewUserById(id);
			System.out.println("Enter updated role");
			String role = sc.next();
			System.out.println("Enter updated contact Number");
			String contactNumber = sc.next();
			user.setRole(role);
			user.setContactNumber(contactNumber);
			String query = "update user_master set role='" + role + "',contactnumber='" + contactNumber
					+ "' where userid='" + id + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);

			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public User loginUser(String email, String password) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			statement = connection.createStatement();
			User user = null;
			String query = "select * from user_master where emailid='" + email + "'" + "AND password='" + password
					+ "'";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				user = extractUser(rs);
			}
			return user;
		} catch (Exception e) {
			System.out.println("Invalid Credentials");
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
}
