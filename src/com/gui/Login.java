package com.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.database.LoginAction;
import com.entity.AdminInfor;
import com.entity.ReaderInfor;

public class Login implements ActionListener {
	/**
	 * 这个是登录的类，继承了ActionListener接口用于实现监听
	 * 功能：登录的界面窗口，可以选择读者登录或管理员登录
	 */
	private JFrame jf;
	private JPanel firstPanel;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField userTextField;
	private JButton loginButton;
	private JButton exitButton;
	private JLabel background1;
	private JPasswordField passwordField;
	private JComboBox<String> identityBox;
	private JLabel identityJLabel;

	public void showLogin() {
		/*
		 * <p>这个函数用于显示登录界面</P> <p>采用的是绝对布局的方式</p>
		 */

		// 导入第三方包，更换系统皮肤
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}		 
		try
	    {
	        //设置本属性将改变窗口边框样式定义
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
		javax.swing.UIManager.put("RootPane.setupButtonVisible", false);

		/******************* 设置界面*******************/	
		jf = new JFrame("");
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setBounds(400, 100, 1000, 780);
		firstPanel = new JPanel();
		jf.setContentPane(firstPanel);
		firstPanel.setLayout(null); // 采用绝对定位，此句不能少
		firstPanel.setBounds(0, 0, jf.getWidth(), jf.getHeight());

		// 用户名
		userLabel = new JLabel("用户名");
		userLabel.setFont(new Font("宋体", Font.BOLD, 20));
		userLabel.setBounds(350, 300, 80, 20);
		userLabel.setForeground(Color.BLUE);
		firstPanel.add(userLabel);

		// 密码
		passwordLabel = new JLabel("密码");
		passwordLabel.setFont(new Font("宋体", Font.BOLD, 20));
		passwordLabel.setForeground(Color.BLUE);
		passwordLabel.setBounds(350, 350, 80, 20);
		firstPanel.add(passwordLabel);

		// 用户名文本框
		userTextField = new JTextField();
		userTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
		userTextField.setBounds(450, 300, 167, 22);
		firstPanel.add(userTextField);
		userTextField.setColumns(10);

		// 密码文本框
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 15));
		passwordField.setBounds(450, 350, 167, 22);
		firstPanel.add(passwordField);

		// 身份
		identityJLabel = new JLabel("身份");
		identityJLabel.setBounds(350, 400, 50, 30);
		identityJLabel.setFont(new Font("宋体", Font.BOLD, 20));
		identityJLabel.setForeground(Color.blue);
		firstPanel.add(identityJLabel);

		// 身份下拉框
		String identity[] = new String[] { "管理员", "读者" };
		identityBox = new JComboBox<String>(identity);
		identityBox.setBounds(450, 400, 167, 30);
		firstPanel.add(identityBox);

		// 登录按钮
		loginButton = new JButton("登录");
		loginButton.setFont(new Font("Dialog", Font.BOLD, 17));
		loginButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		loginButton.setForeground(Color.white);
		loginButton.setBounds(360, 480, 98, 36);
		firstPanel.add(loginButton);
		loginButton.addActionListener(this);
		loginButton.setActionCommand("login");

		// 退出按钮与监听
		exitButton = new JButton("退出");
		exitButton.setFont(new Font("Dialog", Font.BOLD, 17));
		exitButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		exitButton.setForeground(Color.white);
		exitButton.setBounds(520, 480, 98, 36);
		firstPanel.add(exitButton);
		exitButton.addActionListener(this);
		exitButton.setActionCommand("exit");

		// 设置背景
		background1 = new JLabel(new ImageIcon("image/登录.jpg"));
		background1.setBounds(0, 0, firstPanel.getWidth(), firstPanel.getHeight());
		firstPanel.add(background1);

		jf.setVisible(true);
			
	}

	/*******************事件监听 *******************/
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("exit")) {  // 退出
			jf.setVisible(false);
		}

		if (e.getActionCommand().equals("login")) { // 登录
			loginButton();
		}

	}

	public void loginButton() {
		/**
		 * 这个是登录按钮的事件的内容，将其写成一个函数，在登录按钮的监听中直接调用
		 */
		String identity = (String) identityBox.getSelectedItem();  // 身份
		// 账号
		String userString = userTextField.getText().toString();
		// 密码
		@SuppressWarnings("deprecation")
		String passwordString = passwordField.getText().toString();
		LoginAction loginAction = new LoginAction(userString, identity);

		if ("读者".equals(identity)) {  // 读者登录
			if (userString != null && !"".equals(userString)) {  // 账号不为空
				ReaderInfor readerInfor = new ReaderInfor();
				readerInfor = loginAction.getPassword();  // 到数据库获取密码
				if (!"".equals(readerInfor.getreaderId()) && readerInfor.getreaderId() != null
						&& readerInfor.getreaderPassword().equals(passwordString)) {
					jf.dispose();
					new ReaderMenu(userString); // 去读者界面,同时传递读者的id

				} else {
					JOptionPane.showMessageDialog(
							jf, "账号密码错误,请重新登录！",
							"提示", JOptionPane.WARNING_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(
						jf, "账号不能为空", 
						"提示", JOptionPane.WARNING_MESSAGE);
			}
		}else {    // 管理员登录
			if (userString != null && !"".equals(userString)) {
				AdminInfor adminInfor =new AdminInfor();
				adminInfor = loginAction.getAdminPassword();  // 到数据库获取密码
				if (!"".equals(adminInfor.getadminId()) && adminInfor.getadminId() != null
						&& adminInfor.getadminPassword().equals(passwordString)) {
					jf.dispose();
					new AdminMenu(adminInfor.getadminId());				

				} else {
					JOptionPane.showMessageDialog(
							jf, "账号密码错误,请重新登录！", "提示", JOptionPane.WARNING_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(jf, "账号不能为空",
						"提示", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

}
