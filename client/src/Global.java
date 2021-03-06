import java.util.LinkedList;
import java.util.Queue;

public class Global {

	static int server_port = 55551;
	//for debug purpose only;
	static String server_ip = "192.168.0.19";
    static int frame_rate = 60;
	static int FrameW = 1280, FrameH = 720;
	
	static Queue<String> chat_queue =  new LinkedList<>();
	
	
	// OPcode for data processing
	static class OP {
		static final int REQUEST_CODE = 10;
		static final int REPLAY_READY = 11;
		static final int VIDEO_DATA = 12;
		static final int CHAT_DATA = 13;
	}

	static void perror(String err_msg) {
		System.out.println("========ERR=======\n" + err_msg + "========ERR=======\n");
		System.exit(1);

	}
}
