package sockets.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketThread extends Thread{

	private Socket sfd;
	private int id;

	public SocketThread(Socket sfd, int id) {
		this.sfd=sfd;
		this.id=id;
		
	}
	
	public void run(){
		
		try {
			System.out.println("Hilo contestando llamada... "+this.id);
			BufferedReader bf=new BufferedReader(new InputStreamReader(sfd.getInputStream()));
		
		
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(sfd.getOutputStream()));
			pw.println("El hilo " + this.id + " te da la bienvenida");
			pw.flush();
			System.out.println(bf.readLine());
		
		
			
			pw.println("Dato recibido...");
			
			pw.flush();
			pw.close();
			bf.close();
			sfd.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
