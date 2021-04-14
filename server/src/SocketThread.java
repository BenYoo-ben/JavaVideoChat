
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
public class SocketThread extends Thread{
	
	TCPHandler th;
	Socket sock;
	
	SocketThread(TCPHandler th, Socket s)
	{
		this.th = th;
		this.sock =s;
	}
	
	public void run()
	{
		th.Send(sock, new String("0").getBytes());
		
		String[] input = parseRecv(th.Receive(sock));
		
		int op = Integer.parseInt(input[0]);
		
		switch(op)
		{
		case Global.OP.REQUEST_CODE : 
			int idx = Global.chatCodeList.indexOf(input[2]);
			if(idx!=-1)
			{
				Global.chatParticipantCount.set(idx,Global.chatParticipantCount.get(idx)+1 );
				Global.chatSocket.elementAt(idx).add(this.sock);
				
				if(Global.chatParticipantCount.elementAt(idx) == Global.CurrentMaximumCapacity)
				{
					BridgeThread th1 = new BridgeThread(idx,0,this.th,Global.chatSocket.elementAt(idx).get(0),Global.chatSocket.elementAt(idx).get(1));
					BridgeThread th2 = new BridgeThread(idx,1,this.th,Global.chatSocket.elementAt(idx).get(1),Global.chatSocket.elementAt(idx).get(0));
					th1.start();
					th2.start();
				}
			}
			else
			{
				Global.chatCodeList.add(input[0]);
				Global.chatParticipantCount.add(1);
				
				List<Socket> list = new ArrayList<Socket>();
				list.add(this.sock);
				Global.chatSocket.add(list);
			}
			
			break;
		
		}
	}
	
	protected String[] parseRecv(byte[] input) {
		if (input.length <= 0)
			return null;

		//parse request data(in [OP_TYPE]#[Data])
		String[] parsed = new String[2];

		String whole = new String(input);

		parsed = whole.split("[#]");

		return parsed;

	}

}