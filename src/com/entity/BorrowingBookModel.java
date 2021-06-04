package com.entity;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.database.*;

public class BorrowingBookModel extends AbstractTableModel {
	/**
	 * 这个类是读者已借阅书籍的表格模型，继承了 AbstractTableModel类
	 * 功能：通过重写 AbstractTableModel的一些方法，实现将数据在Jtable上显示
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = new String[] { "图书编号", "图书名称",  "图书价格" ,"图书类型","借阅时间","应还时间"};
	private List<BorrowingBook> allList;
	private String readerId = "";
	private String bookName = "";
	
	public BorrowingBookModel(String readerId) {
		this.readerId = readerId;
		ReaderAction readerAction = new ReaderAction(readerId);
		allList = readerAction.getBorrowedBooks();		
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
		
		BorrowingBook allBorrowingBooks = allList.get(rowIndex);	
		if (0 == columnIndex)
			return allBorrowingBooks.getbookId();
		if (1 == columnIndex)
			return allBorrowingBooks.getbookName();		
		if (2 == columnIndex)
			return allBorrowingBooks.getbookPrice();
		if (3 == columnIndex)
			return allBorrowingBooks.getbookType();
		if (4 == columnIndex)
			return allBorrowingBooks.getborrowTime();
		if (5 == columnIndex)
			return allBorrowingBooks.getneedReturnTime();
		return null;
	}

}
