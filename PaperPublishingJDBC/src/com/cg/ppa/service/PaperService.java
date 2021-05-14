package com.cg.ppa.service;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.cg.ppa.DBConnection;
import com.cg.ppa.configuration.AccessFile;
import com.cg.ppa.entity.Paper;

public class PaperService {
	Scanner sc = new Scanner(System.in);
	AccessFile obj = new AccessFile();
	Properties p = new Properties();

	public Paper createPaper() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			Paper paperData = addDetails();

			String query = "INSERT INTO "+p.getProperty("paper_table")+"(paperid, publishdate, userid, price) VALUES('" + paperData.getPaperId() + "','"
					+ paperData.getPublishDate() + "','" + paperData.getEditorId() + "','" + paperData.getPrice() + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Paper Added Successfully");
			return paperData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public <T> Paper viewPaperById(T id) {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			Paper paper = null;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+p.getProperty("paper_table")+" where paperId='" + id + "'");
			while (rs.next()) {
				paper = extractPaper(rs);
			}
			return paper;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Paper> viewAllPaper() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+p.getProperty("paper_table"));
			List<Paper> paperList = new ArrayList<>();
			
			while (rs.next()) {
				Paper paper = extractPaper(rs);
				paperList.add(paper);
			}
			
			return paperList;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public <T> void deletePaper(T id) throws Exception {
		FileReader dbFile = obj.readFile();
		p.load(dbFile);
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM "+p.getProperty("paper_table")+" where paperId='" + id + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Paper Deleted ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public <T> Paper viewPaperByPublishDate(T publishDate) {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			Paper paper = null;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+p.getProperty("paper_table")+" WHERE publishdate='" + publishDate + "'");
			while (rs.next()) {
				paper = extractPaper(rs);
			}
			return paper;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public <T> Paper updatePaper(T id) {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			Paper paper = updateDetails(viewPaperById(id));

			String query = "update "+p.getProperty("paper_table")+" set publishdate='" + paper.getPublishDate() + "',userid='" + paper.getEditorId() + "',price='"
					+ paper.getPrice() + "' where paperid='" + id + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			return paper;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private Paper extractPaper(ResultSet rs) throws SQLException {
		Paper paper = new Paper();

		paper.setPaperId(rs.getInt(1));
		paper.setPublishDate(rs.getDate(2));
		paper.setEditorId(rs.getInt(3));
		paper.setPrice(rs.getInt(4));
		return paper;
	}
	
	private Paper addDetails() {
		System.out.print("Enter Paper ID: ");
		int paperId = sc.nextInt();
		System.out.print("Enter date of publishing: ");
		String date = sc.next();
		Date publishDate = Date.valueOf(date);
		System.out.print("Enter Editor Id: ");
		int editorId = sc.nextInt();
		System.out.print("Enter price: ");
		int price = sc.nextInt();
		
		return new Paper(paperId, publishDate, editorId, price);

	}
	
	private Paper updateDetails(Paper paper) {
		System.out.print("Enter new publish date: ");
		String date = sc.next();
		Date publishDate = Date.valueOf(date);
		paper.setPublishDate(publishDate);
		System.out.print("Enter new editor id: ");
		int editorId = sc.nextInt();
		paper.setEditorId(editorId);
		System.out.print("Enter new price: ");
		int price = sc.nextInt();
		paper.setPrice(price);
		
		return paper;
	}
}
