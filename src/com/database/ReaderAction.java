package com.database;
import com.database.DatabaseConfig;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.entity.*;

public class ReaderAction {
	/**
	 * �������Ҫ�Ƕ��߲�������Ӧ�����ݿ�SQL�������
	 * �������£�
	 * 	1. getBook() :�������ݿ��б�tb_books����������
	 *  2. gotBooks(String bookId) ������bookId��ѯ�������鼮
	 *  3. searchBooks(string bookName) :����������ѯ�������鼮
	 *  4. searchBorrowedBooks(String bookId)����ѯ�Ѿ����ĵ��鼮 
	 *  5. borrowBooks(String bookId) �����������װ�˽������
	 *  		������
	 *  			 AllBooks gotBook = gotBooks(bookId);  ��ȡҪ��������Ϣ
	 *				 int deduceOk = bookDeduce(bookId);    �������������1
	 *		         int addOk= addGotBook(gotBook);	      ���鼮������ĵı���
	 *		         
	 *  6.	returnBookAction(String bookId) ����������ķ�װ
	 *  		������
			       int a = borrwoedDeduce(bookId);    ���黹������ѽ���м�1
			       int b =returnBookAdd(bookId);      ���黹������������м�1	
	    7. getReaderInfor�� ���ڷ��ض��߸�����Ϣ
	    8. updateReaderInfor�� ���ڸ��¸�����Ϣ
	 */
	
	String readerId;
	public ReaderAction(String readerId) {
		this.readerId = readerId;
	}

	public List<AllBooks> getBooks() {
		Connection conn = DatabaseConfig.getConnection();
		String sql = "SELECT * FROM tb_books ";						
		List<AllBooks> ab = new ArrayList<AllBooks>();  // ��List������������
		try {
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				AllBooks allbook = new AllBooks();
				allbook.setbookId(rs.getString("book_id"));
				allbook.setbookName(rs.getString("book_name"));
				allbook.setbookNum(rs.getString("book_num"));
				allbook.setbookPrice(rs.getString("book_price"));
				allbook.setbookType(rs.getString("book_type"));
				ab.add(allbook);
			}
			DatabaseConfig.close(conn, ps);  // �ر�����

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ab;
	}
	
