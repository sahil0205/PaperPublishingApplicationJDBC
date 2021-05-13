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
			Category categoryData = enterDetails();
			String query = "INSERT INTO category_master(categoryid, categoryname) VALUES('"
					+ categoryData.getCategoryId() + "','" + categoryData.getCategoryName() + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Category added succesfully");
			return categoryData;
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

	public <T>Category viewCategoryById(T id) {
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

	public <T> void deleteCategory(T id) throws Exception {
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

	public <T> Category updateCategory(T id) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			Category category = updateDetails(viewCategoryById(id));

			String query = "update category_master set categoryname='" + category.getCategoryName()
					+ "' where categoryid='" + id + "'";
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

	private Category enterDetails() {
		System.out.print("Enter Category Id: ");
		int categoryId = sc.nextInt();

		System.out.print("Enter Category Name: ");
		String categoryName = sc.next();

		return new Category(categoryId, categoryName);

	}

	private Category updateDetails(Category category) {
		System.out.print("Enter updated Category Name: ");
		String categoryName = sc.next();
		category.setCategoryName(categoryName);
		return category;
	}

}
