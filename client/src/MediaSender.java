

class MediaSender extends Thread
{

	VideoHandler vh;
	TCPHandler th;
	int send_count = 0;
	MediaSender(VideoHandler vh, TCPHandler th)
	{
		this.vh =vh;
		this.th = th;
	}
	
	public void run()
	{
		byte[] header = new String(Global.OP.VIDEO_DATA+"#").getBytes();
		while(true)
		{
			
			System.out.println("Preparing Send");
			byte[] data =  vh.CaptureToBytes();
			byte[] whole_data = new byte[header.length + data.length];
			System.arraycopy(header, 0, whole_data, 0, header.length); 
		    System.arraycopy(data, 0, whole_data, header.length, data.length); 
			System.out.println("Sending : "+new String(whole_data));
			if(whole_data==null)
				System.out.println("Trying to send null?\n\n");
			th.Send(whole_data);
			System.out.println("Sent! "+ send_count);
			send_count++;
		}
	}

}