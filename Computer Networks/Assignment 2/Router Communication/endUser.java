import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class endUser {

	public static void main(String[] args) {
		receive();
	}
	
	//need to do an ACK to indicate received packet
	//move to router 1
	public static void receive() {
		final int DEFAULT_PORT = 51510;		//end user is at a default port of 51510
		final int MTU = 1500;				//maximum package size, minumum size being MTU=1280

		DatagramPacket packet;	
		DatagramSocket socket;

		ObjectInputStream ostream;
		ByteArrayInputStream bstream;
		byte[] buffer;


		try {
			System.out.println("E2 is trying to receive a packet...");
			buffer= new byte[MTU];
			packet= new DatagramPacket(buffer, buffer.length);
			socket = new DatagramSocket(DEFAULT_PORT);
			socket.receive(packet);
			System.out.println("E2 has received a packet.");

			String newData = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
