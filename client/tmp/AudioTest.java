import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;

public class AudioTest {
//must resource...
	public static void main(String[] args) {
		System.out.println("hello world");
		
		try {
		AudioFormat format = new AudioFormat(8000.0f, 16 ,1, true, true);
		TargetDataLine microphone = AudioSystem.getTargetDataLine(format);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
