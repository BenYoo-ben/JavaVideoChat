import java.nio.file.Files;
import java.nio.file.Paths;

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
		try {
			byte[] tmp = Files.readAllBytes(Paths.get("022.jpg"));
			jp.getImage(tmp);
			jp.repaint();
			}catch(Exception e) {
					System.out.println("exc");
			}
		String[] input;
		while(true)
		{
			input = parseRecv(th.Receive());
			
			switch(Integer.parseInt(input[0]))
			{
			case Global.OP.VIDEO_DATA:
				//jp.getImage(input[1].getBytes());
				try {
				byte[] tmp = Files.readAllBytes(Paths.get("022.jpg"));
				jp.getImage(tmp);
				jp.repaint();
				}catch(Exception e) {
						System.out.println("exc");
				}
				
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