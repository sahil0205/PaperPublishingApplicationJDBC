package com.cg.ppa.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.ppa.DBConnection;
import com.cg.ppa.entity.News;

public class NewsService {
	Scanner sc = new Scanner(System.in);

	public News addNews() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			System.out.print("Enter news id: ");
			int newsId = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter news headline: ");
			String headline = sc.nextLine();
			System.out.print("Enter reporter Id: ");
			int reporterId = sc.nextInt();
			System.out.print("Enter location: ");
			String location = sc.next();
			System.out.print("Enter category Id: ");
			int categoryId = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter news description: ");
			String newsDescription = sc.nextLine();
			String query = "INSERT INTO news_master(newsid, headline, userid, categoryid, location, newsdescription) VALUES('"
					+ newsId + "','" + headline + "','" + reporterId + "','" + categoryId + "','" + location + "','"
					+ newsDescription + "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("News Added succesfully");
			return new News(newsId, headline, reporterId, location, categoryId, newsDescription);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<News> viewAllNews() {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM news_master");

			List<News> newsList = new ArrayList<>();

			while (rs.next()) {
				News news = extractNews(rs);
				newsList.add(news);
			}
			return newsList;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public News viewNewsById(int id) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			News news = null;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM news_master where newsId='" + id + "'");
			while (rs.next()) {
				news = extractNews(rs);
			}
			return news;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deleteNews(int id) {
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM news_master where newsId='" + id + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("News Deleted ");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public News updateNews(int id) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			News news = viewNewsById(id);
			sc.nextLine();
			System.out.print("Enter new headline: ");
			String headline = sc.nextLine();
			news.setHeadline(headline);
			System.out.print("Enter new description: ");
			String newsDescription = sc.nextLine();
			news.setNewsDescription(newsDescription);
			String query = "update news_master set headline='" + headline + "',newsdescription='" + newsDescription
					+ "' where newsid='" + id + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			return news;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public News viewNewsByLocation(String location) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			News news = null;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM news_master where location='" + location + "'");
			while (rs.next()) {
				news = extractNews(rs);
			}
			return news;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private News extractNews(ResultSet rs) throws SQLException {
		News news = new News();

		news.setNewsId(rs.getInt(1));
		news.setHeadline(rs.getString(2));
		news.setReporterId(rs.getInt(3));
		news.setCategoryId(rs.getInt(4));
		news.setLocation(rs.getString(5));
		news.setNewsDescription(rs.getString(6));

		return news;

	}
}
