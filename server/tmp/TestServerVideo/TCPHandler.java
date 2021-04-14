import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class TCPHandler {

	private ServerSocket s_socket;
	private short conn_state = 0;

	private int num_connections = 0;

	public TCPHandler() {

		// TODO Auto-generated constructor stub
	}

	protected int OpenServer() {
		try {
			s_socket = new ServerSocket(GlobalVar.server_port);
			conn_state = 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

		return 0;

	}

	protected int CloseServer() {
		try {
			s_socket.close();
			conn_state = 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		return 0;
	}

	protected Socket AcceptClient() {
		Socket c_socket;

		try {
			c_socket = s_socket.accept();
			num_connections++;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return c_socket;
	}

	protected int Send(Socket c_socket, byte[] buffer) {
		try {
		DataOutputStream dOut = new DataOutputStream(c_socket.getOutputStream());
		dOut.writeInt(buffer.length);
		dOut.write(buffer);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		return 0;
	}
	
	protected byte[] Receive(Socket c_socket)
	{
		byte[] msg = null;
		try {
			DataInputStream dIn = new DataInputStream(c_socket.getInputStream());
			int length = dIn.readInt();
			if(length > 0)
			{
				msg = new byte[length];
				dIn.readFully(msg,0,msg.length);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return msg;
		
	}

}
