package com.entity;

public class AllBooks {
	/**
	 * ����������ڷ�װ�����Ϣ�ģ���Ӧ�����ݿ��еı�tb_books
	 * ���ͣ�	
	 * 			���е�����:       tb_books����ֶ�:
	 * 			bookId    ->     book_id
	 * 			bookName  ->     book_name
	 * 			bookPrice ->     book_price
	 * 			bookNum   ->     book_num
	 * 			bookType  ->     book_type
	 */
	private String bookId;		// �鼮Id
	private String bookName;	// �鼮����
	private String bookType;	// �鼮����
	private String bookNum;		// �鼮ʣ������
	private String bookPrice;	// �鼮�۸�
		
	// �����set���������ڶ�ģ��Allbooks���и�ֵ
		 	
	public void setbookId(String bookId) {
		this.bookId = bookId;
	}
	
	public void setbookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void setbookType(String bookType) {
		this.bookType = bookType;
	}
	
	public void setbookNum(String   bookNum) {
		this.bookNum = bookNum;
	}
	
	public void setbookPrice(String  bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	// �����get�����Ƕ�ʵ�����ȡֵ
	
	public String getbookId() {
		return bookId;
	}
	
	public String getbookName() {
		return bookName;
	}
	
	public String  getbookNum() {
		return bookNum;
	}
	
	public String  getbookPrice() {
		return bookPrice;
	}
	
	public String getbookType() {
		return bookType;
	}
	
}
