package com.database;
import java.sql.*;
import com.entity.*;
import com.mysql.jdbc.PreparedStatement;

public class LoginAction {
	/**
	 * �������Ҫ�ǵ�¼ʱ�򣬶�Ӧ�����ݿ�SQL�������
	 * ���ܣ������û���¼ʱ�����������û��˻���������Ӧ������û�����Ϣ
	 * 			     	1. ���ߵ�½��public ReaderInfor getPassword()
	 * 					2. ����Ա��¼��public AdminInfor getAdminPassword()
	 */
	private String user;      // �û���
	private String identity;  // ��¼���
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
			ResultSet rs = ps.executeQuery();  // ִ��SQL�����������rs
			while (rs.next()) {
				// ��rs�еĽ����ֵ��readerInforģ���У����淵�ظ�ģ��
				readerInfor.setreaderId(rs.getString("reader_id"));
				readerInfor.setreaderName(rs.getString("reader_name"));
				readerInfor.setreaderSex(rs.getString("reader_sex"));
				readerInfor.setreaderPassword(rs.getString("reader_password"));
			}
			DatabaseConfig.close(conn, ps);		 // �ر�����
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return readerInfor;	  // �����û���Ϣ	
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
				// ��rs�еĽ����ֵ��AdminInforģ���У����淵�ظ�ģ��
				adminInfor.setadminId(rs.getString("admin_id"));
				adminInfor.setadminName(rs.getString("admin_name"));
				adminInfor.setadminSex(rs.getString("admin_sex"));
				adminInfor.setadminPassword(rs.getString("admin_password"));
			}
			    DatabaseConfig.close(conn, ps);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return adminInfor;		// ���ع���Ա��Ϣ	
	}
}
