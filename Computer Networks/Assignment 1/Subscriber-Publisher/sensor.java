import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
//author Aislinn Addison-Smyth 19337226
public class sensor {

	public static void main(String[] args) {
		tempR1();
		humR1();
		tempR2();
		humR2();
		tempR3();
		humR3();
	}
	//the reporting of sensor data to a number of subscribers
	public static void tempR1() {
		final int DEST_PORT = 49000;		//sending to subscriber receive
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;

		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
			
	//		if(dashboard.secondAnswer.equals("temperature"));
				String message = "Location: Reception,  "+ "Temperature: 26 degrees";
		
			System.out.println("Publish: Broker" + DEST_PORT + ", message to send:\n " + message + ".");

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
			System.out.println("Packet End 11/n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void humR1() {
		final int DEST_PORT = 49000;		//sending to subscriber receive
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;

		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
			
	//		if(dashboard.secondAnswer.equals("temperature"));
				String message = "Location: Reception, "+ "Humidity: 30%";
		
			System.out.println("Publish: Broker"  + DEST_PORT + ", message to send:\n " + message + ".");

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
			System.out.println("Packet End 11/n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void tempR2() {
		final int DEST_PORT = 49000;		//free port to use
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;

		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
		//	if(dashboard.secondAnswer.equals("temperature"));
			String message1 = "Location: Office, "+ " Temperature: 22 degrees";
			System.out.println("Publish: Broker"  + DEST_PORT + ", message to send:\n " + message1 + ". ");

			// extract destination from arguments
			address= InetAddress.getLocalHost();   // InetAddress.getByName(args[0]);
			port= DEST_PORT;                       // Integer.parseInt(args[1]);

			// convert string "Hello World" to byte array
			bstream= new ByteArrayOutputStream();
			ostream= new ObjectOutputStream(bstream);
			ostream.writeUTF(message1);
			ostream.flush();
			buffer= bstream.toByteArray();

			// create packet addressed to destination
			packet= new DatagramPacket(buffer, buffer.length,
					address, port);

			// create socket and send packet
			socket= new DatagramSocket();
			socket.send(packet);
			System.out.println("Packet End 11/n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void humR2() {
		final int DEST_PORT = 49000;		//free port to use
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;

		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
		//	if(dashboard.secondAnswer.equals("temperature"));
			String message1 = "Location: Office, "+ " Humidity: 15%";
			System.out.println("Publish: Broker"  + DEST_PORT + ", message to send:\n " + message1 + ".");

			// extract destination from arguments
			address= InetAddress.getLocalHost();   // InetAddress.getByName(args[0]);
			port= DEST_PORT;                       // Integer.parseInt(args[1]);

			// convert string "Hello World" to byte array
			bstream= new ByteArrayOutputStream();
			ostream= new ObjectOutputStream(bstream);
			ostream.writeUTF(message1);
			ostream.flush();
			buffer= bstream.toByteArray();

			// create packet addressed to destination
			packet= new DatagramPacket(buffer, buffer.length,
					address, port);

			// create socket and send packet
			socket= new DatagramSocket();
			socket.send(packet);
			System.out.println("Packet End 11/n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void tempR3() {
		final int DEST_PORT = 49000;		//free port to use
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;

		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
		//	if(dashboard.secondAnswer.equals("temperature"));
			String message2 = "Location: Boiler Room, "+ "Temperature: 34 degrees";
			System.out.println("Publish: Broker"  + DEST_PORT + ", message to send:\n " + message2 + ".");

			// extract destination from arguments
			address= InetAddress.getLocalHost();   // InetAddress.getByName(args[0]);
			port= DEST_PORT;                       // Integer.parseInt(args[1]);

			// convert string "Hello World" to byte array
			bstream= new ByteArrayOutputStream();
			ostream= new ObjectOutputStream(bstream);
			ostream.writeUTF(message2);
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
	
	public static void humR3() {
		final int DEST_PORT = 49000;		//free port to use
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;

		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
		//	if(dashboard.secondAnswer.equals("temperature"));
			String message2 = "Location: Boiler Room, "+ "Humidity: 90%";
			System.out.println("Publish: Broker"  + DEST_PORT + ", message to send:\n " + message2 + ".");

			// extract destination from arguments
			address= InetAddress.getLocalHost();   // InetAddress.getByName(args[0]);
			port= DEST_PORT;                       // Integer.parseInt(args[1]);

			// convert string "Hello World" to byte array
			bstream= new ByteArrayOutputStream();
			ostream= new ObjectOutputStream(bstream);
			ostream.writeUTF(message2);
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
} 


