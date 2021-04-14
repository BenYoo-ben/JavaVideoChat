
public class GlobalVar {
	
	
	static int server_port = 55551;
	static String server_ip = "192.168.0.5";
	
	
	static void perror(String err_msg)
	{
		System.out.println("========ERR=======\n"+err_msg+"========ERR=======\n");
		System.exit(1);
		
	}
}
