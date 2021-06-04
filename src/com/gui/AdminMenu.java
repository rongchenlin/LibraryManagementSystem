package com.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jb2011.lnf.beautyeye.*;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import com.database.AdminAction;
import com.entity.AdminInfor;

public class AdminMenu {
	/**
	 * 这个类用于管理员进行操作的界面
	 * 操作界面包括：
	 * 		1. 注册读者
	 * 		2. 修改读者信息
	 * 		3. 新增图书
	 * 		4. 修改图书
	 */
    private String adminId;
	private JPanel readerPane;
	private JFrame jf;

	public AdminMenu(String adminId) {

		this.adminId = adminId;
		
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
		jf = new JFrame("用户界面");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setBounds(400, 100, 1000, 780);
		readerPane = new JPanel();
		readerPane.setLayout(null);
		readerPane.setBounds(0, 0, jf.getWidth(), jf.getHeight());
		jf.add(readerPane);
		jf.setResizable(false);
		
		AdminInfor infor = new AdminInfor();
		infor = new AdminAction().getAdminInfor(adminId);
		JLabel welcomeJLabel = new JLabel("欢迎您！"+" "+infor.getadminName());
		welcomeJLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		welcomeJLabel.setBounds(0, 0, 300, 30);
		readerPane.add(welcomeJLabel);
		
		// 注册读者的按钮与事件
		JButton inforButton = new JButton("读者注册");
		inforButton.setFont(new Font("Dialog", Font.BOLD, 28));
		inforButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		inforButton.setForeground(Color.white);
		inforButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddReader().showMessage();
			}
		});
		inforButton.setBounds(160, 180, 160, 60);
		readerPane.add(inforButton);

		// 管理读者信息的按钮与事件
		JButton mangeButton = new JButton("读者管理");
		mangeButton.setFont(new Font("Dialog", Font.BOLD, 28));
		mangeButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		mangeButton.setForeground(Color.white);
		mangeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ReaderManage().dispalyreaders(); 
			}
		});
		mangeButton.setBounds(160, 270, 160, 60);
		readerPane.add(mangeButton);

		// 新增书籍的按钮与事件
		JButton returnButton = new JButton("新增图书");
		returnButton.setFont(new Font("Dialog", Font.BOLD, 28));
		returnButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		returnButton.setForeground(Color.white);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddBooks().dispalyBooks();
			}
		});
		returnButton.setBounds(160, 360, 160, 60);
		readerPane.add(returnButton);
		
		// 修改图书的按钮与事件
		JButton updateButton = new JButton("图书修改");
		updateButton.setFont(new Font("Dialog", Font.BOLD, 28));
		updateButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		updateButton.setForeground(Color.white);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateBook().dispalyBooks();
			}
		});
		updateButton.setBounds(160, 450, 160, 60);
		readerPane.add(updateButton);

		// 退出的按钮与事件
		JButton outButton = new JButton("退出");
		outButton.setFont(new Font("Dialog", Font.BOLD, 28));
		outButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		outButton.setForeground(Color.white);
		outButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				jf.dispose();
			}
		});
		outButton.setBounds(160, 540, 160, 60);
		readerPane.add(outButton);

		// 使用多线程，显示时间
		JLabel timeJLabel = new JLabel();
		timeJLabel.setFont(new Font("微软雅黑", Font.BOLD, 28));
		Timer timeAction = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long timemillis = System.currentTimeMillis();
				// 转换日期显示格式
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				timeJLabel.setText(df.format(new Date(timemillis)));
			}
		});
		timeAction.start();
		timeJLabel.setBounds(680, 0, 300, 30);
		readerPane.add(timeJLabel);

		// 背景
		JLabel background1 = new JLabel(new ImageIcon("image/管理员操作界面.jpg"));
		background1.setBounds(0, 0, readerPane.getWidth(), readerPane.getHeight());
		readerPane.add(background1);

		jf.setVisible(true);
	}
}
