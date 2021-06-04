package com.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jb2011.lnf.beautyeye.*;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import com.database.AdminAction;
import com.entity.AdminInfor;

public class AdminMenu {
	/**
	 * ��������ڹ���Ա���в����Ľ���
	 * �������������
	 * 		1. ע�����
	 * 		2. �޸Ķ�����Ϣ
	 * 		3. ����ͼ��
	 * 		4. �޸�ͼ��
	 */
    private String adminId;
	private JPanel readerPane;
	private JFrame jf;

	public AdminMenu(String adminId) {

		this.adminId = adminId;
		
		/******************* ��Ƥ�� *******************/
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
		
		/******************* ���ý��� *******************/
		jf = new JFrame("�û�����");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setBounds(400, 100, 1000, 780);
		readerPane = new JPanel();
		readerPane.setLayout(null);
		readerPane.setBounds(0, 0, jf.getWidth(), jf.getHeight());
		jf.add(readerPane);
		jf.setResizable(false);
		
		AdminInfor infor = new AdminInfor();
		infor = new AdminAction().getAdminInfor(adminId);
		JLabel welcomeJLabel = new JLabel("��ӭ����"+" "+infor.getadminName());
		welcomeJLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		welcomeJLabel.setBounds(0, 0, 300, 30);
		readerPane.add(welcomeJLabel);
		
		// ע����ߵİ�ť���¼�
		JButton inforButton = new JButton("����ע��");
		inforButton.setFont(new Font("Dialog", Font.BOLD, 28));
		inforButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		inforButton.setForeground(Color.white);
		inforButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddReader().showMessage();
			}
		});
		inforButton.setBounds(160, 180, 160, 60);
		readerPane.add(inforButton);

		// ���������Ϣ�İ�ť���¼�
		JButton mangeButton = new JButton("���߹���");
		mangeButton.setFont(new Font("Dialog", Font.BOLD, 28));
		mangeButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		mangeButton.setForeground(Color.white);
		mangeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ReaderManage().dispalyreaders(); 
			}
		});
		mangeButton.setBounds(160, 270, 160, 60);
		readerPane.add(mangeButton);

		// �����鼮�İ�ť���¼�
		JButton returnButton = new JButton("����ͼ��");
		returnButton.setFont(new Font("Dialog", Font.BOLD, 28));
		returnButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		returnButton.setForeground(Color.white);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddBooks().dispalyBooks();
			}
		});
		returnButton.setBounds(160, 360, 160, 60);
		readerPane.add(returnButton);
		
		// �޸�ͼ��İ�ť���¼�
		JButton updateButton = new JButton("ͼ���޸�");
		updateButton.setFont(new Font("Dialog", Font.BOLD, 28));
		updateButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		updateButton.setForeground(Color.white);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateBook().dispalyBooks();
			}
		});
		updateButton.setBounds(160, 450, 160, 60);
		readerPane.add(updateButton);

		// �˳��İ�ť���¼�
		JButton outButton = new JButton("�˳�");
		outButton.setFont(new Font("Dialog", Font.BOLD, 28));
		outButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		outButton.setForeground(Color.white);
		outButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				jf.dispose();
			}
		});
		outButton.setBounds(160, 540, 160, 60);
		readerPane.add(outButton);

		// ʹ�ö��̣߳���ʾʱ��
		JLabel timeJLabel = new JLabel();
		timeJLabel.setFont(new Font("΢���ź�", Font.BOLD, 28));
		Timer timeAction = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long timemillis = System.currentTimeMillis();
				// ת��������ʾ��ʽ
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				timeJLabel.setText(df.format(new Date(timemillis)));
			}
		});
		timeAction.start();
		timeJLabel.setBounds(680, 0, 300, 30);
		readerPane.add(timeJLabel);

		// ����
		JLabel background1 = new JLabel(new ImageIcon("image/����Ա��������.jpg"));
		background1.setBounds(0, 0, readerPane.getWidth(), readerPane.getHeight());
		readerPane.add(background1);

		jf.setVisible(true);
	}
}
