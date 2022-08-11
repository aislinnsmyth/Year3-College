import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
//author Aislinn Addison-Smyth 19337226
public class broker {

	//static DatagramPacket packet;
	public static void main(String[] args) {
		//send();
			receive();
		//make a loop so program executes 
	}

	
	public static void receive() {
		final int RECV_PORT = 49000;		//49000 free port to use
		final int MTU = 1500;				//maximum package size

		DatagramPacket packet;				//initialize the packet being sent
		DatagramSocket socket;
		InetAddress address;
		int port;

		ObjectInputStream ostream;
		ByteArrayInputStream bstream;
		byte[] buffer;


		try {
			System.out.println("Broker has received");

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
			System.out.println("ReceiverProcess, receive successful");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*	public static void loop() {
		while(true) {
		receive();
		}
	} */
}

