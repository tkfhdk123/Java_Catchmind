package main;


import java.awt.*;
import java.awt.event.*;


import javax.swing.*;


public class login extends JFrame implements ActionListener {
	JPanel panel_nickname, panel_ip, panel_button;
	JLabel label_nickname, label_ip, pallete, eraser;
	JButton btn_login, btn_exit;
	JTextField txt_nickname, txt_ip;
	
	public static String ip, nickname;
	
	void init() {
		Container c = getContentPane();
		panel_nickname = new JPanel();
		panel_ip = new JPanel();
		panel_button = new JPanel();
		
		c.add(panel_button);
		c.add(panel_ip);
		c.add(panel_nickname);
		
		pack();
		label_nickname = new JLabel("닉네임");
		label_ip = new JLabel("ip주소");
		label_nickname.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		label_ip.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		txt_nickname = new JTextField(15);
		txt_ip = new JTextField(15);
		
		btn_login = new JButton("로그인");
		btn_exit = new JButton("종료");
		
		panel_nickname.setBackground(new Color(247,243,222));
		panel_ip.setBackground(new Color(247,243,222));
		panel_button.setBackground(new Color(247,243,222));
		
		pallete = new JLabel(new ImageIcon("image\\color-palette.png"));
		eraser = new JLabel(new ImageIcon("image\\eraser2.png"));
		
		pallete.setBounds(15, 60, 50, 47);
		pallete.setOpaque(true);
		pallete.setBackground(new Color(247,243,222));
		c.add(pallete);
	
	
		eraser.setBounds(225,60,50,49);
		eraser.setOpaque(true);
		eraser.setBackground(new Color(247,243,222));
		c.add(eraser);
		
		add(panel_nickname, BorderLayout.NORTH);
		add(panel_ip, BorderLayout.CENTER);
		add(panel_button, BorderLayout.SOUTH);
		
		panel_nickname.setLayout(new FlowLayout());
		panel_ip.setLayout(new FlowLayout());
		panel_button.setLayout(new FlowLayout());
		
		
		txt_nickname.setForeground(Color.BLACK);
		txt_nickname.setBackground(new Color(247,243,222));

		txt_ip.setForeground(Color.BLACK);
		txt_ip.setBackground(new Color(247,243,222));
	
		
		panel_nickname.add(label_nickname);
		panel_nickname.add(txt_nickname);
		
		label_nickname.setForeground(Color.BLACK);
		label_ip.setForeground(Color.BLACK);
		panel_ip.add(label_ip);
		panel_ip.add(txt_ip);
		
		//bottom 버튼 설정
		btn_login.setFocusPainted(false); //포커스 표시
		btn_exit.setFocusPainted(false);
		btn_login.setBackground(Color.BLACK); 
		btn_exit.setBackground(Color.BLACK);
		btn_login.setForeground(Color.WHITE);
		btn_exit.setForeground(Color.WHITE);
		
		btn_login.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		btn_exit.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
		
		//btn_login : 접속버튼, btn_exit : 종료버튼
		panel_button.add(btn_login);
		panel_button.add(btn_exit);
		btn_login.addActionListener(this);
		btn_exit.addActionListener(this);
		

		setVisible(true);
		setTitle("LOGIN");
		setSize(300, 150);
		setResizable(false);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){ 
		
		
		if(e.getSource() == btn_login){
			if(txt_nickname.getText().equals("")){
				JOptionPane.showMessageDialog(null, "닉네임을 입력해 주세요", "ERROR!", JOptionPane.WARNING_MESSAGE);
			}else if(txt_ip.getText().equals("")){
				JOptionPane.showMessageDialog(null, "IP 주소를 입력해 주세요", "ERROR!", JOptionPane.WARNING_MESSAGE);
			}else if(txt_nickname.getText().trim().length() >=5){
				JOptionPane.showMessageDialog(null, "닉네임은 4글자까지만 입력할수 있습니다", "ERROR!", JOptionPane.WARNING_MESSAGE);
				txt_nickname.setText("");
			}else{
				nickname = txt_nickname.getText().trim(); //trim으로 공백빼고 저장 - isempty를 이용해서도 만들 수 있음
				String temp = txt_ip.getText(); //정규식 -- IP를 받아옴.
				if(temp.matches("(^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$)")){
					ip = temp;
					JOptionPane.showMessageDialog(null, "             로그인 성공!", "JAVA CatchMind LOGIN", JOptionPane.INFORMATION_MESSAGE);
					btn_login.setEnabled(false); //버튼 비활성화
					txt_nickname.setEnabled(false);
					txt_ip.setEnabled(false);
					setVisible(false);
					client client = new client();
				}else{
					JOptionPane.showMessageDialog(null, "IP 주소를 정확하게 입력해 주세요! ", "ERROR!", JOptionPane.WARNING_MESSAGE);
				}
			}
		}else if(e.getSource() == btn_exit){
			System.exit(0);
		}
	}
	
	public static void main(String[] args){
		login login = new login();
		login.init();
	}
}
