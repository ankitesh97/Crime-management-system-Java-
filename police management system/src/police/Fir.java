package police;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;

import java.sql.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Fir extends JFrame {

	private JPanel contentpane;
	private JTextField name;
	private JLabel lblSurname;
	private JTextField surname;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton female;
	JRadioButton male = new JRadioButton("MALE");
	private JLabel ADDRESS;
	private JTextField address;
	private JLabel lblAge;
	private JTextField age;
	private JLabel lblYears;
	private JLabel lblCrime;
	private JTextArea details;
	private JTextField crime;
	private JTextField date;
	int count;

	Connection connection = null;

	/**
	 * Create the frame.
	 */
	public Fir() {
		connection = sqliteconnection.dbconnector();
		setTitle("FIR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 571);
		contentpane = new JPanel();
		contentpane.setBackground(new Color(128, 0, 0));
		contentpane.setForeground(new Color(128, 0, 0));
		
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		female = new JRadioButton("FEMALE");
		female.setForeground(new Color(255, 255, 255));
		female.setBackground(new Color(128, 0, 0));
		buttonGroup.add(female);
		female.setBounds(340, 123, 74, 50);
		contentpane.add(female);
		
		name = new JTextField();
		Hover.effect(name);
		name.setBounds(236, 27, 267, 32);
		contentpane.add(name);
		name.setColumns(10);
		
		JLabel lblName = new JLabel("NAME : ");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(145, 27, 53, 32);
		contentpane.add(lblName);
		
		lblSurname = new JLabel("SURNAME :");
		lblSurname.setForeground(Color.WHITE);
		lblSurname.setBackground(Color.WHITE);
		lblSurname.setBounds(124, 84, 74, 32);
		contentpane.add(lblSurname);
		
		surname = new JTextField();
		Hover.effect(surname);
		surname.setColumns(10);
		surname.setBounds(236, 84, 267, 32);
		contentpane.add(surname);
		
		JLabel SEX = new JLabel("SEX :");
		SEX.setForeground(Color.WHITE);
		SEX.setBounds(152, 141, 46, 14);
		contentpane.add(SEX);
		
		
		buttonGroup.add(male);
		male.setBackground(new Color(128, 0, 0));
		male.setForeground(new Color(255, 255, 255));
		male.setBounds(236, 123, 61, 50);
		contentpane.add(male);
		
		ADDRESS = new JLabel("ADDRESS :");
		
		ADDRESS.setForeground(Color.WHITE);
		ADDRESS.setBounds(124, 206, 74, 14);
		contentpane.add(ADDRESS);
		
		address = new JTextField();
		Hover.effect(address);
		address.setBounds(236, 200, 267, 50);
		contentpane.add(address);
		address.setColumns(10);
		
		lblAge = new JLabel("AGE :");
		lblAge.setForeground(Color.WHITE);
		lblAge.setBounds(145, 285, 46, 14);
		contentpane.add(lblAge);
		
		age = new JTextField();
		Hover.effect(age);
		age.setBounds(236, 281, 61, 23);
		contentpane.add(age);
		age.setColumns(10);
		
		lblYears = new JLabel("YEARS");
		lblYears.setForeground(Color.WHITE);
		lblYears.setBounds(314, 285, 46, 14);
		contentpane.add(lblYears);
		
		lblCrime = new JLabel("CRIME :");
		lblCrime.setForeground(Color.WHITE);
		lblCrime.setBounds(131, 341, 46, 14);
		contentpane.add(lblCrime);
		
		JButton SUBMIT = new JButton("SUBMIT");
		SUBMIT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				SUBMIT.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				SUBMIT.setBackground(new Color(240,240,240));
				
			}
		});
		SUBMIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check()==1){
				count=0;
				
				try {
					String query2 = "SELECT * FROM FIR ";
					PreparedStatement pst = connection.prepareStatement(query2);
					ResultSet rs = pst.executeQuery();
					while(rs.next())count=rs.getInt("NO");;
					String query = "insert into FIR (NO,NAME,SURNAME,SEX,ADDRESS,AGE,CRIME,DETAILS,DATE,BY) values (?,?,?,?,?,?,?,?,?,?) ";
					PreparedStatement pst2 = connection.prepareStatement(query);
					pst2.setString(1, String.valueOf(count+1));
					pst2.setString(2, name.getText());
					pst2.setString(3, surname.getText());
					if(male.isSelected())
					pst2.setString(4, String.valueOf('M'));
					else pst2.setString(4, String.valueOf('F'));
					pst2.setString(5, address.getText());
					pst2.setString(6, age.getText());
					pst2.setString(7, crime.getText());
					pst2.setString(8, details.getText());
					pst2.setString(9, date.getText());
					pst2.setString(10, Inspector.name+" "+Inspector.surname);
					pst2.execute();
					JOptionPane.showMessageDialog(null, "FIR Submitted");
					dispose();
					Inspector temp = new Inspector();
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
		SUBMIT.setBounds(88, 486, 89, 23);
		contentpane.add(SUBMIT);
		
		JButton BACK = new JButton("BACK");
		BACK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				BACK.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				BACK.setBackground(new Color(240,240,240));
				
			}
		});
		BACK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inspector temp = new Inspector();
				temp.setVisible(true);
			}
		});
		BACK.setBounds(753, 0, 89, 23);
		contentpane.add(BACK);
		
		JLabel lblDetails = new JLabel("Details :");
		lblDetails.setForeground(Color.WHITE);
		lblDetails.setBounds(131, 403, 46, 14);
		contentpane.add(lblDetails);
		
		details = new JTextArea();
		details.setLineWrap(true);
		details.setBounds(238, 398, 441, 81);
		contentpane.add(details);
		
		crime = new JTextField();
		Hover.effect(crime);
		crime.setBounds(236, 329, 267, 39);
		contentpane.add(crime);
		crime.setColumns(10);
		
		JLabel lblDateDdmmyr = new JLabel("DATE:   DD/MM/YR");
		lblDateDdmmyr.setForeground(Color.WHITE);
		lblDateDdmmyr.setBounds(456, 285, 140, 14);
		contentpane.add(lblDateDdmmyr);
		
		date = new JTextField();
		Hover.effect(date);
		date.setBounds(558, 282, 121, 20);
		contentpane.add(date);
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
	
	}
	
	public int check(){
		
		if( name.getText().isEmpty() ||  surname.getText().isEmpty() || (male.isSelected()==false && female.isSelected()==false) || address.getText().isEmpty() || age.getText().isEmpty() || date.getText().isEmpty() || crime.getText().isEmpty() || details.getText().isEmpty()  )
			return 0;
		return 1;
	}
}
