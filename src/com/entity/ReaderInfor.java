package com.entity;

public class ReaderInfor {
	/**
	 * 这个类是用于封装读者个人的信息，对应了数据库中的表tb_reader
	 * 解释：	
	 * 			类中的属性:       	   tb_reader表的字段:
	 * 			readerId  		->     reader_id
	 * 			raderName 		->     reader_name
	 * 			readerSex 		->     reader_sex
	 * 			readerPassword  ->     reader_password
	 */
	private String readerId;
	private String readerName;
	private String readerPassword;
	private String readerSex;

	public void setreaderId(String readerId) {
		
		this.readerId = readerId;
	}
	
	public void setreaderName(String readerName) {
		this.readerName = readerName;
	}
	
	public void setreaderPassword(String readerPassword) {
		this.readerPassword = readerPassword;
	}
	
	public void setreaderSex(String readerSex) {
		this.readerSex = readerSex;
	}
	
	// get
	public String getreaderId() {
		return readerId;
	}
	
	public String getreaderName() {
		return readerName;
	}
	
	public String getreaderPassword() {
		return readerPassword;
	}
	
	public String getreaderSex() {
		return readerSex;
	}
		
}
