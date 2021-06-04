package com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.database.ReaderAction;
import com.entity.ReaderInfor;

public class ReaderMessage {
	/**
	 * 这个类用于读者查看与修改“个人信息”
	 * 模块： 1. 修改信息： updateInfor();
	 * 		 2. 显示“个人信息" : showMessage();
	 */
	private String readerId;
	private JFrame f;
	private JLabel idJLabel;
	private JTextField nameField;
	private JTextField idField;
	private JLabel sexJLabel;
	private JTextField sexField;
	private JButton goBcakButton;
	private JPanel allJPanel;
	private JButton keepButton;
	private JTextField pwField;
	public  ReaderMessage(String readerId) {
		this.readerId = readerId;
	}

	public void showMessage() {
		
		// 换皮肤
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		try
	    {
	        // 改变窗口边框样式定义
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
		javax.swing.UIManager.put("RootPane.setupButtonVisible", false);
		
		// 根据readerId获取读者对象信息
		ReaderInfor infor = new ReaderInfor();
		infor = new ReaderAction(readerId).getReaderInfor();

		// 显示读者信息
		f = new JFrame("读者信息");
		f.setBounds(400, 100, 700, 650);
		f.setLayout(null);
		f.setResizable(false);
		
		allJPanel = new JPanel();
		allJPanel.setLayout(null);
		allJPanel.setBounds(0, 0, f.getWidth(), f.getHeight());
		
		idJLabel = new JLabel("借阅号:");
		idJLabel.setBounds(160, 160, 200, 40);
		idJLabel.setFont(new Font("Dialog", 1, 30));
		allJPanel.add(idJLabel);
		
		idField = new JTextField();
		idField.setText(infor.getreaderId());
		idField.setBounds(310, 160, 200, 40);
		idField.setFont(new Font("Dialog", 1,25));
		
		allJPanel.add(idField);
		
		JLabel nameJLabel = new JLabel("姓名:");
		nameJLabel.setBounds(160, 230, 200, 40);
		nameJLabel.setFont(new Font("Dialog", 1,30));
		allJPanel.add(nameJLabel);		
		nameField = new JTextField();
		nameField.setText(infor.getreaderName());
		nameField.setBounds(310, 230, 200, 40);
		nameField.setFont(new Font("Dialog", 1,25));
		allJPanel.add(nameField);
				
		sexJLabel = new JLabel("性别:");
		sexJLabel.setBounds(160, 300, 200, 40);
		sexJLabel.setFont(new Font("Dialog", 1, 30));	
		allJPanel.add(sexJLabel);
		sexField = new JTextField();
		sexField.setText(infor.getreaderSex());
		sexField.setBounds(310, 300, 200, 40);
		sexField.setFont(new Font("Dialog", 1,25));
		allJPanel.add(sexField);
		
		JLabel pwJLabel = new JLabel("密码:");
		pwJLabel.setBounds(160, 370, 200, 40);
		pwJLabel.setFont(new Font("Dialog", 1, 30));		
		allJPanel.add(pwJLabel);
		
		pwField = new JTextField();
		pwField.setText(infor.getreaderPassword());
		pwField.setBounds(310, 370, 200, 40);
		pwField.setFont(new Font("Dialog", 1,25));
		allJPanel.add(pwField);
		
		
		goBcakButton = new JButton("返回");
		goBcakButton.setFont(new Font("Dialog",1,25));
		goBcakButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		goBcakButton.setBounds(350, 450, 120, 40);	
		allJPanel.add(goBcakButton);
		
		keepButton = new JButton("更新");
		keepButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		keepButton.setFont(new Font("Dialog",1,25));
		keepButton.setBounds(180, 450, 120, 40);	
		allJPanel.add(keepButton);
		
	   // 背景
		JLabel bgJLabel = new JLabel(new ImageIcon("image/个人信息界面.jpg"));
		bgJLabel.setBounds(0, 0,allJPanel.getWidth(), allJPanel.getHeight());
		allJPanel.add(bgJLabel);
		
		// “退出”与“更新”的事件监听
		goBcakButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		
		keepButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateInfor();
			}
		});			
		f.add(allJPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void updateInfor() {
		/**
		 * 这个方法用于更新读者信息
		 */
		String sex = sexField.getText();
		String name = nameField.getText();
		String id = idField.getText();
		String pw = pwField.getText();
		
		ReaderInfor readerInfor = new ReaderInfor();
		readerInfor.setreaderId(id);
		readerInfor.setreaderName(name);
		readerInfor.setreaderSex(sex);
		readerInfor.setreaderPassword(pw);
		
		int x = new ReaderAction(readerId).updateReaderInfor(readerInfor);
		if (x > 0) {
			JOptionPane.showMessageDialog(f, "更新成功！", "提示", JOptionPane.WARNING_MESSAGE);
		}
		else {
		JOptionPane.showMessageDialog(f, "更新失败！", "提示", JOptionPane.WARNING_MESSAGE);
		}
		f.dispose();
	}
	
}
