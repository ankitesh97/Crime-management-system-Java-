package police;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Font;

public class Caseapp extends JFrame {

	private JPanel contentPane;
	private JTextField Casenumber;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton close;
	private JTable table;
	private JScrollPane scrollPane;
	Connection connection = null;
	private JTextField date;
	private JLabel date1;



	/**
	 * Create the frame.
	 */
	public Caseapp() {
		setTitle("CASE APPROVAL");
		connection = sqliteconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
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
				Commissoner temp = new Commissoner();
				temp.setVisible(true);
			}
		});
		btnBack.setBounds(753, 0, 89, 23);
		contentPane.add(btnBack);
		
		JLabel no = new JLabel("Enter Case Number :");
		no.setForeground(new Color(255, 255, 255));
		no.setBounds(73, 441, 123, 23);
		contentPane.add(no);
		
		Casenumber = new JTextField();
		Hover.effect(Casenumber);
		Casenumber.setBounds(206, 441, 217, 22);
		contentPane.add(Casenumber);
		Casenumber.setColumns(10);
		
		JButton btnSearch = new JButton("Load All Cases");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearch.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSearch.setBackground(new Color(240,240,240));
				
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM CASES";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(1);
				}
				
			}
		});
		btnSearch.setBounds(331, 58, 141, 23);
		contentPane.add(btnSearch);
		
		close = new JButton("Close Case");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				close.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				close.setBackground(new Color(240,240,240));
				
			}
		});
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check()==1){
				try {
					String x = "C";
					String query = "UPDATE CASES set STATUS='"+x+"' , CLOSED_DATE='"+date.getText()+"' WHERE NO='"+Casenumber.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "CASE STATUS UPDATED");
					
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(1);
				}
				}
				else JOptionPane.showMessageDialog(null, "FILL ALL THE DETAILS");
				
			}
		});
		close.setBounds(466, 489, 116, 23);
		contentPane.add(close);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 96, 769, 328);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		scrollPane.setViewportView(table);
		
		date = new JTextField();
		Hover.effect(date);
		date.setBounds(206, 490, 217, 20);
		contentPane.add(date);
		date.setColumns(10);
		
		date1 = new JLabel("Closing Date:");
		date1.setForeground(new Color(255, 255, 255));
		date1.setBounds(73, 493, 109, 14);
		contentPane.add(date1);
	}
	
	public int check(){
		if(Casenumber.getText().isEmpty() || date.getText().isEmpty())
			return 0;
		return 1;
		
	}

}
