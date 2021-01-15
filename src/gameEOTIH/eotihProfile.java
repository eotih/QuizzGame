package gameEOTIH;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class eotihProfile extends JFrame {

	private JPanel contentPane;
	private JTextField txtHoten;
	private JTextField txtEmail;
	private JTextField txtUsername;
	private JTextField txtPass;
	
	public static String pass = "";
	private JPasswordField txtNewpass;
	private JPasswordField txtconfirm;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eotihProfile frame = new eotihProfile();
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
	public eotihProfile() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Logo EOTIH\\logo.png"));
		setTitle("EOTIH - RANKING");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EDIT PROFILE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 27, 478, 39);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 78, 229, 351);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("USER PROFILE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(0, 0, 229, 40);
		panel.add(lblNewLabel_1);
		
		txtHoten = new JTextField();
		
		txtHoten.setBounds(10, 61, 209, 30);
		panel.add(txtHoten);
		txtHoten.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 119, 209, 30);
		panel.add(txtEmail);
		
		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setColumns(10);
		txtUsername.setBounds(10, 180, 209, 30);
		panel.add(txtUsername);
		
		JLabel lblNewLabel_1_2 = new JLabel("Full Name :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(-66, 31, 229, 40);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Email : ");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(-80, 88, 229, 40);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Username : ");
		lblNewLabel_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2_1_1.setBounds(-66, 148, 229, 40);
		panel.add(lblNewLabel_1_2_1_1);
		
		JButton btnSaveuser = new JButton("SAVE PROFILE");
		btnSaveuser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eotih","root","letronghieu");
					Statement stmt = conn.createStatement();
					int rs = stmt.executeUpdate("update taikhoan set hoten ='"+txtHoten.getText()+"',email = '"+txtEmail.getText()+"' where tendn ='"+eotihLogin.tendn+"'");
					if(rs == 1) {
						JOptionPane.showMessageDialog(null, "Thay đổi thông tin tài khoản thành công !");
					}else {
						JOptionPane.showMessageDialog(null, "Thay đổi thông tin tài khoản không thành công!");
					}
					conn.close();
					}catch(Exception e1) {
						System.out.println(e1);
					}
			}
		});
		btnSaveuser.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSaveuser.setBounds(10, 242, 209, 50);
		panel.add(btnSaveuser);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(249, 78, 229, 351);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(0, 0, 229, 40);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Old password :");
		lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2_2.setBounds(10, 27, 229, 40);
		panel_1.add(lblNewLabel_1_2_2);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(10, 62, 209, 30);
		panel_1.add(txtPass);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("New password : ");
		lblNewLabel_1_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_1_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2_1_2.setBounds(10, 94, 229, 40);
		panel_1.add(lblNewLabel_1_2_1_2);
		
		JLabel lblNewLabel_1_2_1_2_1 = new JLabel("Confirm password : ");
		lblNewLabel_1_2_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2_1_2_1.setBounds(10, 145, 229, 40);
		panel_1.add(lblNewLabel_1_2_1_2_1);
		
		JButton btnSavepass = new JButton("SAVE PASS");
		btnSavepass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtPass.getText().equals(pass)) {
					if(txtNewpass.getText().equals(txtconfirm.getText())) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eotih","root","letronghieu");
							Statement stmt = conn.createStatement();
							int rs = stmt.executeUpdate("update taikhoan set pass ='"+txtNewpass.getText()+"' where tendn ='"+eotihLogin.tendn+"'");
							if(rs == 1) {
								JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thành công !");
							}else {
								JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu không thành công!");
							}
							conn.close();
							}catch(Exception e1) {
								System.out.println(e1);
							}
					}else {
						JOptionPane.showMessageDialog(null, "Mật khẩu mới không khớp !");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Sai mật khẩu cũ !");
				}
			}
		});
		btnSavepass.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSavepass.setBounds(10, 242, 209, 50);
		panel_1.add(btnSavepass);
		
		txtNewpass = new JPasswordField();
		txtNewpass.setBounds(10, 125, 209, 30);
		panel_1.add(txtNewpass);
		
		txtconfirm = new JPasswordField();
		txtconfirm.setBounds(10, 178, 209, 30);
		panel_1.add(txtconfirm);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eotih","root","letronghieu");
			Statement stmt = conn.createStatement();
			//String url ="select * from taikhoan where tendn ='"+eotihLogin.tendn+"'";
			String url ="select * from taikhoan where tendn ='"+eotihLogin.tendn+"'";
			ResultSet rs = stmt.executeQuery(url);
			if(rs.next()) {
				txtHoten.setText(rs.getString(1));
				txtEmail.setText(rs.getString(2));
				txtUsername.setText(rs.getString(3));
				pass = rs.getString(4);
			}else {
				JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu !");
			}
			conn.close();
			}catch(Exception e1) {
				System.out.println(e1);
			}
	}
}
