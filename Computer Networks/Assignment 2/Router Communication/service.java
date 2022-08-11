import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class service {

	public static String newData;

	public static void main(String[] args) {
		packetDeterminant();
	}

	public static void packetDeterminant() {
		final int DEFAULT_PORT = 51510;
		final int MTU = 1500;

		DatagramPacket packet;	
		DatagramSocket socket;
		InetSocketAddress address;
		byte[] data;

		try {
			System.out.println("Forwarding Service is trying to receive a packet...");

			data= new byte[MTU];
			packet= new DatagramPacket(data, data.length);
			socket = new DatagramSocket(DEFAULT_PORT);
			socket.receive(packet);
			System.out.println("Forwarding Service has received a packet.");
			
			newData = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
			
			System.out.println("This packet is being forwarded onto " + controller.forwardingTable(newData));
			address = new InetSocketAddress(controller.forwardingTable(newData), DEFAULT_PORT);
			packet.setSocketAddress(address);
			socket.send(packet);
			System.out.println("Packet sent");

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
