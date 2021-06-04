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
import com.database.ReaderAction;
import com.entity.ReaderInfor;

public class ReaderMenu {
	/**
	 * 这个类是读者操作界面，包括“个人信息”、“借书”、“还书”、“退出”按钮与事件监听
	 */
	private String readerId;
	private JPanel readerPane;
	private JFrame jf;

	public ReaderMenu(String readerId) {

		this.readerId = readerId;
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
		
		/**********************界面**************************/
		jf = new JFrame("用户界面");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setBounds(400, 100, 1000, 780);
		readerPane = new JPanel();
		readerPane.setLayout(null);
		readerPane.setBounds(0, 0, jf.getWidth(), jf.getHeight());
		jf.add(readerPane);
		jf.setResizable(false);
		
		ReaderInfor infor = new ReaderInfor();
		infor = new ReaderAction(readerId).getReaderInfor();
		JLabel welcomeJLabel = new JLabel("欢迎您！"+" "+infor.getreaderName());
		welcomeJLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		welcomeJLabel.setBounds(0, 0, 300, 30);
		readerPane.add(welcomeJLabel);
		
		// “个人信息”按钮与事件监听
		JButton inforButton = new JButton("个人信息");
		inforButton.setFont(new Font("Dialog", Font.BOLD, 28));
		inforButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		inforButton.setForeground(Color.white);
		inforButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReaderMessage(readerId).showMessage();

			}
		});
		inforButton.setBounds(160, 180, 160, 60);
		readerPane.add(inforButton);

		// “借阅”按钮与事件监听
		JButton borrowButton = new JButton("借阅");
		borrowButton.setFont(new Font("Dialog", Font.BOLD, 28));
		borrowButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		borrowButton.setForeground(Color.white);
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BorrowBooks(readerId).dispalyBooks(); // 将读者id传过去，用于借阅
			}
		});
		borrowButton.setBounds(160, 270, 160, 60);
		readerPane.add(borrowButton);

		// “还书”按钮与事件监听
		JButton returnButton = new JButton("还书");
		returnButton.setFont(new Font("Dialog", Font.BOLD, 28));
		returnButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		returnButton.setForeground(Color.white);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReturnBooks(readerId).dispalyBooks();
			}
		});
		returnButton.setBounds(160, 360, 160, 60);
		readerPane.add(returnButton);

		// “退出”按钮与事件监听
		JButton outButton = new JButton("退出");
		outButton.setFont(new Font("Dialog", Font.BOLD, 28));
		outButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		outButton.setForeground(Color.white);
		outButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				jf.dispose();
			}
		});
		outButton.setBounds(160, 450, 160, 60);
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

		// 设置背景
		JLabel background1 = new JLabel(new ImageIcon("image/读者界面.jpg"));
		background1.setBounds(0, 0, readerPane.getWidth(), readerPane.getHeight());
		readerPane.add(background1);
		jf.setVisible(true);
	}
}
