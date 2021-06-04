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
	 * ��������ڹ���Ա����鼮����������ļ���ģ��
	 * 1. �鿴������� ��dispalyBooks();
	 * 2. ���������鿴����е��鼮 : searchBooks(String bookname);
	 * 3. ����µ��鵽����� : addButton();
	 */
	private String readerId;
	private JFrame f;
	private JPanel southPanel;
	private JLabel soubgJLabel;  // �ϲ����ı���
	private JButton addButton;
	private JButton backButton;
	private BooksTableModel booksTableModel;
	private JTable jTable;
	private JScrollPane sp1;
	private JPanel northPanel;
	private JLabel bookNameBJLabel;
	private JTextField bookNameField;
	private JButton searchButton;
	private JLabel norbgJLabel;  // �������ı���
	private JDialog addDialog;
	private JLabel bookIdJLabel;
	private JLabel bookTypeJLabel;
	private JLabel bookPriceJLabel;
	private JTextField bookIdField;
	private JTextField bookTypeField;
	private JTextField bookPriceField;

	public void dispalyBooks() {

		/******************* ��Ƥ�� *******************/
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}

		try {
			// �ı䴰�ڱ߿���ʽ����
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			// TODO exception
		}

		/******************** ���� ********************/
		f = new JFrame("ͼ�����");
		f.setBounds(400, 100, 1000, 780);
		f.setLayout(null);
		f.setResizable(false);
		/******************* �ϲ���� *******************/

		southPanel = new JPanel();
		southPanel.setLayout(null);
		southPanel.setBounds(0, 600, 1000, 200);
		// ����
		soubgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		soubgJLabel.setBounds(0, 0, 1000, 200);

		// ��ť
		addButton = new JButton("���");
		addButton.setForeground(Color.white);
		addButton.setFont(new Font("Dialog", Font.BOLD, 17));
		addButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		addButton.setBounds(280, 50, 100, 40); // ����ĸ߶������southPanel��
		backButton = new JButton("����");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// ���
		southPanel.add(addButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // ������ڰ�ť�ĺ�����ӣ�����ť������

		/*************** ������ʾ���ݵı�� ****************/

		booksTableModel = new BooksTableModel(readerId);

		// �������
		jTable = new JTable(booksTableModel);
		jTable.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
		jTable.getTableHeader().setForeground(Color.RED);
		jTable.setRowHeight(30);
		jTable.setForeground(Color.BLACK); // ������ɫ
		jTable.setFont(new Font(null, Font.BOLD, 20)); // ������ʽ
		jTable.setSelectionForeground(Color.blue); // ѡ�к�������ɫ
		jTable.setSelectionBackground(Color.LIGHT_GRAY); // ѡ�к����屳��
		sp1 = new JScrollPane(jTable);
		sp1.setBounds(100, 200, 800, 400);

		JPanel midJPanel2 = new JPanel();
		midJPanel2.setLayout(null);
		midJPanel2.setBounds(0, 200, 100, 400);
		JLabel leftbgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		leftbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel2.add(leftbgJLabel);

		JPanel midJPanel3 = new JPanel();
		midJPanel3.setLayout(null);
		midJPanel3.setBounds(900, 200, 100, 400);
		JLabel rightbgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		rightbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel3.add(rightbgJLabel);

		/******************* ������� *******************/

		// �����û���ѯ�����ĵ����
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBounds(0, 0, 1000, 200);

		// �������ؼ�
		bookNameBJLabel = new JLabel("����:");
		bookNameBJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookNameBJLabel.setForeground(Color.blue);
		bookNameBJLabel.setBounds(260, 100, 100, 40);
		northPanel.add(bookNameBJLabel);

		bookNameField = new JTextField("");
		bookNameField.setFont(new Font(null, Font.BOLD, 25));
		bookNameField.setForeground(Color.red);
		bookNameField.setBounds(380, 100, 150, 40);
		northPanel.add(bookNameField);

		searchButton = new JButton("��ѯ");
		searchButton.setFont(new Font("Dialog", Font.BOLD, 17));
		searchButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		searchButton.setForeground(Color.white);
		searchButton.setBounds(560, 100, 100, 40);
		northPanel.add(searchButton);

		// ���ñ���,���������ڰ�ť�ĺ��棬����Ḳ�ǰ�ť
		norbgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		norbgJLabel.setBounds(0, 0, northPanel.getWidth(), northPanel.getHeight());
		northPanel.add(norbgJLabel);
		
		
		/******************* �¼����� *******************/
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		// ��Ӱ�ť��Ӽ���
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addButton();
			}
		});

		// ��ѯ��ť��Ӽ���
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose(); // ���ͷ���Դ
				searchBooks(bookname); // �ٴ�����Դ
			}
		});

		// Jframe���
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
		 * �����������ͨ����������ͼ��
		 */
		
		/******************* ��Ƥ�� *******************/
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}

		try {
			// ���ñ����Խ��ı䴰�ڱ߿���ʽ����
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			// TODO exception
		}

		/******************** ���� ********************/
		f = new JFrame("ͼ�����");
		f.setBounds(500, 100, 1000, 780);
		f.setLayout(null);
		f.setResizable(false);
		/******************* �ϲ���� *******************/

		southPanel = new JPanel();
		southPanel.setLayout(null);
		southPanel.setBounds(0, 600, 1000, 200);
		// ����
		soubgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		soubgJLabel.setBounds(0, 0, 1000, 200);

		// ��ť
		addButton = new JButton("���");
		addButton.setForeground(Color.white);
		addButton.setFont(new Font("Dialog", Font.BOLD, 17));
		addButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		addButton.setBounds(280, 50, 100, 40); // ����ĸ߶������southPanel��
		backButton = new JButton("����");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// ���
		southPanel.add(addButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // ������ڰ�ť�ĺ�����ӣ�����ť������

		/*************** ������ʾ���ݵı�� ****************/

		booksTableModel = new BooksTableModel(readerId, bookname);

		// �������
		jTable = new JTable(booksTableModel);
		jTable.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
		jTable.getTableHeader().setForeground(Color.RED);
		jTable.setRowHeight(30);
		jTable.setForeground(Color.BLACK); // ������ɫ
		jTable.setFont(new Font(null, Font.BOLD, 20)); // ������ʽ
		jTable.setSelectionForeground(Color.blue); // ѡ�к�������ɫ
		jTable.setSelectionBackground(Color.LIGHT_GRAY); // ѡ�к����屳��
		sp1 = new JScrollPane(jTable);
		sp1.setBounds(100, 200, 800, 400);

		JPanel midJPanel2 = new JPanel();
		midJPanel2.setLayout(null);
		midJPanel2.setBounds(0, 200, 100, 400);
		JLabel leftbgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		leftbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel2.add(leftbgJLabel);

		JPanel midJPanel3 = new JPanel();
		midJPanel3.setLayout(null);
		midJPanel3.setBounds(900, 200, 100, 400);
		JLabel rightbgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		rightbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel3.add(rightbgJLabel);

		/******************* ������� *******************/

		// �����û���ѯ�����ĵ����
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBounds(0, 0, 1000, 200);

		// �������ؼ�
		bookNameBJLabel = new JLabel("����:");
		bookNameBJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookNameBJLabel.setForeground(Color.blue);
		bookNameBJLabel.setBounds(260, 100, 100, 40);
		northPanel.add(bookNameBJLabel);

		bookNameField = new JTextField("");
		bookNameField.setFont(new Font(null, Font.BOLD, 25));
		bookNameField.setForeground(Color.red);
		bookNameField.setBounds(380, 100, 150, 40);
		northPanel.add(bookNameField);

		searchButton = new JButton("��ѯ");
		searchButton.setFont(new Font("Dialog", Font.BOLD, 17));
		searchButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		searchButton.setForeground(Color.white);
		searchButton.setBounds(560, 100, 100, 40);
		northPanel.add(searchButton);

		// ���ñ���,���������ڰ�ť�ĺ��棬����Ḳ�ǰ�ť
		norbgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		norbgJLabel.setBounds(0, 0, northPanel.getWidth(), northPanel.getHeight());
		northPanel.add(norbgJLabel);

		/******************* �¼����� *******************/
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		// ��Ӱ�ť��Ӽ���
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addButton();
			}
		});

		// ��ѯ��ť��Ӽ���
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose(); // ���ͷ���Դ
				searchBooks(bookname); // �ٴ�����Դ
			}
		});

		// Jframe���
		f.add(midJPanel3);
		f.add(southPanel);
		f.add(northPanel);
		f.add(midJPanel2);
		f.add(sp1);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	/**
	 * �������������Ӧ���İ�ť�¼�
	 */
	public void addButton() {
		/**
		 * ������������鼮����ťʱ���������������������������Զ���ĶԻ���
		 */
		showCustomaddDialog(f, f);
	}

	public void showCustomaddDialog(JFrame owner, Component parentComponent) {
		/**
		 * �����Զ���ĶԻ������������鼮
		 */
		
		addDialog = new JDialog(owner, "����鼮", true);
		// ���öԻ���Ŀ��
		addDialog.setSize(560, 560);
		// ���öԻ��������ʾ��λ��
		addDialog.setLocationRelativeTo(parentComponent);
		// ���öԻ����С���ɸı�
		addDialog.setResizable(false);

		// �����Ի�����������, ������ڿ��Ը����Լ�����Ҫ����κ�������������ǲ���
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, addDialog.getWidth(), addDialog.getHeight());
		// �����������

		bookIdJLabel = new JLabel("�鼮���");
		bookIdJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookIdJLabel.setBounds(120, 50, 200, 40);
		bookNameBJLabel = new JLabel("�鼮����");
		bookNameBJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookNameBJLabel.setBounds(120, 120, 200, 40);
		bookPriceJLabel = new JLabel("�鼮�۸�");
		bookPriceJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookPriceJLabel.setBounds(120, 190, 200, 40);
		bookTypeJLabel = new JLabel("�鼮����");
		bookIdJLabel.setFont(new Font(null, Font.BOLD, 28));
		bookTypeJLabel.setBounds(120, 260, 200, 40);
		bookTypeJLabel.setFont(new Font(null, Font.BOLD, 28));

		JLabel bookNumJLabel = new JLabel("�鼮����");
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

		JButton addButton = new JButton("���");
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

			  // ��������鼮��Ϣ��װ��AllBooks������
				AllBooks book = new AllBooks();
				book.setbookId(bookIdString);
				book.setbookName(bookNameString);
				book.setbookPrice(bookPriceString);
				book.setbookType(bookTypeString);
				book.setbookNum(bookNumString);
			int addOk =	new AdminAction().addBooks(book);
			if (addOk == 1) {
				JOptionPane.showMessageDialog(f, "��ӳɹ���", "��ʾ", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(f, "�����������ʧ�ܣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
			}
				addDialog.dispose();
				f.dispose();
				new AddBooks().dispalyBooks();
			}
		});

		JButton cancelButton = new JButton("����");
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

		// ����
		JLabel bgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		bgJLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		panel.add(bgJLabel);

		// ���öԻ�����������
		addDialog.setContentPane(panel);

		addDialog.setVisible(true);
	}

}
