package com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.database.ReaderAction;
import com.entity.ReaderInfor;

public class ReaderMessage {
	/**
	 * ��������ڶ��߲鿴���޸ġ�������Ϣ��
	 * ģ�飺 1. �޸���Ϣ�� updateInfor();
	 * 		 2. ��ʾ��������Ϣ" : showMessage();
	 */
	private String readerId;
	private JFrame f;
	private JLabel idJLabel;
	private JTextField nameField;
	private JTextField idField;
	private JLabel sexJLabel;
	private JTextField sexField;
	private JButton goBcakButton;
	private JPanel allJPanel;
	private JButton keepButton;
	private JTextField pwField;
	public  ReaderMessage(String readerId) {
		this.readerId = readerId;
	}

	public void showMessage() {
		
		// ��Ƥ��
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
		javax.swing.UIManager.put("RootPane.setupButtonVisible", false);
		
		// ����readerId��ȡ���߶�����Ϣ
		ReaderInfor infor = new ReaderInfor();
		infor = new ReaderAction(readerId).getReaderInfor();

		// ��ʾ������Ϣ
		f = new JFrame("������Ϣ");
		f.setBounds(400, 100, 700, 650);
		f.setLayout(null);
		f.setResizable(false);
		
		allJPanel = new JPanel();
		allJPanel.setLayout(null);
		allJPanel.setBounds(0, 0, f.getWidth(), f.getHeight());
		
		idJLabel = new JLabel("���ĺ�:");
		idJLabel.setBounds(160, 160, 200, 40);
		idJLabel.setFont(new Font("Dialog", 1, 30));
		allJPanel.add(idJLabel);
		
		idField = new JTextField();
		idField.setText(infor.getreaderId());
		idField.setBounds(310, 160, 200, 40);
		idField.setFont(new Font("Dialog", 1,25));
		
		allJPanel.add(idField);
		
		JLabel nameJLabel = new JLabel("����:");
		nameJLabel.setBounds(160, 230, 200, 40);
		nameJLabel.setFont(new Font("Dialog", 1,30));
		allJPanel.add(nameJLabel);		
		nameField = new JTextField();
		nameField.setText(infor.getreaderName());
		nameField.setBounds(310, 230, 200, 40);
		nameField.setFont(new Font("Dialog", 1,25));
		allJPanel.add(nameField);
				
		sexJLabel = new JLabel("�Ա�:");
		sexJLabel.setBounds(160, 300, 200, 40);
		sexJLabel.setFont(new Font("Dialog", 1, 30));	
		allJPanel.add(sexJLabel);
		sexField = new JTextField();
		sexField.setText(infor.getreaderSex());
		sexField.setBounds(310, 300, 200, 40);
		sexField.setFont(new Font("Dialog", 1,25));
		allJPanel.add(sexField);
		
		JLabel pwJLabel = new JLabel("����:");
		pwJLabel.setBounds(160, 370, 200, 40);
		pwJLabel.setFont(new Font("Dialog", 1, 30));		
		allJPanel.add(pwJLabel);
		
		pwField = new JTextField();
		pwField.setText(infor.getreaderPassword());
		pwField.setBounds(310, 370, 200, 40);
		pwField.setFont(new Font("Dialog", 1,25));
		allJPanel.add(pwField);
		
		
		goBcakButton = new JButton("����");
		goBcakButton.setFont(new Font("Dialog",1,25));
		goBcakButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		goBcakButton.setBounds(350, 450, 120, 40);	
		allJPanel.add(goBcakButton);
		
		keepButton = new JButton("����");
		keepButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		keepButton.setFont(new Font("Dialog",1,25));
		keepButton.setBounds(180, 450, 120, 40);	
		allJPanel.add(keepButton);
		
	   // ����
		JLabel bgJLabel = new JLabel(new ImageIcon("image/������Ϣ����.jpg"));
		bgJLabel.setBounds(0, 0,allJPanel.getWidth(), allJPanel.getHeight());
		allJPanel.add(bgJLabel);
		
		// ���˳����롰���¡����¼�����
		goBcakButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		
		keepButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateInfor();
			}
		});			
		f.add(allJPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void updateInfor() {
		/**
		 * ����������ڸ��¶�����Ϣ
		 */
		String sex = sexField.getText();
		String name = nameField.getText();
		String id = idField.getText();
		String pw = pwField.getText();
		
		ReaderInfor readerInfor = new ReaderInfor();
		readerInfor.setreaderId(id);
		readerInfor.setreaderName(name);
		readerInfor.setreaderSex(sex);
		readerInfor.setreaderPassword(pw);
		
		int x = new ReaderAction(readerId).updateReaderInfor(readerInfor);
		if (x > 0) {
			JOptionPane.showMessageDialog(f, "���³ɹ���", "��ʾ", JOptionPane.WARNING_MESSAGE);
		}
		else {
		JOptionPane.showMessageDialog(f, "����ʧ�ܣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
		}
		f.dispose();
	}
	
}
