
package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import javax.sound.sampled.*;
import java.io.*;
import java.net.*;

public class client extends JFrame implements ActionListener{
	JPanel contentPane, panel_main, panel_chat, panel_exam, panel_canvas, panel_option, panel_timer, panel_readyexit;
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
	int game_number = 0;
	
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
		panel_ClientList.setBorder(new LineBorder(new Color(255,255,255), 4, true));
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
		panel_option.setBounds(176, 10, 800, 80);
		panel_option.setBorder(new LineBorder(new Color(247,200,200),3,true));
		panel_main.add(panel_option);
		panel_option.setLayout(null);
		
		panel_canvas = new JPanel();
		panel_canvas.setOpaque(false);
		panel_canvas.setBounds(177, 105, 800, 560);
		panel_main.add(panel_canvas);
		panel_canvas.setLayout(new BorderLayout(0, 0));
		
		canvas = new pen();
		canvas.setBackground(Color.WHITE);
		panel_canvas.add(canvas, BorderLayout.CENTER);
		penController p = new penController(); // 캔버스 핸들러
		canvas.addMouseMotionListener(p);
		
		
		btn_c1 = new JButton(new ImageIcon("image\\red.png"));
		btn_c1.setPressedIcon(new ImageIcon());
		btn_c1.setFocusPainted(false);
		btn_c1.setContentAreaFilled(false);
		btn_c1.setBorder(null);
		btn_c1.setBounds(20,8, 64, 64);
		panel_option.add(btn_c1);
		btn_c1.addActionListener(p);
		
		btn_c2 = new JButton(new ImageIcon("image\\green.png"));
		btn_c2.setPressedIcon(new ImageIcon());
		btn_c2.setFocusPainted(false);
		btn_c2.setContentAreaFilled(false);
		btn_c2.setBorder(null);
		btn_c2.setBounds(135, 8, 64, 64);
		panel_option.add(btn_c2);
		btn_c2.addActionListener(p);
		
		btn_c3 = new JButton(new ImageIcon("image\\blue.png"));
		btn_c3.setPressedIcon(new ImageIcon());
		btn_c3.setFocusPainted(false);
		btn_c3.setContentAreaFilled(false);
		btn_c3.setBorder(null);
		btn_c3.setBounds(250,8, 64, 64);
		panel_option.add(btn_c3);
		btn_c3.addActionListener(p);
		
		btn_c4 = new JButton(new ImageIcon("image\\yellow.png"));
		btn_c4.setPressedIcon(new ImageIcon());
		btn_c4.setFocusPainted(false);
		btn_c4.setContentAreaFilled(false);
		btn_c4.setBorder(null);
		btn_c4.setBounds(365, 8, 64, 64);
		panel_option.add(btn_c4);
		btn_c4.addActionListener(p);
		
		btn_c5 = new JButton(new ImageIcon("image\\black.png"));
		btn_c5.setPressedIcon(new ImageIcon());
		btn_c5.setFocusPainted(false);
		btn_c5.setContentAreaFilled(false);
		btn_c5.setBorder(null);
		btn_c5.setBounds(480, 8, 64, 64);
		panel_option.add(btn_c5);
		btn_c5.addActionListener(p);
		
		btn_erase = new JButton(new ImageIcon("image\\eraser.png"));
		btn_erase.setPressedIcon(new ImageIcon());
		btn_erase.setFocusPainted(false);
		btn_erase.setContentAreaFilled(false);
		btn_erase.setBorder(null);
		btn_erase.setBounds(595, 8, 64, 64);
		panel_option.add(btn_erase);
		btn_erase.addActionListener(p);
		
		btn_eraseAll = new JButton(new ImageIcon("image\\eraseAll.png"));
		btn_eraseAll.setPressedIcon(new ImageIcon());
		btn_eraseAll.setFocusPainted(false);
		btn_eraseAll.setContentAreaFilled(false);
		btn_eraseAll.setBorder(null);
		btn_eraseAll.setBounds(710, 8, 64, 64);
		panel_option.add(btn_eraseAll);
		btn_eraseAll.addActionListener(p);
		
