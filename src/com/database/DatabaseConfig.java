package com.database;
import java.sql.*;
public class DatabaseConfig {	
	/**
	 * ����������ݿ������ļ�����
	 * ������еĹ������£�
	 * 1. ���������Ϣ��
	 * 2. �������ݿ⣺public static Connection getConnection()
	 * 3. �ر����ݿ����Ӻ�Statement��public static void close(Connection connection, Statement statement)
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
	// �������ݿ�
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(URL, USER, UPWD);
		} catch (SQLException e) {
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
		return null;
	}

	// �ر����ݿ����Ӻ�Statement
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
	// �ر����ݿ����Ӻ�Statement
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
