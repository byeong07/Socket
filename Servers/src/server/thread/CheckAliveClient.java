package server.thread;

import java.util.Iterator;

import server.cli.Client;

public class CheckAliveClient extends Thread {

	public CheckAliveClient() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		
		checkAliveClient();
		
		try { 
			Thread.sleep(1000); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
			
	private static void checkAliveClient() 
	{
		Iterator<Client> iter = TCPServer.mapClientThread.keySet().iterator();
		while(iter.hasNext())
		{
			Client cli = iter.next();
			if (cli.getClientInfo().isRunnableReadClient() == false)
			{
				cli.getClientInfo().closeSocket();
				TCPServer.mapClientThread.remove(cli);
				
				System.out.println("Client 연결 끊김, 연결된 클라이언트의 수: " + TCPServer.mapClientThread.size() );
			}
		}
		
		iter = null;
	}
	
}
