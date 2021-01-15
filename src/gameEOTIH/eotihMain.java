package gameEOTIH;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.SwingConstants;


public class eotihMain extends JFrame {

	private JPanel contentPane;
	public static String hoten = "";
	public static String tendn = "";
	public static String a = "";
	public static String b = "";
	
	private int question_num = 0;

    static ArrayList<String> quess = new ArrayList<String>();
    static ArrayList<String> anss = new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eotihMain frame = new eotihMain();
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
	public eotihMain() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eotih","root","letronghieu");
			Statement stmt = conn.createStatement();
			String url = "select hoten,tendn,point from taikhoan where tendn ='"+eotihLogin.tendn+"'";
			//String url = "select hoten,tendn,point from taikhoan where tendn ='eotih'";
			ResultSet rs = stmt.executeQuery(url);
			if(rs.next()) {
				a = rs.getString(1);
				b = rs.getString(3);
			}else {
				JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu !");
			}
			conn.close();
			}catch(Exception e1) {
				System.out.println(e1);
			}
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Logo EOTIH\\logo.png"));
		setTitle("EOTIH - GAMESHOW");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Menu menuBar = new Menu((JPanel) null);
		menuBar.setBounds(0, 0, 434, 22);
		contentPane.add(menuBar);
		
		JPanel panelPlay = new JPanel();
		panelPlay.setBackground(new Color(255, 255, 255));
		panelPlay.setBounds(10, 190, 200, 125);
		contentPane.add(panelPlay);
		panelPlay.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("G:\\icon\\game_controller_70px.png"));
		lblNewLabel_2.setBounds(63, 10, 70, 70);
		panelPlay.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("PLAY GAME DATABASE");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Impact", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(0, 81, 200, 32);
		panelPlay.add(lblNewLabel_1_1);
		
		JLabel lblPlay = new JLabel("");
		lblPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Đặt biến 
                String listcauhoi = null;
                String[] cauhoinhapvao = {  };
                String listcautraloi = null;
                String[] cautraloinhapvao = {  };
                
                String type = "toanhoc";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eotih","root","letronghieu");
					PreparedStatement stmt = conn.prepareStatement("select a.ques,b.ans from question a,answer b where a.theloai=? and a.questionid = b.answerid");
					stmt.setString(1, type);
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						listcauhoi= rs.getString(1);
        				listcautraloi = rs.getString(2);
        				List<String> queslist = new ArrayList<String>(Arrays.asList(cauhoinhapvao));
        				List<String> anlist = new ArrayList<String>(Arrays.asList(cautraloinhapvao));
        				
        				queslist.add(listcauhoi); 
        				anlist.add(listcautraloi);
        		        cauhoinhapvao = queslist.toArray(cauhoinhapvao);
        		        cautraloinhapvao = anlist.toArray(cautraloinhapvao);
        		        System.out.println(listcauhoi);
        		        System.out.println(listcautraloi);
					}
					}catch(Exception e1) {
						System.out.println(e1);
					}
				for (int i = 0; i < cauhoinhapvao.length; i++) {
					quess.add(cauhoinhapvao[i]);
                } 
                for (int i = 0; i < cautraloinhapvao.length; i++) { 
                	anss.add(cautraloinhapvao[i]);
                }
				try {
					eotihPRO abc = new eotihPRO();
					abc.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		lblPlay.setBounds(0, 0, 200, 125);
		panelPlay.add(lblPlay);
		
		JPanel panelRank = new JPanel();
		panelRank.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					eotihRank frame = new eotihRank();
					frame.setVisible(true);
				} catch (Exception ea) {
					ea.printStackTrace();
				}
			}
		});
		panelRank.setLayout(null);
		panelRank.setBackground(new Color(255, 255, 255));
		panelRank.setBounds(226, 190, 200, 125);
		contentPane.add(panelRank);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("G:\\icon\\medal_70px.png"));
		lblNewLabel_2_1.setBounds(63, 10, 70, 70);
		panelRank.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("RANKING");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Impact", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setBounds(10, 81, 190, 32);
		panelRank.add(lblNewLabel_1_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
		panel.setBounds(0, 20, 444, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHello = new JLabel(a);
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblHello.setForeground(Color.WHITE);
		lblHello.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHello.setBounds(0, 93, 434, 38);
		panel.add(lblHello);
		
		JLabel lblPic = new JLabel("");
		lblPic.setIcon(new ImageIcon("G:\\icon\\facebook_100px.png"));
		lblPic.setForeground(Color.WHITE);
		lblPic.setFont(new Font("Impact", Font.PLAIN, 20));
		lblPic.setBounds(159, 0, 120, 108);
		panel.add(lblPic);
		
		JLabel lblPoint = new JLabel("Point : " +b);
		lblPoint.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoint.setForeground(Color.WHITE);
		lblPoint.setFont(new Font("Impact", Font.PLAIN, 30));
		lblPoint.setBounds(88, 129, 261, 26);
		panel.add(lblPoint);
		
		JPanel panelPlay_1 = new JPanel();
		panelPlay_1.setLayout(null);
		panelPlay_1.setBackground(new Color(255, 255, 255));
		panelPlay_1.setBounds(10, 325, 200, 125);
		contentPane.add(panelPlay_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("");
		lblNewLabel_2_2.setIcon(new ImageIcon("G:\\icon\\game_controller_70px.png"));
		lblNewLabel_2_2.setBounds(64, 10, 70, 70);
		panelPlay_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("PLAY GAME NORMAL");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setFont(new Font("Impact", Font.PLAIN, 20));
		lblNewLabel_1_1_2.setBounds(0, 81, 200, 32);
		panelPlay_1.add(lblNewLabel_1_1_2);
		
		JLabel lblNormal = new JLabel("");
		lblNormal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Game g = new Game();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblNormal.setBounds(0, 0, 200, 125);
		panelPlay_1.add(lblNormal);
		
		JPanel panelPlay_1_1 = new JPanel();
		panelPlay_1_1.setLayout(null);
		panelPlay_1_1.setBackground(new Color(255, 255, 255));
		panelPlay_1_1.setBounds(10, 460, 200, 125);
		contentPane.add(panelPlay_1_1);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("");
		lblNewLabel_2_1_3.setIcon(new ImageIcon("G:\\icon\\circled_user_male_skin_type_3_70px.png"));
		lblNewLabel_2_1_3.setBounds(70, 10, 70, 70);
		panelPlay_1_1.add(lblNewLabel_2_1_3);
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("PROFILE");
		lblNewLabel_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_3.setFont(new Font("Impact", Font.PLAIN, 25));
		lblNewLabel_1_1_1_3.setBounds(10, 81, 190, 32);
		panelPlay_1_1.add(lblNewLabel_1_1_1_3);
		
		JLabel lblProfile = new JLabel("");
		lblProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					eotihProfile frame = new eotihProfile();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		lblProfile.setBounds(0, 0, 200, 125);
		panelPlay_1_1.add(lblProfile);
		
		JPanel panelPlay_1_2 = new JPanel();
		panelPlay_1_2.setLayout(null);
		panelPlay_1_2.setBackground(Color.WHITE);
		panelPlay_1_2.setBounds(226, 325, 200, 125);
		contentPane.add(panelPlay_1_2);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("");
		lblNewLabel_2_1_2.setIcon(new ImageIcon("G:\\icon\\money_70px.png"));
		lblNewLabel_2_1_2.setBounds(63, 10, 70, 70);
		panelPlay_1_2.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("DONATE");
		lblNewLabel_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_2.setFont(new Font("Impact", Font.PLAIN, 25));
		lblNewLabel_1_1_1_2.setBounds(0, 81, 200, 32);
		panelPlay_1_2.add(lblNewLabel_1_1_1_2);
		
		JLabel lblDonate = new JLabel("");
		lblDonate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Momo : 0906213807 ( Lê Trọng Hiếu ) ");
			}
		});
		lblDonate.setBounds(0, 0, 200, 125);
		panelPlay_1_2.add(lblDonate);
		
		JPanel panelPlay_1_1_1 = new JPanel();
		panelPlay_1_1_1.setLayout(null);
		panelPlay_1_1_1.setBackground(Color.WHITE);
		panelPlay_1_1_1.setBounds(226, 460, 200, 125);
		contentPane.add(panelPlay_1_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon("G:\\icon\\shutdown_70px.png"));
		lblNewLabel_2_1_1.setBounds(63, 10, 70, 70);
		panelPlay_1_1_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("EXIT GAME");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Impact", Font.PLAIN, 25));
		lblNewLabel_1_1_1_1.setBounds(0, 81, 200, 32);
		panelPlay_1_1_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblExit = new JLabel("");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0); 
			}
		});
		lblExit.setBounds(0, 0, 200, 125);
		panelPlay_1_1_1.add(lblExit);
	}
}
