import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientUDP {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getByName("localhost");
		byte[] buf;
		try(DatagramSocket socket = new DatagramSocket()) {
			Scanner scanner = new Scanner(System.in);
			String line = null;
			while (!"exit".equalsIgnoreCase(line)) {
			line = scanner.nextLine();
			buf = line.getBytes();
	        DatagramPacket packet 
	          = new DatagramPacket(buf, buf.length, address, 9999);
	        socket.send(packet);
	        packet = new DatagramPacket(buf, buf.length);
	        socket.receive(packet);
	        String received = new String(
	          packet.getData(), 0, packet.getLength());
		}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}