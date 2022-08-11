import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		//Sends onto endUser
		//docker cp eclipse-workspace/compNetwork <root>:/
		//Stop and wait ARQ
		while(true) {
			Scanner newScanner = new Scanner(System.in);
			System.out.println("Press 1 if you would like to send a Datagram packet.");
			String data = newScanner.next();
			if(data.equals("1")) {
				sendMessage();
			} else {
				System.out.println("Incorrect answer, retry.");
			}

		}
	}

	public static void sendMessage() {
		final int DEST_PORT = 51510;		//destination port is 51510
		final int SRC_PORT = 51510;			//current source port is the same as destination port @ 51510
		
		DatagramPacket packet;				//Datagram packet sent solely off information in that packet
		DatagramSocket socket;				//Provides a connection-less point for sending and receiving packets.
		InetSocketAddress address;			

		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
			Scanner routerScanner = new Scanner(System.in);
			//Need to change this to what destination via string destination to send to.
			System.out.println("Where would you like to send the Datagram packet to?");
			String routerString = routerScanner.next();
			System.out.println("Sending a packet to the forwarding service...");
			address= new InetSocketAddress("F1", DEST_PORT); 			//creating the address in which packet will be sent to using destination port                      
			byte[] data = routerString.getBytes();
			
			// create packet addressed to destination
			packet= new DatagramPacket(data, data.length);		//attaching the header information to the packet.
			packet.setSocketAddress(address);			//header
			socket= new DatagramSocket(SRC_PORT);						//creating a socket within the source port in order to send the packet
			socket.send(packet);
			System.out.println("Packet sent");
			System.out.println("Source		 Next hop\n" + "-------------------------\n" 
			+ "E1		F1");

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
