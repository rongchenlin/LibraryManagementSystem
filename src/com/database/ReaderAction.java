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
	 * 这个类主要是读者操作，对应的数据库SQL操作语句
	 * 功能如下：
	 * 	1. getBook() :返回数据库中表tb_books的所有数据
	 *  2. gotBooks(String bookId) ：根据bookId查询并返回书籍
	 *  3. searchBooks(string bookName) :根据书名查询并返回书籍
	 *  4. searchBorrowedBooks(String bookId)：查询已经借阅的书籍 
	 *  5. borrowBooks(String bookId) ：这个函数封装了借书操作
	 *  		包括：
	 *  			 AllBooks gotBook = gotBooks(bookId);  获取要借的书的信息
	 *				 int deduceOk = bookDeduce(bookId);    从书库中数量减1
	 *		         int addOk= addGotBook(gotBook);	      将书籍加入借阅的表中
	 *		         
	 *  6.	returnBookAction(String bookId) ：还书操作的封装
	 *  		包括：
			       int a = borrwoedDeduce(bookId);    将归还的书从已借表中减1
			       int b =returnBookAdd(bookId);      将归还的书从已书库表中加1	
	    7. getReaderInfor： 用于返回读者个人信息
	    8. updateReaderInfor： 用于更新个人信息
	 */
	
	String readerId;
	public ReaderAction(String readerId) {
		this.readerId = readerId;
	}

	public List<AllBooks> getBooks() {
		Connection conn = DatabaseConfig.getConnection();
		String sql = "SELECT * FROM tb_books ";						
		List<AllBooks> ab = new ArrayList<AllBooks>();  // 用List来存放书库数据
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
			DatabaseConfig.close(conn, ps);  // 关闭连接

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ab;
	}
	
	// 根据bookId查询并返回书籍
	public AllBooks gotBooks(String bookId) {
		AllBooks gotbook = new AllBooks();  // 用于暂时存储从书库中取的书
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
		// 关闭连接
			DatabaseConfig.close(conn, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return gotbook;
    }
	
	// 根据书名查询并返回书籍
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
	
	
	// 将GotBook添加到tb_borrowing
	public int addGotBook(AllBooks gotbook) {
		Connection conn = DatabaseConfig.getConnection();
		// 时间测试
		Date date = new Date();  
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd "); // 设置日期格式
		String nowString = df.format(date).toString();       // 转成字符串		
		Calendar calendar = Calendar.getInstance();          // 日历对象
		calendar.setTime(date);                              // 设置当前日期
		calendar.add(Calendar.DAY_OF_MONTH, +21);            // 日期+21
		String nextString = df.format(calendar.getTime()).toString();
		
		try {				
			// 将书库中的书放入正在借阅的数 tb_borrowing	
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
		// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return 0;
	}
	
	// 将借出的书从tb_books减1
	public int bookDeduce(String bookId) {	
		Connection conn = DatabaseConfig.getConnection();		
		try {		
			// 修改书库中书的数量，减1
					String sql = "UPDATE tb_books "
										 + "   SET book_num = book_num - 1"
										 + " WHERE book_id = '"+bookId+"'";
			PreparedStatement sqlAddBookS =conn.prepareStatement(sql);
			int res= sqlAddBookS.executeUpdate();
			return res;
		// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return 0;
	}
	
	// 封装借书的函数
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
	
	  // 将还出的书从tb_borrwoing删除
		public int borrwoedDeduce(String bookId) {	
			Connection conn = DatabaseConfig.getConnection();		
			try {		
				// 修改书库中书的数量，减1
						String sql = "DELETE FROM  tb_borrowing "
								   + "WHERE book_id = '"+bookId+"'";		 
				PreparedStatement sqlAddBookS =conn.prepareStatement(sql);
				int res= sqlAddBookS.executeUpdate();
				return res;
			// 关闭连接
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			return 0;
		}
		
		
		// 将借出的书在书库中加1
		public int returnBookAdd(String bookId) {	
			Connection conn = DatabaseConfig.getConnection();		
			try {		
				// 修改书库中书的数量，减1
						String sql = "UPDATE tb_books "
											 + "   SET book_num = book_num + 1"
											 + " WHERE book_id = '"+bookId+"'";
				PreparedStatement sqlAddBookS =conn.prepareStatement(sql);
				int res= sqlAddBookS.executeUpdate();
				return res;
			// 关闭连接
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			return 0;
		}
	
		// 还书动作的封装
		public int returnBookAction(String bookId) {
			int a = borrwoedDeduce(bookId);  // 借阅表书本减1
			int b =returnBookAdd(bookId);    // 书库书本加1
			if (a != 0 && b!=0) {
				return 1;
			}
			return 0;
		}
		
	// 根据已借阅的书的id查询并返回书籍
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
		
	  // 获取个人数据
		public ReaderInfor getReaderInfor() {
			ReaderInfor readerInfor = new ReaderInfor();  // 用于暂时存储从书库中取的书
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
			// 关闭连接
				DatabaseConfig.close(conn, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			return readerInfor;
	    }
	// 修改个人数据
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
		// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return 0;
	}
}
	
	
	 