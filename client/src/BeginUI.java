import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

class BeginUI
{
	EventHandler eh;
	
	protected JFrame frame;
	protected JButton jb;
	protected JTextField tf;
	protected JLabel jl;
	TCPHandler th;
	
	BeginUI()
	{
		th = new TCPHandler();
		setUI();
		eh = new EventHandler(this);
		Handle();
	}
	
	public void setUI() {
		
		//set ui
		
		int w=Global.FrameW,h=Global.FrameH;	
		int tf_w=500,tf_h=100;
		
		frame = new JFrame("Welcome.");
		frame.setSize(1280, 720);

		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);

		frame.setLayout(null);

		tf = new JTextField(10);
		tf.setFont(new Font("Arial",Font.BOLD,14));
		tf.setBounds(w/2-tf_w/2, h/4-tf_h/2, tf_w, tf_h);
		frame.add(tf);
		
		
		jl = new JLabel("Input Room Code");
		jl.setFont(new Font("Arial",Font.PLAIN,14));
		jl.setBounds(w/2-tf_w/2, h/3-tf_h/2, tf_w, tf_h);
		frame.add(jl);
		
		jb = new JButton("Connect");
		jb.setFont(new Font("Arial",Font.BOLD,14));
		jb.setBounds(w/2-tf_w/2, h/2-tf_h/2, tf_w, tf_h);
		frame.add(jb);
		
		frame.revalidate();
		frame.repaint();
		
	}
	
	public void Handle()
	{
		//event handling
		jb.addActionListener(eh);
		
	}
	
	

}