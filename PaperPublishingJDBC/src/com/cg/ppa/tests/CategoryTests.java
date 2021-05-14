package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.ppa.DBConnection;
import com.cg.ppa.entity.Category;
import com.cg.ppa.service.CategoryService;


class CategoryTests {
	
	CategoryService service = new CategoryService();
	
	@BeforeAll
	static void testAddCategory() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			String query = "INSERT INTO category_master(categoryid, categoryname) VALUES('" + 100 + "','"
					+ "Test Category" + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("category added");
					} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void testViewCategoryById() {
		Category category = service.viewCategoryById(100);
		assertEquals(10, category.getCategoryId());
	}

	@Test
	void testViewAllCategory() {
		try {
			List<Category> categoryList = service.viewAllCategory();
			assertNotNull(categoryList);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@AfterAll
	static void testDeleteCategory() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			
			statement = connection.createStatement();
			String query = "DELETE FROM category_master where categoryId='" + 100 + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Category Deleted ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}