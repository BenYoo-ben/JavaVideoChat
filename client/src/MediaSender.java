
class MediaSender extends Thread {

	VideoHandler vh;
	TCPHandler th;
	int send_count = 0;

	MediaSender(VideoHandler vh, TCPHandler th) {
		this.vh = vh;
		this.th = th;
	}

	public void run() {
		byte[] header = new String(Global.OP.VIDEO_DATA + "").getBytes();
		while (true) {

			System.out.println("Sending Header...");
			th.Send(header);
			System.out.println("Sending Header Complete!");

			byte[] data = vh.CaptureToBytes();
			System.out.println("Sending Data...");
			th.Send(data);
			System.out.println("Sending Data Complete!");

			System.out.println("Sent! " + send_count);
			send_count++;
		}
	}

}