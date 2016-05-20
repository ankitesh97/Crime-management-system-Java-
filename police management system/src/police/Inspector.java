package police;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollBar;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


public class Inspector extends JFrame {

	private JPanel contentPane;
	
	static String name = null ;
	static String surname = null;
	/**
	 * Create the frame.
	 */
	public Inspector() {
		setForeground(new Color(128, 0, 0));
		setTitle("INSPECTOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 571);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(128, 0, 0));
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		JButton Leave = new JButton("REQUEST LEAVE\r\n");
		Leave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Leave.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Leave.setBackground(new Color(240,240,240));
				
			}
		});
		Image img3 = new ImageIcon (this.getClass().getResource("/leave.png")).getImage();
		Leave.setIcon(new ImageIcon(img3));
		Leave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "LEAVE LETTER HAS BEEN SENT TO COMMISSONER");
			}
		});
		Leave.setBounds(527, 108, 190, 121);
		contentPane.add(Leave);
		
		JButton Criminal = new JButton("ADD CRIMINAL");
		Image img1 = new ImageIcon (this.getClass().getResource("/criminal.png")).getImage();
		Criminal.setIcon(new ImageIcon(img1));
		Criminal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Criminal.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Criminal.setBackground(new Color(240,240,240));
				
			}
		});
		Criminal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Criminal temp = new Criminal();
				temp.setVisible(true);
				
			}
		});
		Criminal.setBounds(135, 332, 190, 121);
		contentPane.add(Criminal);
		
		JButton addcase = new JButton("ADD CASE");
		Image img2 = new ImageIcon (this.getClass().getResource("/case.png")).getImage();
		addcase.setIcon(new ImageIcon(img2));
		addcase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addcase.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addcase.setBackground(new Color(240,240,240));
				
			}
		});
		addcase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Addcase temp = new Addcase();
				temp.setVisible(true);
			}
		});
		addcase.setBounds(527, 332, 190, 121);
		contentPane.add(addcase);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuBar.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				menuBar.setBackground(new Color(240,240,240));
				
			}
		});
		menuBar.setBounds(0, 0, 63, 23);
		
		menuBar.setBackground(new Color(240, 240, 240));
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
		
		JLabel intro_name = new JLabel("dwadaw");
		intro_name.setForeground(new Color(255, 255, 255));
		intro_name.setFont(intro_name.getFont().deriveFont(intro_name.getFont().getStyle() | Font.BOLD));
		intro_name.setText("WELCOME"+" "+name+" "+surname+"!!");
		intro_name.setBounds(309, 11, 262, 23);
		contentPane.add(intro_name);
		
		JButton Fir = new JButton("ADD FIR");
		Fir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Fir.setBackground(new Color(250,235,46));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Fir.setBackground(new Color(240,240,240));
				
			}
		});
		Fir.setFocusable(false);
		addcase.setFocusable(false);
		Criminal.setFocusable(false);
		Leave.setFocusable(false);
		
		Image img = new ImageIcon (this.getClass().getResource("/fir3.png")).getImage();
		Fir.setIcon(new ImageIcon(img));
		Fir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Fir temp = new Fir();
				temp.setVisible(true);
			}
		});
		Fir.setBounds(135, 108, 190, 121);
		contentPane.add(Fir);
	}
}

