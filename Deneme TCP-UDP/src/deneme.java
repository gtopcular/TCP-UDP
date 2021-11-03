import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class deneme extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deneme frame = new deneme();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public deneme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTcp = new JButton("TCP");
		btnTcp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client client = new Client();
				client.main(null);
			}
		});
		btnTcp.setBounds(33, 209, 117, 25);
		contentPane.add(btnTcp);
		
		JButton btnUdp = new JButton("UDP");
		btnUdp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClientUDP client = new ClientUDP();
						try {
							client.main(null);
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			}
		});
		btnUdp.setBounds(272, 209, 117, 25);
		contentPane.add(btnUdp);
		
		JButton btnServer = new JButton("Server");
		btnServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Server server = new Server();
				server.main(null);
			}
		});
		btnServer.setBounds(157, 120, 117, 25);
		contentPane.add(btnServer);
	}

}
