
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class play_sound {
	
	SourceDataLine speakers;
	Thread starter;
	
	public play_sound(AudioFormat format, byte[] audioData) throws LineUnavailableException {
		
		AudioInputStream audioInputStream;
		InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
		audioInputStream = new AudioInputStream(byteArrayInputStream,format,audioData.length / format.getFrameSize());
		
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
		speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		speakers.open();
		speakers.start();
		
		starter = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int cnt = 0;
				byte tempBuffer[] = new byte[1024];
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
		System.out.println("finish");
	}
	
	

}
