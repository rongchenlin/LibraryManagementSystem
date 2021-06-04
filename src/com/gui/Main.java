package com.gui;

/**
 * 介绍： 	
 * 		这是一个以Java作为开发语言的图书管理系统，主要使用的是
 * 		Swing、JDBC、多线程、集合类进行程序设计。实现了下面的功能：
 * 		读者和管理员的登录
 * 		读者端：  
 * 			1. 查看、修改个人信息
 * 			2. 查看、搜索、借阅、归还图书
 * 		管理员端：
 * 			1. 注册、管理读者信息
 * 			2. 新增、查看、搜索、修改图书信息
 * 功能：        
 * 		 实现用户登录程序
 * @author 林镕琛
 * @date 2020年06月12日
 * @version 1.0.0
 */
public class Main {
	/**
	 * 这是本程序的入口处，即进入登录界面
	 * @param args
	 */
	public static void main(String[] args) {
		new Login().showLogin();
	}
}