		panel_timer = new JPanel();
		panel_timer.setOpaque(true);
		panel_timer.setBackground(Color.WHITE);
		panel_timer.setBorder(new LineBorder(new Color(247,200,200),3,true));
		panel_main.add(panel_timer);
		panel_timer.setBounds(10, 10,156, 80);
		
		panel_timer.setLayout(null);
		
		JLabel stopwatch = new JLabel(new ImageIcon("image\\stopwatch.png"));
		stopwatch.setOpaque(true);
		label_timer = new JLabel("00 : 00");
		label_timer.setHorizontalTextPosition(SwingConstants.CENTER);
		label_timer.setHorizontalAlignment(SwingConstants.CENTER);
		label_timer.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		label_timer.setForeground(Color.BLACK);
		stopwatch.setBounds(7, 15, 45, 45);
		label_timer.setBounds(55, 2, 96, 80);
		label_timer.setBackground(Color.WHITE);
		panel_timer.add(stopwatch);
		panel_timer.add(label_timer);
		
		panel_readyexit = new JPanel();
		panel_readyexit.setOpaque(true);
		panel_readyexit.setBackground(Color.WHITE);
		panel_readyexit.setBorder(new LineBorder(new Color(247,200,200),3,true));
		panel_readyexit.setBounds(995, 10,255, 80);
		panel_main.add(panel_readyexit);
		panel_readyexit.setLayout(null);
		
		if(game_number != 0) {btn_ready.setEnabled(false);}
		btn_ready = new JButton(new ImageIcon("image\\ready.png"));
		btn_ready.setPressedIcon(new ImageIcon());
		btn_ready.setFocusPainted(false);
		btn_ready.setBorderPainted(false);
		btn_ready.setContentAreaFilled(false);
		btn_ready.setBounds(40, 10, 64, 64);
		panel_readyexit.add(btn_ready);
		
		btn_exit = new JButton(new ImageIcon("image\\exit.png"));
		btn_exit.setPressedIcon(new ImageIcon());
		btn_exit.setFocusPainted(false);
		btn_exit.setBorderPainted(false);
		btn_exit.setContentAreaFilled(false);
		btn_exit.setBounds(150, 10, 64,64);
		panel_readyexit.add(btn_exit);
		btn_exit.addActionListener(this);
		
		JLabel labell_Canvas_Top = new JLabel(new ImageIcon("image\\canvas.png"));
		labell_Canvas_Top.setBounds(176, 105, 802, 34);
		labell_Canvas_Top.setOpaque(true);
		panel_main.add(labell_Canvas_Top);
		
		panel_chat = new JPanel();
		panel_chat.setBounds(992, 105, 263, 567);
		panel_main.add(panel_chat);
		panel_chat.setLayout(null);
		
