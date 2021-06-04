package com.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import com.database.*;
import com.entity.*;

public class ReturnBooks {
	/**
	 * 这个类用于读者还书
	 */
	private String readerId;
	private JFrame f;
	private JPanel northPanel;
	private JLabel tipJLabel;
	private JLabel norbgJLabel;
	private JPanel southPanel;
	private JLabel soubgJLabel;
	private JButton returnButton;
	private JButton backButton;
	private JPanel midJPanel;
	private BorrowingBookModel borrowingBookModel;
	private JTable jTable;
	private JScrollPane sp1;

	public ReturnBooks(String readerId) {
		this.readerId = readerId;
	}

	public void dispalyBooks() {
		/**
		 * 这个方法用于显示书库的图书
		 */

		/******************* 换皮肤 *******************/
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

		f = new JFrame("读者还书中...");
		f.setBounds(400, 100, 1000, 780);
		f.setResizable(false);
		f.setLayout(null);

		/******************* 北部面板 *******************/

		// 面板属性
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBounds(0, 0, 1000, 180);
		// 提示还书方式
		tipJLabel = new JLabel("请点击要还的书籍，按还书执行！");
		tipJLabel.setBounds(100, 20, 350, 50);

		// 设置背景
		norbgJLabel = new JLabel(new ImageIcon("image/还书上.jpg"));
		norbgJLabel.setBounds(0, 0, 1000, 200);

		// 添加
		northPanel.add(norbgJLabel);
		northPanel.add(tipJLabel);

		/******************* 南部面板 *******************/

		southPanel = new JPanel();
		southPanel.setLayout(null);
		southPanel.setBounds(0, 580, 1000, 220);
		// 背景
		soubgJLabel = new JLabel(new ImageIcon("image/还书下.jpg"));
		soubgJLabel.setBounds(0, 0, 1000, 200);

		// 按钮
		returnButton = new JButton("还书");
		returnButton.setFont(new Font("Dialog", Font.BOLD, 17));
		returnButton.setForeground(Color.white);
		returnButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		returnButton.setBounds(300, 50, 100, 40); // 这里的高度是相对southPanel的
		backButton = new JButton("返回");
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setBounds(580, 50, 100, 40);

		// 添加
		southPanel.add(returnButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // 必须放在按钮的后面添加，否则按钮被覆盖

		/******************* 中部面板 *******************/
		midJPanel = new JPanel();
		midJPanel.setLayout(null);
		midJPanel.setBounds(0, 180, 1000, 400);

		// 用于显示数据的表格
		borrowingBookModel = new BorrowingBookModel(readerId);
		jTable = new JTable(borrowingBookModel);
		// 设置背景,必须设置在按钮的后面，否则会覆盖按钮
		

		// 表格属性设置
		jTable.getTableHeader().setFont(new Font(null, Font.BOLD, 20));
		jTable.getTableHeader().setForeground(Color.BLACK);
		jTable.setRowHeight(30);
		jTable.setForeground(Color.BLACK); // 字体颜色
		jTable.setFont(new Font(null, Font.BOLD, 20)); // 字体样式
		jTable.setSelectionForeground(Color.blue); // 选中后字体颜色
		jTable.setSelectionBackground(Color.LIGHT_GRAY); // 选中后字体背景

		sp1 = new JScrollPane(jTable);   // JScrollPane不能设置setLayout(null)
		sp1.setBounds(100, 00, 800, 400);
		midJPanel.add(sp1);
		

		JPanel midJPanel2 = new JPanel();
		midJPanel2.setLayout(null);
		midJPanel2.setBounds(0, 180, 100, 400);
		JLabel leftbgJLabel = new JLabel(new ImageIcon("image/还书左.jpg"));
		leftbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel2.add(leftbgJLabel);
		f.add(midJPanel2);
		
		
		JPanel midJPanel3 = new JPanel();
		midJPanel3.setLayout(null);
		midJPanel3.setBounds(900, 180, 100, 400);
		JLabel rightbgJLabel = new JLabel(new ImageIcon("image/还书右.jpg"));
		rightbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel3.add(rightbgJLabel);
		f.add(midJPanel3);
		
		/******************* 事件监听 *******************/
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
			}
		});

		// 还书按钮添加监听
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnButton();
			}
		});

		// Jframe添加
		f.add(southPanel);
		f.add(northPanel);
		f.add(midJPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void returnButton() {
		/**
		 * 用于响应还书的事件
		 */
		int row = jTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(f, "你好，请选择图书！", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String bookId = borrowingBookModel.getValueAt(row, 0).toString();   // 获取所选行的bookId
		ReaderAction readerAction = new ReaderAction(readerId);
		int returnOk = readerAction.returnBookAction(bookId);   // 到数据库中执行操作
		if (returnOk == 1) {
			JOptionPane.showMessageDialog(
					f, "还书成功！",
					"提示", JOptionPane.WARNING_MESSAGE);

			// 刷新界面
			f.dispose();
			new ReturnBooks(readerId).dispalyBooks();
			
		} else {
			JOptionPane.showMessageDialog(
					f, "借阅失败！", 
					"提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
}
