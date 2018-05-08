
package ohtello;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//�p�b�P�[�W�̃C���|�[�g
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Client extends JFrame implements MouseListener {
	private JButton buttonArray[];//�I�Z���՗p�̃{�^���z��
	private JButton stop, pass; //��~�A�X�L�b�v�p�{�^��
	private JLabel colorLabel; // �F�\���p���x��
	private JLabel turnLabel; // ��ԕ\���p���x��
	private ImageIcon blackIcon, whiteIcon, boardIcon; //�A�C�R��
	private PrintWriter out;//�f�[�^���M�p�I�u�W�F�N�g
	private Receiver receiver; //�f�[�^��M�p�I�u�W�F�N�g
	private othello game; //Othello�I�u�W�F�N�g
	private Player player; //Player�I�u�W�F�N�g
	private String playername = "Player";
	
	 /*********�ʐM����*********/
	  //private Receive receive;  //���̓X�g���[���p�����N���X�̃C���X�^���X�ϐ�
	  private BufferedReader in;  //���̓X�g���[��
	  private PrintWriter out1;  //�o�̓X�g���[��
	  private ObjectInputStream ois;  //�I�u�W�F�N�g���̓X�g���[��
	  private ObjectOutputStream oos;  //�I�u�W�F�N�g�o�̓X�g���[�����K�v�Ȃ������H
	  private Socket soc = null;  //�\�P�b�g

	// �R���X�g���N�^
	public Client(othello game) {
		this.game = game; //������Othello�I�u�W�F�N�g��n��
		/*
		//�E�B���h�E�ݒ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�E�B���h�E�����ꍇ�̏���
		setTitle("�l�b�g���[�N�ΐ�^�I�Z���Q�[��");//�E�B���h�E�̃^�C�g��
		setSize(row * 45 + 10, row * 45 + 200);//�E�B���h�E�̃T�C�Y��ݒ�
		c = getContentPane();//�t���[���̃y�C�����擾
		//�A�C�R���ݒ�(�摜�t�@�C�����A�C�R���Ƃ��Ďg��)
		whiteIcon = new ImageIcon("White.jpg");
		blackIcon = new ImageIcon("Black.jpg");
		boardIcon = new ImageIcon("GreenFrame.jpg");
		c.setLayout(null);//
		//�I�Z���Ղ̐���
		buttonArray = new JButton[row * row];//�{�^���̔z����쐬
		for(int i = 0 ; i < row * row ; i++){
			if(grids[i]==1){ buttonArray[i] = new JButton(blackIcon);}//�Ֆʏ�Ԃɉ������A�C�R����ݒ�
			if(grids[i]==2){ buttonArray[i] = new JButton(whiteIcon);}//�Ֆʏ�Ԃɉ������A�C�R����ݒ�
			if(grids[i]==0){ buttonArray[i] = new JButton(boardIcon);}//�Ֆʏ�Ԃɉ������A�C�R����ݒ�
			c.add(buttonArray[i]);//�{�^���̔z����y�C���ɓ\��t��
			// �{�^����z�u����
			int x = (i % row) * 45;
			int y = (int) (i / row) * 45;
			buttonArray[i].setBounds(x, y, 45, 45);//�{�^���̑傫���ƈʒu��ݒ肷��D
			buttonArray[i].addMouseListener(this);//�}�E�X�����F���ł���悤�ɂ���
			buttonArray[i].setActionCommand(Integer.toString(i));//�{�^�������ʂ��邽�߂̖��O(�ԍ�)��t������
		}
		//�I���{�^��
		stop = new JButton("�I��");//�I���{�^�����쐬
		c.add(stop); //�I���{�^�����y�C���ɓ\��t��
		stop.setBounds(0, row * 45 + 30, (row * 45 + 10) / 2, 30);//�I���{�^���̋��E��ݒ�
		stop.addMouseListener(this);//�}�E�X�����F���ł���悤�ɂ���
		stop.setActionCommand("stop");//�{�^�������ʂ��邽�߂̖��O��t������
		//�p�X�{�^��
		pass = new JButton("�p�X");//�p�X�{�^�����쐬
		c.add(pass); //�p�X�{�^�����y�C���ɓ\��t��
		pass.setBounds((row * 45 + 10) / 2, row * 45 + 30, (row * 45 + 10 ) / 2, 30);//�p�X�{�^���̋��E��ݒ�
		pass.addMouseListener(this);//�}�E�X�����F���ł���悤�ɂ���
		pass.setActionCommand("pass");//�{�^�������ʂ��邽�߂̖��O��t������
		//�F�\���p���x��
		String myName = player.getName();
		colorLabel = new JLabel(myName + "����̐F�͖���ł�");//�F����\�����邽�߂̃��x�����쐬
		colorLabel.setBounds(10, row * 45 + 60 , row * 45 + 10, 30);//���E��ݒ�
		c.add(colorLabel);//�F�\���p���x�����y�C���ɓ\��t��
		//��ԕ\���p���x��
		turnLabel = new JLabel("��Ԃ�" + game.getTurn() + "�ł�");//��ԏ���\�����邽�߂̃��x�����쐬
		turnLabel.setBounds(10, row * 45 + 120, row * 45 + 10, 30);//���E��ݒ�
		c.add(turnLabel);//��ԏ�񃉃x�����y�C���ɓ\��t��
		
		*/
		
	}

	// ���\�b�h
	public void Login() { //���O�C�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�E�B���h�E�����ꍇ�̏���
		setTitle("Login");//�E�B���h�E�̃^�C�g��
		setSize(800, 550);//�E�B���h�E�̃T�C�Y��ݒ�
		//�F
		Color back =new Color(55,113,184);
		Color text =new Color(247,247,247);
		Color textback =new Color(4,49,105);
		Color button =new Color(113,166,0);
		Color error =new Color(230,92,0);
		
		ImageIcon titleimage = new ImageIcon("titleimage.png");
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(back);
		//�Q�[���^�C�g��
		JLabel title = new JLabel("OTHELLO");
		title.setForeground(text);
		title.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 36));
		title.setBounds(310,20,180,40);
		//���O
		JLabel name = new JLabel("Name");
		name.setForeground(text);
		name.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		name.setBounds(65,120,80,20);
		//���O����
		JTextField username = new JTextField(24);
		username.setForeground(text);
		username.setBackground(textback);
		username.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 30));
		username.setBounds(60,160,300,70);
		//�p�X���[�h
		JLabel pass1 = new JLabel("Password");
		pass1.setForeground(text);
		pass1.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		pass1.setBounds(65,250,200,20);
		//�p�X���[�h����
		JPasswordField password = new JPasswordField(24);
		password.setForeground(text);
		password.setBackground(textback);
		password.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 30));
		password.setBounds(60,290,300,70);
		//���O�C���{�^��
		JButton login = new JButton("Login");
		login.setForeground(text);
		login.setBackground(button);
		login.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		login.setBounds(475,350,250,50);
		login.setOpaque(true);
		login.setBorderPainted(false);
		//�V�K�A�J�E���g�o�^�{�^��
		JButton newuser = new JButton("Newuser");
		newuser.setForeground(text);
		newuser.setBackground(button);
		newuser.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		newuser.setBounds(475,440,250,50);
		newuser.setOpaque(true);
		newuser.setBorderPainted(false);
		//�摜
		JLabel titleimg = new JLabel(titleimage);
		titleimg.setBounds(430, 70, 321, 257);
		
				//�y�Վ��z�z�[����ʑJ�ڗp�{�^��
				JButton home = new JButton("home");
				home.setForeground(text);
				home.setBackground(button);
				home.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 1));
				home.setBounds(10,10,10,10);
				home.setOpaque(true);
				home.setBorderPainted(false);
		//�T�[�o�[�ڑ����s
		JLabel serverfailed = new JLabel();
		serverfailed.setForeground(error);
		serverfailed.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 20));
		serverfailed.setBounds(440,395,500,50);
		//���O�C�����s
		JLabel loginfailed = new JLabel();
		loginfailed.setForeground(error);
		loginfailed.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 20));
		loginfailed.setBounds(535,395,500,50);
		
		p.add(title);
		p.add(name);
		p.add(username);
		p.add(pass1);
		p.add(password);
		p.add(login);
		p.add(newuser);
		p.add(titleimg);
		p.add(home);
		add(p);
		
		login.addActionListener(new ActionListener() { // ���O�C���{�^�������������̏���
			 public void actionPerformed(ActionEvent as) {
				 connectServer("localhost", 10005);
				 if( connectServer("localhost", 10005) == false) {
					 serverfailed.setText("Could not connect to the server!");
				 }
				 if( connectServer("localhost", 10005) == true) {
					 char[] pass2 = password.getPassword(); //char�^�z���String�ɕϊ�
					 String pass3 = new String(pass2);
					 loginRequest(username.getText(),pass3);
					 if( loginRequest(username.getText(),pass3)==false) {
						 loginfailed.setText("Login failed");
					 }
					 if( loginRequest(username.getText(),pass3)==true) {
						 playername = username.getText();
						 userhome();
						 p.removeAll();
					 }
				 }
				
        }
		});
		newuser.addActionListener(new ActionListener() { //�A�J�E���g�o�^�{�^�������������̏���
			 public void actionPerformed(ActionEvent as) {
				 Newuser();
				 p.removeAll();
				 }
		});
		
		home.addActionListener(new ActionListener() { //�Վ��{�^�������������̏���
			 public void actionPerformed(ActionEvent as) {
				 userhome();
				 p.removeAll();
				 }
		});
		
		
		setVisible(true);	
	}
	
	  public boolean loginRequest(String userName,String password) { //���O�C���v��
	    try {
	      /*
	      �T�[�o�[����(���[�U�[�[��,�p�X���[�h)���Ɣ��ʂł���悤�ȏ������K�v
	      �Ⴆ�΁A
	      loginRequest
	      userName:~~~~~~~
	      password:^^^^^^^
	      �̂悤�ɃL�[������
	      sendMessage("userName:"+userName);
	      sendMessage("password:"+password);
	      */
	      in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
	      out = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()));
	      out.println("loginRequest");
	      out.println(userName);
	      out.println(password);
	      out.flush();
	      String isPer = in.readLine();
	      if(isPer.equals("permit") == true) {  //���O�C���F�؂��ꂽ
	        return true;
	      } else if(isPer.equals("notPermit") == true) {  //���O�C���F�؂���Ȃ�����
	        return false;
	      } else {
	        System.out.println("�F�؂Ƃ͕ʂ̕�����ł��B");  //�ʂ̕����񂪑����Ă���
	      }
	      //�X�g���[�����N���[�Y����
	      out.close();
	      in.close();
	    } catch(IOException e) {
	      System.out.println(e);
	    }
		return false;
	  }
	
	public void Newuser() { //�A�J�E���g�쐬���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�E�B���h�E�����ꍇ�̏���
		setTitle("Sign up");//�E�B���h�E�̃^�C�g��
		setSize(800, 550);//�E�B���h�E�̃T�C�Y��ݒ�
		
		//�F
		Color back =new Color(55,113,184);
		Color text =new Color(247,247,247);
		Color textback =new Color(4,49,105);
		Color button =new Color(113,166,0);
		Color error =new Color(230,92,0);
		
		ImageIcon titleimage = new ImageIcon("titleimage.png");
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(back);
		//�^�C�g��
		JLabel title = new JLabel("SIGN UP");
		title.setForeground(text);
		title.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 36));
		title.setBounds(320,20,600,40);
		//���O
		JLabel name = new JLabel("Name");
		name.setForeground(text);
		name.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		name.setBounds(65,120,80,20);
		//���O����
		JTextField username = new JTextField(16);
		username.setForeground(text);
		username.setBackground(textback);
		username.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 30));
		username.setBounds(60,160,300,70);
		//�p�X���[�h
		JLabel pass1 = new JLabel("Password");
		pass1.setForeground(text);
		pass1.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		pass1.setBounds(65,250,200,20);
		//�p�X���[�h����
		JPasswordField password = new JPasswordField(16);
		password.setForeground(text);
		password.setBackground(textback);
		password.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 30));
		password.setBounds(60,290,300,70);
		//�p�X���[�h�m�F
		JLabel pass2 = new JLabel("Password");
		pass2.setForeground(text);
		pass2.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		pass2.setBounds(65,390,200,20);
		//�p�X���[�h����2
		JPasswordField password2 = new JPasswordField(16);
		password2.setForeground(text);
		password2.setBackground(textback);
		password2.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 30));
		password2.setBounds(60,430,300,70);
		//�o�^�{�^��
		JButton login = new JButton("Sign up");
		login.setForeground(text);
		login.setBackground(button);
		login.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		login.setBounds(475,350,250,50);
		login.setOpaque(true);
		login.setBorderPainted(false);
		//�߂�{�^��
		JButton returntologin = new JButton("Back");
		returntologin.setForeground(text);
		returntologin.setBackground(button);
		returntologin.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		returntologin.setBounds(475,440,250,50);
		returntologin.setOpaque(true);
		returntologin.setBorderPainted(false);
		//�T�[�o�[�ڑ����s
		JLabel serverfailed = new JLabel();
		serverfailed.setForeground(error);
		serverfailed.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 20));
		serverfailed.setBounds(440,395,500,50);
		//�A�J�E���g�o�^���s
		JLabel newaccountfailed = new JLabel();
		newaccountfailed.setForeground(error);
		newaccountfailed.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 20));
		newaccountfailed.setBounds(140,120,600,20);
		//�p�X���[�h�s��v
		JLabel passwordfailed = new JLabel();
		passwordfailed.setForeground(error);
		passwordfailed.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		passwordfailed.setBounds(200,250,200,20);
		//�摜
		JLabel titleimg = new JLabel(titleimage);
		titleimg.setBounds(430, 70, 321, 257);
		
		p.add(title);
		p.add(name);
		p.add(username);
		p.add(pass1);
		p.add(password);
		p.add(pass2);
		p.add(password2);
		p.add(login);
		p.add(returntologin);
		p.add(titleimg);
		p.add(passwordfailed);
		p.add(serverfailed);
		p.add(newaccountfailed);
		add(p);
		
		
		
		login.addActionListener(new ActionListener() { // �o�^�{�^�������������̏���
			 public void actionPerformed(ActionEvent as) {
				 
				char[] pass = password.getPassword(); //char�^�z���String�ɕϊ�
				String passstring1 = new String(pass);
				char[] pass3 = password2.getPassword(); //char�^�z���String�ɕϊ�
				String passstring2 = new String(pass3);
					
				 if(!(passstring2.equals(passstring1))) {
					 passwordfailed.setText("Mismatch");
				 }
				 if(passstring2.equals(passstring1)) {
					 connectServer("localhost", 10005);
					 if( connectServer("localhost", 10005) == false) {
						 serverfailed.setText("Could not connect to the server!");
					 }
					 if(connectServer("localhost", 10005) == true) {
						 
						 accountRequest(username.getText(),passstring1);
						 if (accountRequest(username.getText(),passstring1)==false) {
							 newaccountfailed.setText("This name is unavailable");
						 }
						 if (accountRequest(username.getText(),passstring1)==true) {
							 playername = username.getText();
							 userhome();
							 p.removeAll();
						 }
					 }
				 }
			 }
		});
		returntologin.addActionListener(new ActionListener() { // �߂�{�^�������������̏���
			 public void actionPerformed(ActionEvent as) {
				Login();
				p.removeAll();
			 }
		});
		setVisible(true);	
	}
	
	  public boolean accountRequest(String userName,String password) { //�A�J�E���g�쐬�v��
	    try {
	      out = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()));
	      in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
	      out.println("accountRequest");
	      out.println(userName);
	      out.println(password);
	      out.flush();
	      String isPer = in.readLine();
	      if(isPer.equals("permit") == true) {  //�V�K�쐬�ł���
	        return true;
	      } else if(isPer.equals("notPermit") == true) {  //�V�K�쐬�ł���
	        return false;
	      } else {
	        System.out.println("�m�F�Ƃ͕ʂ̕�����ł��B");  //�ʂ̕����񂪑����Ă���
	      }
	      out.close();
	      in.close();
	    }catch(IOException e) {
	      System.out.println(e);
	    }
		return false;
	  }
	
	public void userhome() { //���[�U�[�z�[�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�E�B���h�E�����ꍇ�̏���
		setTitle("Home");//�E�B���h�E�̃^�C�g��
		setSize(800, 550);//�E�B���h�E�̃T�C�Y��ݒ�
		
		//�F
		Color back =new Color(55,113,184);
		Color text =new Color(247,247,247);
		Color textback =new Color(4,49,105);
		Color button =new Color(113,166,0);
		Color error =new Color(230,92,0);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(back);
		//�^�C�g��
		JLabel title = new JLabel("HOME");
		title.setForeground(text);
		title.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 36));
		title.setBounds(340,20,600,40);
		
		//���[�U�[��
		JLabel name = new JLabel(playername);
		name.setForeground(text);
		name.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 20));
		name.setBounds(700,10,600,40);
		
		//�΋ǃ{�^��
		JButton playgame = new JButton("Play");
		playgame.setForeground(text);
		playgame.setBackground(button);
		playgame.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		playgame.setBounds(270,170,250,100);
		playgame.setOpaque(true);
		playgame.setBorderPainted(false);
		
		//���ъm�F�{�^��
		JButton score = new JButton("Score");
		score.setForeground(text);
		score.setBackground(button);
		score.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		score.setBounds(270,340,250,100);
		score.setOpaque(true);
		score.setBorderPainted(false);
		
		//���O�A�E�g�{�^��
		JButton logout = new JButton("Logout");
		logout.setForeground(text);
		logout.setBackground(button);
		logout.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		logout.setBounds(650,440,130,50);
		logout.setOpaque(true);
		logout.setBorderPainted(false);
		
		p.add(title);
		p.add(name);
		p.add(playgame);
		p.add(score);
		p.add(logout);
		add(p);
		
		logout.addActionListener(new ActionListener() { // ���O�A�E�g�{�^�������������̏���
			 public void actionPerformed(ActionEvent as) {
				Login();
				p.removeAll();
			 }
		});
		
		playgame.addActionListener(new ActionListener() { // �΋ǃ{�^�������������̏���
			 public void actionPerformed(ActionEvent as) {
				//playothello();
				p.removeAll();
			 }
		});
		
		score.addActionListener(new ActionListener() { // ���у{�^�������������̏���
			 public void actionPerformed(ActionEvent as) {
				score();
				p.removeAll();
			 }
		});
		
		setVisible(true);
	}
	
	public void gamewaiting() {
		
	}
	
	public void score() {//���ъm�F���
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//�E�B���h�E�����ꍇ�̏���
		setTitle("Score");//�E�B���h�E�̃^�C�g��
		setSize(800, 550);//�E�B���h�E�̃T�C�Y��ݒ�
		
		//�F
		Color back =new Color(55,113,184);
		Color text =new Color(247,247,247);
		Color textback =new Color(4,49,105);
		Color button =new Color(113,166,0);
		Color error =new Color(230,92,0);
				
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(back);
		
		//�^�C�g��
		JLabel title = new JLabel("SCORE");
		title.setForeground(text);
		title.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 36));
		title.setBounds(340,20,600,40);
		
		//�߂�{�^��
		JButton returntohome = new JButton("Home");
		returntohome.setForeground(text);
		returntohome.setBackground(button);
		returntohome.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 24));
		returntohome.setBounds(650,440,130,50);
		returntohome.setOpaque(true);
		returntohome.setBorderPainted(false);
		
		p.add(title);
		p.add(returntohome);
		add(p);
		
		returntohome.addActionListener(new ActionListener() { // ���у{�^�������������̏���
			 public void actionPerformed(ActionEvent as) {
				userhome();
				p.removeAll();
			 }
		});
		
		setVisible(true);
	}
	
