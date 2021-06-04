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

public class UpdateBook {
	/**
	 * 这个方法管理员用于修改书库中书籍信息
	 */
	private String readerId;
	private JFrame f;
	private JPanel southPanel;
	private JLabel soubgJLabel;
	private JButton updateButton;
	private JButton backButton;
	private BooksTableModel booksTableModel;
	private JTable jTable;
	private JScrollPane sp1;
	private JPanel northPanel;
	private JLabel bookNameBJLabel;
	private JTextField bookNameField;
	private JButton searchButton;
	private JLabel norbgJLabel;
	private JDialog updateDialog;
	private JLabel bookIdJLabel;
	private JLabel bookTypeJLabel;
	private JLabel bookPriceJLabel;
	private JTextField bookIdField;
	private JTextField bookTypeField;
	private JTextField bookPriceField;

	public void dispalyBooks() {
		/**
		 * 这个方法用于显示书库
		 */

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
		updateButton = new JButton("修改");
		updateButton.setForeground(Color.white);
		updateButton.setFont(new Font("Dialog", Font.BOLD, 17));
		updateButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		updateButton.setBounds(280, 50, 100, 40); // 这里的高度是相对southPanel的
		backButton = new JButton("返回");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// 修改
		southPanel.add(updateButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // 必须放在按钮的后面修改，否则按钮被覆盖

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

		/*************** 事件监听 ****************/
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		// 修改按钮修改监听
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateButton();
			}
		});

		// 查询按钮修改监听
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose(); // 先释放资源
				searchBooks(bookname); // 再创建资源
			}
		});

		// Jframe修改
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
		 * 这个方法用于根据书名查找书库中的图书
		 */
		/******************* 换皮肤 *******************/
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		try {
		// 设改变窗口边框样式定义
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
		updateButton = new JButton("修改");
		updateButton.setForeground(Color.white);
		updateButton.setFont(new Font("Dialog", Font.BOLD, 17));
		updateButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		updateButton.setBounds(280, 50, 100, 40); // 这里的高度是相对southPanel的
		backButton = new JButton("返回");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// 修改
		southPanel.add(updateButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // 必须放在按钮的后面修改，否则按钮被覆盖

		/*************** 用于显示数据的表格 ****************/

		booksTableModel = new BooksTableModel(readerId, bookname);

		// 表格属性
		jTable = new JTable(booksTableModel);  // 表格
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

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		// 修改按钮修改监听
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				updateButton();
			}
		});

		// 查询按钮修改监听
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose(); // 先释放资源
				searchBooks(bookname); // 再创建资源
			}
		});

		// Jframe修改
		f.add(midJPanel3);
		f.add(southPanel);
		f.add(northPanel);
		f.add(midJPanel2);
		f.add(sp1);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void updateButton() {
		/**
		 * 这个函数用于响应借阅按钮事件
		 */
		int row = jTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(f, "你好，请选择图书！", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String bookNumString = (String) booksTableModel.getValueAt(row, 2);
		String bookIdString = (String) booksTableModel.getValueAt(row, 0);
		String bookNameString = (String) booksTableModel.getValueAt(row, 1);
		String bookTypeString = (String) booksTableModel.getValueAt(row, 4);
		String bookPriceString  =  (String) booksTableModel.getValueAt(row, 3);
	
		
		AllBooks book = new AllBooks();
		book.setbookId(bookIdString);
		book.setbookName(bookNameString);
		book.setbookNum(bookNumString);
		book.setbookPrice(bookPriceString);
		book.setbookType(bookTypeString);
		
		showCustomupdateDialog(f, f,book);
		
	}

	public void showCustomupdateDialog(JFrame owner, Component parentComponent,AllBooks book) {
		updateDialog = new JDialog(owner, "修改书籍", true);
		// 设置对话框的宽高
		updateDialog.setSize(560, 560);
		// 设置对话框相对显示的位置
		updateDialog.setLocationRelativeTo(parentComponent);
		// 设置对话框大小不可改变
		updateDialog.setResizable(false);

		// 创建对话框的内容面板, 在面板内可以根据自己的需要修改任何组件并做任意是布局
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, updateDialog.getWidth(), updateDialog.getHeight());
		// 修改组件到面板

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

		bookIdField = new JTextField(book.getbookId());
		String bookId = book.getbookId();  // 记住旧的ID
		bookIdField.setBounds(250, 50, 150, 40);
		bookNameField = new JTextField(book.getbookName());
		bookNameField.setBounds(250, 120, 150, 40);
		
		
		bookPriceField = new JTextField(book.getbookPrice());
		bookPriceField.setBounds(250, 190, 150, 40);
		bookTypeField = new JTextField(book.getbookType());
		bookTypeField.setBounds(250, 260, 150, 40);
		JTextField bookNumField = new JTextField(book.getbookNum());
		bookNumField.setBounds(250, 330, 150, 40);
		panel.add(bookNumField);

		JButton updateButton = new JButton("修改");
		updateButton.setBounds(130, 400, 100, 40);
		panel.add(updateButton);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				book.setbookId(bookIdField.getText());
				book.setbookName(bookNameField.getText());
				book.setbookNum(bookNumField.getText());
				book.setbookPrice(bookPriceField.getText());
				book.setbookType(bookTypeField.getText());
							
				int okUpdate = new AdminAction().updateBooks(book,bookId);
				if (okUpdate == 1) {
					JOptionPane.showMessageDialog(f, "修改成功！", "提示", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(f, "修改失败！", "提示", JOptionPane.WARNING_MESSAGE);
				}
				
				updateDialog.dispose();
				f.dispose();
				new UpdateBook().dispalyBooks();
			}

		});

		JButton cancelButton = new JButton("返回");
		cancelButton.setBounds(300, 400, 100, 40);
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

				updateDialog.dispose();
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
		updateDialog.setContentPane(panel);
		updateDialog.setVisible(true);
	}

}
