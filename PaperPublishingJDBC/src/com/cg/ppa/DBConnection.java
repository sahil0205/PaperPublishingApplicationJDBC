package com.cg.ppa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
	public Connection get_connection() {
		Connection connection = null;
		Statement statement = null;
		String host = "localhost";
		String port = "5432";
		String db_name = "PaperPublishingJDBC";
		String username = "postgres";
		String password = "0205";
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "",
					"" + username + "", "" + password + "");
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
