package com.database;
import java.sql.*;
import com.entity.*;
import com.mysql.jdbc.PreparedStatement;

public class LoginAction {
	/**
	 * 这个类主要是登录时候，对应的数据库SQL操作语句
	 * 功能：根据用户登录时，传过来的用户账户，返回相应的这个用户的信息
	 * 			     	1. 读者登陆：public ReaderInfor getPassword()
	 * 					2. 管理员登录：public AdminInfor getAdminPassword()
	 */
	private String user;      // 用户名
	private String identity;  // 登录身份
	public LoginAction(String user,String identity) {
		this.user = user;
		this.identity = identity;
	}
	public ReaderInfor getPassword() {
		Connection conn = DatabaseConfig.getConnection();
		ReaderInfor readerInfor = new ReaderInfor();
		String sql = " SELECT * "
				   + " FROM tb_reader"
				   + " WHERE reader_id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);			
			ps.setString(1, this.user);
			ResultSet rs = ps.executeQuery();  // 执行SQL，结果保存在rs
			while (rs.next()) {
				// 将rs中的结果赋值到readerInfor模型中，后面返回该模型
				readerInfor.setreaderId(rs.getString("reader_id"));
				readerInfor.setreaderName(rs.getString("reader_name"));
				readerInfor.setreaderSex(rs.getString("reader_sex"));
				readerInfor.setreaderPassword(rs.getString("reader_password"));
			}
			DatabaseConfig.close(conn, ps);		 // 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return readerInfor;	  // 返回用户信息	
	}	
		
	public AdminInfor getAdminPassword() {
		Connection conn = DatabaseConfig.getConnection();	
		AdminInfor adminInfor = new AdminInfor();
		String sql = "SELECT * "
				   + "  FROM tb_admin"
				   + " WHERE admin_id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);			
			ps.setString(1, this.user);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// 将rs中的结果赋值到AdminInfor模型中，后面返回该模型
				adminInfor.setadminId(rs.getString("admin_id"));
				adminInfor.setadminName(rs.getString("admin_name"));
				adminInfor.setadminSex(rs.getString("admin_sex"));
				adminInfor.setadminPassword(rs.getString("admin_password"));
			}
			    DatabaseConfig.close(conn, ps);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return adminInfor;		// 返回管理员信息	
	}
}
