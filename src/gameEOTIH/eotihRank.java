package gameEOTIH;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.swing.UIManager;


public class eotihRank extends JFrame {

	private JPanel contentPane;
	public static String hoten = "";
	public static String tendn = "";
	
	// Top 1
	public static String aname = "";
	public static String apoint = "";
	
	// Top 2
	public static String bname = "";
	public static String bpoint = "";
	
	// Top 3
	public static String cname = "";
	public static String cpoint = "";
	
	// Top 4
	public static String dname = "";
	public static String dpoint = "";
		
	// Top 5
	public static String ename = "";
	public static String epoint = "";
		
	// Top 6
	public static String fname = "";
	public static String fpoint = "";
	
	// Top 7
	public static String gname = "";
	public static String gpoint = "";
	
	static ArrayList<String> quess = new ArrayList<String>();
    static ArrayList<String> anss = new ArrayList<String>();
	
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eotihRank frame = new eotihRank();
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
	public eotihRank() {
		String hoten = null;
		String diem = null;
		String[] sttTen = {  };
		String[] sttPoint = {  };
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eotih","root","letronghieu");
			Statement stmt = conn.createStatement();
			String url = "select rank() over(order by point desc) stt, hoten,point from taikhoan";
			ResultSet rs = stmt.executeQuery(url);
			while(rs.next()) {
				hoten = rs.getString(2);
				diem = rs.getString(3);
				List<String> name = new ArrayList<String>(Arrays.asList(sttTen));
				List<String> point = new ArrayList<String>(Arrays.asList(sttPoint));
				name.add(hoten);
				point.add(diem);
				sttTen = name.toArray(sttTen);
		        sttPoint = point.toArray(sttPoint);
			}
			}catch(Exception e1) {
				System.out.println(e1);
			}
		for (int i = 0; i < sttTen.length; i++) {
			aname = sttTen[0];
			bname = sttTen[1];
			cname = sttTen[2];
			dname = sttTen[3];
			ename = sttTen[4];
			fname = sttTen[5];
			gname = sttTen[6];
        } 
        for (int i = 0; i < sttPoint.length; i++) { 
        	apoint = sttPoint[0];
        	bpoint = sttPoint[1];
        	cpoint = sttPoint[2];
        	dpoint = sttPoint[3];
        	epoint = sttPoint[4];
        	fpoint = sttPoint[5];
        	gpoint = sttPoint[6];
        }
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Logo EOTIH\\logo.png"));
		setTitle("EOTIH - RANKING");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Menu menuBar = new Menu((JPanel) null);
		menuBar.setBounds(0, 0, 434, 22);
		contentPane.add(menuBar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 69, 0));
		panel.setBounds(0, 20, 444, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPic = new JLabel("");
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		lblPic.setIcon(new ImageIcon("G:\\icon\\trophy_120px.png"));
		lblPic.setForeground(Color.WHITE);
		lblPic.setFont(new Font("Impact", Font.PLAIN, 20));
		lblPic.setBounds(157, 0, 120, 120);
		panel.add(lblPic);
		
		JLabel lblNewLabel = new JLabel("TOP 1 SEVER - CONGRATULATIONS");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(37, 118, 372, 35);
		panel.add(lblNewLabel);
		
		JLabel lblTop1 = new JLabel(aname+"  Point : "+apoint);
		lblTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop1.setForeground(Color.WHITE);
		lblTop1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTop1.setBounds(37, 155, 372, 35);
		panel.add(lblTop1);
		
		JPanel panelPlay_1_1_1 = new JPanel();
		panelPlay_1_1_1.setLayout(null);
		panelPlay_1_1_1.setBackground(new Color(255, 255, 255));
		panelPlay_1_1_1.setBounds(0, 230, 444, 52);
		contentPane.add(panelPlay_1_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon("G:\\icon\\silver_medal_50px.png"));
		lblNewLabel_2_1_1.setBounds(10, 0, 50, 50);
		panelPlay_1_1_1.add(lblNewLabel_2_1_1);
		
		JLabel lblTop2 = new JLabel("TOP 2 : " +bname+"  Point : "+bpoint);
		lblTop2.setHorizontalAlignment(SwingConstants.LEFT);
		lblTop2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTop2.setBounds(74, 10, 348, 32);
		panelPlay_1_1_1.add(lblTop2);
		
		JPanel panelPlay_1_1_1_1 = new JPanel();
		panelPlay_1_1_1_1.setLayout(null);
		panelPlay_1_1_1_1.setBackground(new Color(255, 255, 255));
		panelPlay_1_1_1_1.setBounds(0, 291, 444, 52);
		contentPane.add(panelPlay_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1.setIcon(new ImageIcon("G:\\icon\\bronze_medal_50px.png"));
		lblNewLabel_2_1_1_1.setBounds(10, 0, 50, 50);
		panelPlay_1_1_1_1.add(lblNewLabel_2_1_1_1);
		
		JLabel lblTop3 = new JLabel("TOP 3 : "+cname+"  Point : "+cpoint);
		lblTop3.setHorizontalAlignment(SwingConstants.LEFT);
		lblTop3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTop3.setBounds(74, 10, 348, 32);
		panelPlay_1_1_1_1.add(lblTop3);
		
		JPanel panelPlay_1_1_1_2 = new JPanel();
		panelPlay_1_1_1_2.setLayout(null);
		panelPlay_1_1_1_2.setBackground(Color.WHITE);
		panelPlay_1_1_1_2.setBounds(0, 350, 444, 52);
		contentPane.add(panelPlay_1_1_1_2);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("");
		lblNewLabel_2_1_1_2.setBounds(10, 0, 50, 50);
		panelPlay_1_1_1_2.add(lblNewLabel_2_1_1_2);
		
		JLabel lblTop4 = new JLabel("TOP 4 : "+dname+"  Point : "+dpoint);
		lblTop4.setHorizontalAlignment(SwingConstants.LEFT);
		lblTop4.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTop4.setBounds(74, 10, 348, 32);
		panelPlay_1_1_1_2.add(lblTop4);
		
		JPanel panelPlay_1_1_1_2_1 = new JPanel();
		panelPlay_1_1_1_2_1.setLayout(null);
		panelPlay_1_1_1_2_1.setBackground(Color.WHITE);
		panelPlay_1_1_1_2_1.setBounds(0, 412, 444, 52);
		contentPane.add(panelPlay_1_1_1_2_1);
		
		JLabel lblNewLabel_2_1_1_2_1 = new JLabel("");
		lblNewLabel_2_1_1_2_1.setBounds(10, 0, 50, 50);
		panelPlay_1_1_1_2_1.add(lblNewLabel_2_1_1_2_1);
		
		JLabel lblTop = new JLabel("TOP 5  : "+ename+"  Point : "+epoint);
		lblTop.setHorizontalAlignment(SwingConstants.LEFT);
		lblTop.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTop.setBounds(74, 10, 348, 32);
		panelPlay_1_1_1_2_1.add(lblTop);
		
		JPanel panelPlay_1_1_1_2_2 = new JPanel();
		panelPlay_1_1_1_2_2.setLayout(null);
		panelPlay_1_1_1_2_2.setBackground(Color.WHITE);
		panelPlay_1_1_1_2_2.setBounds(0, 474, 444, 52);
		contentPane.add(panelPlay_1_1_1_2_2);
		
		JLabel lblNewLabel_2_1_1_2_2 = new JLabel("");
		lblNewLabel_2_1_1_2_2.setBounds(10, 0, 50, 50);
		panelPlay_1_1_1_2_2.add(lblNewLabel_2_1_1_2_2);
		
		JLabel lblTop_1 = new JLabel("TOP 6 : "+fname+"  Point : "+fpoint);
		lblTop_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTop_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTop_1.setBounds(74, 10, 348, 32);
		panelPlay_1_1_1_2_2.add(lblTop_1);
		
		JPanel panelPlay_1_1_1_2_3 = new JPanel();
		panelPlay_1_1_1_2_3.setLayout(null);
		panelPlay_1_1_1_2_3.setBackground(Color.WHITE);
		panelPlay_1_1_1_2_3.setBounds(0, 536, 444, 52);
		contentPane.add(panelPlay_1_1_1_2_3);
		
		JLabel lblNewLabel_2_1_1_2_3 = new JLabel("");
		lblNewLabel_2_1_1_2_3.setBounds(10, 0, 50, 50);
		panelPlay_1_1_1_2_3.add(lblNewLabel_2_1_1_2_3);
		
		JLabel lblTop_2 = new JLabel("TOP 7 : "+gname+"  Point : "+gpoint);
		lblTop_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblTop_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTop_2.setBounds(74, 10, 348, 32);
		panelPlay_1_1_1_2_3.add(lblTop_2);
	}
}
