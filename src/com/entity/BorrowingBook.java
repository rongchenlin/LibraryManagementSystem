package com.entity;
public class BorrowingBook {
	/**
	 * 这个类是用于封装已经借阅的书籍的信息，对应了数据库中的表tb_borrowing
	 * 解释：	
	 * 			类中的属性:       tb_borrowing表的字段:
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
	
	// 下面的set方法适用于对实体Allbooks进行赋值
	
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
	

	
	// 下面的get方法是对实体进行取值
	
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
