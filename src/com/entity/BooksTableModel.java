package com.entity;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.database.*;
public class BooksTableModel extends AbstractTableModel {
	/**
	 * 这个类是书库信息的表格模型，继承了 AbstractTableModel类
	 * 功能：通过重写 AbstractTableModel的一些方法，实现将数据在Jtable上显示
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = new String[] { "书籍编号", "书籍名称", "剩余数量", "价格" ,"类别"};
	private List<AllBooks> allList;
	private String readerId = "";
	private String bookName = "";
	
	public BooksTableModel(String readerId) {
		this.readerId = readerId;
		ReaderAction readerAction = new ReaderAction(readerId);
		allList = readerAction.getBooks();		
	}
	
	// 重载构造方法，方便后面的图书按书名查询操作
	public BooksTableModel (String readerId,String bookName) {
		this.readerId = readerId;
		this.bookName = bookName;
		ReaderAction readerAction = new ReaderAction(readerId);
		allList = readerAction.searchBooks(bookName);
	}	
	// 返回一共有多少行
	public int getRowCount() {
		// TODO Auto-generated method stub
		return allList.size();
	}

	public int getColumnCount() {
		
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		/**
		 * 返回相应行的数据，并将其保存在模型AllBooks中
		 */
		AllBooks allBooks = allList.get(rowIndex);	
		if (0 == columnIndex)
			return allBooks.getbookId();
		if (1 == columnIndex)
			return allBooks.getbookName();
		if (2 == columnIndex)
			return allBooks.getbookNum();
		if (3 == columnIndex)
			return allBooks.getbookPrice();
		if (4 == columnIndex)
			return allBooks.getbookType();
		return null;
	}

}
