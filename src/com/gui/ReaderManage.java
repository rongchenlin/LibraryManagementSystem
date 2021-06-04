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

public class ReaderManage {
	/**
	 * 这个类用于管理员对读者信息进行管理
	 * 功能模块如下：
	 *		1. 查看读者信息 : dispalyreaders();
	 *		2. 搜索读者信息 : searchReaders(String readerName);
	 *		3. 修改读者信息 : manageButton();						
	 */
	private String readerId;
	private JFrame f;
	private JPanel southPanel;
	private JLabel soubgJLabel;
	private JButton manageButton;
	private JButton backButton;
	private ReaderTableModel readerTableModel;
	private JTable jTable;
	private JScrollPane sp1;
	private JPanel northPanel;
	private JLabel readerNameJlabel;
	private JTextField readerNameField;
	private JButton searchButton;
	private JLabel norbgJLabel;
	private JDialog updateDialog;
	private JLabel readerIdJLabel;
	private JLabel readerSexJLabel;
	private JTextField readerIdField;
	private JTextField readerSexField;
	private JTextField readerPwField;
	private JLabel readerPwJLabel;
	public void dispalyreaders() {
		/**
		 * 这个方法用于显示读者信息
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
		f = new JFrame("图书修改");
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
		manageButton = new JButton("修改");
		manageButton.setForeground(Color.white);
		manageButton.setFont(new Font("Dialog", Font.BOLD, 17));
		manageButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		manageButton.setBounds(280, 50, 100, 40); // 这里的高度是相对southPanel的
		backButton = new JButton("返回");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// 添加
		southPanel.add(manageButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // 必须放在按钮的后面添加，否则按钮被覆盖

		/*************** 用于显示数据的表格 ****************/

		readerTableModel = new ReaderTableModel(readerId);
		
		// 表格属性
		jTable = new JTable(readerTableModel);
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

		// 用于用户查询、修改的面板
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBounds(0, 0, 1000, 200);

		// 北部面板控件
		readerNameJlabel = new JLabel("读者姓名:");
		readerNameJlabel.setFont(new Font(null, Font.BOLD, 28));
		readerNameJlabel.setForeground(Color.blue);
		readerNameJlabel.setBounds(220, 100, 140, 40);
		northPanel.add(readerNameJlabel);

		readerNameField = new JTextField("");
		readerNameField.setFont(new Font(null, Font.BOLD, 25));
		readerNameField.setForeground(Color.red);
		readerNameField.setBounds(380, 100, 150, 40);
		northPanel.add(readerNameField);

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

		// 修改按钮添加监听
		manageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageButton();
			}
		});

		// 查询按钮添加监听
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String readerName = readerNameField.getText();
				f.dispose();      // 先释放资源  
				searchReaders(readerName);  //再创建资源
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

	public void searchReaders(String readerName) {
		/**
		 * 这个方法用于搜索读者信息
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
		f = new JFrame("图书修改");
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
		manageButton = new JButton("修改");
		manageButton.setForeground(Color.white);
		manageButton.setFont(new Font("Dialog", Font.BOLD, 17));
		manageButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		manageButton.setBounds(280, 50, 100, 40); // 这里的高度是相对southPanel的
		backButton = new JButton("返回");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// 添加
		southPanel.add(manageButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // 必须放在按钮的后面添加，否则按钮被覆盖

		/*************** 用于显示数据的表格 ****************/

		readerTableModel = new ReaderTableModel(readerId,readerName);
		
		// 表格属性
		jTable = new JTable(readerTableModel);
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

		// 用于用户查询、修改的面板
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBounds(0, 0, 1000, 200);

		// 北部面板控件
		readerNameJlabel = new JLabel("读者姓名:");
		readerNameJlabel.setFont(new Font(null, Font.BOLD, 28));
		readerNameJlabel.setForeground(Color.blue);
		readerNameJlabel.setBounds(220, 100, 140, 40);
		northPanel.add(readerNameJlabel);

		readerNameField = new JTextField("");
		readerNameField.setFont(new Font(null, Font.BOLD, 25));
		readerNameField.setForeground(Color.red);
		readerNameField.setBounds(380, 100, 150, 40);
		northPanel.add(readerNameField);

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

		// 修改按钮添加监听
		manageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageButton();
			}
		});

		// 查询按钮添加监听
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String readerName = readerNameField.getText();
				f.dispose();      // 先释放资源  
				searchReaders(readerName);  //再创建资源
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
	 * 这个函数用于响应修改按钮事件
	 */
	public void manageButton() {
		/**
		 * 这个方法用于对读者信息进行修改
		 */
		int row = jTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(f, "你好，请选择要修改的读者！", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String readerpwString = (String) readerTableModel.getValueAt(row, 2);
		String readerIdString = (String) readerTableModel.getValueAt(row, 0);
		String readerNameString = (String) readerTableModel.getValueAt(row, 1);
		String readerSexString  =  (String) readerTableModel.getValueAt(row, 3);
			
		ReaderInfor reader = new ReaderInfor();
		reader.setreaderId(readerIdString);
		reader.setreaderName(readerNameString);
		reader.setreaderSex(readerSexString);
		reader.setreaderPassword(readerpwString);
		
		// 弹出自定义的对话框，用于修改读者信息
		showCustomupdateDialog(f, f,reader);	

	}
	
	public void showCustomupdateDialog(JFrame owner, Component parentComponent,ReaderInfor readerInfor) {
		/**
		 * 这个自定义对话框，用于修改读者信息
		 */
		updateDialog = new JDialog(owner, "添加书籍", true);
		// 设置对话框的宽高
		updateDialog.setSize(560, 560);
		// 设置对话框相对显示的位置
		updateDialog.setLocationRelativeTo(parentComponent);
		// 设置对话框大小不可改变
		updateDialog.setResizable(false);

		// 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, updateDialog.getWidth(), updateDialog.getHeight());
		// 添加组件到面板

	    readerIdJLabel = new JLabel("读者编号");
	    readerIdJLabel.setFont(new Font(null, Font.BOLD, 28));
	    readerIdJLabel.setBounds(120, 50, 200, 40);
		readerNameJlabel = new JLabel("读者姓名");
		readerNameJlabel.setFont(new Font(null, Font.BOLD, 28));
		readerNameJlabel.setBounds(120, 120, 200, 40);
		readerSexJLabel = new JLabel("读者性别");
		readerSexJLabel .setFont(new Font(null, Font.BOLD, 28));
		readerSexJLabel .setBounds(120, 190, 200, 40);
		readerPwJLabel = new JLabel("读者密码");
		readerPwJLabel .setFont(new Font(null, Font.BOLD, 28));
		readerPwJLabel .setBounds(120, 260, 200, 40);
		readerPwJLabel .setFont(new Font(null, Font.BOLD, 28));

		
		readerIdField = new JTextField(readerInfor.getreaderId());
		readerIdField.setBounds(250, 50, 150, 40);
		readerNameField = new JTextField(readerInfor.getreaderName());
		readerNameField.setBounds(250, 120, 150, 40);
		readerSexField = new JTextField(readerInfor.getreaderSex());
		readerSexField .setBounds(250, 190, 150, 40);
		readerPwField = new JTextField(readerInfor.getreaderPassword());
		readerPwField.setBounds(250, 260, 150, 40);
	

		JButton updateButton = new JButton("修改");
		updateButton.setBounds(130, 400, 100, 40);
		panel.add(updateButton);
		
		
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String readerIdString = readerIdField.getText();
				String readerNameString = readerNameField.getText();
				String readerSexString = readerSexField.getText();
				String readerpxString = readerPwField.getText();
			 
				// 将修改的信息放在ReaderInfor
				ReaderInfor reader = new ReaderInfor();
				reader.setreaderId(readerIdString);
				reader.setreaderName(readerNameString);
				reader.setreaderSex(readerSexString);
				reader.setreaderPassword(readerpxString);
				
			int addOk =	new ReaderAction(readerIdString).updateReaderInfor(reader);
			if (addOk == 1) {
				JOptionPane.showMessageDialog(f, "修改成功！", "提示", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(f, "操作有误，添加失败！", "提示", JOptionPane.WARNING_MESSAGE);
			}
				updateDialog.dispose();
				f.dispose();
				new ReaderManage().dispalyreaders();
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

		panel.add(readerIdField);
		panel.add(readerNameField);
		panel.add(readerSexField);
		panel.add(readerPwField);

		panel.add(readerIdJLabel);
		panel.add(readerNameJlabel);
		panel.add(readerPwJLabel);
		panel.add(readerSexJLabel);

		// 背景
		JLabel bgJLabel = new JLabel(new ImageIcon("image/还书下.jpg"));
		bgJLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		panel.add(bgJLabel);

		// 设置对话框的内容面板
		updateDialog.setContentPane(panel);
		updateDialog.setVisible(true);
	}


}
