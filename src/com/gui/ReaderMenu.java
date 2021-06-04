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
import com.database.ReaderAction;
import com.entity.ReaderInfor;

public class ReaderMenu {
	/**
	 * ������Ƕ��߲������棬������������Ϣ���������顱�������顱�����˳�����ť���¼�����
	 */
	private String readerId;
	private JPanel readerPane;
	private JFrame jf;

	public ReaderMenu(String readerId) {

		this.readerId = readerId;
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
		
		/**********************����**************************/
		jf = new JFrame("�û�����");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setBounds(400, 100, 1000, 780);
		readerPane = new JPanel();
		readerPane.setLayout(null);
		readerPane.setBounds(0, 0, jf.getWidth(), jf.getHeight());
		jf.add(readerPane);
		jf.setResizable(false);
		
		ReaderInfor infor = new ReaderInfor();
		infor = new ReaderAction(readerId).getReaderInfor();
		JLabel welcomeJLabel = new JLabel("��ӭ����"+" "+infor.getreaderName());
		welcomeJLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		welcomeJLabel.setBounds(0, 0, 300, 30);
		readerPane.add(welcomeJLabel);
		
		// ��������Ϣ����ť���¼�����
		JButton inforButton = new JButton("������Ϣ");
		inforButton.setFont(new Font("Dialog", Font.BOLD, 28));
		inforButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		inforButton.setForeground(Color.white);
		inforButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReaderMessage(readerId).showMessage();

			}
		});
		inforButton.setBounds(160, 180, 160, 60);
		readerPane.add(inforButton);

		// �����ġ���ť���¼�����
		JButton borrowButton = new JButton("����");
		borrowButton.setFont(new Font("Dialog", Font.BOLD, 28));
		borrowButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		borrowButton.setForeground(Color.white);
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BorrowBooks(readerId).dispalyBooks(); // ������id����ȥ�����ڽ���
			}
		});
		borrowButton.setBounds(160, 270, 160, 60);
		readerPane.add(borrowButton);

		// �����顱��ť���¼�����
		JButton returnButton = new JButton("����");
		returnButton.setFont(new Font("Dialog", Font.BOLD, 28));
		returnButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		returnButton.setForeground(Color.white);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReturnBooks(readerId).dispalyBooks();
			}
		});
		returnButton.setBounds(160, 360, 160, 60);
		readerPane.add(returnButton);

		// ���˳�����ť���¼�����
		JButton outButton = new JButton("�˳�");
		outButton.setFont(new Font("Dialog", Font.BOLD, 28));
		outButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		outButton.setForeground(Color.white);
		outButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				jf.dispose();
			}
		});
		outButton.setBounds(160, 450, 160, 60);
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

		// ���ñ���
		JLabel background1 = new JLabel(new ImageIcon("image/���߽���.jpg"));
		background1.setBounds(0, 0, readerPane.getWidth(), readerPane.getHeight());
		readerPane.add(background1);
		jf.setVisible(true);
	}
}
