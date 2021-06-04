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
	 * ��������ڶ��߽����鼮����������ʾ�����Ϣ�������鼮�������鼮
	 * ģ�����£�
	 * 		1. ��ʾ��� : dispalyBooks();
	 * 		2. �����鼮 : searchBooks(String bookname);
	 * 		3. �����鼮 : borrowButton();
	 * ���ĵĹ��̣�
	 * 		���Ҫ���ĵ���->�鿴֮ǰ�Ƿ���->�Ƿ��п��->���н��Ĳ���
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

		/******************* ��Ƥ�� *******************/
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		try
	    {
	        //���ñ����Խ��ı䴰�ڱ߿���ʽ����
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
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
		borrowButton = new JButton("����");
		borrowButton.setForeground(Color.white);
		borrowButton.setFont(new Font("Dialog", Font.BOLD, 17));
		borrowButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		borrowButton.setBounds(280, 50, 100, 40);
		backButton = new JButton("����");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// ���
		southPanel.add(borrowButton);
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

		// ���İ�ť��Ӽ���
		borrowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrowButton();
			}
		});

		// ��ѯ��ť��Ӽ���
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose();      // ���ͷ���Դ  
				searchBooks(bookname);  //�ٴ�����Դ
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
		try
	    {
	        //���ñ����Խ��ı䴰�ڱ߿���ʽ����
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
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
		borrowButton = new JButton("����");
		borrowButton.setForeground(Color.white);
		borrowButton.setFont(new Font("Dialog", Font.BOLD, 17));
		borrowButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		borrowButton.setBounds(280, 50, 100, 40); // ����ĸ߶������southPanel��
		backButton = new JButton("����");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// ���
		southPanel.add(borrowButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // ������ڰ�ť�ĺ�����ӣ�����ť������

		/*************** ������ʾ���ݵı�� ****************/

		booksTableModel = new BooksTableModel(readerId,bookname);

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

		// ���İ�ť��Ӽ���
		borrowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrowButton();
			}
		});

		// ��ѯ��ť��Ӽ���
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = bookNameField.getText();
				f.dispose();      // ���ͷ���Դ  
				searchBooks(bookname);  //�ٴ�����Դ
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

	public void borrowButton() {
		/**
		 * ����������ġ���ťʱ�������������
		 */
		
		int row = jTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(f, "��ã���ѡ��ͼ�飡", "��ʾ", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// ��ȡ��ѡ�����һ�е��鱾ʣ����
		String bookNumString = (String) booksTableModel.getValueAt(row, 2);
		int bookNum = Integer.parseInt(bookNumString);  
		if (bookNum > 0) {  // ��Ҫ����黹�п��
			String bookId = booksTableModel.getValueAt(row, 0).toString();
			ReaderAction readerAction = new ReaderAction(readerId);
			// �Ȳ鿴�Ȿ�����֮ǰ�Ƿ���
			List<AllBooks> ab = readerAction.searchBorrowedBooks(bookId); 
			if (ab.size() == 0) {  // ���֮ǰû�н��
				int borrowOk = readerAction.borrowBooks(bookId);  // ���н��Ĳ���
				if (borrowOk == 1) {
					// ������Ϊ��������21���ڹ黹
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//�������ڸ�ʽ
					Date date = new Date();
					Calendar calendar = Calendar.getInstance();//��������
					calendar.setTime(date);//���õ�ǰ����
					calendar.add(Calendar.DAY_OF_MONTH, +21);// ����+21
					String nextString = df.format(calendar.getTime()).toString();
					JOptionPane.showMessageDialog(f, "        ���ĳɹ���\n����"+nextString+"֮ǰ���飡"
							, "��ʾ", JOptionPane.WARNING_MESSAGE);

					// ˢ�½���
					f.dispose();
					new BorrowBooks(readerId).dispalyBooks();
				} else {
					JOptionPane.showMessageDialog(f, "����ʧ�ܣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}

			} else {
				JOptionPane.showMessageDialog(f, "�����Ѿ�����", "��ʾ", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(f, "�Բ��𣬸�ͼ���ѱ��������", "��ʾ", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

}
