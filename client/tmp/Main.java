
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TCPHandler tcp_handler = new TCPHandler();
		System.out.println("Start Connection...");
		
		int conn_state=-9;
		
		if( (conn_state=tcp_handler.MakeConnection())!=0)
			System.out.println("Connection Fail.");
		
		System.out.println("Conn status = "+conn_state);
		String str = "1HelloServer";
		byte[] testData = str.getBytes();

		
		System.out.println("Sending data to server...");
		conn_state = tcp_handler.Send(testData);
		System.out.println("Send status = "+conn_state);
		
		byte[] recvBuff = new byte[8192];
		
		recvBuff = tcp_handler.Receive();
		
		if(recvBuff.equals(null))
			System.out.println("Recv Failure...");
		
		String recvStr = new String(recvBuff);
		
		System.out.println("From Server :\n"+recvStr);
		
		
		System.out.println("Finished");
	}

}
