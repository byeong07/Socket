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
				
				
				System.out.println("Ŭ���̾�Ʈ ���� ���(����� Ŭ���̾�Ʈ ��: " + mapClientThread.size() + ")");
				clientSocket = serverSocket.accept();
				
				System.out.println("connect");
				
				
			    Client clientThread = new Client(new ClientInfo(clientSocket));

				System.out.println("connects");
				// serverSocket.close();
				
				
				if(mapClientThread.containsKey(read.client_id)) {
			    	System.out.println("id connect ���� �ʽ��ϴ�.");
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
			    	System.out.println("connect ���� �ʽ��ϴ�.");
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
