

class MediaSender extends Thread {

	VideoHandler vh;
	TCPHandler th;
	int send_count = 0;

	MediaSender(VideoHandler vh, TCPHandler th) {
		this.vh = vh;
		this.th = th;
	}

	public void run() {
		int media_send_rate = (int)(1000 / Global.frame_rate);
        byte[] video_header = new String(Global.OP.VIDEO_DATA + "").getBytes();
        byte[] chat_header = new String(Global.OP.CHAT_DATA+ "").getBytes();
        String str;
        while (true) {
			//System.out.println("Sending Header...");
			th.Send(video_header);
			//System.out.println("Sending Header Complete!");

			byte[] data = vh.CaptureToBytes();
			//System.out.println("Sending Data...");
			th.Send(data);
			//System.out.println("Sending Data Complete!");

			//System.out.println("Video Sent! " + send_count);
			send_count++;
			
	
			if( (str = Global.chat_queue.poll())!=null) {
				//chat handler;
				th.Send(chat_header);
				data = str.getBytes();
				th.Send(data);
				System.out.println("Chat Sent!");
			}
			
            try{
			    this.sleep(media_send_rate);
            }catch(Exception e){
                e.printStackTrace();
            }
		}
	}

}
