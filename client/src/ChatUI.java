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
	private JPanel LeftPanel;
	private JPanel RightPanel;
	private JPanel RightUnderPanel;
	private JButton sendButton;
	
	private JTextField tf;
	private JTextArea ta;

	public ChatUI() {
		setUI();

	}

	public void setUI(VideoHandler vh) {
		frame = new JFrame("Test webcam panel");
		frame.setSize(1280, 720);

		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		frame.setVisible(true);

		LeftPanel = new JPanel(new GridLayout(1, 1));
		RightPanel = new JPanel(new BorderLayout());
		
		RightUnderPanel = new JPanel(new BorderLayout());
		
		frame.setLayout(new GridLayout(1, 2));
		frame.add(LeftPanel);
		frame.add(RightPanel);

		vh.initCam();
		vh.setCam();
		LeftPanel.add(vh.getPanel());

		tf = new JTextField();
		ta = new JTextArea();
		sendButton = new JButton("Send!");
	
		RightPanel.add(ta,"Center");
		RightPanel.add(RightUnderPanel,"South");
		
		
		
		RightUnderPanel.add(tf,"Center");
		RightUnderPanel.add(sendButton,"East");

	}

	public JFrame getFrame() {
		return this.frame;
	}

}
