import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Component;

class ChatUI {
	private JFrame frame;

	private DrawingBoard LeftPanel;
	private JPanel RightPanel;
	private JPanel RightUnderPanel;
	private JButton sendButton;

	private JTextField tf;
	private JTextArea ta;

	MediaReceiver mr;
	MediaSender ms;
	TCPHandler th;
	EventHandler eh;
	
	public ChatUI(TCPHandler th, VideoHandler vh) {
		this.th = th;
		eh = new EventHandler(this);
		setUI(vh);
	}
	
	public void TextOffer() {
		String str = this.tf.getText();
		Global.chat_queue.offer(str);
		this.ta.setText(ta.getText()+"\nME: "+str);
		this.tf.setText("");
	}
	
	public void TextRecv(String str) {
		this.ta.setText(ta.getText()+"\nTalker: "+str);
	}
	
	public JButton getSendButton() {
		return this.sendButton;
	}

	public void setUI(VideoHandler vh) {
		frame = new JFrame("Test webcam panel");
		frame.setSize(Global.FrameW, Global.FrameH);

		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		frame.setVisible(true);

		RightPanel = new JPanel(new BorderLayout());

		RightUnderPanel = new JPanel(new BorderLayout());

		// Drawable JPanel(Overrode grahpics)
		LeftPanel = new DrawingBoard();

		frame.setLayout(new GridLayout(1, 2));
		frame.add(LeftPanel);
		frame.add(RightPanel);
		
		frame.addKeyListener(this.eh);

		vh.initCam();
		vh.setCam();

		JFrame window = new JFrame("Test webcam panel");
		window.add(vh.getPanel());
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);

		// start media send/receive thread
		ms = new MediaSender(vh, this.th);
		mr = new MediaReceiver(this.LeftPanel, this.th,this);

		tf = new JTextField();
		ta = new JTextArea();
		ta.setEditable(false);
		sendButton = new JButton("Send");
		sendButton.addActionListener(this.eh);

		RightPanel.add(ta, "Center");
		RightPanel.add(RightUnderPanel, "South");

		RightUnderPanel.add(tf, "Center");
		RightUnderPanel.add(sendButton, "East");
		
		ms.start();
		mr.start();
		
	}

	public JFrame getFrame() {
		return this.frame;
	}

}
