package server.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import server.cli.Client;
import server.cli.ClientInfo;
import server.read.thread.ReadThread;


public class TCPServer {
	
	ServerSocket serverSocket = null;
	public static ConcurrentHashMap<Client, ClientInfo> mapClientThread = new ConcurrentHashMap<Client, ClientInfo>();
	ClientInfo clientInfo;
	ReadThread read;
	Client cli;
	public static Socket clientSocket;
	
	public void connect() {
		
		try {
			
			CheckAliveClient aliveclient = new CheckAliveClient();
			aliveclient.start();
			
			serverSocket = new ServerSocket(9990);
			
			while(true) {
				
				
				System.out.println("클라이언트 연결 대기(연결된 클라이언트 수: " + mapClientThread.size() + ")");
				clientSocket = serverSocket.accept();
				
				System.out.println("connect");
				
				
			    Client clientThread = new Client(new ClientInfo(clientSocket));

				System.out.println("connects");
				// serverSocket.close();
				
				
				if(mapClientThread.containsKey(read.client_id)) {
			    	System.out.println("id connect 되지 않습니다.");
			    	clientSocket.close();
			    	
			    	/*
			    	clientThread.startThread();
					mapClientThread.put(clientThread, clientThread.getClientInfo());
			    	*/
				} else if(!mapClientThread.containsValue(read.client_id)) {
					mapClientThread.put(clientThread, clientThread.getClientInfo());
				}
				
				
				// serverSocket.close();
				//clientThread.startThread();
				
				
				clientThread.startThread();
					
				
				/*
			    if(mapClientThread.containsKey(clientThread.)) {
			    	System.out.println("connect 되지 않습니다.");
			    	clientSocket.close();
			    	
			    	
			    	/*
			    	clientThread.startThread();
					mapClientThread.put(clientThread, clientThread.getClientInfo());
			    	*/
			    	
				//}
			    
			 
				
				
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		TCPServer server = new TCPServer();
		server.connect();
		
	}

}
