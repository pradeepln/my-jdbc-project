package com.training.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jedijuly22";
		String userName = "root";
		String password = "secret";
		return DriverManager.getConnection(url, userName, password);
	}

	public static void main(String[] args) {
		
		try(Connection c = getConnection()) {
			DatabaseMetaData meta = c.getMetaData();
			System.out.println(meta.getDatabaseProductName());
			System.out.println(meta.getDatabaseProductVersion());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
