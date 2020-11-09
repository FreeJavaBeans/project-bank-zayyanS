package bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	
	
	
	//single instance of the connection utility
	private static ConnectionUtil singleton = new ConnectionUtil();//call private constructor once
	
	
	private Connection conn;
	
	
	//private constructor so no one else can call
	private ConnectionUtil() {
		super();
		
		String password = System.getenv("DB_PASSWORD");
		String username = System.getenv("DB_USERNAME");
		String url = System.getenv("DB_URL");
		try {
			this.conn = DriverManager.getConnection(url, username, password);
		}catch(SQLException e) {
			System.out.println("Failed to Connect to DB");
		
			System.out.println("Url: " + url);
			
		}
		
		
	}
	
	//public getter for the static reference
	public static ConnectionUtil getConnectionUtil() {
		return singleton;
	}
	
	
	
	public Connection getConnection() {
		return conn;
	}
	


}