		scrollPane = new JScrollPane(txt_area);
		scrollPane.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 263, 535);
		panel_chat.add(scrollPane);
		
		txt_area = new JTextArea();
		txt_area.setBorder(new LineBorder(new Color(255,255,255), 3, true));
		txt_area.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		txt_area.setEditable(false);
		//txt_area.setLineWrap(true);
		scrollPane.setViewportView(txt_area);
		txt_area.setBackground(new Color(255,255,255));
		
		txt_field = new JTextField();
		txt_field.setBorder(new LineBorder(new Color(120,120,160), 1, true));
		txt_field.setBackground(new Color(255,255,255));
		txt_field.setBounds(0, 537, 263, 30);
		
		panel_chat.add(txt_field);
		//panel_chat.setBorder(new LineBorder(Color.BLACK,6,true));
		txt_field.setColumns(10);
		
		chatting();
		
	}
	
	public void chatting() {
		String nickname = login.nickname;
		String ip = login.ip;

		try{
			Socket s = new Socket(ip, port);
			Sender sender = new Sender(s, nickname);
			Receiver receiver = new Receiver(s);
			new Thread(sender).start();
			new Thread(receiver).start();
			
			txt_field.addKeyListener(new Sender(s, nickname));
			btn_c1.addActionListener(new Sender(s, nickname));
			btn_c2.addActionListener(new Sender(s, nickname));
			btn_c3.addActionListener(new Sender(s, nickname));
			btn_c4.addActionListener(new Sender(s, nickname));
			btn_c5.addActionListener(new Sender(s, nickname));
			btn_ready.addActionListener(new Sender(s, nickname));
			btn_erase.addActionListener(new Sender(s, nickname));
			btn_exit.addActionListener(new exit());
			btn_eraseAll.addActionListener(new Sender(s, nickname));
			canvas.addMouseMotionListener(new Sender(s, nickname));
			
		}catch(UnknownHostException uh){
			JOptionPane.showMessageDialog(null, "호스트를 찾지 못했습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(IOException io){
			JOptionPane.showMessageDialog(null, "서버 접속 실패!\n서버가 열리지 않았습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	class exit extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn_exit){
				int set;
				set = JOptionPane.showConfirmDialog(null, "게임을 종료하시겠습니까?", "Exit", JOptionPane.OK_CANCEL_OPTION);
				if(set == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				else {}
			}
		}
	}
	
	class Sender extends Thread implements KeyListener, ActionListener, MouseMotionListener {
		DataOutputStream dos;
		Socket s;
		String nickname;

		Sender(Socket s, String nickname){
			this.s = s;
			try{
				dos = new DataOutputStream(s.getOutputStream());
				this.nickname = nickname;
			}catch(IOException io){}
		}

		public void run(){
			try{
				dos.writeUTF(nickname); //nickname을 넘겨줌
			}catch(IOException io){}
		}
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == btn_ready){ // '준비' 버튼을 누르면 15회 게임이 시작됨
				try{
					dos.writeUTF("_Chat " + "[ " + nickname + " 님 준비 완료  ]");
					dos.flush();
					dos.writeUTF("_Ready");
					dos.flush();
					btn_ready.setEnabled(false);
				}catch(IOException io){}
			}else if(e.getSource() == btn_c1){ // 색상 설정 버튼
				try{
					dos.writeUTF("_Color" + "Red");
					dos.flush();
				}catch(IOException io){}
			}else if(e.getSource() == btn_c2){
				try{
					dos.writeUTF("_Color" + "Green");
					dos.flush();
				}catch(IOException io){}
			}else if(e.getSource() == btn_c3){
				try{
					dos.writeUTF("_Color" + "Blue");
					dos.flush();
				}catch(IOException io){}
			}else if(e.getSource() == btn_c4){
				try{
					dos.writeUTF("_Color" + "Yellow");
					dos.flush();
				}catch(IOException io){}
			}else if(e.getSource() == btn_c5){
				try{
					dos.writeUTF("_Color" + "Black");
					dos.flush();
				}catch(IOException io){}
			}else if(e.getSource() == btn_erase){ // '지우기' 버튼
				try{
					dos.writeUTF("_Erase");
					dos.flush();
				}catch(IOException io){}
			}else if(e.getSource() == btn_eraseAll){ // '모두 지우기' 버튼
				try{
					if(auth == true){
						dos.writeUTF("_ErAll");
						dos.flush();
					}
				}catch(IOException io){}
			}
		}
		
		public void keyReleased(KeyEvent e){ // 채팅 입력
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				String chat = txt_field.getText();
				txt_field.setText("");
				try{
					dos.writeUTF("_Chat " + nickname + " : " + chat);
					dos.flush();
				}catch(IOException io){}
			}
		}
		public void keyTyped(KeyEvent e){}
		public void keyPressed(KeyEvent e){}
		
		public void mouseDragged(MouseEvent e){ // 마우스 좌표 전송
		    try{
		    	if(auth == true){
		    		int x = e.getX(); int y = e.getY();
		    		dos.writeUTF("_Mouse" + x + "." + y);
		    		dos.flush();
		    	}
		    }catch(IOException io){}
		}
		public void mousePressed(MouseEvent e){}
		public void mouseMoved(MouseEvent e){}
	}
	class Receiver extends Thread {
		Socket s;
		DataInputStream dis;

		Receiver(Socket s){
			this.s = s;
			try{
				dis = new DataInputStream(this.s.getInputStream());
			}catch(IOException io){}
		}

		public void run(){
			while(dis != null){
				try{
					String msg = dis.readUTF();
					if(msg.startsWith("_CList")){ // 명령어 : 클라이언트 목록 갱신
						playerName = msg.substring(6, msg.indexOf(" "));
						playerScore = msg.substring(msg.indexOf(" ") + 1, msg.indexOf("#"));
						playerIdx = msg.substring(msg.indexOf("#") + 1);
						updateClientList();
					}else if(msg.startsWith("_Start")){ // 명령어 : 게임 시작 (+타이머)
						gameStart = true;
						g = canvas.getGraphics(); // 캔버스 설정 초기화
						g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
						pen canvas2 = (pen)canvas;
						canvas2.color = Color.BLACK;
						color = Color.BLACK;
						bgm("//Play"); // BGM 재생
					}else if(msg.equals("_GmGG ")){ // 명령어 : 게임 포기
						gameStart = false;
						auth = false;
						txt_field.setEnabled(true);
						btn_ready.setEnabled(true);
						label_timer.setText("00 : 00");
						bgm("_Stop"); // BGM 정지
					}else if(msg.equals("_GmEnd")){
						gameStart = false;
						auth = false;
						txt_field.setEnabled(true);
						btn_ready.setEnabled(true);
						label_timer.setText("00 : 00");
						bgm("_Stop"); // BGM 정지
					}else if(msg.startsWith("_RExam")){ // 명령어 : 문제 랜덤 출제
						if(auth == true){
							JOptionPane.showMessageDialog(null,"당신이 출제자입니다", msg.substring(6), JOptionPane.PLAIN_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, playerName + "님이 출제자입니다.", "답을 적어주세요 ㅎㅎ", JOptionPane.PLAIN_MESSAGE);
						}
					}else if(msg.startsWith("_Auth ")){ // 명령어 : 출제자 권한 부여
						if(login.nickname.equals(msg.substring(6))){
							auth = true;
							txt_area.append("[ 당신이 문제 출제자입니다 !! ]" + "\n");
							txt_field.setEnabled(false);
						}
					}else if(msg.startsWith("_Mouse")){ // 명령어 : 캔버스 공유
						if(auth == false){
							int tempX = Integer.parseInt(msg.substring(6, msg.indexOf("."))); 
							int tempY = Integer.parseInt(msg.substring(msg.indexOf(".") + 1));
							g = canvas.getGraphics();
							g2d = (Graphics2D)g;
							g2d.setColor(color);
				            g2d.setStroke(new BasicStroke(6));
				            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		                    g.drawLine(tempX, tempY, tempX, tempY);
						}
					}else if(msg.startsWith("_Timer")){
						label_timer.setText(msg.substring(6));
					}else if(msg.startsWith("_Color")){ // 명령어 : 컬러 설정
						String temp = msg.substring(6);
						switch(temp){
							case "Red": color = Color.RED; break;
							case "Green": color = Color.GREEN; break;
							case "Blue": color = Color.BLUE; break;
							case "Yellow": color = Color.YELLOW; break;
							case "Black": color = Color.BLACK; break;
						}
					}else if(msg.equals("_Erase")){ // 클라이언트측 명령어 : 지우기
						color = Color.WHITE;
					}else if(msg.equals("_ErAll")){ // 클라이언트측 명령어 : 모두 지우기
						g = canvas.getGraphics();
						g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
					}else{ // 채팅 출력
						txt_area.append(msg + "\n");
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
					}
				}catch(IOException io){
					txt_area.append("[ 서버와의 연결이 끊어졌거나 닉네임중복, 정원초과, 게임시작중 입니다...\n확인후 다시접속해 주세요\n3초 후 프로그램을 종료합니다 .. ]");
					try{
						Thread.sleep(3000);
						System.exit(0);
					}catch(InterruptedException it){}
				}
			}
		}
		
		public void updateClientList(){ // 클라이언트 목록 추가
			ImageIcon ii;
			if(Integer.parseInt(playerIdx) == 0){
				label_player1_nickname.setText("[ " + playerName + " 님 ]");
				label_player1_score.setText("[ 점수 : " + playerScore + " ]");
				deleteClientList();
			}else if(Integer.parseInt(playerIdx) == 1){
				label_player2_nickname.setText("[ " + playerName + " 님 ]");
				label_player2_score.setText("[ 점수 : " + playerScore + " ]");
				deleteClientList();
			}else if(Integer.parseInt(playerIdx) == 2){
				label_player3_nickname.setText("[ " + playerName + " 님 ]");
				label_player3_score.setText("[ 점수 : " + playerScore + " ]");
				deleteClientList();
			}else if(Integer.parseInt(playerIdx) == 3){
				label_player4_nickname.setText("[ " + playerName + " 님 ]");
				label_player4_score.setText("[ 점수 : " + playerScore + " ]");
				deleteClientList();
			}
		}
		
		public void deleteClientList(){ // 클라이언트 목록 제거
			if(Integer.parseInt(playerIdx) == 0){
				label_player2_nickname.setText("[nickname]");
				label_player2_score.setText("[score]");
				label_player3_nickname.setText("[nickname]");
				label_player3_score.setText("[score]");
				label_player4_nickname.setText("[nickname]");
				label_player4_score.setText("[score]");
			}else if(Integer.parseInt(playerIdx) == 1){
				label_player3_nickname.setText("[nickname]");
				label_player3_score.setText("[score]");
				label_player4_nickname.setText("[nickname]");
				label_player4_score.setText("[score]");
			}else if(Integer.parseInt(playerIdx) == 2){
				label_player4_nickname.setText("[nickname]");
				label_player4_score.setText("[score]");
			}
		}
		
		public void bgm(String play){ // BGM 재생 & 정지
			new Thread(){ 
				public void run(){	
					try{
						AudioInputStream ais = AudioSystem.getAudioInputStream(new File("bgm\\bgm.wav"));
						Clip clip = AudioSystem.getClip();
						if(play.equals("//Play")){
							clip.stop();
							clip.open(ais);
				            clip.start();
				            clip.loop(Clip.LOOP_CONTINUOUSLY);
						}else if(play.equals("//Stop")){
							System.out.println("진입");
							clip.close();
						}
					}catch(Exception e){}
				}
			}.start();
		}
	}
	class penController extends JFrame implements ActionListener, MouseMotionListener
	{	
		int x1, y1;
		public void mouseDragged(MouseEvent e){
		    x1 = e.getX(); y1 = e.getY();
		    ((pen)canvas).x = x1; ((pen)canvas).y = y1;
		    canvas.repaint();
		}
		public void mousePressed(MouseEvent e){}
		public void mouseMoved(MouseEvent e){}
		
		public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
			pen canvas2 = (pen)canvas;
		   
			if(auth == true){
			    if(obj == btn_c1){
				    canvas2.color = Color.RED;
			    }else if(obj == btn_c2){
			    	canvas2.color = Color.GREEN;
			    }else if(obj == btn_c3){
			    	canvas2.color = Color.BLUE;
			    }else if(obj == btn_c4){
			    	canvas2.color = Color.YELLOW;
			    }else if(obj == btn_c5){
			    	canvas2.color = Color.BLACK;
			    }else if(obj == btn_erase){
			    	canvas2.color = canvas.getBackground();
			    }else if(obj == btn_eraseAll){
			    	Graphics g = canvas2.getGraphics();
				    g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); 
			    }
			}
		}
	}
	class pen extends Canvas
	{
		int x;
		int y;
		Color color = Color.BLACK;

		public void paintOption(Graphics g){
			if(gameStart == true && auth == true){// 바꾸기
				Graphics2D g2d = (Graphics2D)g;
	            g2d.setColor(color);
	            g2d.setStroke(new BasicStroke(3));
	            //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2d.drawLine(x, y, x, y);
			}
		}
		
		public void update(Graphics g){
			paintOption(g);
		}
	}
}
