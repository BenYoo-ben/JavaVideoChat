
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
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
		System.out.println("Connection check, op="+op);
		switch(op)
		{
		case Global.OP.REQUEST_CODE :
			System.out.println("request : "+input[1]);
			int idx = -1,tmp=0;
			Iterator<String> i = Global.chatCodeList.iterator();
			
			while(i.hasNext())
			{
				String s = i.next();
				
				if(s.equals(input[1]))
				{
					//check if chat room exists
					idx=tmp;
					break;
				}
				tmp++;
			}
			
			if(idx!=-1) //somebody already made chat room, so join inside.
			{
				//increment # participants in the room, and add client socket.
				Global.chatParticipantCount.set(idx,Global.chatParticipantCount.get(idx)+1 ); 
				Global.chatSocket.get(idx).add(this.sock);
				
				//when # of clients reach room's maximum capacity (now it's 2)
				if(Global.chatParticipantCount.get(idx) == Global.CurrentMaximumCapacity)
				{
					//create bridge(now 1 to 1 only)
					BridgeThread th1 = new BridgeThread(idx,0,this.th,Global.chatSocket.elementAt(idx).get(0),Global.chatSocket.elementAt(idx).get(1));
					BridgeThread th2 = new BridgeThread(idx,1,this.th,Global.chatSocket.elementAt(idx).get(1),Global.chatSocket.elementAt(idx).get(0));
					th1.start();
					th2.start();
				}
			}
			else //no room exists with code, so make new room for it.
			{
				Global.chatCodeList.add(input[1]);
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