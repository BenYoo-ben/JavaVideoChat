
public class Global {

	static int server_port = 55551;
	static String server_ip = "127.0.0.1";

	static int FrameW = 1280, FrameH = 720;

	// OPcode for
	static class OP {
		static final int REQUEST_CODE = 11;
		static final int REPLAY_READY = 24;
		static final int VIDEO_DATA = 33;
		static final int AUDIO_DATA = 55;
	}

	static void perror(String err_msg) {
		System.out.println("========ERR=======\n" + err_msg + "========ERR=======\n");
		System.exit(1);

	}
}
