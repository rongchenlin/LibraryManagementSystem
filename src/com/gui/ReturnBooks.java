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
	 * ��������ڶ��߻���
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
		 * �������������ʾ����ͼ��
		 */

		/******************* ��Ƥ�� *******************/
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}		 
		try
	    {
	     // �ı䴰�ڱ߿���ʽ����
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }

		f = new JFrame("���߻�����...");
		f.setBounds(400, 100, 1000, 780);
		f.setResizable(false);
		f.setLayout(null);

		/******************* ������� *******************/

		// �������
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBounds(0, 0, 1000, 180);
		// ��ʾ���鷽ʽ
		tipJLabel = new JLabel("����Ҫ�����鼮��������ִ�У�");
		tipJLabel.setBounds(100, 20, 350, 50);

		// ���ñ���
		norbgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		norbgJLabel.setBounds(0, 0, 1000, 200);

		// ���
		northPanel.add(norbgJLabel);
		northPanel.add(tipJLabel);

		/******************* �ϲ���� *******************/

		southPanel = new JPanel();
		southPanel.setLayout(null);
		southPanel.setBounds(0, 580, 1000, 220);
		// ����
		soubgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		soubgJLabel.setBounds(0, 0, 1000, 200);

		// ��ť
		returnButton = new JButton("����");
		returnButton.setFont(new Font("Dialog", Font.BOLD, 17));
		returnButton.setForeground(Color.white);
		returnButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		returnButton.setBounds(300, 50, 100, 40); // ����ĸ߶������southPanel��
		backButton = new JButton("����");
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setBounds(580, 50, 100, 40);

		// ���
		southPanel.add(returnButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // ������ڰ�ť�ĺ�����ӣ�����ť������

		/******************* �в���� *******************/
		midJPanel = new JPanel();
		midJPanel.setLayout(null);
		midJPanel.setBounds(0, 180, 1000, 400);

		// ������ʾ���ݵı��
		borrowingBookModel = new BorrowingBookModel(readerId);
		jTable = new JTable(borrowingBookModel);
		// ���ñ���,���������ڰ�ť�ĺ��棬����Ḳ�ǰ�ť
		

		// �����������
		jTable.getTableHeader().setFont(new Font(null, Font.BOLD, 20));
		jTable.getTableHeader().setForeground(Color.BLACK);
		jTable.setRowHeight(30);
		jTable.setForeground(Color.BLACK); // ������ɫ
		jTable.setFont(new Font(null, Font.BOLD, 20)); // ������ʽ
		jTable.setSelectionForeground(Color.blue); // ѡ�к�������ɫ
		jTable.setSelectionBackground(Color.LIGHT_GRAY); // ѡ�к����屳��

		sp1 = new JScrollPane(jTable);   // JScrollPane��������setLayout(null)
		sp1.setBounds(100, 00, 800, 400);
		midJPanel.add(sp1);
		

		JPanel midJPanel2 = new JPanel();
		midJPanel2.setLayout(null);
		midJPanel2.setBounds(0, 180, 100, 400);
		JLabel leftbgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		leftbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel2.add(leftbgJLabel);
		f.add(midJPanel2);
		
		
		JPanel midJPanel3 = new JPanel();
		midJPanel3.setLayout(null);
		midJPanel3.setBounds(900, 180, 100, 400);
		JLabel rightbgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		rightbgJLabel.setBounds(0, 0, 100, 400);
		midJPanel3.add(rightbgJLabel);
		f.add(midJPanel3);
		
		/******************* �¼����� *******************/
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
			}
		});

		// ���鰴ť��Ӽ���
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnButton();
			}
		});

		// Jframe���
		f.add(southPanel);
		f.add(northPanel);
		f.add(midJPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void returnButton() {
		/**
		 * ������Ӧ������¼�
		 */
		int row = jTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(f, "��ã���ѡ��ͼ�飡", "��ʾ", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String bookId = borrowingBookModel.getValueAt(row, 0).toString();   // ��ȡ��ѡ�е�bookId
		ReaderAction readerAction = new ReaderAction(readerId);
		int returnOk = readerAction.returnBookAction(bookId);   // �����ݿ���ִ�в���
		if (returnOk == 1) {
			JOptionPane.showMessageDialog(
					f, "����ɹ���",
					"��ʾ", JOptionPane.WARNING_MESSAGE);

			// ˢ�½���
			f.dispose();
			new ReturnBooks(readerId).dispalyBooks();
			
		} else {
			JOptionPane.showMessageDialog(
					f, "����ʧ�ܣ�", 
					"��ʾ", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
}
