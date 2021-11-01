import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

class BeginUI {
	EventHandler eh;

	protected JFrame frame;
	protected JButton jb;
	protected JTextField tf,tf2,tf3;
	protected JLabel jl,jl2,jl3 ;
	TCPHandler th;

	BeginUI() {
		th = new TCPHandler();
		setUI();
		eh = new EventHandler(this);
		Handle();
	}

	public void setUI() {

		//set look and feel
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		int w = Global.FrameW, h = Global.FrameH;
		

		frame = new JFrame("Welcome.");
		frame.setSize(400, 700);

		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setLayout(new GridLayout(4,2));

		tf = new JTextField(10);
		tf.setFont(new Font("Arial", Font.BOLD, 14));

		jl = new JLabel("Input Room Code");
		jl.setFont(new Font("Arial", Font.BOLD, 14));
		jl.setHorizontalAlignment(JLabel.CENTER);
		
		tf2 = new JTextField(10);
		tf2.setFont(new Font("Arial", Font.BOLD, 14));

		jl2 = new JLabel("Input Sever Address(example: 192.168.0.1)");
		jl2.setFont(new Font("Arial", Font.BOLD, 14));
		jl2.setHorizontalAlignment(JLabel.CENTER);
		
		jb = new JButton("Connect");
		jb.setFont(new Font("Arial", Font.BOLD, 14));
		
		jl3 = new JLabel("FrameRate(ex: 30, 60)");
		jl3.setFont(new Font("Arial",Font.BOLD,14));
		jl3.setHorizontalAlignment(JLabel.CENTER);
		
		tf3 = new JTextField(10);
		tf3.setFont(new Font("Arial", Font.BOLD, 14));
		

		JPanel jp_for_button = new JPanel();
		jp_for_button.setLayout(new BorderLayout());
		jp_for_button.add(jb,"Center");
		
		frame.add(jl);
		frame.add(tf);
		frame.add(jl2);
		frame.add(tf2);
		frame.add(jl3);
		frame.add(tf3);
		frame.add(new JLabel());
		frame.add(jp_for_button);
		
	
		
		frame.revalidate();
		frame.repaint();
	
		frame.setVisible(true);
	}

	public void Handle() {
		// event handling
		jb.addActionListener(eh);

	}

}
