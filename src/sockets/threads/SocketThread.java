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
	private boolean end;

	public SocketThread(Socket sfd, int id) {
		this.sfd=sfd;
		this.id=id;
		
	}
	
	public void run(){
		
		try {
			System.out.println("Hilo contestando llamada... "+this.id);
			BufferedReader bf=new BufferedReader(new InputStreamReader(sfd.getInputStream()));
		
		
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(sfd.getOutputStream()));
			//pw.println("El hilo " + this.id + " te da la bienvenida");
			//pw.flush();
			
			while(!end){
				String entrada="";
				//System.out.println(entrada);
				
				while(bf.ready()){
					
					entrada=bf.readLine();
					System.out.println(entrada);
				}
				
				String html="<html><body>Ya deja de conectarte <a href='http://google.com'>Javier</a>!!</body></html>";
				
				pw.println("HTTP/1.0 200 OK");
				pw.println("Date: Fri, 31 Dec 1999 23:59:59 GMT");
				pw.println("Content-Type: text/html");
				pw.println("Content-Length: "+ html.length() +"");
				pw.println("\n");
				pw.println(html);
				
				if(entrada.lastIndexOf("finalizar")>-1){
					this.end=true;
				}
				
				pw.flush();
			}
			System.out.println("Adios...");
			
			pw.close();
			bf.close();
			sfd.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
