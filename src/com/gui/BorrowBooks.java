package com.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.database.*;
import com.entity.*;

public class BorrowBooks {
	/**
	 * 这个类用于读者借阅书籍，包括：显示书库信息、搜索书籍、借阅书籍
	 * 模块如下：
	 * 		1. 显示书库 : dispalyBooks();
	 * 		2. 搜索书籍 : searchBooks(String bookname);
	 * 		3. 借阅书籍 : borrowButton();
	 * 借阅的过程：
	 * 		点击要借阅的书->查看之前是否借过->是否还有库存->进行借阅操作
	 */
	private String readerId;
	private JFrame f;
	private JPanel southPanel;
	private JLabel soubgJLabel;
	private JButton borrowButton;
	private JButton backButton;
	private BooksTableModel booksTableModel;
	private JTable jTable;
	private JScrollPane sp1;
	private JPanel northPanel;
	private JLabel bookNameBJLabel;
	private JTextField bookNameField;
	private JButton searchButton;
	private JLabel norbgJLabel;
		
	public BorrowBooks(String readerId) {
		this.readerId = readerId;
	}

	public void dispalyBooks() {

		/******************* 换皮肤 *******************/
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		try
	    {
	        //设置本属性将改变窗口边框样式定义
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }

		/******************** 容器 ********************/
		f = new JFrame("图书借阅");
		f.setBounds(400, 100, 1000, 780);
		f.setLayout(null);
		f.setResizable(false);
		/******************* 南部面板 *******************/

		southPanel = new JPanel();
		southPanel.setLayout(null);
		southPanel.setBounds(0, 600, 1000, 200);
		// 背景
		soubgJLabel = new JLabel(new ImageIcon("image/借书下.jpg"));
		soubgJLabel.setBounds(0, 0, 1000, 200);

		// 按钮
		borrowButton = new JButton("借阅");
		borrowButton.setForeground(Color.white);
		borrowButton.setFont(new Font("Dialog", Font.BOLD, 17));
		borrowButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		borrowButton.setBounds(280, 50, 100, 40);
		backButton = new JButton("返回");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// 添加
		southPanel.add(borrowButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // 必须放在按钮的后面添加，否则按钮被覆盖

		/*************** 用于显示数据的表格 ****************/

		booksTableModel = new BooksTableModel(readerId);

		// 表格属性
		jTable = new JTable(booksTableModel);
		jTable.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
		jTable.getTableHeader().setForeground(Color.RED);
		jTable.setRowHeight(30);
		jTable.setForeground(Color.BLACK); // 字体颜色
		jTable.setFont(new Font(null, Font.BOLD, 20)); // 字体样式
		jTable.setSelectionForeground(Color.blue); // 选中后字体颜色
		jTable.setSelectionBackground(Color.LIGHT_GRAY); // 选中后字体背景
		sp1 = new JScrollPane(jTable);
		sp1.setBounds(100, 200, 800, 400);
		
		
		JPanel midJPanel2 = new JPanel();
		midJPanel2.setLayout(null);
		midJPanel2.setBounds(0, 200, 100, 400);
		JLabel leftbgJLabel = new JLabel(new ImageIcon("image/借书左.jpg"));
		leftbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel2.add(leftbgJLabel);
		
		
		JPanel midJPanel3 = new JPanel();
		midJPanel3.setLayout(null);
		midJPanel3.setBounds(900, 200, 100, 400);
		JLabel rightbgJLabel = new JLabel(new ImageIcon("image/借书右.jpg"));
		rightbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel3.add(rightbgJLabel);
		
		

		/******************* 北部面板 *******************/

		// 用于用户查询、借阅的面板
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBounds(0, 0, 1000, 200);

		// 北部面板控件
		bookNameBJLabel = new JLabel("书名:");
		bookNameBJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookNameBJLabel.setForeground(Color.blue);
		bookNameBJLabel.setBounds(260, 100, 100, 40);
		northPanel.add(bookNameBJLabel);

		bookNameField = new JTextField("");
		bookNameField.setFont(new Font(null, Font.BOLD, 25));
		bookNameField.setForeground(Color.red);
		bookNameField.setBounds(380, 100, 150, 40);
		northPanel.add(bookNameField);

		searchButton = new JButton("查询");
		searchButton.setFont(new Font("Dialog", Font.BOLD, 17));
		searchButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		searchButton.setForeground(Color.white);
		searchButton.setBounds(560, 100, 100, 40);
		northPanel.add(searchButton);

		// 设置背景,必须设置在按钮的后面，否则会覆盖按钮
		norbgJLabel = new JLabel(new ImageIcon("image/借书上.jpg"));
		norbgJLabel.setBounds(0, 0, northPanel.getWidth(), northPanel.getHeight());
		northPanel.add(norbgJLabel);

		/******************* 事件监听 *******************/
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		// 借阅按钮添加监听
		borrowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrowButton();
			}
		});

		// 查询按钮添加监听
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose();      // 先释放资源  
				searchBooks(bookname);  //再创建资源
			}
		});

		// Jframe添加
		f.add(midJPanel3);	
		f.add(southPanel);
		f.add(northPanel);
		f.add(midJPanel2);	
		f.add(sp1);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void searchBooks(String bookname) {
		/**
		 * 这个方法用于通过书名搜索图书
		 */
		/******************* 换皮肤 *******************/
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}		 
		try
	    {
	        //设置本属性将改变窗口边框样式定义
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }

		/******************** 容器 ********************/
		f = new JFrame("图书借阅");
		f.setBounds(500, 100, 1000, 780);
		f.setLayout(null);
		f.setResizable(false);
		/******************* 南部面板 *******************/

		southPanel = new JPanel();
		southPanel.setLayout(null);
		southPanel.setBounds(0, 600, 1000, 200);
		// 背景
		soubgJLabel = new JLabel(new ImageIcon("image/借书下.jpg"));
		soubgJLabel.setBounds(0, 0, 1000, 200);

		// 按钮
		borrowButton = new JButton("借阅");
		borrowButton.setForeground(Color.white);
		borrowButton.setFont(new Font("Dialog", Font.BOLD, 17));
		borrowButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		borrowButton.setBounds(280, 50, 100, 40); // 这里的高度是相对southPanel的
		backButton = new JButton("返回");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// 添加
		southPanel.add(borrowButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // 必须放在按钮的后面添加，否则按钮被覆盖

		/*************** 用于显示数据的表格 ****************/

		booksTableModel = new BooksTableModel(readerId,bookname);

		// 表格属性
		jTable = new JTable(booksTableModel);
		jTable.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
		jTable.getTableHeader().setForeground(Color.RED);
		jTable.setRowHeight(30);
		jTable.setForeground(Color.BLACK); // 字体颜色
		jTable.setFont(new Font(null, Font.BOLD, 20)); // 字体样式
		jTable.setSelectionForeground(Color.blue); // 选中后字体颜色
		jTable.setSelectionBackground(Color.LIGHT_GRAY); // 选中后字体背景
		sp1 = new JScrollPane(jTable);
		sp1.setBounds(100, 200, 800, 400);
		
		
		JPanel midJPanel2 = new JPanel();
		midJPanel2.setLayout(null);
		midJPanel2.setBounds(0, 200, 100, 400);
		JLabel leftbgJLabel = new JLabel(new ImageIcon("image/借书左.jpg"));
		leftbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel2.add(leftbgJLabel);
		
		
		JPanel midJPanel3 = new JPanel();
		midJPanel3.setLayout(null);
		midJPanel3.setBounds(900, 200, 100, 400);
		JLabel rightbgJLabel = new JLabel(new ImageIcon("image/借书右.jpg"));
		rightbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel3.add(rightbgJLabel);
		
		/******************* 北部面板 *******************/

		// 用于用户查询、借阅的面板
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBounds(0, 0, 1000, 200);

		// 北部面板控件
		bookNameBJLabel = new JLabel("书名:");
		bookNameBJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookNameBJLabel.setForeground(Color.blue);
		bookNameBJLabel.setBounds(260, 100, 100, 40);
		northPanel.add(bookNameBJLabel);

		bookNameField = new JTextField("");
		bookNameField.setFont(new Font(null, Font.BOLD, 25));
		bookNameField.setForeground(Color.red);
		bookNameField.setBounds(380, 100, 150, 40);
		northPanel.add(bookNameField);

		searchButton = new JButton("查询");
		searchButton.setFont(new Font("Dialog", Font.BOLD, 17));
		searchButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		searchButton.setForeground(Color.white);
		searchButton.setBounds(560, 100, 100, 40);
		northPanel.add(searchButton);

		// 设置背景,必须设置在按钮的后面，否则会覆盖按钮
		norbgJLabel = new JLabel(new ImageIcon("image/借书上.jpg"));
		norbgJLabel.setBounds(0, 0, northPanel.getWidth(), northPanel.getHeight());
		northPanel.add(norbgJLabel);

		/******************* 事件监听 *******************/
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		// 借阅按钮添加监听
		borrowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrowButton();
			}
		});

		// 查询按钮添加监听
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose();      // 先释放资源  
				searchBooks(bookname);  //再创建资源
			}
		});

		// Jframe添加
		f.add(midJPanel3);	
		f.add(southPanel);
		f.add(northPanel);
		f.add(midJPanel2);	
		f.add(sp1);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void borrowButton() {
		/**
		 * 当点击“借阅”按钮时，调用这个函数
		 */
		
		int row = jTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(f, "你好，请选择图书！", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// 获取所选择的那一行的书本剩余量
		String bookNumString = (String) booksTableModel.getValueAt(row, 2);
		int bookNum = Integer.parseInt(bookNumString);  
		if (bookNum > 0) {  // 当要借的书还有库存
			String bookId = booksTableModel.getValueAt(row, 0).toString();
			ReaderAction readerAction = new ReaderAction(readerId);
			// 先查看这本书读者之前是否借过
			List<AllBooks> ab = readerAction.searchBorrowedBooks(bookId); 
			if (ab.size() == 0) {  // 如果之前没有借过
				int borrowOk = readerAction.borrowBooks(bookId);  // 进行借阅操作
				if (borrowOk == 1) {
					// 下面是为了设置在21天内归还
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
					Date date = new Date();
					Calendar calendar = Calendar.getInstance();//日历对象
					calendar.setTime(date);//设置当前日期
					calendar.add(Calendar.DAY_OF_MONTH, +21);// 日期+21
					String nextString = df.format(calendar.getTime()).toString();
					JOptionPane.showMessageDialog(f, "        借阅成功！\n请在"+nextString+"之前还书！"
							, "提示", JOptionPane.WARNING_MESSAGE);

					// 刷新界面
					f.dispose();
					new BorrowBooks(readerId).dispalyBooks();
				} else {
					JOptionPane.showMessageDialog(f, "借阅失败！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}

			} else {
				JOptionPane.showMessageDialog(f, "该书已经借阅", "提示", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(f, "对不起，该图书已被借出！！", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

}
