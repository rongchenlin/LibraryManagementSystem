package com.entity;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.database.*;

public class ReaderTableModel extends AbstractTableModel {
	/**
	 * 这个类是读者信息的表格模型，继承了 AbstractTableModel类
	 * 功能：通过重写 AbstractTableModel的一些方法，实现将数据在Jtable上显示
	 */
	private static final long serialVersionUID = 1L;
	String[] columnNames = new String[] { "读者编号", "读者姓名", "密     码" ,"性     别"};
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
		
	// 返回一共有多少行
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
