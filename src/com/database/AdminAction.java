package com.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.entity.AdminInfor;
import com.entity.AllBooks;
import com.entity.ReaderInfor;

public class AdminAction {
	/**
	 * �������Ҫ�ǹ���Ա��������Ӧ�����ݿ�SQL�������
	 * �������£�
	 * 	1. addBooks(AllBooks book):����������鼮
	 *  2. updateBooks(AllBooks book,String bookid)���޸�����鼮
	 *  3. addReader() :ע�������Ϣ
	 *  4. getAdminInfor(String adminId) ����ȡ����Ա��Ϣ
	 *  5. getReaderInfor() ����ȡ������Ϣ
	 *  6. searchReaders(String readerName) ������������Ϣ
	 */	
	public int addBooks(AllBooks book) {	
		Connection conn = DatabaseConfig.getConnection();		
		try {		
			
					String sql = "INSERT INTO tb_books VALUES ( '"
							   + book.getbookId() + "','"
							   + book.getbookName() + "','"
							   + book.getbookType() + "',"
							   + book.getbookNum() + ","
							   + book.getbookPrice()+")";
					
			PreparedStatement sqlAddBookS =conn.prepareStatement(sql);
			int res= sqlAddBookS.executeUpdate();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return 0;
	}
	
	public int updateBooks(AllBooks book,String bookid) {	
		Connection conn = DatabaseConfig.getConnection();		
		try {		
				String sql = "UPDATE tb_books "
						   + "SET book_num = '" + book.getbookNum()+"',"
						   + "book_id ='" + book.getbookId()+"',"
						   + "book_name = '" + book.getbookName()+"',"
						   + "book_type = '" + book.getbookType()+"',"
						   + "book_price = '" + book.getbookPrice()+"'"
						   + " WHERE book_id = '"+bookid+"'";
			
			PreparedStatement sqlAddBookS =conn.prepareStatement(sql);
			int res= sqlAddBookS.executeUpdate();
			return res;
		// �ر�����
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return 0;
	}
	
	// ע�����
	public int addReader(ReaderInfor reader) {
		Connection conn = DatabaseConfig.getConnection();
		
		try {					
			String sql = "insert into tb_reader "
							   + "values('"+reader.getreaderId()+"'"
							   + ",'"+reader.getreaderName()+"'"
							   + ",'"+reader.getreaderPassword()+"'"
							   + ",'"+reader.getreaderSex()+"')";							  
			PreparedStatement sqlAdd =conn.prepareStatement(sql);		
			int res= sqlAdd.executeUpdate();
			return res;
		// �ر�����
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return 0;
	}
	// ��ȡ������Ϣ
	public List<ReaderInfor> getReaderInfor() {
		Connection conn = DatabaseConfig.getConnection();
		String sql = "SELECT * FROM tb_reader ";						
		List<ReaderInfor> ab = new ArrayList<ReaderInfor>();
		try {
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				ReaderInfor reader = new ReaderInfor();
				reader.setreaderId(rs.getString("reader_id"));
				reader.setreaderName(rs.getString("reader_name"));
				reader.setreaderSex(rs.getString("reader_sex"));
				reader.setreaderPassword(rs.getString("reader_password"));
				ab.add(reader);
				
			}
			DatabaseConfig.close(conn, ps);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ab;
	}
	
	// ����������Ϣ
	public List<ReaderInfor> searchReaders(String readerName) {
		Connection conn = DatabaseConfig.getConnection();
		String sql = "SELECT * FROM tb_reader WHERE reader_name = '" + readerName + "'";
		List<ReaderInfor> ab = new ArrayList<ReaderInfor>();
		try {

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ReaderInfor reader = new ReaderInfor();
				reader.setreaderId(rs.getString("reader_id"));
				reader.setreaderName(rs.getString("reader_name"));
				reader.setreaderSex(rs.getString("reader_sex"));
				reader.setreaderPassword(rs.getString("reader_password"));
				ab.add(reader);
			}
			DatabaseConfig.close(conn, ps);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ab;
	}

	// ��ȡ����Ա������Ϣ
	public AdminInfor getAdminInfor(String adminId) {
		AdminInfor adminInfor = new AdminInfor(); // ������ʱ�洢�������ȡ����
		Connection conn = DatabaseConfig.getConnection();
		String sqlGetBook = "SELECT * FROM tb_admin WHERE admin_id ='" + adminId + "'";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sqlGetBook);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				adminInfor.setadminName(rs.getString("admin_name"));
			}
			// �ر�����
			DatabaseConfig.close(conn, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adminInfor;
	}

}
