package main;

import java.io.*;
import java.util.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class server extends JFrame implements ActionListener{
	JPanel basePane, panel_main, panel_textarea, panel_btn;
	JScrollPane scrollpane;
	JTextArea textarea;
	JLabel serverstatus_label;
	JButton serverstart_btn, serverclose_btn;
	
	boolean gamestart;
	LinkedHashMap<String, DataOutputStream> clientList = new LinkedHashMap<String, DataOutputStream>();
	LinkedHashMap<String, Integer> clientInfo = new LinkedHashMap<String, Integer>();
	
	ServerSocket ss;
	Socket s;
	int port=7777;
	public static final int MAX_CLIENT=4;
	
	public void init() {
		setTitle("SERVER");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,400,400);
		setLocationRelativeTo(null);
		
		basePane = new JPanel();
		basePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(basePane);
		basePane.setLayout(new GridLayout(0, 1, 10, 0));
		
		panel_main = new JPanel();
		basePane.add(panel_main);
		panel_main.setLayout(new BoxLayout(panel_main,BoxLayout.Y_AXIS));
		
		panel_btn = new JPanel();
		panel_btn.setPreferredSize(new Dimension(10, 43));
		panel_btn.setAutoscrolls(true);
		panel_main.add(panel_btn);
		panel_btn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		serverstart_btn = new JButton(" 서버 시작 ");
		serverstart_btn.setHorizontalTextPosition(SwingConstants.CENTER);
		serverstart_btn.setPreferredSize(new Dimension(120, 40));
		serverstart_btn.setFocusPainted(false);
		serverstart_btn.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		serverstart_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		serverstart_btn.setForeground(Color.WHITE);
		serverstart_btn.setBackground(Color.DARK_GRAY);
		serverstart_btn.setBorder(null);
		panel_btn.add(serverstart_btn);
		serverstart_btn.addActionListener(this);
		
		serverclose_btn = new JButton(" 서버 종료 ");
		serverclose_btn.setHorizontalTextPosition(SwingConstants.CENTER);
		serverclose_btn.setPreferredSize(new Dimension(120, 40));
		serverclose_btn.setFocusPainted(false);
		serverclose_btn.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		serverclose_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		serverclose_btn.setForeground(Color.WHITE);
		serverclose_btn.setBackground(Color.DARK_GRAY);
		serverclose_btn.setBorder(null);
		panel_btn.add(serverclose_btn);
		serverclose_btn.addActionListener(this);
		serverclose_btn.setEnabled(false);
		
		serverstatus_label = new JLabel("[ Server Waiting .. ]");
		serverstatus_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		serverstatus_label.setPreferredSize(new Dimension(96, 50));
		panel_main.add(serverstatus_label);
		serverstatus_label.setHorizontalTextPosition(SwingConstants.CENTER);
		serverstatus_label.setHorizontalAlignment(SwingConstants.CENTER);
		serverstatus_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		
		panel_textarea = new JPanel();
		panel_main.add(panel_textarea);
		panel_textarea.setLayout(new BorderLayout(0, 0));
		
		scrollpane = new JScrollPane();
		scrollpane.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_textarea.add(scrollpane);
		
		textarea = new JTextArea();
		textarea.setLineWrap(true);
		textarea.setEditable(false);
		scrollpane.setViewportView(textarea);
		
		basePane.setBackground(new Color(247, 243, 222));
		panel_main.setBackground(new Color(247, 243, 222));
		panel_textarea.setBackground(new Color(247, 243, 222));
		panel_btn.setBackground(new Color(247, 243, 222));
	}
	
	public void actionPerformed(ActionEvent e){ // '서버 시작 & 종료' 버튼
		if(e.getSource() == serverstart_btn){
			new Thread(){
				public void run() {
					try{
						Collections.synchronizedMap(clientList);
						ss = new ServerSocket(port);
						serverstatus_label.setText("[ Server Started ]");
						textarea.append("[ 서버가 시작되었습니다 ]" + "\n");
						serverstart_btn.setEnabled(false);
						serverclose_btn.setEnabled(true);
						while(true){
							s = ss.accept();
							if ((clientList.size())+1>MAX_CLIENT || gamestart == true){
								s.close();
							}else{
								//Thread gm = new GameManager(s);
								//gm.start();
							}
							
						}
					}catch(IOException io){}
				}
			}.start();
		}else if(e.getSource() == serverclose_btn){
			int select = JOptionPane.showConfirmDialog(null, "서버를 정말 종료하시겠습니까?", "JAVA CatchMind Server", JOptionPane.OK_CANCEL_OPTION);
			try{
				if(select == JOptionPane.YES_OPTION){
					ss.close();
					serverstatus_label.setText("[ Server Closed ]");
					textarea.append("[ 서버가 종료되었습니다 ]" + "\n");
					serverstart_btn.setEnabled(true);
					serverclose_btn.setEnabled(false);
				}
			}catch(IOException io){}
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server sv = new server();
					sv.init();
					sv.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}