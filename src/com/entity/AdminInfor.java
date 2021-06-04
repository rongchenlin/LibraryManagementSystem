package com.entity;

public class AdminInfor {
	/**
	 * 这个类是用于封装管理员信息的，对应了数据库中的表tb_admin
	 * 解释：	
	 * 			类中的属性:             tb_books表的字段:
	 * 			adminId    		->     admin_id
	 * 			adminName  		->     admin_name
	 * 			adminPassword   ->     admin_password
	 * 			adminSex 		->     admin_sex
	 */
	private String adminId;
	private String adminName;
	private String adminPassword;
	private String adminSex;
	
	public void setadminId(String adminId) {
		
		this.adminId = adminId;
	}
	
	public void setadminName(String adminName) {
		this.adminName = adminName;
	}
	
	public void setadminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	public void setadminSex(String adminSex) {
		this.adminSex = adminSex;
	}
	
	// get
	
	public String getadminId() {
		return adminId;
	}
	
	public String getadminName() {
		return adminName;
	}
	
	public String getadminPassword() {
		return adminPassword;
	}
	
	public String getadminSex() {
		return adminSex;
	}
		
}
