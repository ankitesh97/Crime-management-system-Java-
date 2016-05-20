package police;

import java.applet.Applet;
import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;



public class MAIN extends JFrame {

	private JPanel contentPane;
	private JTextField UID;
	private JPasswordField PWD;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MAIN frame = new MAIN();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;

	/**
	 * Create the frame.
	 */
	public MAIN() {
		
		connection = sqliteconnection.dbconnector();
		setTitle("HOME PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(128, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		UID = new JTextField();
		UID.setBounds(579, 65, 109, 23);
		contentPane.add(UID);
		UID.setColumns(10);
		
		JLabel lblUserId = new JLabel("USER ID :");
		lblUserId.setFont(lblUserId.getFont().deriveFont(lblUserId.getFont().getStyle() | Font.BOLD));
		lblUserId.setForeground(Color.WHITE);
		lblUserId.setBounds(480, 67, 70, 19);
		contentPane.add(lblUserId);
		
		JLabel lblPwd = new JLabel("PASSWORD :");
		lblPwd.setFont(lblPwd.getFont().deriveFont(lblPwd.getFont().getStyle() | Font.BOLD));
		lblPwd.setForeground(Color.WHITE);
		lblPwd.setBounds(480, 112, 89, 19);
		contentPane.add(lblPwd);
		
		JButton btnSignIn = new JButton("SIGN IN");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement pst=null;
				
				try{
					String query = "SELECT * FROM OFFICERS where username=? and password=?";
					String rank=null;
					pst = connection.prepareStatement(query);
					pst.setString(1, UID.getText());
					pst.setString(2, PWD.getText());
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count++;
						rank = rs.getString("RANK");
						if(rank.equals("INSPECTOR")){
						Inspector.name = rs.getString("NAME");
						Inspector.surname = rs.getString("SURNAME");
						}
						
						else {
							Commissoner.name = rs.getString("NAME");
							Commissoner.surname = rs.getString("SURNAME");
						}
						}
					if(count==1){
						if(rank.equals("INSPECTOR")){
						dispose();
						Inspector ins_obj = new Inspector();
						ins_obj.setVisible(true);
						}
						else if(rank.equals("COMMISSIONER")){
							dispose();
							Commissoner temp = new Commissoner();
							temp.setVisible(true);
						}
						
						
					}
					else JOptionPane.showMessageDialog(null, "user name and password not correct");
					rs.close();
					pst.close();
					
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
					System.exit(1);
					
				}
			}
		});
		btnSignIn.setBounds(579, 172, 89, 23);
		contentPane.add(btnSignIn);
		
		PWD = new JPasswordField();
		PWD.setBounds(579, 111, 109, 19);
		contentPane.add(PWD);
		
		JLabel justice_img = new JLabel("");
		justice_img.setBounds(35, 28, 377, 422);
		contentPane.add(justice_img);
		Image img = new ImageIcon (this.getClass().getResource("/india justice(main page).jpe")).getImage();
		justice_img.setIcon(new ImageIcon(img));
		getContentPane().add(justice_img);
		
		
		
		
	}
}
