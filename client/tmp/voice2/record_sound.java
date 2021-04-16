
import java.io.ByteArrayOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class record_sound {
	
	TargetDataLine targetDataLine;
	
	public byte[] record_sound(AudioFormat format) throws LineUnavailableException {
		DataLine.Info info = new DataLine.Info(TargetDataLine.class , format);
		targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
		
		
		if(! AudioSystem.isLineSupported(info)) {
			System.out.println("Line is not supported");
		}
		
		targetDataLine.open();
		targetDataLine.start();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int numBytesRead=0;
		int CHUNK_SIZE = 1024;
		byte[] data = new byte[targetDataLine.getBufferSize() / 5];
		
		System.out.println("starting Recording");
		
		int bytesRead = 0;
		
		try {
			while(bytesRead < 500000) { //1024�� ���� �ؼ� ������ �����̳���
				numBytesRead = targetDataLine.read(data, 0, CHUNK_SIZE);
				bytesRead = bytesRead + numBytesRead;
				System.out.println(bytesRead);
				out.write(data, 0, numBytesRead);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//�������� �ʿ�
		byte audioData[] = out.toByteArray();
		return audioData;
	}
}
