import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	public static void main(String[] args) {
		// TCP
		new Thread(new Runnable() {
			@Override
			public void run() {
				ExecutorService executor = null;
				try (ServerSocket server = new ServerSocket(1234)) {
					executor = Executors.newFixedThreadPool(5);
					System.out.println("Listening on TCP port 1234, Say hi!");
					while (true) {
						final Socket socket = server.accept();
						executor.execute(new Runnable() {
							@Override
							public void run() {
								String inputLine = "";
								System.err.println(
										socket.toString() + " ~> connected");
								try (PrintWriter out = new PrintWriter(
										socket.getOutputStream(), true);
										BufferedReader in = new BufferedReader(
												new InputStreamReader(socket
														.getInputStream()))) {
									while (!inputLine.equals("!quit")
											&& (inputLine = in
													.readLine()) != null) {
										System.out.println(socket.toString()
												+ ": " + inputLine);
										// Echo server...
										out.println(inputLine);
										writeTxt(inputLine);
									}
								} catch (Exception ioe) {
									ioe.printStackTrace();
								} finally {
									try {
										System.err.println(socket.toString()
												+ " ~> closing");
										socket.close();
									} catch (IOException ioe) {
										ioe.printStackTrace();
									}
								}
							}
						});
					}
				} catch (IOException ioe) {
					System.err.println("Cannot open the port on TCP");
					ioe.printStackTrace();
				} finally {
					System.out.println("Closing TCP server");
					if (executor != null) {
						executor.shutdown();
					}
				}
			}
		}).start();

		// UDP
		new Thread(new Runnable() {
			@Override
			public void run() {
				try (DatagramSocket socket = new DatagramSocket(9999)) {
					byte[] buf = new byte[socket.getReceiveBufferSize()];
					System.out.println("Listening on UDP port 9999, Say hi!");
					while (true) {
						DatagramPacket packet = new DatagramPacket(buf, buf.length);
						socket.receive(packet);
						String str = new String (packet.getData(),0,packet.getLength());
						System.out.println("UDPClient Say : "+ str);
						writeTxt(str);
					}
				} catch (Exception ioe) {
					System.err.println("Cannot open the port on UDP");
					ioe.printStackTrace();
				} finally {
					System.out.println("Closing UDP server");
				}
			}
		}).start();
	}
	
	public static synchronized void writeTxt (String str) throws Exception {
        File file = new File("dosya.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(str + "\n");
        bWriter.close();
	}
	
}
