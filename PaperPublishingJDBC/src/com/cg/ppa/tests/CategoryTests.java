package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
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

			String query = "INSERT INTO category_master(categoryid, categoryname) VALUES('" + 10 + "','"
					+ "Test Category" + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("category added");
					} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Order(1)
	void testViewCategoryById() {
		Category category = service.viewCategoryById(10);
		assertEquals(10, category.getCategoryId());
	}

	@Test
	@Order(2)
	void testDeleteCategory() {
		try {
			service.deleteCategory(10);
			Category category = service.viewCategoryById(10);
			assertNull(category);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
