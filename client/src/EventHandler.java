import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

class EventHandler implements ActionListener, KeyListener {

	ChatUI chui = null;
	BeginUI beginui = null;
	TCPHandler th = null;
	
	public EventHandler(BeginUI bui) {
		this.beginui = bui;
		this.th = bui.th;
	}
	
	public EventHandler(ChatUI chui) {
		this.chui = chui;
	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		Object o = e.getSource();
		if(this.chui!= null && o.equals(chui.getSendButton())) {
			chui.TextOffer();
		}
		else if (this.beginui!= null && o.equals(beginui.jb)) {	// when connect button is clicked;
			//get server ip address from text field
			if(beginui.tf2.getText().length()>0)
					Global.server_ip = (beginui.tf2.getText());
			
			System.out.println("Connecting...");
			th.MakeConnection();
			th.Receive();
			System.out.println("Connected!");
			th.Send(new String(Global.OP.REQUEST_CODE + "#" + beginui.tf.getText()).getBytes());

			beginui.tf.setText("");

			String s = new String(th.Receive());
			System.out.println("Join Room!");
			System.out.println("RECVD : " + s);
			if (Integer.parseInt(s) != Global.OP.REPLAY_READY) {
				System.out.println("ERROR!!");
				System.exit(1);
			}
			
			System.out.println("Begin Session!");
			// move on to videochat screen.
			beginui.frame.setVisible(false);
			VideoHandler vh = new VideoHandler();
			ChatUI cui = new ChatUI(th, vh);
		}
		
		

	};

	public void keyPressed(java.awt.event.KeyEvent e) {

	};

	public void keyReleased(java.awt.event.KeyEvent e) {
	};

	public void keyTyped(java.awt.event.KeyEvent e) {
		if(e.getKeyChar() == '\n') {
			chui.TextOffer();
		}
	};

}
