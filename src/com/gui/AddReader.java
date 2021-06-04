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
import com.database.AdminAction;
import com.entity.ReaderInfor;

public class AddReader {
	/**
	 * 这个类用于管理员注册读者信息
	 */
	
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
	public void showMessage() {
		
		/******************* 换皮肤 *******************/
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

		/******************* 设置界面 *******************/
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
		idField.setText("");
		idField.setBounds(310, 160, 200, 40);
		idField.setFont(new Font("Dialog", 1,25));
		
		allJPanel.add(idField);
		
		JLabel nameJLabel = new JLabel("姓名:");
		nameJLabel.setBounds(160, 230, 200, 40);
		nameJLabel.setFont(new Font("Dialog", 1,30));
		allJPanel.add(nameJLabel);
		
		nameField = new JTextField();
		nameField.setText("");
		nameField.setBounds(310, 230, 200, 40);
		nameField.setFont(new Font("Dialog", 1,25));
		allJPanel.add(nameField);
				
		sexJLabel = new JLabel("性别:");
		sexJLabel.setBounds(160, 300, 200, 40);
		sexJLabel.setFont(new Font("Dialog", 1, 30));
		
		allJPanel.add(sexJLabel);
		
		sexField = new JTextField();
		sexField.setText("");
		sexField.setBounds(310, 300, 200, 40);
		sexField.setFont(new Font("Dialog", 1,25));
		allJPanel.add(sexField);
		
		JLabel pwJLabel = new JLabel("密码:");
		pwJLabel.setBounds(160, 370, 200, 40);
		pwJLabel.setFont(new Font("Dialog", 1, 30));		
		allJPanel.add(pwJLabel);
		
	    pwField = new JTextField();
		pwField.setText("");
		pwField.setBounds(310, 370, 200, 40);
		pwField.setFont(new Font("Dialog", 1,25));
		allJPanel.add(pwField);
				
		goBcakButton = new JButton("返回");
		goBcakButton.setFont(new Font("Dialog",1,25));
		goBcakButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		goBcakButton.setBounds(350, 450, 120, 40);	
		allJPanel.add(goBcakButton);
		
		keepButton = new JButton("添加");
		keepButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		keepButton.setFont(new Font("Dialog",1,25));
		keepButton.setBounds(180, 450, 120, 40);	
		allJPanel.add(keepButton);
		
	   //	背景
		JLabel bgJLabel = new JLabel(new ImageIcon("image/个人信息界面.jpg"));
		bgJLabel.setBounds(0, 0,allJPanel.getWidth(), allJPanel.getHeight());
		allJPanel.add(bgJLabel);
		
		/******************* 事件监听 *******************/	
		goBcakButton.addActionListener(new ActionListener() {
			// 返回按钮
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		
		keepButton.addActionListener(new ActionListener() {
			// 增加读者信息
			public void actionPerformed(ActionEvent e) {
				addReaderInfor();
			}
		});
					
		f.add(allJPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void addReaderInfor() {
	
		String sex = sexField.getText();
		String name = nameField.getText();
		String id = idField.getText();
		String pwString = pwField.getText();
	
		
		ReaderInfor readerInfor = new ReaderInfor();
		readerInfor.setreaderId(id);
		readerInfor.setreaderName(name);
		readerInfor.setreaderSex(sex);
		readerInfor.setreaderPassword(pwString);
			
		int x = new AdminAction().addReader(readerInfor);
		if (x > 0) {
			JOptionPane.showMessageDialog(f, "添加成功！", "提示", JOptionPane.WARNING_MESSAGE);
		}
		else {
		JOptionPane.showMessageDialog(f, "添加失败！", "提示", JOptionPane.WARNING_MESSAGE);
		}
		f.dispose();
	}
	
}
