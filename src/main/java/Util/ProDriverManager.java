package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ProDriverManager{
	
	public static Connection getConnection(Properties properties) throws SQLException{
		return DriverManager.getConnection(properties.getProperty("host"), properties.getProperty("username"), properties.getProperty("password"));
	}
}
