package ua.itea.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static final String ROOT_PACKAGE = Connector.class.getProtectionDomain()
													          .getCodeSource()
													          .getLocation()
													          .getPath()
													          .toString();
	private static final String CLASS = "org.sqlite.JDBC";
	private static final String URL = "jdbc:sqlite:" + ROOT_PACKAGE + "../users.db?";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(CLASS);
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
