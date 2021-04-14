import java.awt.FlowLayout;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			TCPHandler tcp_handle = new TCPHandler();
			if(tcp_handle.OpenServer()!=0)
				GlobalVar.perror("Server open failure check port or IP");
			
			
			Socket sock = null;
			System.out.println("waiting for client...");
			sock = tcp_handle.AcceptClient();
			System.out.println("Client online!");
			
			JFrame jf = new JFrame();
			jf.setSize(800,800);
			jf.setVisible(true);
			jf.setLayout(new FlowLayout());;
			
			ImageIcon ic = null;
			JLabel jl =  new JLabel();
			
			jf.add(jl);
			
			
			if(sock!=null)
				tcp_handle.Receive(sock);
			else
				System.out.println("Conn Fail..");
			
			while(true)
			{
				System.out.println("Receiving ... ");
				byte[] recv_bytes = tcp_handle.Receive(sock);
				System.out.println("Received!");
				
				ic = new ImageIcon(recv_bytes);
				jl.setIcon(ic);

				jf.repaint();
				jf.pack();
				
				System.out.println("configure done!");
				tcp_handle.Send(sock,new String("0").getBytes());
			}
			
	}

}
