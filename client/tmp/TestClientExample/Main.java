import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.ByteArrayInputStream;
import java.io.BufferedOutputStream;;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TCPHandler tcp_handler = new TCPHandler();

		System.out.println("Connection to server...");
		tcp_handler.MakeConnection();
		
		byte[] t = tcp_handler.Receive();
		String str = new String(t);
		if (str.equals("0"))
			System.out.println("Connection Established");
		
		Scanner sc = new Scanner(System.in);

		String s = sc.nextLine();
		tcp_handler.Send(s.getBytes());

		// send file example
		
		try {
			byte[] array = Files.readAllBytes(Paths.get("./iu.jpg"));
			tcp_handler.Send(array);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
