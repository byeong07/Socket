package server.cli;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientInfo {
	
	public Socket socket;
	public DataOutputStream dos;
	public DataInputStream dis;
	
	private boolean isRunnableReadClient = false;
	private boolean isRunnableSendClient = false;
	
	public ClientInfo(Socket socket) {
		
		this.socket = socket;
		
		try {
			dos = new DataOutputStream(this.socket.getOutputStream());
			dis = new DataInputStream(this.socket.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean isRunnableReadClient() {
		return isRunnableReadClient;
	}

	public void setRunnableReadClient(boolean isRunnableReadClient) {
		this.isRunnableReadClient = isRunnableReadClient;
	}

	public boolean isRunnableSendClient() {
		return isRunnableSendClient;
	}
	
	public void setRunnableSendClient(boolean isRunnableSendClient) {
		this.isRunnableSendClient = isRunnableSendClient;
	}
	
	public void closeSocket() {
		
		if(dis != null) {
			try {
				dis.close();
				dis = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(dos != null) {
			try {
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
		
	
}

