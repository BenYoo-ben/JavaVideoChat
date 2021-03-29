package voice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class voiceRecord {

	public static void main(String[] args) throws LineUnavailableException, InterruptedException {
		AudioFormat format = new AudioFormat(16000, 8, 2, true, true);
		
		DataLine.Info info = new DataLine.Info(TargetDataLine.class , format);
		
		if(! AudioSystem.isLineSupported(info)) {
			System.out.println("Line is not supported");
		}
		
		final TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
		
		targetDataLine.open();
		
		System.out.println("starting Recording");
		
		targetDataLine.start();
		
		Thread stopper =  new Thread(new Runnable() {
			
			@Override
			public void run() {
				AudioInputStream audioStream = new AudioInputStream(targetDataLine);
			File wavFile = new File("C://RecordingAudio.wav");
			
			try {
				AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, wavFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		});
		
		stopper.start();
		
		Thread.sleep(5000);
		
		targetDataLine.stop();
		
		targetDataLine.close();
		
		System.out.println("Ended Sound Test");
		
	
	}
		

}
