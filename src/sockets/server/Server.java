package sockets.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import sockets.threads.SocketThread;

public class Server {

	public static void main(String args[]){
		try {
			int port=60000;
			ServerSocket server=new ServerSocket(port);
			
			
			for(int i=0;i<3;i++){
				Socket sfd=server.accept();
				
				System.out.println("Creando hilo " + i);
				SocketThread thread=new SocketThread(sfd,i);
				
				thread.start();
				
				//comment
			}
				
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
