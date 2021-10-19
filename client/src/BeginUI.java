import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

class BeginUI {
	EventHandler eh;

	protected JFrame frame;
	protected JButton jb;
	protected JTextField tf,tf2;
	protected JLabel jl,jl2;
	TCPHandler th;

	BeginUI() {
		th = new TCPHandler();
		setUI();
		eh = new EventHandler(this);
		Handle();
	}

	public void setUI() {

		// set ui

		int w = Global.FrameW, h = Global.FrameH;
		int tf_w = 500, tf_h = 60;

		frame = new JFrame("Welcome.");
		frame.setSize(1280, 720);

		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);

		frame.setLayout(null);

		tf = new JTextField(10);
		tf.setFont(new Font("Arial", Font.BOLD, 14));
		tf.setBounds(w / 2 - tf_w / 2, tf_h * 2 , tf_w, tf_h);
		frame.add(tf);

		jl = new JLabel("Input Room Code");
		jl.setFont(new Font("Arial", Font.PLAIN, 14));
		jl.setBounds(w / 2 - tf_w / 2, tf_h * 3, tf_w, tf_h);
		frame.add(jl);
		
		tf2 = new JTextField(10);
		tf2.setFont(new Font("Arial", Font.BOLD, 14));
		tf2.setBounds(w / 2 - tf_w / 2,tf_h * 5 , tf_w, tf_h);
		frame.add(tf2);

		jl2 = new JLabel("Input Sever Address(example: 192.168.0.1)");
		jl2.setFont(new Font("Arial", Font.PLAIN, 14));
		jl2.setBounds(w / 2 - tf_w / 2, tf_h * 6, tf_w, tf_h);
		frame.add(jl2);

		jb = new JButton("Connect");
		jb.setFont(new Font("Arial", Font.BOLD, 14));
		jb.setBounds(w / 2 - tf_w / 2, tf_h * 8, tf_w, tf_h);
		frame.add(jb);

		frame.revalidate();
		frame.repaint();

	}

	public void Handle() {
		// event handling
		jb.addActionListener(eh);

	}

}
