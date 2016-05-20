package police;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Font;

public class Ofiicer extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField surname;
	private JTextField username;
	private JTextField rank;
	private JTextField date;
	private JButton submit;
	int count;
	Connection connection = null;
	private JPasswordField password;


	/**
	 * Create the frame.
	 */
	public Ofiicer() {
		setBackground(new Color(0, 0, 0));
		JLabel name1 = new JLabel("NAME :");
		name1.setFont(new Font("Tahoma", Font.BOLD, 11));
		name1.setBackground(new Color(255, 255, 255));
		name1.setForeground(new Color(255, 255, 255));
		connection = sqliteconnection.dbconnector();
		setTitle("Add Officer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = new JTextField();
		name.setBounds(321, 78, 185, 28);
		contentPane.add(name);
		name.setColumns(10);
		Hover.effect(name);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(new Color(240,240,240));
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Commissoner temp = new Commissoner();
				temp.setVisible(true);
			}
		});
		btnNewButton.setBounds(747, 0, 95, 28);
		contentPane.add(btnNewButton);
		
		surname = new JTextField();
		Hover.effect(surname);
		surname.setBounds(321, 131, 185, 28);
		contentPane.add(surname);
		surname.setColumns(10);
		
		username = new JTextField();
		Hover.effect(username);
		username.setBounds(321, 184, 185, 28);
		contentPane.add(username);
		username.setColumns(10);
		
		rank = new JTextField();
		Hover.effect(rank);
		rank.setBounds(321, 309, 185, 28);
		contentPane.add(rank);
		rank.setColumns(10);
		
		date = new JTextField();
		Hover.effect(date);
		date.setBounds(321, 370, 185, 28);
		contentPane.add(date);
		date.setColumns(10);
		
		submit = new JButton("SUBMIT");
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				submit.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				submit.setBackground(new Color(240,240,240));
				
			}
		});
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check()==1){
					count=0;
					
					try {
						String query2 = "SELECT * FROM OFFICERS ";
						PreparedStatement pst = connection.prepareStatement(query2);
						ResultSet rs = pst.executeQuery();
						while(rs.next()){count= rs.getInt("ID");}
						String query = "insert into OFFICERS (ID,NAME,SURNAME,USERNAME,PASSWORD,RANK,DATE) values (?,?,?,?,?,?,?) ";
						PreparedStatement pst2 = connection.prepareStatement(query);
						pst2.setString(1, String.valueOf(count+1));
						pst2.setString(2, name.getText());
						pst2.setString(3, surname.getText());
					    pst2.setString(4, username.getText());
					    pst2.setString(5, password.getText());
					    pst2.setString(6, rank.getText());
					    pst2.setString(7, date.getText());
						pst2.execute();
						JOptionPane.showMessageDialog(null, "OFFICER ADDED");
						dispose();
						Commissoner temp = new Commissoner();
						temp.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.exit(1);
					}
				}
					else {JOptionPane.showMessageDialog(null, "FILL ALL THE DETAILS ");}
			}
		});
		submit.setBounds(156, 463, 113, 36);
		contentPane.add(submit);
		
		
		name1.setBounds(250, 85, 61, 14);
		contentPane.add(name1);
		
		JLabel surname2 = new JLabel("SURNAME :");
		surname2.setFont(new Font("Tahoma", Font.BOLD, 11));
		surname2.setForeground(new Color(255, 255, 255));
		surname2.setBounds(231, 138, 61, 14);
		contentPane.add(surname2);
		
		JLabel username1 = new JLabel("USERNAME :");
		username1.setForeground(new Color(255, 255, 255));
		username1.setFont(username1.getFont().deriveFont(username1.getFont().getStyle() | Font.BOLD));
		username1.setBounds(229, 188, 82, 21);
		contentPane.add(username1);
		
		JLabel password1 = new JLabel("PASSWORD :");
		password1.setFont(new Font("Tahoma", Font.BOLD, 11));
		password1.setForeground(new Color(255, 255, 255));
		password1.setBounds(224, 243, 103, 14);
		contentPane.add(password1);
		
		JLabel rank1 = new JLabel("RANK :");
		rank1.setFont(new Font("Tahoma", Font.BOLD, 11));
		rank1.setForeground(new Color(255, 255, 255));
		rank1.setBounds(250, 316, 46, 14);
		contentPane.add(rank1);
		
		JLabel date1 = new JLabel("DATE of JOINING :");
		date1.setFont(new Font("Tahoma", Font.BOLD, 11));
		date1.setForeground(new Color(255, 255, 255));
		date1.setBounds(197, 374, 130, 21);
		contentPane.add(date1);
		
		JLabel lblDdmmyr = new JLabel("DD/MM/YR");
		lblDdmmyr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDdmmyr.setForeground(new Color(255, 255, 255));
		lblDdmmyr.setBounds(556, 377, 76, 14);
		contentPane.add(lblDdmmyr);
		
		JLabel lblInspectorcommissoner = new JLabel("INSPECTOR/COMMISSONER");
		lblInspectorcommissoner.setForeground(new Color(255, 255, 255));
		lblInspectorcommissoner.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInspectorcommissoner.setBounds(556, 316, 171, 14);
		contentPane.add(lblInspectorcommissoner);
		
		password = new JPasswordField();
		Hover.effect(password);
		password.setBounds(321, 235, 185, 31);
		contentPane.add(password);
		
	}
public int check(){
		
		if(name.getText().isEmpty()|| surname.getText().isEmpty() || username.getText().isEmpty()|| password.getText().isEmpty()|| rank.getText().isEmpty() || date.getText().isEmpty() )
			return 0;
		return 1;
	}
}
