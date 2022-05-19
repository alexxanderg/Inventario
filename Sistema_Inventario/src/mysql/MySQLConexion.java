package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MySQLConexion {
	public MySQLConexion(){}
	
	//private static String URL="jdbc:mysql://192.168.0.101:3306/db_inventario?useSSL=false";
	//private static String USER="root2";
	private static String URL="jdbc:mysql://localhost:3306/db_inventario?useSSL=false";
	private static String USER="root";
	private static String PASS="Aa123";
	
	/*private static String URL="jdbc:mysql://179.61.14.159:3306/bytexbyt_db_inventario?useSSL=false";
	private static String USER="bytexbyt_ghalex";
	private static String PASS="6H1tw1kSWK";
	
	com.mysql.jdbc.Driver
	com.mysql.cj.jdbc.Driver -------2021
	*/
	
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConection(){
		Connection con = null;
		try {
			con=DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}






