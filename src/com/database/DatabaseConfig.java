package com.database;
import java.sql.*;
public class DatabaseConfig {	
	/**
	 * 这个类是数据库配置文件的类
	 * 这个类中的功能如下：
	 * 1. 配置相关信息：
	 * 2. 连接数据库：public static Connection getConnection()
	 * 3. 关闭数据库连接和Statement：public static void close(Connection connection, Statement statement)
	 */
	private static final String USER = "root";
	private static final String UPWD = "12345678";
	private static final String URL = "jdbc:mysql://localhost:3306/db_library?characterEncoding=utf8";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	static {
		try {
			Class.forName(DRIVER);  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// 连接数据库
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(URL, USER, UPWD);
		} catch (SQLException e) {
			System.out.println("连接失败");
			e.printStackTrace();
		}
		return null;
	}

	// 关闭数据库连接和Statement
	public static void close(Connection connection, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 关闭数据库连接和Statement
	public static void close(Connection connection, Statement statement, ResultSet rs) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
