package com.entity;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.database.*;

public class ReaderTableModel extends AbstractTableModel {
	/**
	 * ������Ƕ�����Ϣ�ı��ģ�ͣ��̳��� AbstractTableModel��
	 * ���ܣ�ͨ����д AbstractTableModel��һЩ������ʵ�ֽ�������Jtable����ʾ
	 */
	private static final long serialVersionUID = 1L;
	String[] columnNames = new String[] { "���߱��", "��������", "��     ��" ,"��     ��"};
	List<ReaderInfor> allList;
	String readerId = "";
	String readerName = "";
	
	public ReaderTableModel(String readerId) {
		this.readerId = readerId;
		AdminAction adminAction = new AdminAction();
		allList = adminAction.getReaderInfor();
	}
	
	public ReaderTableModel(String readerId,String readerName) {
		this.readerId = readerId;
		this.readerName = readerName;
		AdminAction adminAction = new AdminAction();
		allList = adminAction.searchReaders(readerName);
	}
		
	// ����һ���ж�����
	public int getRowCount() {
		return allList.size();
	}

	public int getColumnCount() {
		
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
		ReaderInfor readerInfor = allList.get(rowIndex);
		
		if (0 == columnIndex)
			return readerInfor.getreaderId();
		if (1 == columnIndex)
			return readerInfor.getreaderName();
		if (2 == columnIndex)
			return readerInfor.getreaderPassword();
		if (3 == columnIndex)
			return readerInfor.getreaderSex();	
		return null;
	}

}
