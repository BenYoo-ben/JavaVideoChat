import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Font;

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
		if (str.length()>0) {
			Global.chat_queue.offer(str);
			this.ta.setText(ta.getText() + "\nME: " + str);
			this.tf.setText("");
		}
	}
	

	public void TextRecv(String str) {
		this.ta.setText(ta.getText() + "\nAnonymous: " + str);
	}

	public JButton getSendButton() {
		return this.sendButton;
	}

	public void setUI(VideoHandler vh) {
		frame = new JFrame("JavaVideoChat");
		frame.setSize(Global.FrameW, Global.FrameH);

		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		RightPanel = new JPanel(new BorderLayout());

		RightUnderPanel = new JPanel(new BorderLayout());

		// Drawable JPanel(Overrode grahpics)
		LeftPanel = new DrawingBoard();

		frame.setLayout(new GridLayout(1, 2));
		frame.add(LeftPanel);
		frame.add(RightPanel);

		vh.initCam();
		vh.setCam();

		JFrame window = new JFrame("SelfView Frame");
		window.add(vh.getPanel());
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);

		// start media send/receive thread
		ms = new MediaSender(vh, this.th);
		mr = new MediaReceiver(this.LeftPanel, this.th, this);

		tf = new JTextField();
		tf.addKeyListener(this.eh);
		
		
		
		ta = new JTextArea();
		ta.setEditable(false);
		ta.setFont(new Font("Arial",Font.PLAIN,13));
		DefaultCaret caret = (DefaultCaret)ta.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JScrollPane scrollpane = new JScrollPane(ta);
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(this.eh);

		RightPanel.add(scrollpane, "Center");
		RightPanel.add(RightUnderPanel, "South");

		RightUnderPanel.add(tf, "Center");
		RightUnderPanel.add(sendButton, "East");

		ms.start();
		mr.start();
		
		frame.revalidate();
		frame.repaint();
		frame.setVisible(true);
		
	}

	public JFrame getFrame() {
		return this.frame;
	}

}
