package com.entity;

public class AdminInfor {
	/**
	 * ����������ڷ�װ����Ա��Ϣ�ģ���Ӧ�����ݿ��еı�tb_admin
	 * ���ͣ�	
	 * 			���е�����:             tb_books����ֶ�:
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
