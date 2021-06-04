package com.entity;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.database.*;
public class BooksTableModel extends AbstractTableModel {
	/**
	 * ������������Ϣ�ı��ģ�ͣ��̳��� AbstractTableModel��
	 * ���ܣ�ͨ����д AbstractTableModel��һЩ������ʵ�ֽ�������Jtable����ʾ
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = new String[] { "�鼮���", "�鼮����", "ʣ������", "�۸�" ,"���"};
	private List<AllBooks> allList;
	private String readerId = "";
	private String bookName = "";
	
	public BooksTableModel(String readerId) {
		this.readerId = readerId;
		ReaderAction readerAction = new ReaderAction(readerId);
		allList = readerAction.getBooks();		
	}
	
	// ���ع��췽������������ͼ�鰴������ѯ����
	public BooksTableModel (String readerId,String bookName) {
		this.readerId = readerId;
		this.bookName = bookName;
		ReaderAction readerAction = new ReaderAction(readerId);
		allList = readerAction.searchBooks(bookName);
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
		/**
		 * ������Ӧ�е����ݣ������䱣����ģ��AllBooks��
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
