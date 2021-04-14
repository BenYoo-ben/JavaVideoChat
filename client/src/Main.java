import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/******************************
		 * 
		 * CONNECTION EXAMPLE
		 *
		 * TCPHandler tcp_handler = new TCPHandler(); System.out.println("Start
		 * Connection...");
		 * 
		 * int conn_state=-1;
		 * 
		 * if( (conn_state=tcp_handler.MakeConnection())!=0)
		 * System.out.println("Connection Fail.");
		 * 
		 * System.out.println("Conn status = "+conn_state); String str = "Hello Server";
		 * byte[] testData = str.getBytes();
		 * 
		 * System.out.println("Sending data to server..."); conn_state =
		 * tcp_handler.Send(testData); System.out.println("Send status = "+conn_state);
		 * 
		 * byte[] recvBuff = new byte[8192];
		 * 
		 * recvBuff = tcp_handler.Receive();
		 * 
		 * if(recvBuff.equals(null)) System.out.println("Recv Failure...");
		 * 
		 * String recvStr = new String(recvBuff);
		 * 
		 * System.out.println("From Server :\n"+recvStr);
		 * 
		 * 
		 * System.out.println("Finished");
		 * 
		 */
		VideoHandler vh = new VideoHandler();
		ChatUI ui = new ChatUI(vh);
		
		TCPHandler tcp_h = new TCPHandler();
		tcp_h.MakeConnection();
		long send_count = 0;
		tcp_h.Receive();
		while(true)
		{
			byte[] data_to_send = vh.CaptureToBytes();
			tcp_h.Send(data_to_send);
			tcp_h.Receive();
			System.out.println(send_count++);
			
		}
		
	
	}

}
