package com.entity;
public class BorrowingBook {
	/**
	 * ����������ڷ�װ�Ѿ����ĵ��鼮����Ϣ����Ӧ�����ݿ��еı�tb_borrowing
	 * ���ͣ�	
	 * 			���е�����:       tb_borrowing����ֶ�:
	 * 			bookId    ->     book_id
	 * 			bookName  ->     book_name
	 * 			bookPrice ->     book_price
	 * 			readerId  ->     reader_id
	 * 			bookType  ->     book_type
	 * 			borrowTime  ->   borrow_time
	 * 			needReturnTime -> need_return_time    
	 */
	
	private String bookId;
	private String bookName;
	private String bookType;
	private float bookPrice;
	private String readerId;
	private String borrowTime;
	private String needReturnTime;
	
	// �����set���������ڶ�ʵ��Allbooks���и�ֵ
	
	public void setreaderId(String readerId) {
		this.readerId = readerId;
	}
	 	
	public void setbookId(String bookId) {
		this.bookId = bookId;
	}
	
	public void setbookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void setbookType(String bookType) {
		this.bookType = bookType;
	}
	
	
	public void setbookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	public void setborrowTime(String  borrowTime) {
		this.borrowTime = borrowTime;
	}
	
	public void setneedReturnTime(String needReturnTime) {
		this.needReturnTime = needReturnTime;
	}
	

	
	// �����get�����Ƕ�ʵ�����ȡֵ
	
	public String getbookId() {
		return bookId;
	}
	
	public String getbookName() {
		return bookName;
	}
	
	public String getreadId() {
		return readerId;
	}
	
	public float getbookPrice() {
		return bookPrice;
	}
	
	public String getbookType() {
		return bookType;
	}
	
	public String getborrowTime() {
		return borrowTime;
	}
	
	public String getneedReturnTime() {
		return needReturnTime;
	}	
}
