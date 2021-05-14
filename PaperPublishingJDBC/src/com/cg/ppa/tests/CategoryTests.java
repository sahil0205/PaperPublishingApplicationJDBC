package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.ppa.DBConnection;
import com.cg.ppa.configuration.AccessFile;
import com.cg.ppa.entity.Category;
import com.cg.ppa.service.CategoryService;


class CategoryTests {
	
	CategoryService service = new CategoryService();
	static AccessFile obj = new AccessFile();
	static Properties p = new Properties();
	
	@BeforeAll
	static void testAddCategory() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			String query = "INSERT INTO "+p.getProperty("category_table")+"(categoryid, categoryname) VALUES('" + 100 + "','"
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
		assertEquals(100, category.getCategoryId());
		assertEquals("Test Category", category.getCategoryName());
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
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			
			statement = connection.createStatement();
			String query = "DELETE FROM "+p.getProperty("category_table")+" where categoryId='" + 100 + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Category Deleted ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}