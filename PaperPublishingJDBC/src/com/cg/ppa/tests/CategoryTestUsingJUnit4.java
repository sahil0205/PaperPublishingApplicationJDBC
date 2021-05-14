package com.cg.ppa.tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.ppa.DBConnection;
import com.cg.ppa.entity.Category;
import com.cg.ppa.service.CategoryService;

public class CategoryTestUsingJUnit4 {

	CategoryService service = new CategoryService();
	
	@Before
	public void testAddCategory() {
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
	public void testViewAllCategory() {
		try {
			List<Category> categoryList = service.viewAllCategory();
			assertNotNull(categoryList);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testViewCategoryById() {
		Category category = service.viewCategoryById(100);
		assertEquals(100, category.getCategoryId());
	}
	
	@After
	public void testDeleteCategory() {
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
