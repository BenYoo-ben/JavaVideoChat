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
		
		if(o.equals(beginui.jb))
		{
			
			th.MakeConnection();
			
			th.Send(new String(Global.OP.REQUEST_CODE+"#"+beginui.tf.getText()).getBytes());
			beginui.tf.setText("");
			beginui.jl.setText("Connecting...");
			
			
			while(true)
			{
				String s = new String(th.Receive());
				if(Integer.parseInt(s)==Global.OP.REPLAY_READY)
					break;
			}
			
			beginui.frame.setVisible(false);
			VideoHandler vh = new VideoHandler();
			beginui.cui = new ChatUI(th,vh);
		}
		
		
	};
	public void keyPressed(java.awt.event.KeyEvent e) {};
	public void keyReleased(java.awt.event.KeyEvent e) {};
	public void keyTyped(java.awt.event.KeyEvent e) {};
	
}
