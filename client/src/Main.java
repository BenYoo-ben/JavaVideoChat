import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/******************************
		 * 
		 * CONNECTION EXAMPLE
		 *
		 * TCPHandler tcp_handler = new TCPHandler(); System.out.println("Start
		 * Connection...");
		 * 
		 * int conn_state=-1;
		 * 
		 * if( (conn_state=tcp_handler.MakeConnection())!=0)
		 * System.out.println("Connection Fail.");
		 * 
		 * System.out.println("Conn status = "+conn_state); String str = "Hello Server";
		 * byte[] testData = str.getBytes();
		 * 
		 * 
		 * System.out.println("Sending data to server..."); conn_state =
		 * tcp_handler.Send(testData); System.out.println("Send status = "+conn_state);
		 * 
		 * byte[] recvBuff = new byte[8192];
		 * 
		 * recvBuff = tcp_handler.Receive();
		 * 
		 * if(recvBuff.equals(null)) System.out.println("Recv Failure...");
		 * 
		 * String recvStr = new String(recvBuff);
		 * 
		 * System.out.println("From Server :\n"+recvStr);
		 * 
		 * 
		 * System.out.println("Finished");
		 * 
		 */
	
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());

		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(true);
		panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(true);

		JFrame window = new JFrame("Test webcam panel");
		window.add(panel);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

}
