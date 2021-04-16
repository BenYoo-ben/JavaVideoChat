public class GlobalVar {

	static int server_port = 54326;
	static String server_ip = "127.0.0.1";
	
	static void perror(String err_msg) {
		System.out.println("========ERR=======\n"+err_msg+"========ERR=======\n");
		System.exit(1);
	}
}
