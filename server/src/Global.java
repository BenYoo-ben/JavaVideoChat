import java.util.Vector;
import java.util.List;
import java.net.Socket;

public class Global {
	
	static int server_port = 55551;

	static int CurrentMaximumCapacity = 2;
	
	static Vector<String> chatCodeList;
	static Vector<Integer> chatParticipantCount;
	static Vector<List<Socket>> chatSocket;
	static short chatCount;
	
	static class OP
	{
		static final int REQUEST_CODE = 10;
		static final int REPLAY_READY = 11;
		static final int VIDEO_DATA = 12;
		static final int CHAT_DATA = 13;
	}
	
	
	static void perror(String err_msg)
	{
		System.out.println("========ERR=======\n"+err_msg+"\n========ERR=======\n");
		System.exit(1);
		
	}
	
	static void chatVarInit()
	{
		chatCodeList = new Vector<String>();
		chatParticipantCount = new Vector<Integer>();
		chatSocket = new Vector<List<Socket>>();
		chatCount = 0;
	}
	

}
/*
 *  
 */
