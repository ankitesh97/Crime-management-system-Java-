package police;

import java.awt.BorderLayout;
import java.sql.*;

import javax.swing.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;

public class Criminal extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField surname;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField address;
	private JTextField crime;
	private JTextField date;
	JRadioButton male = new JRadioButton("Male");
	JTextArea details = new JTextArea();
	JRadioButton female = new JRadioButton("Female");
	int count;
    Connection connection = null;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Criminal() {
		connection = sqliteconnection.dbconnector();
		setTitle("ADD CRIMINAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Back = new JButton("BACK");
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Back.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Back.setBackground(new Color(240,240,240));
				
			}
		});
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inspector temp = new Inspector();
				temp.setVisible(true);
			}
		});
		Back.setBounds(753, 0, 89, 23);
		contentPane.add(Back);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(100, 49, 46, 14);
		contentPane.add(lblName);
		
		JLabel variable = new JLabel("Surname : ");
		variable.setForeground(new Color(255, 255, 255));
		variable.setBounds(95, 63, 99, 72);
		contentPane.add(variable);
		
		JLabel lblSex = new JLabel("Sex :");
		lblSex.setForeground(new Color(255, 255, 255));
		lblSex.setBackground(new Color(255, 255, 255));
		lblSex.setBounds(105, 137, 46, 14);
		contentPane.add(lblSex);
		
		JLabel lblCrime = new JLabel("Crime : ");
		lblCrime.setForeground(new Color(255, 255, 255));
		lblCrime.setBounds(100, 260, 46, 14);
		contentPane.add(lblCrime);
		
		JLabel lblData = new JLabel("Date :");
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setBounds(100, 323, 46, 14);
		contentPane.add(lblData);
		
		JLabel lblDetails = new JLabel("Details :");
		lblDetails.setForeground(new Color(255, 255, 255));
		lblDetails.setBounds(100, 377, 46, 14);
		contentPane.add(lblDetails);
		
		name = new JTextField();
		Hover.effect(name);
		name.setBounds(159, 45, 206, 24);
		contentPane.add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		Hover.effect(surname);
		surname.setBounds(159, 89, 206, 23);
		contentPane.add(surname);
		surname.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address : ");
		lblAddress.setForeground(new Color(255, 255, 255));
		lblAddress.setBounds(95, 189, 70, 39);
		contentPane.add(lblAddress);
		
		
		buttonGroup.add(male);
		male.setBackground(new Color(128, 0, 0));
		male.setForeground(new Color(255, 255, 255));
		male.setBounds(159, 120, 60, 49);
		contentPane.add(male);
		
	
		buttonGroup.add(female);
		female.setBackground(new Color(128, 0, 0));
		female.setForeground(new Color(255, 255, 255));
		female.setBounds(232, 119, 200, 50);
		contentPane.add(female);
		
		address = new JTextField();
		Hover.effect(address);
		address.setBounds(159, 178, 206, 60);
		contentPane.add(address);
		address.setColumns(10);
		
		crime = new JTextField();
		Hover.effect(crime);
		crime.setBounds(159, 249, 206, 37);
		contentPane.add(crime);
		crime.setColumns(10);
		
		date = new JTextField();
		Hover.effect(date);
		date.setBounds(159, 316, 106, 29);
		contentPane.add(date);
		date.setColumns(10);
		
		details.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				details.setBackground(new Color(250,235,46));
			}
			@Override
			public void focusLost(FocusEvent e) {
				details.setBackground(new Color(240,240,240));
			}
		});
		details.setBounds(156, 372, 305, 72);
		contentPane.add(details);
		
		JButton submit = new JButton("Submit");
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
						
						String query2 = "SELECT * FROM CRIMINAL ";
						PreparedStatement pst = connection.prepareStatement(query2);
						ResultSet rs = pst.executeQuery();
						while(rs.next())count=rs.getInt("NO");
						String query = "insert into CRIMINAL (NO,NAME,SURNAME,SEX,ADDRESS,CRIME,DATE,DETAILS) values (?,?,?,?,?,?,?,?) ";
						PreparedStatement pst2 = connection.prepareStatement(query);
						pst2.setString(1, String.valueOf(count+1));
						pst2.setString(2, name.getText());
						pst2.setString(3, surname.getText());
						if(male.isSelected())
						pst2.setString(4, String.valueOf('M'));
						else pst2.setString(4, String.valueOf('F'));
						pst2.setString(5, address.getText());
						pst2.setString(6, crime.getText());
						pst2.setString(7, date.getText());
						pst2.setString(8, details.getText());
						pst2.execute();
						JOptionPane.showMessageDialog(null, "CRIMINAL ADDED");
						dispose();
						Inspector temp = new Inspector();
						temp.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.exit(1);
					}
				}
				else JOptionPane.showMessageDialog(null, "FILL ALL THE DETAILS");
				
				
			}
		});
		submit.setBounds(100, 481, 89, 23);
		contentPane.add(submit);
		
		JLabel lblDdmmyr = new JLabel("DD/MM/YR");
		lblDdmmyr.setForeground(new Color(255, 255, 255));
		lblDdmmyr.setBounds(275, 323, 75, 14);
		contentPane.add(lblDdmmyr);
	}
	
public int check(){
		
		if(name.getText().isEmpty()|| surname.getText().isEmpty() || (male.isSelected()==false && female.isSelected()==false) || address.getText().isEmpty() ||  date.getText().isEmpty() || crime.getText().isEmpty() || details.getText().isEmpty()  )
			return 0;
		return 1;
}
	
}
