import java.net.*;
import java.io.*;

public class TCPserver
{
	public static void main (String args[]) throws IOException
	{
		// CONNECTION
		ServerSocket serverSocket = new ServerSocket(2000);
		Socket socket = serverSocket.accept();
		System.out.println("Client Connected");
		
		//READ
		InputStreamReader isr = new InputStreamReader(socket.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		
		String str = br.readLine();
		System.out.println("Client : "+ str);
		
		//RESPOND
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.println("hello from server");
		pw.flush();
	}
}