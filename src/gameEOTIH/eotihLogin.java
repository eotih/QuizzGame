package gameEOTIH;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sun.jdi.connect.spi.Connection;

public class eotihLogin extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	String pass ="letronghieu";

	eotihMusic bgMusic = new eotihMusic("G:/JAVA/background/a.wav");
	
	public static String tendn = "";
	public static String matkhau = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eotihLogin frame = new eotihLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public eotihLogin() {
		setVisible(true);
		setTitle("EOTIH - Sign In");
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\JAVA\\background\\Material-design.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELLCOME BACK");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 40));
		lblNewLabel.setBounds(93, 10, 268, 121);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_2.setIcon(new ImageIcon("G:\\icon\\user_male_skin_type_6_100px.png"));
		lblNewLabel_3_2.setBounds(136, 99, 150, 107);
		contentPane.add(lblNewLabel_3_2);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(32, 162, 366, 328);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 204));
		panel_1.setLayout(null);
		panel_1.setBounds(34, 229, 300, 50);
		panel.add(panel_1);
		
		JLabel lblLogin = new JLabel("LOG IN\r\n");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tendn = txtUser.getText();
				matkhau = txtPass.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eotih","root",pass);
					Statement stmt = conn.createStatement();
					String url ="select * from taikhoan where tendn ='"+txtUser.getText()+"' and pass ='"+txtPass.getText()+"'";
					ResultSet rs = stmt.executeQuery(url);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công !");
						try {
							eotihMain frame = new eotihMain();
							frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						bgMusic.start();
					}else {
						JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu !");
					}
					conn.close();
					}catch(Exception e1) {
						System.out.println(e1);
					}
			}
		});
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setBounds(0, 0, 300, 50);
		panel_1.add(lblLogin);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Impact", Font.PLAIN, 30));
		
		JLabel lblNewLabel_1 = new JLabel("REGISTER NOW !");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eotihReg reg = new eotihReg();
			}
		});
		lblNewLabel_1.setFont(new Font("Impact", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(204, 188, 140, 31);
		panel.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(34, 64, 300, 50);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBorder(null);
		txtUser.setBounds(5, 3, 244, 42);
		txtUser.setText("Username");
		txtUser.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtUser.setColumns(10);
		panel_2.add(txtUser);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("New label");
		lblNewLabel_3_1_1.setBounds(249, 0, 50, 50);
		panel_2.add(lblNewLabel_3_1_1);
		lblNewLabel_3_1_1.setIcon(new ImageIcon("G:\\icon\\account_50px.png"));
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(34, 128, 300, 50);
		panel.add(panel_2_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("New label");
		lblNewLabel_3_1_1_1.setIcon(new ImageIcon("G:\\icon\\key_50px.png"));
		lblNewLabel_3_1_1_1.setBounds(250, 0, 50, 50);
		panel_2_1.add(lblNewLabel_3_1_1_1);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtPass.setBorder(null);
		txtPass.setText("Password");
		txtPass.setBounds(5, 3, 244, 42);
		panel_2_1.add(txtPass);
		
		JLabel lblNewLabel_3_3 = new JLabel("");
		lblNewLabel_3_3.setIcon(new ImageIcon("G:\\JAVA\\background\\Material-design.jpg"));
		lblNewLabel_3_3.setBounds(-13, 0, 480, 634);
		contentPane.add(lblNewLabel_3_3);
	}
}
