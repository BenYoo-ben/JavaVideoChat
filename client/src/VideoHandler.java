import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;

import javax.swing.JPanel;

public class VideoHandler {

	private Webcam webcam;
	private WebcamPanel panel;

	// find def cam on computer.
	public int initCam() {
		webcam = Webcam.getDefault();
		if (webcam != null) {
			System.out.println("Webcam: " + webcam.getName());

		} else {
			System.out.println("No webcam detected");
			return 1;
		}
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		return 0;
	}

	// basic setting for cam.
	public void setCam() {

		panel = new WebcamPanel(webcam);

		webcam.setViewSize(WebcamResolution.VGA.getSize());

		panel.setFPSDisplayed(true);
		panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(true);

	}

	// get panel.
	public WebcamPanel getPanel() {
		return this.panel;
	}

	// capture img
	byte[] CaptureToBytes() {
		System.out.println("Capturing...");
		byte[] bytes = WebcamUtils.getImageBytes(webcam, "jpg");
		System.out.println("Capture Done!");
		return bytes;
	}
}