/*	public void playothello() {//�I�Z��
		
		int[] grids = game.getGrids(); //getGrid���\�b�h�ɂ��ǖʏ����擾
	//	int row = game.getRow(); //getRow���\�b�h�ɂ��I�Z���Ղ̏c���}�X�̐����擾
		
		//�E�B���h�E�ݒ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�E�B���h�E�����ꍇ�̏���
		setTitle("game");//�E�B���h�E�̃^�C�g��
		setSize(row * 45 + 10, row * 45 + 200);//�E�B���h�E�̃T�C�Y��ݒ�
		
		//�F
		Color back =new Color(217,217,217);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(back);
		
		//�A�C�R���ݒ�(�摜�t�@�C�����A�C�R���Ƃ��Ďg��)
		whiteIcon = new ImageIcon("White.jpg");
		blackIcon = new ImageIcon("Black.jpg");
		boardIcon = new ImageIcon("GreenFrame.jpg");
		
		//�I�Z���Ղ̐���
		buttonArray = new JButton[row * row];//�{�^���̔z����쐬
		for(int i = 0 ; i < row * row ; i++){
			if(grids[i]==1){ buttonArray[i] = new JButton(blackIcon);}//�Ֆʏ�Ԃɉ������A�C�R����ݒ�
			if(grids[i]==2){ buttonArray[i] = new JButton(whiteIcon);}//�Ֆʏ�Ԃɉ������A�C�R����ݒ�
			if(grids[i]==0){ buttonArray[i] = new JButton(boardIcon);}//�Ֆʏ�Ԃɉ������A�C�R����ݒ�
			p.add(buttonArray[i]);//�{�^���̔z����y�C���ɓ\��t��
			// �{�^����z�u����
			int x = (i % row) * 45;
			int y = (int) 10 + (i / row)*45;
			buttonArray[i].setBounds(x, y, 45, 45);//�{�^���̑傫���ƈʒu��ݒ肷��D
			//buttonArray[i].addMouseListener(this);//�}�E�X�����F���ł���悤�ɂ���
			buttonArray[i].setActionCommand(Integer.toString(i));//�{�^�������ʂ��邽�߂̖��O(�ԍ�)��t������
		}
		
		for(int i=0;i<64;i++) {
			buttonArray[i].addActionListener(new ActionListener() { // �Ֆʂ����������̏���
				 public void actionPerformed(ActionEvent as) {
					
				 }
			});
		}
		
		
		//�~�Q�{�^��
		stop = new JButton("�~�Q");
		p.add(stop); 
		stop.setBounds(0, row * 45 + 30, (row * 45 + 10) / 2, 30);
		//stop.addMouseListener(this);
		stop.setActionCommand("stop");
		
		//��ԕ\��
		turnLabel = new JLabel("��Ԃ͖���ł�");
		turnLabel.setBounds(10, row * 45 + 120, row * 45 + 10, 30);
		p.add(turnLabel);
		
		add(p);
		
		setVisible(true);
	}*/
	
	  public boolean connectServer(String ipAddress,int port) {
		    try {
		      soc = new Socket(ipAddress,port);
		      return true;
		    }catch(UnknownHostException e) {
		      System.out.println("�z�X�g�ɐڑ��ł��܂���B");
		      System.out.println(e);
		      return false;
		    }catch(IOException e) {
		      System.out.println("�T�[�o�[�ڑ����ɃG���[���������܂����B");
		      System.out.println(e);
		      return false;
		    }
		  }

	public void sendMessage(String msg){	// �T�[�o�ɑ�����𑗐M
		out.println(msg);//���M�f�[�^���o�b�t�@�ɏ����o��
		out.flush();//���M�f�[�^�𑗂�
		System.out.println("�T�[�o�Ƀ��b�Z�[�W " + msg + " �𑗐M���܂���"); //�e�X�g�W���o��
	}

	// �f�[�^��M�p�X���b�h(�����N���X)
	class Receiver extends Thread {
		private InputStreamReader sisr; //��M�f�[�^�p�����X�g���[��
		private BufferedReader br; //�����X�g���[���p�̃o�b�t�@

		// �����N���XReceiver�̃R���X�g���N�^
		Receiver (Socket socket){
			try{
				sisr = new InputStreamReader(socket.getInputStream()); //��M�����o�C�g�f�[�^�𕶎��X�g���[����
				br = new BufferedReader(sisr);//�����X�g���[�����o�b�t�@�����O����
			} catch (IOException e) {
				System.err.println("�f�[�^��M���ɃG���[���������܂���: " + e);
			}
		}
		// �����N���X Receiver�̃��\�b�h
		public void run(){
			try{
				while(true) {//�f�[�^����M��������
					String inputLine = br.readLine();//��M�f�[�^����s���ǂݍ���
					if (inputLine != null){//�f�[�^����M������
						receiveMessage(inputLine);//�f�[�^��M�p���\�b�h���Ăяo��
					}
				}
			} catch (IOException e){
				System.err.println("�f�[�^��M���ɃG���[���������܂���: " + e);
			}
		}
	}

	public void receiveMessage(String msg){	// ���b�Z�[�W�̎�M
		System.out.println("�T�[�o���烁�b�Z�[�W " + msg + " ����M���܂���"); //�e�X�g�p�W���o��
	}
	public void updateDisp(){// ��ʂ��X�V����
		
		
	}
	public void acceptOperation(String command){	// �v���C���̑������t
	}

  	//�}�E�X�N���b�N���̏���
	public void mouseClicked(MouseEvent e) {
		JButton theButton = (JButton)e.getComponent();//�N���b�N�����I�u�W�F�N�g�𓾂�D�L���X�g��Y�ꂸ��
		String command = theButton.getActionCommand();//�{�^���̖��O�����o��
		game.putStone(Integer.parseInt(command), game.getTurn());
		game.chengeTurn();
		game.getGrids();
		updateDisp();
		System.out.println("�}�E�X���N���b�N����܂����B�����ꂽ�{�^���� " + command + "�ł��B");//�e�X�g�p�ɕW���o��
		sendMessage(command); //�e�X�g�p�Ƀ��b�Z�[�W�𑗐M
	}
	public void mouseEntered(MouseEvent e) {}//�}�E�X���I�u�W�F�N�g�ɓ������Ƃ��̏���
	public void mouseExited(MouseEvent e) {}//�}�E�X���I�u�W�F�N�g����o���Ƃ��̏���
	public void mousePressed(MouseEvent e) {}//�}�E�X�ŃI�u�W�F�N�g���������Ƃ��̏���
	public void mouseReleased(MouseEvent e) {}//�}�E�X�ŉ����Ă����I�u�W�F�N�g�𗣂����Ƃ��̏���

	//main
	public static void main(String args[]){
		
		othello game = new othello();
		Client client = new Client(game);
		client.Login();
	}
}
