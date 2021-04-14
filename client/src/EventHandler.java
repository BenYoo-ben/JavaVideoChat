import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

class EventHandler implements ActionListener, KeyListener
{

	BeginUI beginui;
	TCPHandler th;
	
	public EventHandler(BeginUI bui) {
		beginui = bui;
		th = bui.th;
	}
	
	
	public void actionPerformed(java.awt.event.ActionEvent e) {
		Object o = e.getSource();
		
		//when connect button is cliecked;
		if(o.equals(beginui.jb))
		{
			beginui.jl.setText("Connecting...");
			th.MakeConnection();
			th.Receive();
			beginui.jl.setText("Connected!");
			th.Send(new String(Global.OP.REQUEST_CODE+"#"+beginui.tf.getText()).getBytes());
		
			beginui.tf.setText("");
			
			
			
			
				String s = new String(th.Receive());
				System.out.println("RECVD : "+s);
				if(Integer.parseInt(s)!=Global.OP.REPLAY_READY)
				{
					System.out.println("ERROR!!");
					System.exit(1);
				}
			
			//move on to videochat screen.
			beginui.frame.setVisible(false);
			VideoHandler vh = new VideoHandler();
			ChatUI cui = new ChatUI(th,vh);
		}
		
		
	};
	public void keyPressed(java.awt.event.KeyEvent e) {};
	public void keyReleased(java.awt.event.KeyEvent e) {};
	public void keyTyped(java.awt.event.KeyEvent e) {};
	
}
