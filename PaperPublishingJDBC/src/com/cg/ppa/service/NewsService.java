package com.cg.ppa.service;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.cg.ppa.DBConnection;
import com.cg.ppa.configuration.AccessFile;
import com.cg.ppa.entity.News;

public class NewsService {
	Scanner sc = new Scanner(System.in);
	AccessFile obj = new AccessFile();
	Properties p = new Properties();

	public News addNews() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			News newsData = addDetails();
			String query = "INSERT INTO "+p.getProperty("news_table")+"(newsid, headline, userid, categoryid, location, newsdescription) VALUES('"
					+ newsData.getNewsId() + "','" + newsData.getHeadline() + "','" + newsData.getReporterId() + "','"
					+ newsData.getCategoryId() + "','" + newsData.getLocation() + "','" + newsData.getNewsDescription()
					+ "')";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("News Added succesfully");
			return newsData;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<News> viewAllNews() {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+p.getProperty("news_table"));

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

	public <T> News viewNewsById(T id) {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();
			News news = null;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+p.getProperty("news_table")+" WHERE newsid='" + id + "'");
			while (rs.next()) {
				news = extractNews(rs);
			}
			return news;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage()+"id error");
			return null;
		}
	}

	public <T> void deleteNews(T id) throws Exception {
		DBConnection obj_ConnectDB = new DBConnection();
		Connection connection = null;
		Statement statement = null;
		connection = obj_ConnectDB.get_connection();

		try {
			statement = connection.createStatement();
			String query = "DELETE FROM "+p.getProperty("news_table")+" where newsid='" + id + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("News Deleted ");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public <T> News updateNews(T id) {
		try {
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			News news = updateDetails(viewNewsById(id));

			String query = "update "+p.getProperty("news_table")+" set headline='" + news.getHeadline() + "',newsdescription='"
					+ news.getNewsDescription() + "' where newsid='" + id + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			return news;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public <T> News viewNewsByLocation(T location) {
		try {
			FileReader dbFile = obj.readFile();
			p.load(dbFile);
			DBConnection obj_ConnectDB = new DBConnection();
			Connection connection = null;
			Statement statement = null;
			connection = obj_ConnectDB.get_connection();

			News news = null;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+p.getProperty("news_table")+" where location='" + location + "'");
			while (rs.next()) {
				news = extractNews(rs);
			}
			return news;
		} catch (Exception e) {
			System.out.println(e.getMessage()+"Location error");
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

	private News addDetails() {
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

		return new News(newsId, headline, reporterId, location, categoryId, newsDescription);
	}

	private News updateDetails(News news) {
		sc.nextLine();
		System.out.print("Enter new headline: ");
		String headline = sc.nextLine();
		news.setHeadline(headline);
		System.out.print("Enter new description: ");
		String newsDescription = sc.nextLine();
		news.setNewsDescription(newsDescription);

		return news;
	}
}
