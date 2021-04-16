import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;

public class Main {

	public static void main(String[] args) throws LineUnavailableException, InterruptedException {
		// TODO Auto-generated method stub
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
		
		TCPHandler tcp_handler  = new TCPHandler();
		
		
		System.out.println("Connection to server...");
		tcp_handler.MakeConnection();
		
		byte[] t = tcp_handler.Receive();
		String str = new String(t);
		if(str.equals("0")) {
			System.out.println("Connection Established");
		}
		
		Scanner sc = new Scanner(System.in);
		
		String s = sc.nextLine();
		tcp_handler.Send(s.getBytes());
		
		//send file example
		Record rc = new Record();
		byte [] audioData = rc.record(format);
		
		try {
			byte[] array = Files.readAllBytes(Paths.get("./iu.jpg"));
			tcp_handler.Send(array);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		play p = new play(audioData, format);
		
		rc.stopper.start();
		p.speakers.start();
		
		Thread.sleep(50000);
		
		rc.targetDataLine.stop();
		rc.targetDataLine.close();
		p.speakers.drain();
		p.speakers.close();
		
		System.out.println("Ended Sound Test");
	}

}
