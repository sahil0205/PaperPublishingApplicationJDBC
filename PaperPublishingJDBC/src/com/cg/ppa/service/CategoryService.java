	package com.cg.ppa.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.ppa.DBConnection;
import com.cg.ppa.entity.Category;

public class CategoryService {
	Scanner sc = new Scanner(System.in);

	public Category addCategory() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			System.out.print("Enter Category Id: ");
			int categoryId = sc.nextInt();

			System.out.print("Enter Category Name: ");
			String categoryName = sc.next();
			String query = "INSERT INTO category_master(categoryid, categoryname) VALUES('" + categoryId + "','"
					+ categoryName + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Category added succesfully");
			return new Category(categoryId, categoryName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Category> viewAllCategory() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM category_master");
			List<Category> categories = new ArrayList<>();

			while (rs.next()) {
				Category category = extractCategory(rs);
				categories.add(category);
			}
			return categories;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Category viewCategoryById(int id) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			Category category = null;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM category_master where categoryId='" + id + "'");
			while (rs.next()) {
				category = extractCategory(rs);
			}
			return category;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deleteCategory(int id) {
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM category_master where categoryId='" + id + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Category Deleted ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Category updateCategory(int id) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			Category category = viewCategoryById(id);
			System.out.print("Enter updated Category Name: ");
			String categoryName = sc.next();
			category.setCategoryName(categoryName);
			String query = "update category_master set categoryname='" + categoryName + "' where categoryid='" + id
					+ "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);

			return category;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private Category extractCategory(ResultSet rs) throws SQLException {
		Category category = new Category();

		category.setCategoryId(rs.getInt(1));
		category.setCategoryName(rs.getString(2));

		return category;
	}

}
