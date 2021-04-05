package voice;

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
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


public class voiceRecord {
 // 데이터화 시키기 https://docs.oracle.com/javase/tutorial/sound/capturing.html
//https://stackoverflow.com/questions/4826169/record-voice-with-jav
//https://www.youtube.com/watch?v=GVtl19L9GxU	
	
	public static void main(String[] args) throws LineUnavailableException, InterruptedException, IOException, UnsupportedAudioFileException {
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
		SourceDataLine speakers;
	
		
		DataLine.Info info = new DataLine.Info(TargetDataLine.class , format);
		
		if(! AudioSystem.isLineSupported(info)) {
			System.out.println("Line is not supported");
		}
		
		final TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
		targetDataLine.open();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int numBytesRead;
		int CHUNK_SIZE = 1024;
		byte[] data = new byte[targetDataLine.getBufferSize() / 5];
		
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
		
		int bytesRead = 0;
		
		try {
			while(bytesRead < 100000) {
				numBytesRead = targetDataLine.read(data, 0, CHUNK_SIZE);
				bytesRead = bytesRead + numBytesRead;
				System.out.println(bytesRead);
				out.write(data, 0, numBytesRead);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		byte audioData[] = out.toByteArray();
		
		AudioInputStream audioInputStream;
		InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
		audioInputStream = new AudioInputStream(byteArrayInputStream,format,audioData.length / format.getFrameSize());
		
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
		speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		speakers.open();
		speakers.start();
		
		Thread starter = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int cnt = 0;
				byte tempBuffer[] = new byte[10000];
				try {
					while((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
						if(cnt > 0) {
							speakers.write(tempBuffer, 0, cnt);
						}
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		stopper.start();
		starter.start();
		
		Thread.sleep(50000);
		
		targetDataLine.stop();
		
		targetDataLine.close();
		speakers.drain();
		speakers.close();
		
		System.out.println("Ended Sound Test");
	
	}
		

}
