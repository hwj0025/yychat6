package com.yychatclient.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.*;

public class FriendList extends JFrame implements ActionListener,MouseListener{//��������
	public static HashMap hmFriendChat1=new HashMap<String,FriendChat1>();//��ֵ��
	
	CardLayout cardLayout;//��Ƭ����
	//�����������ñ���
	JPanel myFriendPanel;
	JButton myFriendJButton;
	
	JScrollPane myFriendJScrollPane;
	JPanel myFriendListJPanel;
	static final int FRIENDCOUNT=51;
	JLabel[] myFriendJLabel=new JLabel[FRIENDCOUNT];//��������?
	//myFriendJLabel[0]...myFriendJLabel[50] ÿһ���������ñ���
	
	JPanel myStrangerBlackListJPanel;
	JButton myStrangerJButton;
	JButton blackListJButton;
	
	JPanel myStrangerPanel;
	
	JPanel myFriendStrangerPanel;
	JButton myFriendJButton1;
	JButton myStrangerJButton1;
	
	JButton blackListJButton1;
	
	String userName;
	
	public FriendList(String userName){
		this.userName=userName;//�ֲ���������Ա������ֵ
		
		//��һ�ſ�Ƭ����������
		myFriendPanel=new JPanel(new BorderLayout());//�߽粼��
		//System.out.println(myFriendPanel.getLayout());
		
		myFriendJButton=new JButton("�ҵĺ���");		
		myFriendPanel.add(myFriendJButton,"North");
		
		//�в�
		/*JScrollPane myFriendJScrollPane;
		JPanel myFriendListJPanel;
		static final int FRIENDCOUNT=51;
		JLabel[] myFriendJLabel;//��������*/
		myFriendListJPanel=new JPanel(new GridLayout(FRIENDCOUNT-1,1));
		for(int i=1;i<FRIENDCOUNT;i++){
			myFriendJLabel[i]=new JLabel(i+"",new ImageIcon("images/YY1.gif"),JLabel.LEFT);//"1"
			myFriendJLabel[i].setEnabled(false);//δ��������ͼ��
			//�����Լ���ͼ��
			//if(Integer.parseInt(userName)==i) myFriendJLabel[i].setEnabled(true);
			
			myFriendJLabel[i].addMouseListener(this);//������������
			myFriendListJPanel.add(myFriendJLabel[i]);
		}
		myFriendJLabel[Integer.parseInt(userName)].setEnabled(true);
		//myFriendJScrollPane =new JScrollPane();
		//myFriendJScrollPane.add(myFriendListJPanel);
		myFriendJScrollPane =new JScrollPane(myFriendListJPanel);
		myFriendPanel.add(myFriendJScrollPane);
		
		myStrangerBlackListJPanel=new JPanel(new GridLayout(2,1));//���񲼾�
		myStrangerJButton=new JButton("�ҵ�İ����");
		//�����¼�������
		myStrangerJButton.addActionListener(this);
		
		blackListJButton=new JButton("������");
		myStrangerBlackListJPanel.add(myStrangerJButton);
		myStrangerBlackListJPanel.add(blackListJButton);
		myFriendPanel.add(myStrangerBlackListJPanel,"South");
		
		//��һ�ſ�Ƭ
		myStrangerPanel = new JPanel(new BorderLayout());
		
		myFriendStrangerPanel=new JPanel(new GridLayout(2,1));
		myFriendJButton1=new JButton("�ҵĺ���");//���Ӽ�����
		myFriendJButton1.addActionListener(this);
		myStrangerJButton1=new JButton("�ҵ�İ����");
		myFriendStrangerPanel.add(myFriendJButton1);
		myFriendStrangerPanel.add(myStrangerJButton1);
		myStrangerPanel.add(myFriendStrangerPanel,"North");	
		
		blackListJButton1=new JButton("������");
		myStrangerPanel.add(blackListJButton1,"South");
		
		cardLayout=new CardLayout();
		this.setLayout(cardLayout);
		this.add(myFriendPanel,"1");		 
		this.add(myStrangerPanel,"2");
		
		this.setSize(150,500);
		this.setTitle(this.userName+" �ĺ����б�");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		//FriendList friendList=new FriendList();

	}
	public void setEnableFriendIcon(String friendString){
		String[] friendName=friendString.split(" ");
		int count=friendName.length;
		for(int i=0;i<count;i++){
		myFriendJLabel[Integer.parseInt(friendName[i])].setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==myStrangerJButton){
			cardLayout.show(this.getContentPane(), "2");
		}
		if(arg0.getSource()==myFriendJButton1){
			cardLayout.show(this.getContentPane(), "1");
		}		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount()==2){
			JLabel jlbl=(JLabel)arg0.getSource();
			String receiver=jlbl.getText();
			//new FriendChat(this.userName,receiver);
			//new Thread(new FriendChat(this.userName,receiver)).start();//�����߳�
			
			//˼·������ȥ���Һ���������棬���û�ҵ������½����ҵ��Ļ�����ʾ��
			FriendChat1 friendChat1=(FriendChat1)hmFriendChat1.get(userName+"to"+receiver);
			if(friendChat1==null){//Ϊ��˵���ö��󻹲�����
				friendChat1=new FriendChat1(this.userName,receiver);//friendChat1��һ�����������ñ��������ö���
				hmFriendChat1.put(userName+"to"+receiver,friendChat1);//�������HashMap��
			}else{//��Ϊ�գ�ֱ����ʾ�ö���
				friendChat1.setVisible(true);
			}			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel jLabel=(JLabel)e.getSource();
		jLabel.setForeground(Color.red);		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jLabel=(JLabel)e.getSource();
		jLabel.setForeground(Color.black);	
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}