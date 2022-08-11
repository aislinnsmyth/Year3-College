import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
//author Aislinn Addison-Smyth 19337226
public class subscriber {


	// receive, not in use

	public static void main(String[] args) {

	}
	
	public static void receive() {
		final int RECV_PORT = 50000;		//49000 free port to use
		final int MTU = 1500;				//maximum package size

		DatagramPacket packet;				//initialize the packet being sent
		DatagramSocket socket;
		InetAddress address;
		int port;

		ObjectInputStream ostream;
		ByteArrayInputStream bstream;
		byte[] buffer;


		try {
			System.out.println("Subscriber has received");

			// extract destination from arguments
			address= InetAddress.getLocalHost(); // InetAddress.getByName(args[0]);
			port= RECV_PORT;                     // Integer.parseInt(args[1]);

			// create buffer for data, packet and socket
			buffer= new byte[MTU];
			packet= new DatagramPacket(buffer, buffer.length);
			socket= new DatagramSocket(port, address);

			// attempt to receive packet
			System.out.println("Trying to receive");
			socket.receive(packet);

			// extract data from packet
			buffer= packet.getData();
			bstream= new ByteArrayInputStream(buffer);
			ostream= new ObjectInputStream(bstream);

			// print data and end of program
			System.out.println("Data: " + ostream.readUTF());
			System.out.println("ReceiverProcess - Program end, Subscriber");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void send(String message) {
		final int DEST_PORT = 49000;		//free port to use

		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;

		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
			System.out.println("Publisher has message, message to send... " + message + ". Starting to send...");

			// extract destination from arguments
			address= InetAddress.getLocalHost();   // InetAddress.getByName(args[0]);
			port= DEST_PORT;                       // Integer.parseInt(args[1]);

			// convert string "Hello World" to byte array
			bstream= new ByteArrayOutputStream();
			ostream= new ObjectOutputStream(bstream);
			ostream.writeUTF(message);
			ostream.flush();
			buffer= bstream.toByteArray();

			// create packet addressed to destination
			packet= new DatagramPacket(buffer, buffer.length,
					address, port);

			// create socket and send packet
			socket= new DatagramSocket();
			socket.send(packet);
			System.out.println("Subscribing to broker");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}