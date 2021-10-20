import java.awt.FlowLayout;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
		
		Global.chatVarInit(); //vector variables initialize
		
		TCPHandler server = new TCPHandler();
		
		//open server, start listening to port listed in Global.java
		if(server.OpenServer()!=0)
		{
			System.out.println("Server Open Failed.");
			System.exit(0);
		}
		
		//count connections
		long conn_count = 0;
		
		while(true)	//main loop for handling requests.
		{
			System.out.println("Waiting...");
			Socket s = server.AcceptClient();
			SocketThread st = new SocketThread(server,s);
			st.start();
			System.out.println("New Connection-"+conn_count+" ;");
			conn_count++;
		}
			
	}

}
