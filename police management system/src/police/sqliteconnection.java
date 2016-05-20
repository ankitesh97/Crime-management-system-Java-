package police;

import java.sql.*;
import javax.swing.*;
public class sqliteconnection {

	Connection conn = null;
	public static Connection dbconnector(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
			
			return conn;
			}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			System.exit(0);
			return null;
		}
		
	}
}
