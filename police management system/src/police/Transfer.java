package police;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Font;

public class Transfer extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTable table;
	private JTextField place;


	Connection connection = null;

	/**
	 * Create the frame.
	 */
	public Transfer() {
		connection = sqliteconnection.dbconnector();
		setTitle("TRANSFER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblEnterName = new JLabel("Enter ID Of Officer To Transfer");
		lblEnterName.setForeground(new Color(255, 255, 255));
		lblEnterName.setBounds(170, 439, 171, 17);
		contentPane.add(lblEnterName);
		
		JButton btnSearch = new JButton("Transfer");
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
			if(id.getText().isEmpty() || place.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "FILL ALL DETAILS");
			else{
				try {
					String query = "DELETE FROM OFFICERS WHERE ID='"+id.getText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "TRANSFER LETTER WILL BE SENT");
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(1);
				}
				
			}
			}
		});
		btnSearch.setBounds(541, 472, 89, 23);
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
		
		id = new JTextField();
		Hover.effect(id);
		id.setBounds(351, 433, 147, 29);
		contentPane.add(id);
		id.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 67, 714, 352);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Load All Ofiicers");
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
				try {
					String query = "SELECT ID,NAME,SURNAME,RANK,DATE FROM OFFICERS";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while(rs.next()){
					if(rs.getString("RANK").equals("COMMISSONER"));
					else
					table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(1);
				}
				
			}
		});
		btnNewButton.setBounds(327, 30, 171, 23);
		contentPane.add(btnNewButton);
		
		place = new JTextField();
		Hover.effect(place);
		place.setBounds(349, 469, 149, 29);
		contentPane.add(place);
		place.setColumns(10);
		
		JLabel lblEnterPlace = new JLabel("Enter Place ");
		lblEnterPlace.setForeground(new Color(255, 255, 255));
		lblEnterPlace.setBounds(170, 475, 74, 17);
		contentPane.add(lblEnterPlace);
	}
}
