package police;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;

public class Commissoner extends JFrame {

	private JPanel contentPane;
	static String name;
	static String surname;


	/**
	 * Create the frame.
	 */
	public Commissoner() {
		setTitle("COMMISSONER");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 571);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(100, 149, 237));
		menuBar.setBounds(0, 0, 69, 21);
		contentPane.add(menuBar);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MAIN temp = new MAIN();
				temp.setVisible(true);
			}
		});
		mnSettings.add(mntmLogout);
		
		JButton btnNewButton = new JButton("Case Approval");
		Image img1 = new ImageIcon (this.getClass().getResource("/approve.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img1));
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
				Caseapp temp = new Caseapp();
				temp.setVisible(true);
			}
		});
		btnNewButton.setBounds(135, 108, 190, 121);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Criminal Search");
		Image img = new ImageIcon (this.getClass().getResource("/search.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(img));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setBackground(new Color(240,240,240));
				
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CriminalRecords temp = new CriminalRecords();
				temp.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(135, 315, 190, 113);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Transfer Officer");
		Image img2 = new ImageIcon (this.getClass().getResource("/transfer.png")).getImage();
		btnNewButton_2.setIcon(new ImageIcon(img2));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setBackground(new Color(240,240,240));
				
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Transfer temp = new Transfer();
				temp.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(521, 108, 190, 121);
		contentPane.add(btnNewButton_2);
		
		JButton officer = new JButton("Add Officer");
		Image img3 = new ImageIcon (this.getClass().getResource("/officer.png")).getImage();
		officer.setIcon(new ImageIcon(img3));
		officer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				officer.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				officer.setBackground(new Color(240,240,240));
				
			}
		});
		officer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Ofiicer temp = new Ofiicer();
				temp.setVisible(true);
			}
		});
		officer.setBounds(521, 315, 190, 113);
		contentPane.add(officer);
		
		JLabel intro = new JLabel("New label");
		intro.setFont(intro.getFont().deriveFont(intro.getFont().getStyle() | Font.BOLD));
		intro.setForeground(new Color(255, 255, 255));
		intro.setBounds(348, 11, 256, 21);
		contentPane.add(intro);
		
		intro.setText("WELCOME"+" "+name+" "+surname);
		officer.setFocusable(false);
		btnNewButton_1.setFocusable(false);
		btnNewButton.setFocusable(false);
		btnNewButton_2.setFocusable(false);
	}
}
