import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

//This will be the router class, instead of having multiple different classes for routers
//we will have one router class which refers to router 1-8.

public class router {

	public static void main(String[] args) {
		receivePacket();
	}

	public static void receivePacket() {
		final int DEFAULT_PORT = 51510;			//end user @ port 51510
		final int MTU = 1500;
		DatagramPacket packet;	
		DatagramSocket socket;
		InetSocketAddress address;

		ObjectInputStream ostream;
		ByteArrayInputStream bstream;
		byte[] buffer;


		try {
			Scanner routerScanner = new Scanner(System.in);
			System.out.println("Router is waiting for a packet...");

			
			buffer= new byte[MTU];
			packet= new DatagramPacket(buffer, buffer.length);
			socket = new DatagramSocket(DEFAULT_PORT);
			socket.receive(packet);
			System.out.println("Router has received a packet.");
			
			//send back an acknowledgement here from the port packet was sent from.

			String newData = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
			
			System.out.println("Which router would you like to forward this packet onto?");
			String router = routerScanner.next();
			if (router == "E2") {
				System.out.println("Forwarding this packet onto E2");
			} else {
				System.out.println("Forwarding this packet onto" + router);
			}
			
			address = new InetSocketAddress(router, DEFAULT_PORT);
			packet.setSocketAddress(address);
			socket.send(packet);
			System.out.println("Packet sent");

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
