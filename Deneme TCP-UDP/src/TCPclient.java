import java.net.*;
import java.io.*;

public class TCPclient
{
	public static void main(String args[]) throws IOException
	{
		Socket socket = new Socket("localhost",2000);
		
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.println("Hello from client");
		pw.flush();
		
		InputStreamReader isr = new InputStreamReader(socket.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		
		String str = br.readLine();
		System.out.println("server : " + str);
	}
}