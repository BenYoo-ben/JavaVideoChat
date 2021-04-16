
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class Main_1 {
	
	public static void main(String[] args) throws LineUnavailableException, InterruptedException {
		// TODO Auto-generated method stub
		record_sound r = new record_sound();

		
		TCPHandler tcp_handler = new TCPHandler();
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
		int i=0;
		
		
		
		while(i != 10) {
			AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
			byte[] audioData = r.record_sound(format);
			play_sound p = new play_sound(format, audioData);
			p.starter.start();
			Thread.sleep(50000);
			p.speakers.drain();
			r.targetDataLine.stop();
			r.targetDataLine.close();
			p.speakers.close();
			i++;
		}
		
		
		System.out.println("Ended Sound Test");
		
	}

}
