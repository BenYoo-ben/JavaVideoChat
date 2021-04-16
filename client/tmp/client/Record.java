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

public class Record {
	
	TargetDataLine targetDataLine;
	Thread stopper;

	public byte[] record(AudioFormat format) throws LineUnavailableException {
		DataLine.Info info = new DataLine.Info(TargetDataLine.class , format);
		targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
		
		if(! AudioSystem.isLineSupported(info)) {
			System.out.println("Line is not supported");
		}
		
		targetDataLine.open();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int numBytesRead;
		int CHUNK_SIZE = 1024;
		byte[] data = new byte[targetDataLine.getBufferSize() / 5];
		
		System.out.println("starting Recording");
		
		targetDataLine.start();
		
		stopper =  new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
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
			while(bytesRead < 1000000) { //1024식 증가 해서 끝나면 음성이나옴
				numBytesRead = targetDataLine.read(data, 0, CHUNK_SIZE);
				bytesRead = bytesRead + numBytesRead;
				System.out.println(bytesRead);
				out.write(data, 0, numBytesRead);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		byte audioData[] = out.toByteArray();
		return audioData;
	}
}