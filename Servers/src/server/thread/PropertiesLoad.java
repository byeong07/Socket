package server.thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import server.read.thread.ReadThread;

public class PropertiesLoad {
	
	private static String defaultPropertie = "D:\\server\\Servers\\db_config.properties";
	Properties properties = null;
	FileInputStream fis = null;
	Connection conn = null;
	String url = null;
	String user = null;
	String password = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ReadThread read;
	
	public void LoadPropertie() {
		
		properties = new Properties();
		
		try {
			
			fis = new FileInputStream(defaultPropertie);
			properties.load(fis);
			
			String dbType = properties.getProperty("db_type", "postgresql");
			String dbAddress = properties.getProperty("db_ip", "127.0.0.1");
			String dbPort = properties.getProperty("db_port", "5432");
			String dbName = properties.getProperty("db_name", "postgres");
			
			url = "jdbc:" + dbType + "://" + dbAddress + ":" + dbPort + "/" + dbName;
			user = "postgres";
			password = "1234";
			
			conn = DriverManager.getConnection(url, user, password);
			
			System.out.println("DB connection");
			
			String query = "INSERT INTO CLIENT_STAT( CLIENT_ID, TIME, VALUE ) VALUES (" + 
			      read.client_id + ",'" + read.str + "'," + read.value + ")";
			
			stmt = conn.createStatement();
			
			stmt.executeUpdate(query);
			
			//pstmt = conn.prepareStatement(query);
			
			//System.out.println(pstmt);
			
			
			
			//pstmt.executeUpdate(query);
			
			
			/*
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			*/
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			
		}
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		PropertiesLoad load = new PropertiesLoad();
		load.LoadPropertie();
	}
	
	
}