	// ����bookId��ѯ�������鼮
	public AllBooks gotBooks(String bookId) {
		AllBooks gotbook = new AllBooks();  // ������ʱ�洢�������ȡ����
		Connection conn = DatabaseConfig.getConnection();
		String sqlGetBook = "SELECT * FROM tb_books WHERE book_id ='"+bookId+"'";
		try {			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sqlGetBook);			
			ResultSet rs = ps.executeQuery();		
			while (rs.next()) {
				gotbook.setbookId(rs.getString("book_id"));
				gotbook.setbookName(rs.getString("book_name"));
				gotbook.setbookPrice(rs.getString("book_price"));
				gotbook.setbookType(rs.getString("book_type"));				
			}		
		// �ر�����
			DatabaseConfig.close(conn, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return gotbook;
    }
	
	// ����������ѯ�������鼮
		public List<AllBooks> searchBooks(String bookname) {
			Connection conn = DatabaseConfig.getConnection();
			String sql = "SELECT * FROM tb_books WHERE book_name = '"+bookname+"'";						
			List<AllBooks> ab = new ArrayList<AllBooks>();
			try {
				
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);			
				ResultSet rs = ps.executeQuery();			
				while (rs.next()) {
					AllBooks allbook = new AllBooks();
					allbook.setbookId(rs.getString("book_id"));
					allbook.setbookName(rs.getString("book_name"));
					allbook.setbookNum(rs.getString("book_num"));
					allbook.setbookPrice(rs.getString("book_price"));
					allbook.setbookType(rs.getString("book_type"));
					ab.add(allbook);
				}
				DatabaseConfig.close(conn, ps);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ab;
	    }
	
	
	// ��GotBook��ӵ�tb_borrowing
	public int addGotBook(AllBooks gotbook) {
		Connection conn = DatabaseConfig.getConnection();
		// ʱ�����
		Date date = new Date();  
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd "); // �������ڸ�ʽ
		String nowString = df.format(date).toString();       // ת���ַ���		
		Calendar calendar = Calendar.getInstance();          // ��������
		calendar.setTime(date);                              // ���õ�ǰ����
		calendar.add(Calendar.DAY_OF_MONTH, +21);            // ����+21
		String nextString = df.format(calendar.getTime()).toString();
		
		try {				
			// ������е���������ڽ��ĵ��� tb_borrowing	
			String sql = "insert into tb_borrowing "
							   + "values('"+gotbook.getbookId()+"'"
							   + ",'"+gotbook.getbookName()+"'"
							   + ",'"+readerId+"'"
							   + ",'"+gotbook.getbookType()+"'"
							   + ","+gotbook.getbookPrice()+""
							   + ",'"+nowString+"'"
							   + ",'"+nextString+"' )";
			PreparedStatement sqlAddBookS =conn.prepareStatement(sql);		
			int res= sqlAddBookS.executeUpdate();
			return res;
		// �ر�����
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return 0;
	}
	
	// ����������tb_books��1
	public int bookDeduce(String bookId) {	
		Connection conn = DatabaseConfig.getConnection();		
		try {		
			// �޸�����������������1
					String sql = "UPDATE tb_books "
										 + "   SET book_num = book_num - 1"
										 + " WHERE book_id = '"+bookId+"'";
			PreparedStatement sqlAddBookS =conn.prepareStatement(sql);
			int res= sqlAddBookS.executeUpdate();
			return res;
		// �ر�����
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return 0;
	}
	
	// ��װ����ĺ���
	public int borrowBooks(String bookId) {
		AllBooks gotBook = gotBooks(bookId);
		int deduceOk = bookDeduce(bookId);
		int addOk= addGotBook(gotBook);		
		if (deduceOk !=0 &&addOk != 0) {
			return 1;
		}
		return 0;
	}
	
	public List<BorrowingBook> getBorrowedBooks() {
		Connection conn = DatabaseConfig.getConnection();
		String sql = "SELECT * FROM tb_borrowing where reader_id = '"+readerId+"'";						
		List<BorrowingBook> ab = new ArrayList<BorrowingBook>();
		try {
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				
				BorrowingBook borrowingBook = new BorrowingBook();				
				borrowingBook.setbookId(rs.getString("book_id"));
				borrowingBook.setbookName(rs.getString("book_name"));				
				borrowingBook.setbookPrice(rs.getFloat("book_price"));
				borrowingBook.setbookType(rs.getString("book_type"));
				borrowingBook.setborrowTime(rs.getString("borrow_time"));
				borrowingBook.setneedReturnTime(rs.getString("need_return_time"));
				
				ab.add(borrowingBook);
			}
			DatabaseConfig.close(conn, ps);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ab;
	}
	
	  // �����������tb_borrwoingɾ��
		public int borrwoedDeduce(String bookId) {	
			Connection conn = DatabaseConfig.getConnection();		
			try {		
				// �޸�����������������1
						String sql = "DELETE FROM  tb_borrowing "
								   + "WHERE book_id = '"+bookId+"'";		 
				PreparedStatement sqlAddBookS =conn.prepareStatement(sql);
				int res= sqlAddBookS.executeUpdate();
				return res;
			// �ر�����
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			return 0;
		}
		
		
		// ���������������м�1
		public int returnBookAdd(String bookId) {	
			Connection conn = DatabaseConfig.getConnection();		
			try {		
				// �޸�����������������1
						String sql = "UPDATE tb_books "
											 + "   SET book_num = book_num + 1"
											 + " WHERE book_id = '"+bookId+"'";
				PreparedStatement sqlAddBookS =conn.prepareStatement(sql);
				int res= sqlAddBookS.executeUpdate();
				return res;
			// �ر�����
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			return 0;
		}
	
		// ���鶯���ķ�װ
		public int returnBookAction(String bookId) {
			int a = borrwoedDeduce(bookId);  // ���ı��鱾��1
			int b =returnBookAdd(bookId);    // ����鱾��1
			if (a != 0 && b!=0) {
				return 1;
			}
			return 0;
		}
		
	// �����ѽ��ĵ����id��ѯ�������鼮
	public List<AllBooks> searchBorrowedBooks(String bookId) {
		Connection conn = DatabaseConfig.getConnection();
		String sql = "SELECT * "
				   + "  FROM tb_borrowing "
				   + " WHERE book_id = '" + bookId + "' and reader_id= '"+readerId+"'";
		List<AllBooks> ab = new ArrayList<AllBooks>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AllBooks allbook = new AllBooks();
				allbook.setbookId(rs.getString("book_id"));
				allbook.setbookName(rs.getString("book_name"));
				allbook.setbookPrice(rs.getString("book_price"));
				allbook.setbookType(rs.getString("book_type"));
				ab.add(allbook);
			}
			DatabaseConfig.close(conn, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ab;
	}
		
	  // ��ȡ��������
		public ReaderInfor getReaderInfor() {
			ReaderInfor readerInfor = new ReaderInfor();  // ������ʱ�洢�������ȡ����
			Connection conn = DatabaseConfig.getConnection();
			String sqlGetBook = "SELECT * FROM tb_reader WHERE reader_id ='"+readerId+"'";
			try {			
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sqlGetBook);			
				ResultSet rs = ps.executeQuery();		
				while (rs.next()) {
					readerInfor.setreaderId(rs.getString("reader_id"));
					readerInfor.setreaderName(rs.getString("reader_name"));
					readerInfor.setreaderSex(rs.getString("reader_sex"));
					readerInfor.setreaderPassword(rs.getString("reader_password"));
				}		
			// �ر�����
				DatabaseConfig.close(conn, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			return readerInfor;
	    }
	// �޸ĸ�������
	public int updateReaderInfor(ReaderInfor readerInfor) {
		String sex = readerInfor.getreaderSex();
		String name = readerInfor.getreaderName();
		String id = readerInfor.getreaderId();
		String pw =readerInfor.getreaderPassword();
		Connection conn = DatabaseConfig.getConnection();		
		try {		
			
			String sql = "UPDATE tb_reader "
					 + "   SET reader_sex = '"+sex+"'"
					 + "   , reader_id = '"+id+"'"
					 + "   , reader_name = '"+name+"'"
					 + ",reader_password = '"+pw+"'"
					 + " WHERE reader_id = '"+readerId+"'";
			PreparedStatement sqlaction =conn.prepareStatement(sql);
			int res= sqlaction.executeUpdate();
			return res;
		// �ر�����
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return 0;
	}
}
	
	
	 