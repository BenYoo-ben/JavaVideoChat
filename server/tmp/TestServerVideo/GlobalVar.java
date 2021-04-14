
public class GlobalVar {
	
	static int server_port = 55551;

	static void perror(String err_msg)
	{
		System.out.println("========ERR=======\n"+err_msg+"\n========ERR=======\n");
		System.exit(1);
		
	}
}
