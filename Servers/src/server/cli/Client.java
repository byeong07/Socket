package server.cli;

import server.read.thread.ReadThread;

public class Client {
	
	private ClientInfo clientInfo;
	private ReadThread readThread;
	
	public Client(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
	
	public void startThread() {
		
		try {
		
			this.readThread = new ReadThread(clientInfo); 
			this.readThread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
