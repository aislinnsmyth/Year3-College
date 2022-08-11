import java.net.InetAddress;

public class controller {

	public static void main(String[] args) {


	}

	public static String forwardingTable(String newData) {
		if(newData.equals("trinity")) {
			 System.out.println(newData="R1");
		} else if(newData.equals("centrallab")) {
			 System.out.println(newData="R2");
		} else if(newData.equals("LG35")) {
			 System.out.println(newData="R3");
		}else if(newData.equals("hamilton")) {
			 System.out.println(newData="R5");
		} else if(newData.equals("berkeley")) {
			 System.out.println(newData="R8");
		} else {
			 System.out.println(newData);
		}
		return newData;
			
		
	}

}
