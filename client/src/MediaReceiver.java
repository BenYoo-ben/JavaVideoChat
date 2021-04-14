class MediaReceiver extends Thread
{
	DrawingBoard jp;
	TCPHandler th;
	
	MediaReceiver(DrawingBoard jp, TCPHandler th)
	{
		this.jp = jp;
		this.th = th;
	}
	
	public void run()
	{
		String[] input;
		while(true)
		{
			input = parseRecv(th.Receive());
			
			switch(Integer.parseInt(input[0]))
			{
			case Global.OP.VIDEO_DATA:
				jp.getImage(input[1].getBytes());
				jp.repaint();
				break;
			
			}
		}
		
		
	}
	
	protected String[] parseRecv(byte[] input) {
		if (input.length <= 0)
			return null;

		//parse request data(in [OP_TYPE]#[Data])
		String[] parsed = new String[2];

		String whole = new String(input);

		parsed = whole.split("[#]");

		return parsed;

	}
	
}