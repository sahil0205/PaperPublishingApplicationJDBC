package com.cg.ppa;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.cg.ppa.configuration.AccessFile;

public class DBConnection {
	public Connection get_connection() throws Exception {
		Connection connection = null;
		AccessFile obj = new AccessFile();
		Properties p = new Properties();

		FileReader dbFile = obj.readFile();
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
			System.out.println(e.getMessage());
		}
		return connection;

	}
}
