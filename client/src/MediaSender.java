

class MediaSender extends Thread
{

	VideoHandler vh;
	TCPHandler th;
	int send_count = 0;
	MediaSender(VideoHandler vh, TCPHandler th)
	{
		this.vh =vh;
	}
	
	public void run()
	{
		byte[] header = new String(Global.OP.VIDEO_DATA+"#").getBytes();
		while(true)
		{
			
			byte[] data =  vh.CaptureToBytes();
			byte[] whole_data = new byte[header.length + data.length];
			System.arraycopy(header, 0, whole_data, 0, header.length); 
		    System.arraycopy(data, 0, whole_data, header.length, data.length); 
			System.out.println("Sending : "+new String(whole_data));
			th.Send(whole_data);
			System.out.println("Sent! "+ send_count);
			send_count++;
		}
	}

}