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
	 * ��������ڹ���Ա�Զ�����Ϣ���й���
	 * ����ģ�����£�
	 *		1. �鿴������Ϣ : dispalyreaders();
	 *		2. ����������Ϣ : searchReaders(String readerName);
	 *		3. �޸Ķ�����Ϣ : manageButton();						
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
		 * �������������ʾ������Ϣ
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
		f = new JFrame("ͼ���޸�");
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
		manageButton = new JButton("�޸�");
		manageButton.setForeground(Color.white);
		manageButton.setFont(new Font("Dialog", Font.BOLD, 17));
		manageButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		manageButton.setBounds(280, 50, 100, 40); // ����ĸ߶������southPanel��
		backButton = new JButton("����");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// ���
		southPanel.add(manageButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // ������ڰ�ť�ĺ�����ӣ�����ť������

		/*************** ������ʾ���ݵı�� ****************/

		readerTableModel = new ReaderTableModel(readerId);
		
		// �������
		jTable = new JTable(readerTableModel);
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

		// �����û���ѯ���޸ĵ����
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBounds(0, 0, 1000, 200);

		// �������ؼ�
		readerNameJlabel = new JLabel("��������:");
		readerNameJlabel.setFont(new Font(null, Font.BOLD, 28));
		readerNameJlabel.setForeground(Color.blue);
		readerNameJlabel.setBounds(220, 100, 140, 40);
		northPanel.add(readerNameJlabel);

		readerNameField = new JTextField("");
		readerNameField.setFont(new Font(null, Font.BOLD, 25));
		readerNameField.setForeground(Color.red);
		readerNameField.setBounds(380, 100, 150, 40);
		northPanel.add(readerNameField);

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

		// �޸İ�ť��Ӽ���
		manageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageButton();
			}
		});

		// ��ѯ��ť��Ӽ���
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String readerName = readerNameField.getText();
				f.dispose();      // ���ͷ���Դ  
				searchReaders(readerName);  //�ٴ�����Դ
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

	public void searchReaders(String readerName) {
		/**
		 * ���������������������Ϣ
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
		f = new JFrame("ͼ���޸�");
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
		manageButton = new JButton("�޸�");
		manageButton.setForeground(Color.white);
		manageButton.setFont(new Font("Dialog", Font.BOLD, 17));
		manageButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		manageButton.setBounds(280, 50, 100, 40); // ����ĸ߶������southPanel��
		backButton = new JButton("����");
		backButton.setForeground(Color.white);
		backButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		backButton.setFont(new Font("Dialog", Font.BOLD, 17));
		backButton.setBounds(580, 50, 100, 40);

		// ���
		southPanel.add(manageButton);
		southPanel.add(backButton);
		southPanel.add(soubgJLabel); // ������ڰ�ť�ĺ�����ӣ�����ť������

		/*************** ������ʾ���ݵı�� ****************/

		readerTableModel = new ReaderTableModel(readerId,readerName);
		
		// �������
		jTable = new JTable(readerTableModel);
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

		// �����û���ѯ���޸ĵ����
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBounds(0, 0, 1000, 200);

		// �������ؼ�
		readerNameJlabel = new JLabel("��������:");
		readerNameJlabel.setFont(new Font(null, Font.BOLD, 28));
		readerNameJlabel.setForeground(Color.blue);
		readerNameJlabel.setBounds(220, 100, 140, 40);
		northPanel.add(readerNameJlabel);

		readerNameField = new JTextField("");
		readerNameField.setFont(new Font(null, Font.BOLD, 25));
		readerNameField.setForeground(Color.red);
		readerNameField.setBounds(380, 100, 150, 40);
		northPanel.add(readerNameField);

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

		// �޸İ�ť��Ӽ���
		manageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageButton();
			}
		});

		// ��ѯ��ť��Ӽ���
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String readerName = readerNameField.getText();
				f.dispose();      // ���ͷ���Դ  
				searchReaders(readerName);  //�ٴ�����Դ
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
	 * �������������Ӧ�޸İ�ť�¼�
	 */
	public void manageButton() {
		/**
		 * ����������ڶԶ�����Ϣ�����޸�
		 */
		int row = jTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(f, "��ã���ѡ��Ҫ�޸ĵĶ��ߣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
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
		
		// �����Զ���ĶԻ��������޸Ķ�����Ϣ
		showCustomupdateDialog(f, f,reader);	

	}
	
	public void showCustomupdateDialog(JFrame owner, Component parentComponent,ReaderInfor readerInfor) {
		/**
		 * ����Զ���Ի��������޸Ķ�����Ϣ
		 */
		updateDialog = new JDialog(owner, "����鼮", true);
		// ���öԻ���Ŀ��
		updateDialog.setSize(560, 560);
		// ���öԻ��������ʾ��λ��
		updateDialog.setLocationRelativeTo(parentComponent);
		// ���öԻ����С���ɸı�
		updateDialog.setResizable(false);

		// �����Ի�����������, ������ڿ��Ը����Լ�����Ҫ����κ�������������ǲ���
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, updateDialog.getWidth(), updateDialog.getHeight());
		// �����������

	    readerIdJLabel = new JLabel("���߱��");
	    readerIdJLabel.setFont(new Font(null, Font.BOLD, 28));
	    readerIdJLabel.setBounds(120, 50, 200, 40);
		readerNameJlabel = new JLabel("��������");
		readerNameJlabel.setFont(new Font(null, Font.BOLD, 28));
		readerNameJlabel.setBounds(120, 120, 200, 40);
		readerSexJLabel = new JLabel("�����Ա�");
		readerSexJLabel .setFont(new Font(null, Font.BOLD, 28));
		readerSexJLabel .setBounds(120, 190, 200, 40);
		readerPwJLabel = new JLabel("��������");
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
	

		JButton updateButton = new JButton("�޸�");
		updateButton.setBounds(130, 400, 100, 40);
		panel.add(updateButton);
		
		
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String readerIdString = readerIdField.getText();
				String readerNameString = readerNameField.getText();
				String readerSexString = readerSexField.getText();
				String readerpxString = readerPwField.getText();
			 
				// ���޸ĵ���Ϣ����ReaderInfor
				ReaderInfor reader = new ReaderInfor();
				reader.setreaderId(readerIdString);
				reader.setreaderName(readerNameString);
				reader.setreaderSex(readerSexString);
				reader.setreaderPassword(readerpxString);
				
			int addOk =	new ReaderAction(readerIdString).updateReaderInfor(reader);
			if (addOk == 1) {
				JOptionPane.showMessageDialog(f, "�޸ĳɹ���", "��ʾ", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(f, "�����������ʧ�ܣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
			}
				updateDialog.dispose();
				f.dispose();
				new ReaderManage().dispalyreaders();
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

		panel.add(readerIdField);
		panel.add(readerNameField);
		panel.add(readerSexField);
		panel.add(readerPwField);

		panel.add(readerIdJLabel);
		panel.add(readerNameJlabel);
		panel.add(readerPwJLabel);
		panel.add(readerSexJLabel);

		// ����
		JLabel bgJLabel = new JLabel(new ImageIcon("image/������.jpg"));
		bgJLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		panel.add(bgJLabel);

		// ���öԻ�����������
		updateDialog.setContentPane(panel);
		updateDialog.setVisible(true);
	}


}
