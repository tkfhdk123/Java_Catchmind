package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.sound.sampled.*;
import java.io.*;
import java.net.*;

public class client extends JFrame implements ActionListener{
	JPanel contentPane, panel_main, panel_chat, panel_exam, panel_canvas, panel_option;
	JButton btn_ready, btn_exit, btn_c1, btn_c2, btn_c3, btn_c4, btn_c5, btn_erase, btn_eraseAll, btn_GG;
	JLabel label_canvas, label_exam, label_exam_nickname, label_timer, label_player1, label_player2, label_player3, label_player4;
	Label label_player1_nickname, label_player2_nickname, label_player3_nickname, label_player4_nickname;
	Label label_player1_score, label_player2_score, label_player3_score, label_player4_score;
	JTextField txt_field;
	JTextArea txt_area;
	JScrollPane scrollPane;
	Canvas canvas;
	Color color;
	Graphics g;
	Graphics2D g2d;
	
	int port = 7777;
	String playerName, playerScore, playerIdx;
	boolean gameStart, auth;
	
	public client(){
		setFont(new Font("나눔바른고딕", Font.PLAIN, 13)); //글꼴 : 나눔바른고딕, 폰트 : 평평하게, 크기 : 13
		setTitle("JAVA CatchMind Client Ver.181017"); //제목
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //끌때 깔끔하게 꺼짐
		setBounds(100, 100, 1280, 720); //x, y, width, height
		setLocationRelativeTo(null); //스크린 중간에 배치
		setVisible(true); //화면을 볼 수 있게
		setResizable(false); //화면을 확대, 축소 불가능
		
		contentPane = new JPanel(); //contentpanel을 만든다.
		contentPane.setBorder(null); //
		setContentPane(contentPane); //contentpanel을 contentPane으로 해준다
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS)); //box 레이아웃
		
		panel_main = new JPanel();
		panel_main.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		panel_main.setBackground(new Color(247, 243, 222));
		contentPane.add(panel_main);
		panel_main.setLayout(null);
		
		// 참여자 목록 영역 -- 게임하는 사람들
		JPanel panel_ClientList = new JPanel();
		panel_ClientList.setBackground(new Color(247, 243, 222));
		panel_ClientList.setBorder(new LineBorder(Color.white, 4, true));
		panel_ClientList.setBounds(10, 95, 156, 570);
		panel_main.add(panel_ClientList);
		panel_ClientList.setLayout(null);
		
		label_player1 = new JLabel(new ImageIcon("image\\p1.png"));
		label_player1.setBackground(Color.GRAY);
		label_player1.setBounds(18, 15, 120, 80);
		panel_ClientList.add(label_player1);
		
		label_player1_nickname = new Label("[nickname]");
		label_player1_nickname.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		label_player1_nickname.setAlignment(Label.CENTER);
		label_player1_nickname.setBackground(Color.WHITE);
		label_player1_nickname.setBounds(18, 95, 120, 18);
		panel_ClientList.add(label_player1_nickname);
		
		
		label_player1_score = new Label("[score]");
		label_player1_score.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		label_player1_score.setAlignment(Label.CENTER);
		label_player1_score.setBackground(Color.WHITE);
		label_player1_score.setBounds(18, 117, 120, 18);
		panel_ClientList.add(label_player1_score);
		
		
		label_player2 = new JLabel(new ImageIcon("image\\p2.png"));
		label_player2.setBackground(Color.GRAY);
		label_player2.setBounds(18, 155, 120, 80);
		panel_ClientList.add(label_player2);
		
		label_player2_nickname = new Label("[nickname]");
		label_player2_nickname.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		label_player2_nickname.setAlignment(Label.CENTER);
		label_player2_nickname.setBackground(Color.WHITE);
		label_player2_nickname.setBounds(18, 235, 120, 18);
		panel_ClientList.add(label_player2_nickname);
		
		label_player2_score = new Label("[score]");
		label_player2_score.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		label_player2_score.setAlignment(Label.CENTER);
		label_player2_score.setBackground(Color.WHITE);
		label_player2_score.setBounds(18, 257, 120, 18);
		panel_ClientList.add(label_player2_score);
		
		label_player3 = new JLabel(new ImageIcon("image\\p3.png"));
		label_player3.setBackground(Color.GRAY);
		label_player3.setBounds(18, 295, 120, 80);
		panel_ClientList.add(label_player3);
		
		label_player3_nickname = new Label("[nickname]");
		label_player3_nickname.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		label_player3_nickname.setAlignment(Label.CENTER);
		label_player3_nickname.setBackground(Color.WHITE);
		label_player3_nickname.setBounds(18, 375, 120, 18);
		panel_ClientList.add(label_player3_nickname);
		
		label_player3_score = new Label("[score]");
		label_player3_score.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		label_player3_score.setAlignment(Label.CENTER);
		label_player3_score.setBackground(Color.WHITE);
		label_player3_score.setBounds(18, 397, 120, 18);
		panel_ClientList.add(label_player3_score);
		
		label_player4 = new JLabel(new ImageIcon("image\\p4.png"));
		label_player4.setBackground(Color.GRAY);
		label_player4.setBounds(18, 435, 120, 80);
		panel_ClientList.add(label_player4);
		
		label_player4_nickname = new Label("[nickname]");
		label_player4_nickname.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		label_player4_nickname.setAlignment(Label.CENTER);
		label_player4_nickname.setBackground(Color.WHITE);
		label_player4_nickname.setBounds(18, 515, 120, 18);
		panel_ClientList.add(label_player4_nickname);
		
		label_player4_score = new Label("[score]");
		label_player4_score.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		label_player4_score.setAlignment(Label.CENTER);
		label_player4_score.setBackground(Color.WHITE);
		label_player4_score.setBounds(18, 537, 120, 18);
		panel_ClientList.add(label_player4_score);
		
		panel_option = new JPanel();
		panel_option.setOpaque(true);
		panel_option.setBackground(Color.WHITE);
		panel_option.setBounds(176, 10, 1050, 80);
		panel_main.add(panel_option);
		panel_option.setLayout(null);
		
		btn_c1 = new JButton(new ImageIcon("image\\red.png"));
		btn_c1.setPressedIcon(new ImageIcon());
		btn_c1.setFocusPainted(false);
		btn_c1.setContentAreaFilled(false);
		btn_c1.setBorder(null);
		btn_c1.setBounds(20,8, 64, 64);
		panel_option.add(btn_c1);
		//btn_c1.addActionListener(ch);
		
		btn_c2 = new JButton(new ImageIcon("image\\green.png"));
		btn_c2.setPressedIcon(new ImageIcon());
		btn_c2.setFocusPainted(false);
		btn_c2.setContentAreaFilled(false);
		btn_c2.setBorder(null);
		btn_c2.setBounds(135, 8, 64, 64);
		panel_option.add(btn_c2);
		//btn_c2.addActionListener(ch);
		
		btn_c3 = new JButton(new ImageIcon("image\\blue.png"));
		btn_c3.setPressedIcon(new ImageIcon());
		btn_c3.setFocusPainted(false);
		btn_c3.setContentAreaFilled(false);
		btn_c3.setBorder(null);
		btn_c3.setBounds(250,8, 64, 64);
		panel_option.add(btn_c3);
		//btn_c3.addActionListener(ch);
		
		btn_c4 = new JButton(new ImageIcon("image\\yellow.png"));
		btn_c4.setPressedIcon(new ImageIcon());
		btn_c4.setFocusPainted(false);
		btn_c4.setContentAreaFilled(false);
		btn_c4.setBorder(null);
		btn_c4.setBounds(365, 8, 64, 64);
		panel_option.add(btn_c4);
		//btn_c4.addActionListener(ch);
		
		btn_c5 = new JButton(new ImageIcon("image\\black.png"));
		btn_c5.setPressedIcon(new ImageIcon());
		btn_c5.setFocusPainted(false);
		btn_c5.setContentAreaFilled(false);
		btn_c5.setBorder(null);
		btn_c5.setBounds(480, 8, 64, 64);
		panel_option.add(btn_c5);
		//btn_c5.addActionListener(ch);
		
		btn_erase = new JButton(new ImageIcon("image\\eraser.png"));
		btn_erase.setPressedIcon(new ImageIcon());
		btn_erase.setFocusPainted(false);
		btn_erase.setContentAreaFilled(false);
		btn_erase.setBorder(null);
		btn_erase.setBounds(595, 8, 64, 64);
		panel_option.add(btn_erase);
		//btn_erase.addActionListener(ch);
		
		btn_eraseAll = new JButton(new ImageIcon("image\\eraseAll.png"));
		btn_eraseAll.setPressedIcon(new ImageIcon());
		btn_eraseAll.setFocusPainted(false);
		btn_eraseAll.setContentAreaFilled(false);
		btn_eraseAll.setBorder(null);
		btn_eraseAll.setBounds(710, 8, 64, 64);
		panel_option.add(btn_eraseAll);
		//btn_eraseAll.addActionListener(ch);
		
		JLabel stopwatch = new JLabel(new ImageIcon("image\\stopwatch.png"));
		stopwatch.setOpaque(true);
		label_timer = new JLabel("00 : 00");
		label_timer.setHorizontalTextPosition(SwingConstants.CENTER);
		label_timer.setHorizontalAlignment(SwingConstants.CENTER);
		label_timer.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		label_timer.setForeground(Color.BLACK);
		stopwatch.setBounds(10, 25, 45, 45);
		label_timer.setBounds(60, 10, 96, 80);
		label_timer.setBackground(Color.WHITE);
		panel_main.add(stopwatch);
		panel_main.add(label_timer);
		
		btn_ready = new JButton(new ImageIcon("image\\ready.png"));
		btn_ready.setPressedIcon(new ImageIcon());
		btn_ready.setFocusPainted(false);
		btn_ready.setBorderPainted(false);
		btn_ready.setContentAreaFilled(false);
		btn_ready.setBounds(840, 11, 64, 64);
		panel_option.add(btn_ready);
		
		btn_exit = new JButton(new ImageIcon("image\\exit.png"));
		btn_exit.setPressedIcon(new ImageIcon());
		btn_exit.setFocusPainted(false);
		btn_exit.setBorderPainted(false);
		btn_exit.setContentAreaFilled(false);
		btn_exit.setBounds(960, 11, 64,64);
		panel_option.add(btn_exit);
		//btn_exit.addActionListener(this);
		
	}
	public void startchat() {
		
	}
	public void actionPerformed(ActionEvent e) {
		
	}
	class Sender extends Thread implements KeyListener, ActionListener, MouseMotionListener {
		
	}
	class Listener extends Thread {
		
	}
}
