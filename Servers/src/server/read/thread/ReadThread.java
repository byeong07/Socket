package server.read.thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.TimeZone;

import server.cli.ClientInfo;
import server.thread.PropertiesLoad;
import server.thread.TCPServer;

public class ReadThread extends Thread {
	
	private ClientInfo clientInfo;
	int idx = 0;
	byte[] packet;
	static public String str;
	static public int client_id;
	TCPServer tcp;
	static public byte header;
	static public int value;
	PropertiesLoad propertie;
	private static String defaultPropertie = "D:\\server\\Servers\\db_config.properties";
	Properties properties = null;
	FileInputStream fis = null;
	Connection conn = null;
	String url = null;
	String user = null;
	String password = null;
	PreparedStatement pstmt = null;
	
	
	
	public ReadThread(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	public void TimeStampConvert() {
		
		try {
			
			long time = clientInfo.dis.readLong();
			
			Date date = new Date(time * 1000L);
			
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			dayTime.setTimeZone(TimeZone.getTimeZone("GMT+9"));
			
			str = dayTime.format(date);
			
			System.out.println(str);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void ReadPacket() {
	
		System.out.println("[ReadClient] Started.");
		this.clientInfo.setRunnableReadClient(true);
		
		try {
			
			while(clientInfo.socket.isConnected()) {
				
				header = clientInfo.dis.readByte();
				client_id = clientInfo.dis.readInt();
			 	TimeStampConvert();
			 	value = clientInfo.dis.readInt();
			 	//int cliend_ids = clientInfo.dis.readInt();
			 	
			 	if(tcp.mapClientThread.containsKey(client_id)) {
			 		
			 		System.out.println("connect 되지 않는다.");
			 		tcp.clientSocket.close();
			 		
			 		//System.out.println("header : "+ header+" / "+client_id+" / " + str +" / "+value);
			 		
			 	} else if(!tcp.mapClientThread.containsKey(client_id)) {

				 	System.out.println("client 수" + tcp.mapClientThread.mappingCount() + "header : "+ header+" / "+client_id+" / " + str +" / "+value);
			 	}
			 	
			 	
			 	
			 	
				/*
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
				String query = "INSERT INTO CLIENT_STAT ( CLIENT_ID, TIME, VALUE ) VALUES (" + client_id + ",'" + str + "','" + value + "')";
				
				conn.prepareStatement(arg0)
				
				
				
				pstmt.executeUpdate(query);
				
			 	*/
			 	
			 	
			 	
			 	
			 	propertie = new PropertiesLoad();
			 	propertie.LoadPropertie();
			 	
			}
			
		} catch(Exception e) {
			
			if(e.getMessage().equals("Connection reset")) {
				System.out.println("connection reset");
			}
			
		} finally { 
		
			this.clientInfo.setRunnableReadClient(false);
		}
		
	}
	
	@Override
	public void run() {
		ReadPacket();
		
	}
	
}
