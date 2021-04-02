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
			if(sock!=null)
				tcp_handle.Send(sock,new String("0").getBytes());
			
			
			byte[] recv_bytes = tcp_handle.Receive(sock);
			System.out.println(new String(recv_bytes));
			
			recv_bytes = tcp_handle.Receive(sock);
			
			JFrame jf = new JFrame();
			jf.setSize(500,500);
			jf.setVisible(true);
			jf.setLayout(new FlowLayout());;
			
			ImageIcon ic = new ImageIcon(recv_bytes);
			JLabel jl =  new JLabel();
			jl.setIcon(ic);
			
			jf.add(jl);
			jf.pack();
			jf.repaint();
	}

}
