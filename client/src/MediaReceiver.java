import java.nio.file.Files;
import java.nio.file.Paths;

class MediaReceiver extends Thread {
	DrawingBoard jp;
	TCPHandler th;
	ChatUI chui;

	MediaReceiver(DrawingBoard jp, TCPHandler th, ChatUI chui) {
		this.jp = jp;
		this.th = th;
		this.chui = chui;
	}

	public void run() {

		String input;
		while (true) {
			input = new String(th.Receive());

			switch (Integer.parseInt(input)) {
			case Global.OP.VIDEO_DATA:

				try {

					jp.getImage(th.Receive());
					jp.repaint();
				} catch (Exception e) {
					System.out.println("exception in Code: "+Global.OP.VIDEO_DATA);
				}
				break;
			case Global.OP.CHAT_DATA:
				try{
					chui.TextRecv(new String(th.Receive()));
				}catch(Exception e) {
					System.out.println("exception in Code: "+Global.OP.CHAT_DATA);
				}
				break;
			}
		}

	}

}