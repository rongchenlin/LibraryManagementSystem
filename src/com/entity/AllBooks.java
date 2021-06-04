package com.entity;

public class AllBooks {
	/**
	 * 这个类是用于封装书库信息的，对应了数据库中的表tb_books
	 * 解释：	
	 * 			类中的属性:       tb_books表的字段:
	 * 			bookId    ->     book_id
	 * 			bookName  ->     book_name
	 * 			bookPrice ->     book_price
	 * 			bookNum   ->     book_num
	 * 			bookType  ->     book_type
	 */
	private String bookId;		// 书籍Id
	private String bookName;	// 书籍名称
	private String bookType;	// 书籍类型
	private String bookNum;		// 书籍剩余数量
	private String bookPrice;	// 书籍价格
		
	// 下面的set方法适用于对模型Allbooks进行赋值
		 	
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
	
	// 下面的get方法是对实体进行取值
	
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
