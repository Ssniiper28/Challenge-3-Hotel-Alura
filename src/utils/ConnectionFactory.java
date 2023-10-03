package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	
	
	public Connection getConnection(){
		
		try {
			Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost/hotelalura?useTimeZone=true&serverTimeZone=UTC",
			"root",
			"admin"
			);
		
			return con;
		} catch (Exception e){
			throw new RuntimeException(e);
		}
		
	}
}
