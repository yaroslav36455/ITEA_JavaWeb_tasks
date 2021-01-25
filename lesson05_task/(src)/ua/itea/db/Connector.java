package ua.itea.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static final String URL = "jdbc:sqlite:../webapps/ROOT/lesson05_task/users.db/?";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
