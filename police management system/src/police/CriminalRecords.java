package police;

import java.awt.BorderLayout;
import java.sql.*;

import javax.swing.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.MatteBorder;

public class CriminalRecords extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	private JTextField search;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public CriminalRecords() {
		
		connection = sqliteconnection.dbconnector();
		setTitle("CRIMINAL RECORDS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setForeground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSearch = new JButton("Load All");
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
					String query = "SELECT * FROM CRIMINAL";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(1);
				}
			}
		});
		btnSearch.setBounds(366, 47, 89, 23);
		contentPane.add(btnSearch);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 81, 694, 371);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setBackground(new Color(255, 255, 255));
		table.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(table);
		
		search = new JTextField();
		Hover.effect(search);
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					
					String query = "SELECT * FROM CRIMINAL WHERE NAME=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,search.getText() );
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		search.setBounds(287, 463, 230, 40);
		contentPane.add(search);
		search.setColumns(10);
		
		JLabel lblStartTypingName = new JLabel("Type The Name To Search(CAPS)\r\n\r\n");
		lblStartTypingName.setForeground(new Color(255, 255, 255));
		lblStartTypingName.setBounds(75, 469, 202, 29);
		contentPane.add(lblStartTypingName);
	}
}
