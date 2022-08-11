import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
//author Aislinn Addison-Smyth 19337226
public class actuator {

	public static void main(String[] args) {

		AC();
		blinds();
		laptop();
		windows();
	}

	public static void AC() {
		final int DEST_PORT = 49000;		//sending to subscriber receive
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;
		String message = "Turn the air conditioning off";
		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {

			System.out.println("Subscribing: Broker " + DEST_PORT + ", message to send:\n " + message + ". ");

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
			System.out.println("Packet End 11");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void blinds() {
		final int DEST_PORT = 49000;		//sending to subscriber receive
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;
		String message = "Close the blinds, the sun is too bright";
		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {

			System.out.println("Subscribing: Broker" + DEST_PORT +", message to send:\n " + message + ".");

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
			System.out.println("Packet End 11");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void laptop() {
		final int DEST_PORT = 49000;		//sending to subscriber receive
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;
		String message = "Open the laptop and press the power button";
		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
			
			System.out.println("Subscribing: Broker" + DEST_PORT + ", message to send:\n " + message + ".");

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
			System.out.println("End Packet 11");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void windows() {
		final int DEST_PORT = 49000;		//sending to subscriber receive
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;
		String message = "Close the windows, there is a draught";
		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
			
			System.out.println("Subscribing: Broker, message to send:\n " + message + ".");

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
			System.out.println("End Packet 11");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
