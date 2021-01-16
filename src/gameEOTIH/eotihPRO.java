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
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.connect.spi.Connection;

import gameEOTIH.eotihLogin;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.SwingConstants;
import javax.swing.JTextField;


public class eotihPRO extends JFrame {

	private JPanel contentPane;
	public static String hoten = "";
	public static String point = "";
	public static String tendn = "";
	public static String abc = "";
	public static String daylacauhoidautien = "";
	public static String cauhoi = "";
	public static String ques = "";
	public static String ans = "";
	public static int diem = 0;
	
	private int question_num = 0;

    static ArrayList<String> quess = new ArrayList<String>();
    static ArrayList<String> anss = new ArrayList<String>();
	
	public static String nguoidungnhapvao = "";
	private JTextField txtAns;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eotihPRO abc = new eotihPRO();
					abc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	

	/**
	 * Create the frame.
	 */
	public eotihPRO() {
		//Lấy list câu hỏi trong database
		String[] questionType= {};
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eotih","root","letronghieu");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select ques from question");
			while(rs.next()) {
				cauhoi = rs.getString(1);
				List<String> list = new ArrayList<String>(Arrays.asList(questionType));
				if(!(list.contains(cauhoi))) {
					list.add(cauhoi);
				}
				questionType = list.toArray(questionType);
			}
			// Select câu hỏi đầu tiên 
			ResultSet rs2 = stmt.executeQuery("select ques from question");
			rs2.next();
			daylacauhoidautien = rs2.getString(1);
			
			// Select điểm
			ResultSet rs3 = stmt.executeQuery("select point from taikhoan where tendn='"+eotihLogin.tendn+"'");
			if(rs3.next()){
				diem = rs3.getInt(1);
			}else {
				System.out.println("Không lấy được điểm");
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
		
		JPanel ansPane = new JPanel();
		ansPane.setLayout(null);
		ansPane.setBackground(Color.WHITE);
		ansPane.setBounds(47, 166, 330, 162);
		contentPane.add(ansPane);
		
		txtAns = new JTextField();
		txtAns.setEnabled(false);
		txtAns.setText("abc");
		txtAns.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtAns.setColumns(10);
		txtAns.setBounds(29, 55, 259, 65);
		ansPane.add(txtAns);
		
		JLabel lblNewLabel_2 = new JLabel("Answer :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(29, 10, 103, 39);
		ansPane.add(lblNewLabel_2);
		
		JPanel quesPane = new JPanel();
		quesPane.setLayout(null);
		quesPane.setBackground(new Color(65, 105, 225));
		quesPane.setBounds(0, 20, 447, 200);
		contentPane.add(quesPane);
		
		JLabel lblNewLabel_1 = new JLabel("Question : ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(10, 10, 168, 39);
		quesPane.add(lblNewLabel_1);
		
		JLabel lblQues = new JLabel(daylacauhoidautien);
		lblQues.setHorizontalAlignment(SwingConstants.CENTER);
		lblQues.setForeground(Color.WHITE);
		lblQues.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblQues.setBounds(10, 48, 426, 61);
		quesPane.add(lblQues);
		
		JLabel lblPoint = new JLabel();
	    lblPoint.setText("Score: " +diem);
		lblPoint.setForeground(Color.WHITE);
		lblPoint.setFont(new Font("Impact", Font.PLAIN, 20));
		lblPoint.setBounds(323, 119, 102, 26);
		quesPane.add(lblPoint);
		
		Menu menuBar = new Menu(quesPane);
		menuBar.setBounds(0, 0, 434, 22);
		contentPane.add(menuBar);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(Color.YELLOW);
		panel_1_1_1.setBounds(47, 463, 150, 70);
		contentPane.add(panel_1_1_1);
		panel_1_1_1.setLayout(null);
		
		JLabel lblPlay = new JLabel("Play");
		lblPlay.setBounds(0, 0, 150, 71);
		panel_1_1_1.add(lblPlay);
		lblPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Khi người dùng bấm Play thì sẽ mở ô khóa và get câu hỏi
				txtAns.setEnabled(true);
                String listcauhoi = null;
                String[] cauhoinhapvao = {  };
                String listcautraloi = null;
                String[] cautraloinhapvao = {  };
                String type = "toanhoc";
				nguoidungnhapvao = txtAns.getText();
				
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
			}
		});
		lblPlay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlay.setForeground(Color.WHITE);
		lblPlay.setFont(new Font("Impact", Font.PLAIN, 30));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(243, 463, 150, 71);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNext = new JLabel("NEXT");
		lblNext.setBounds(0, 0, 150, 71);
		panel.add(lblNext);
		lblNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nguoidungnhapvao = txtAns.getText();
		        if (nguoidungnhapvao.equalsIgnoreCase(anss.get(question_num))) {
		            question_num++;
		            diem++;
		            lblPoint.setText("Score: " +diem);
		            try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						java.sql.Connection connn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eotih","root","letronghieu");
						Statement stmt2 = connn.createStatement();
						int rsaa = stmt2.executeUpdate("update taikhoan set point ='"+diem+"' where tendn ='"+eotihLogin.tendn+"'");
						if(rsaa == 1) {
							JOptionPane.showMessageDialog(null, "Success!");
							try
				        	{
								WinMusic win = new WinMusic();
								win.start();
							}
				        	catch (IOException e1) {}
						}else {
							JOptionPane.showMessageDialog(null, "Faill!");
						}
						connn.close();
						}catch(Exception e1) {
							System.out.println(e1);
						}
		            try {
		            	lblQues.setText(quess.get(question_num));
		            }
		            catch (IndexOutOfBoundsException exception) {
		            	lblQues.setText("Game Over!!!");
		            	txtAns.setEnabled(false);
		            }
		        } else {
		            System.out.print("Trả lời sai!!");
		            try
		        	{
		            	LoseMusic lose = new LoseMusic();
						lose.start();
					}
		        	catch (LineUnavailableException e1) {} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
			}
		});
		lblNext.setHorizontalAlignment(SwingConstants.CENTER);
		lblNext.setForeground(Color.WHITE);
		lblNext.setFont(new Font("Impact", Font.PLAIN, 30));
		
	}
}
