import com.github.sarxos.webcam.Webcam;


public class VideoHandler {
	
	public VideoHandler()
	{
		Webcam webcam = Webcam.getDefault();
		if (webcam != null) {
			System.out.println("Webcam: " + webcam.getName());
		} else {
			System.out.println("No webcam detected");
		}
	}

	
}