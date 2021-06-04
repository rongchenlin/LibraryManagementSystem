package com.entity;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.database.*;

public class BorrowingBookModel extends AbstractTableModel {
	/**
	 * ������Ƕ����ѽ����鼮�ı��ģ�ͣ��̳��� AbstractTableModel��
	 * ���ܣ�ͨ����д AbstractTableModel��һЩ������ʵ�ֽ�������Jtable����ʾ
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = new String[] { "ͼ����", "ͼ������",  "ͼ��۸�" ,"ͼ������","����ʱ��","Ӧ��ʱ��"};
	private List<BorrowingBook> allList;
	private String readerId = "";
	private String bookName = "";
	
	public BorrowingBookModel(String readerId) {
		this.readerId = readerId;
		ReaderAction readerAction = new ReaderAction(readerId);
		allList = readerAction.getBorrowedBooks();		
	}
	
	// ����һ���ж�����
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
