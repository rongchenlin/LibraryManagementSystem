package com.gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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

public class AddBooks {
	/**
	 * 这个类用于管理员添加书籍，包括下面的几个模块
	 * 1. 查看已有书库 ：dispalyBooks();
	 * 2. 根据书名查看书库中的书籍 : searchBooks(String bookname);
	 * 3. 添加新的书到书库中 : addButton();
	 */
	private String readerId;
	private JFrame f;
	private JPanel southPanel;
	private JLabel soubgJLabel;  // 南部面板的背景
	private JButton addButton;
	private JButton backButton;
	private BooksTableModel booksTableModel;
	private JTable jTable;
	private JScrollPane sp1;
	private JPanel northPanel;
	private JLabel bookNameBJLabel;
	private JTextField bookNameField;
	private JButton searchButton;
	private JLabel norbgJLabel;  // 北部面板的背景
	private JDialog addDialog;
	private JLabel bookIdJLabel;
	private JLabel bookTypeJLabel;
	private JLabel bookPriceJLabel;
	private JTextField bookIdField;
	private JTextField bookTypeField;
	private JTextField bookPriceField;

	public void dispalyBooks() {

		/******************* 换皮肤 *******************/
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}

		try {
			// 改变窗口边框样式定义
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			// TODO exception
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
		addButton = new JButton("添加");
		addButton.setForeground(Color.white);
		addButton.setFont(new Font("Dialog", Font.BOLD, 17));
		addButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		addButton.setBounds(280, 50, 100, 40); // 这里的高度是相对southPanel的
		backButton = new JButton("返回");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// 添加
		southPanel.add(addButton);
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

		// 添加按钮添加监听
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addButton();
			}
		});

		// 查询按钮添加监听
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose(); // 先释放资源
				searchBooks(bookname); // 再创建资源
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

		try {
			// 设置本属性将改变窗口边框样式定义
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			// TODO exception
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
		addButton = new JButton("添加");
		addButton.setForeground(Color.white);
		addButton.setFont(new Font("Dialog", Font.BOLD, 17));
		addButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		addButton.setBounds(280, 50, 100, 40); // 这里的高度是相对southPanel的
		backButton = new JButton("返回");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// 添加
		southPanel.add(addButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // 必须放在按钮的后面添加，否则按钮被覆盖

		/*************** 用于显示数据的表格 ****************/

		booksTableModel = new BooksTableModel(readerId, bookname);

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

		// 添加按钮添加监听
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addButton();
			}
		});

		// 查询按钮添加监听
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose(); // 先释放资源
				searchBooks(bookname); // 再创建资源
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

	/**
	 * 这个函数用于响应借阅按钮事件
	 */
	public void addButton() {
		/**
		 * 当点击“增加书籍”按钮时，调用这个函数，这个函数调用自定义的对话框
		 */
		showCustomaddDialog(f, f);
	}

	public void showCustomaddDialog(JFrame owner, Component parentComponent) {
		/**
		 * 这是自定义的对话框，用于增加书籍
		 */
		
		addDialog = new JDialog(owner, "添加书籍", true);
		// 设置对话框的宽高
		addDialog.setSize(560, 560);
		// 设置对话框相对显示的位置
		addDialog.setLocationRelativeTo(parentComponent);
		// 设置对话框大小不可改变
		addDialog.setResizable(false);

		// 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, addDialog.getWidth(), addDialog.getHeight());
		// 添加组件到面板

		bookIdJLabel = new JLabel("书籍编号");
		bookIdJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookIdJLabel.setBounds(120, 50, 200, 40);
		bookNameBJLabel = new JLabel("书籍名称");
		bookNameBJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookNameBJLabel.setBounds(120, 120, 200, 40);
		bookPriceJLabel = new JLabel("书籍价格");
		bookPriceJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookPriceJLabel.setBounds(120, 190, 200, 40);
		bookTypeJLabel = new JLabel("书籍类型");
		bookIdJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookTypeJLabel.setBounds(120, 260, 200, 40);
		bookTypeJLabel.setFont(new Font(null, Font.BOLD, 28));

		JLabel bookNumJLabel = new JLabel("书籍数量");
		bookNumJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookNumJLabel.setBounds(120, 330, 200, 40);
		bookNumJLabel.setFont(new Font(null, Font.BOLD, 28));
		panel.add(bookNumJLabel);

		bookIdField = new JTextField("");
		bookIdField.setBounds(250, 50, 150, 40);
		bookNameField = new JTextField("");
		bookNameField.setBounds(250, 120, 150, 40);
		bookPriceField = new JTextField("0");
		bookPriceField.setBounds(250, 190, 150, 40);
		bookTypeField = new JTextField("");
		bookTypeField.setBounds(250, 260, 150, 40);
		JTextField bookNumField = new JTextField("0");
		bookNumField.setBounds(250, 330, 150, 40);
		panel.add(bookNumField);

		JButton addButton = new JButton("添加");
		addButton.setBounds(130, 400, 100, 40);
		panel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookIdString = bookIdField.getText();
				String bookNameString = bookNameField.getText();
				String bookPriceString = bookPriceField.getText();
				String bookTypeString = bookTypeField.getText();
				String bookNumString = bookNumField.getText();

			  // 将输入的书籍信息封装在AllBooks对象中
				AllBooks book = new AllBooks();
				book.setbookId(bookIdString);
				book.setbookName(bookNameString);
				book.setbookPrice(bookPriceString);
				book.setbookType(bookTypeString);
				book.setbookNum(bookNumString);
			int addOk =	new AdminAction().addBooks(book);
			if (addOk == 1) {
				JOptionPane.showMessageDialog(f, "添加成功！", "提示", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(f, "操作有误，添加失败！", "提示", JOptionPane.WARNING_MESSAGE);
			}
				addDialog.dispose();
				f.dispose();
				new AddBooks().dispalyBooks();
			}
		});

		JButton cancelButton = new JButton("返回");
		cancelButton.setBounds(300, 400, 100, 40);
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addDialog.dispose();
			}
		});

		panel.add(bookIdField);
		panel.add(bookNameField);
		panel.add(bookPriceField);
		panel.add(bookTypeField);

		panel.add(bookIdJLabel);
		panel.add(bookNameBJLabel);
		panel.add(bookPriceJLabel);
		panel.add(bookTypeJLabel);

		// 背景
		JLabel bgJLabel = new JLabel(new ImageIcon("image/还书下.jpg"));
		bgJLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		panel.add(bgJLabel);

		// 设置对话框的内容面板
		addDialog.setContentPane(panel);

		addDialog.setVisible(true);
	}

}
