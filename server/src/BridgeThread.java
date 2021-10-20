
import java.net.Socket;
import java.util.List;


public class BridgeThread extends Thread{
	
	TCPHandler th;
	Socket src;
	Socket dst;
	int id;
	int rev;
	
	BridgeThread(int id,int rev,TCPHandler th, Socket src,Socket dst)
	{
		this.id = id;
		this.rev = rev;
		this.th = th;
		this.src = src;
		this.dst =dst;
	}
	
	public void run()
	{
		
		th.Send(src,new String(Global.OP.REPLAY_READY+"").getBytes());
		
		while(true)
		{
			byte[] data = th.Receive(src);
			th.Send(dst,data);
			//System.out.println(id+"-"+rev+" > sent."); //for debug purpose.
		}
	}


}