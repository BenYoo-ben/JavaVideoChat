import java.nio.file.Files;
import java.nio.file.Paths;

class MediaReceiver extends Thread {
	DrawingBoard jp;
	TCPHandler th;

	MediaReceiver(DrawingBoard jp, TCPHandler th) {
		this.jp = jp;
		this.th = th;
	}

	public void run() {
		try {
			byte[] tmp = Files.readAllBytes(Paths.get("022.jpg"));
			jp.getImage(tmp);
			jp.repaint();
		} catch (Exception e) {
			System.out.println("exc");
		}
		String input;
		while (true) {
			input = new String(th.Receive());

			switch (Integer.parseInt(input)) {
			case Global.OP.VIDEO_DATA:

				try {

					jp.getImage(th.Receive());
					jp.repaint();
				} catch (Exception e) {
					System.out.println("exc");
				}

				break;

			}
		}

	}

}