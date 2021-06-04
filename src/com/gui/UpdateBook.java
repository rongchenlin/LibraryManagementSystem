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
	 * �����������Ա�����޸�������鼮��Ϣ
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
		 * �������������ʾ���
		 */

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
		updateButton = new JButton("�޸�");
		updateButton.setForeground(Color.white);
		updateButton.setFont(new Font("Dialog", Font.BOLD, 17));
		updateButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		updateButton.setBounds(280, 50, 100, 40); // ����ĸ߶������southPanel��
		backButton = new JButton("����");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// �޸�
		southPanel.add(updateButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // ������ڰ�ť�ĺ����޸ģ�����ť������

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

		/*************** �¼����� ****************/
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		// �޸İ�ť�޸ļ���
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateButton();
			}
		});

		// ��ѯ��ť�޸ļ���
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose(); // ���ͷ���Դ
				searchBooks(bookname); // �ٴ�����Դ
			}
		});

		// Jframe�޸�
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
		 * ����������ڸ���������������е�ͼ��
		 */
		/******************* ��Ƥ�� *******************/
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		try {
		// ��ı䴰�ڱ߿���ʽ����
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
		updateButton = new JButton("�޸�");
		updateButton.setForeground(Color.white);
		updateButton.setFont(new Font("Dialog", Font.BOLD, 17));
		updateButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		updateButton.setBounds(280, 50, 100, 40); // ����ĸ߶������southPanel��
		backButton = new JButton("����");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// �޸�
		southPanel.add(updateButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // ������ڰ�ť�ĺ����޸ģ�����ť������

		/*************** ������ʾ���ݵı�� ****************/

		booksTableModel = new BooksTableModel(readerId, bookname);

		// �������
		jTable = new JTable(booksTableModel);  // ���
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

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		// �޸İ�ť�޸ļ���
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				updateButton();
			}
		});

		// ��ѯ��ť�޸ļ���
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose(); // ���ͷ���Դ
				searchBooks(bookname); // �ٴ�����Դ
			}
		});

		// Jframe�޸�
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
		 * �������������Ӧ���İ�ť�¼�
		 */
		int row = jTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(f, "��ã���ѡ��ͼ�飡", "��ʾ", JOptionPane.WARNING_MESSAGE);
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
		updateDialog = new JDialog(owner, "�޸��鼮", true);
		// ���öԻ���Ŀ��
		updateDialog.setSize(560, 560);
		// ���öԻ��������ʾ��λ��
		updateDialog.setLocationRelativeTo(parentComponent);
		// ���öԻ����С���ɸı�
		updateDialog.setResizable(false);

		// �����Ի�����������, ������ڿ��Ը����Լ�����Ҫ�޸��κ�������������ǲ���
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, updateDialog.getWidth(), updateDialog.getHeight());
		// �޸���������

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

		bookIdField = new JTextField(book.getbookId());
		String bookId = book.getbookId();  // ��ס�ɵ�ID
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

		JButton updateButton = new JButton("�޸�");
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
					JOptionPane.showMessageDialog(f, "�޸ĳɹ���", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(f, "�޸�ʧ�ܣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				
				updateDialog.dispose();
				f.dispose();
				new UpdateBook().dispalyBooks();
			}

		});

		JButton cancelButton = new JButton("����");
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

		// ����
		JLabel bgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		bgJLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		panel.add(bgJLabel);

		// ���öԻ�����������
		updateDialog.setContentPane(panel);
		updateDialog.setVisible(true);
	}

}
