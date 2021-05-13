package com.cg.ppa;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
	public Connection get_connection() throws Exception {
		Connection connection = null;
		FileReader dbFile = new FileReader(
				"C:\\Users\\SAPALASK\\git\\PaperPublishingUsingJDBC\\PaperPublishingJDBC\\src\\com\\cg\\ppa\\configuration\\db.properties");
		Properties p = new Properties();
		p.load(dbFile);
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://" + p.getProperty("host") + ":" + p.getProperty("port") + "/"
							+ p.getProperty("db_name") + "",
					"" + p.getProperty("username") + "", "" + p.getProperty("password") + "");
			if (connection != null) {
				System.out.println("Connection OK ");
			} else {
				System.out.println("Connection Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;

	}
}
