package com.cg.ppa.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.ppa.DBConnection;
import com.cg.ppa.entity.Paper;

public class PaperService {
	Scanner sc = new Scanner(System.in);

	public Paper createPaper() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			System.out.print("Enter Paper ID: ");
			int paperId = sc.nextInt();
			System.out.print("Enter date of publishing: ");
			String date = sc.next();
			Date publishDate = Date.valueOf(date);
			System.out.print("Enter Editor Id: ");
			int editorId = sc.nextInt();
			System.out.print("Enter price: ");
			int price = sc.nextInt();

			String query = "INSERT INTO paper_master(paperid, publishdate, userid, price) VALUES('" + paperId + "','"
					+ publishDate + "','" + editorId + "','" + price + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Paper Added Successfully");
			return new Paper(paperId, publishDate, editorId, price);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Paper viewPaperById(int id) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			Paper paper = null;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM paper_master where paperId='" + id + "'");
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
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM paper_master");
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

	public void deletePaper(int id) {
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM paper_master where paperId='" + id + "'";
			statement.executeQuery(query);
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Paper Deleted ");
		} catch (Exception e) {
			System.out.println("Cannot delete paper");
		}
	}

	public Paper viewPaperByPublishDate(Date publishDate) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			Paper paper = null;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM paper_master where publishdate='" + publishDate + "'");
			while (rs.next()) {
				paper = extractPaper(rs);
			}
			return paper;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Paper updatePaper(int id) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			Paper paper = viewPaperById(id);

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
			String query = "update paper_master set publishdate='" + publishDate + "',userid='" + editorId + "',price='"
					+ price + "' where paperid='" + id + "'";
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
}
