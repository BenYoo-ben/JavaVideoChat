import java.awt.FlowLayout;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Global.chatVarInit();
		
		TCPHandler server = new TCPHandler();
		
		if(server.OpenServer()!=0)
		{
			System.out.println("Server Open Failed.");
			System.exit(0);
		}
		
		long conn_count = 0;
		
		while(true)
		{
			Socket s = server.AcceptClient();
			SocketThread st = new SocketThread(server,s);
			st.start();
			System.out.println("New Connection-"+conn_count+" is UP!");
			
		}
			
	}

}
