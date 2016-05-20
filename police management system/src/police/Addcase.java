package police;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Addcase extends JFrame {

	private JPanel contentPane;

	Connection connection = null;
	private JTable table;
	private JTextField no;
	private JTextField type;
	private JTextField date;
	JTextPane details = new JTextPane();
	
	

	/**
	 * Create the frame.
	 */
	public Addcase() {
		setTitle("ADD CASE");
		connection = sqliteconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(369, 103, 463, 372);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		scrollPane.setViewportView(table);
		
		JButton fir = new JButton("Load All Firs");
		fir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				fir.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				fir.setBackground(new Color(240,240,240));
				
			}
		});
		
		fir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT NO,CRIME,DETAILS,DATE FROM FIR ";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(1);
				}
			}
		});
		fir.setBounds(568, 479, 104, 23);
		contentPane.add(fir);
		
		no = new JTextField();
		Hover.effect(no);
		
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
		
		no.setBounds(159, 110, 167, 23);
		contentPane.add(no);
		no.setColumns(10);
		
		type = new JTextField();
		Hover.effect(type);
		type.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				type.setBackground(new Color(250,235,46));
			}
			@Override
			public void focusLost(FocusEvent e) {
				type.setBackground(new Color(240,240,240));
			}
		});
		
		type.setBounds(159, 185, 167, 23);
		contentPane.add(type);
		type.setColumns(10);
		
		date = new JTextField();
		Hover.effect(date);
		
		date.setBounds(159, 259, 167, 23);
		contentPane.add(date);
		date.setColumns(10);
		
		
		details.setBounds(159, 322, 195, 106);
		contentPane.add(details);
		
		JLabel lblNewLabel = new JLabel("Case no");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(45, 114, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Case type");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(45, 189, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Open date");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(45, 262, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Details");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(45, 367, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("SUBMIT");
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
				
                  if(check()==1){
					
					
					try {
						
	                    String query = "insert into CASES (NO,CASE_TYPE,OPEN_DATE,STATUS,DETAILS,WITH) values (?,?,?,?,?,?) ";
						PreparedStatement pst2 = connection.prepareStatement(query);
						pst2.setString(1, no.getText());
						pst2.setString(2, type.getText());
						pst2.setString(3, date.getText());
						pst2.setString(4,String.valueOf('O'));
						pst2.setString(5, details.getText());
						pst2.setString(6, Inspector.name+" "+Inspector.surname);
						pst2.execute();
						JOptionPane.showMessageDialog(null, "CASE Submitted");
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
		btnNewButton.setBounds(110, 476, 118, 28);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBack.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBack.setBackground(new Color(240,240,240));
				
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inspector temp = new Inspector();
				temp.setVisible(true);
				
			}
		});
		btnBack.setBounds(753, 0, 89, 23);
		contentPane.add(btnBack);
	}
	public int check(){
		
		if( no.getText().isEmpty() ||  type.getText().isEmpty() || date.getText().isEmpty() || details.getText().isEmpty()  )
			return 0;
		return 1;
	}
}
