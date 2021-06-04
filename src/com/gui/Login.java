package com.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.database.LoginAction;
import com.entity.AdminInfor;
import com.entity.ReaderInfor;

public class Login implements ActionListener {
	/**
	 * ����ǵ�¼���࣬�̳���ActionListener�ӿ�����ʵ�ּ���
	 * ���ܣ���¼�Ľ��洰�ڣ�����ѡ����ߵ�¼�����Ա��¼
	 */
	private JFrame jf;
	private JPanel firstPanel;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField userTextField;
	private JButton loginButton;
	private JButton exitButton;
	private JLabel background1;
	private JPasswordField passwordField;
	private JComboBox<String> identityBox;
	private JLabel identityJLabel;

	public void showLogin() {
		/*
		 * <p>�������������ʾ��¼����</P> <p>���õ��Ǿ��Բ��ֵķ�ʽ</p>
		 */

		// �����������������ϵͳƤ��
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println("ERROR");
		}		 
		try
	    {
	        //���ñ����Խ��ı䴰�ڱ߿���ʽ����
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
		javax.swing.UIManager.put("RootPane.setupButtonVisible", false);

		/******************* ���ý���*******************/	
		jf = new JFrame("");
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setBounds(400, 100, 1000, 780);
		firstPanel = new JPanel();
		jf.setContentPane(firstPanel);
		firstPanel.setLayout(null); // ���þ��Զ�λ���˾䲻����
		firstPanel.setBounds(0, 0, jf.getWidth(), jf.getHeight());

		// �û���
		userLabel = new JLabel("�û���");
		userLabel.setFont(new Font("����", Font.BOLD, 20));
		userLabel.setBounds(350, 300, 80, 20);
		userLabel.setForeground(Color.BLUE);
		firstPanel.add(userLabel);

		// ����
		passwordLabel = new JLabel("����");
		passwordLabel.setFont(new Font("����", Font.BOLD, 20));
		passwordLabel.setForeground(Color.BLUE);
		passwordLabel.setBounds(350, 350, 80, 20);
		firstPanel.add(passwordLabel);

		// �û����ı���
		userTextField = new JTextField();
		userTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
		userTextField.setBounds(450, 300, 167, 22);
		firstPanel.add(userTextField);
		userTextField.setColumns(10);

		// �����ı���
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 15));
		passwordField.setBounds(450, 350, 167, 22);
		firstPanel.add(passwordField);

		// ���
		identityJLabel = new JLabel("���");
		identityJLabel.setBounds(350, 400, 50, 30);
		identityJLabel.setFont(new Font("����", Font.BOLD, 20));
		identityJLabel.setForeground(Color.blue);
		firstPanel.add(identityJLabel);

		// ���������
		String identity[] = new String[] { "����Ա", "����" };
		identityBox = new JComboBox<String>(identity);
		identityBox.setBounds(450, 400, 167, 30);
		firstPanel.add(identityBox);

		// ��¼��ť
		loginButton = new JButton("��¼");
		loginButton.setFont(new Font("Dialog", Font.BOLD, 17));
		loginButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		loginButton.setForeground(Color.white);
		loginButton.setBounds(360, 480, 98, 36);
		firstPanel.add(loginButton);
		loginButton.addActionListener(this);
		loginButton.setActionCommand("login");

		// �˳���ť�����
		exitButton = new JButton("�˳�");
		exitButton.setFont(new Font("Dialog", Font.BOLD, 17));
		exitButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		exitButton.setForeground(Color.white);
		exitButton.setBounds(520, 480, 98, 36);
		firstPanel.add(exitButton);
		exitButton.addActionListener(this);
		exitButton.setActionCommand("exit");

		// ���ñ���
		background1 = new JLabel(new ImageIcon("image/��¼.jpg"));
		background1.setBounds(0, 0, firstPanel.getWidth(), firstPanel.getHeight());
		firstPanel.add(background1);

		jf.setVisible(true);
			
	}

	/*******************�¼����� *******************/
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("exit")) {  // �˳�
			jf.setVisible(false);
		}

		if (e.getActionCommand().equals("login")) { // ��¼
			loginButton();
		}

	}

	public void loginButton() {
		/**
		 * ����ǵ�¼��ť���¼������ݣ�����д��һ���������ڵ�¼��ť�ļ�����ֱ�ӵ���
		 */
		String identity = (String) identityBox.getSelectedItem();  // ���
		// �˺�
		String userString = userTextField.getText().toString();
		// ����
		@SuppressWarnings("deprecation")
		String passwordString = passwordField.getText().toString();
		LoginAction loginAction = new LoginAction(userString, identity);

		if ("����".equals(identity)) {  // ���ߵ�¼
			if (userString != null && !"".equals(userString)) {  // �˺Ų�Ϊ��
				ReaderInfor readerInfor = new ReaderInfor();
				readerInfor = loginAction.getPassword();  // �����ݿ��ȡ����
				if (!"".equals(readerInfor.getreaderId()) && readerInfor.getreaderId() != null
						&& readerInfor.getreaderPassword().equals(passwordString)) {
					jf.dispose();
					new ReaderMenu(userString); // ȥ���߽���,ͬʱ���ݶ��ߵ�id

				} else {
					JOptionPane.showMessageDialog(
							jf, "�˺��������,�����µ�¼��",
							"��ʾ", JOptionPane.WARNING_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(
						jf, "�˺Ų���Ϊ��", 
						"��ʾ", JOptionPane.WARNING_MESSAGE);
			}
		}else {    // ����Ա��¼
			if (userString != null && !"".equals(userString)) {
				AdminInfor adminInfor =new AdminInfor();
				adminInfor = loginAction.getAdminPassword();  // �����ݿ��ȡ����
				if (!"".equals(adminInfor.getadminId()) && adminInfor.getadminId() != null
						&& adminInfor.getadminPassword().equals(passwordString)) {
					jf.dispose();
					new AdminMenu(adminInfor.getadminId());				

				} else {
					JOptionPane.showMessageDialog(
							jf, "�˺��������,�����µ�¼��", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(jf, "�˺Ų���Ϊ��",
						"��ʾ", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

}
