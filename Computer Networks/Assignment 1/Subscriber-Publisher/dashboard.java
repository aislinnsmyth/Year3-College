import java.util.Scanner;
import java.net.DatagramPacket;
//author Aislinn Addison-Smyth 19337226
public class dashboard {

	public static String answer;
	public static String secondAnswer;
	
	public static void main(String[] args) {

		while(true) {
			Scanner newScanner = new Scanner(System.in);
			System.out.println("Welcome to the dashboard, would you like to publish or subscribe?");
			String answer = newScanner.next();
			if(answer.equals("subscribe")) {
				System.out.println("Would you like readings from rooms 1, 2 or 3");
				String answer2= newScanner.next();
				System.out.println("Would you like information on the temperature or humidity?");
				secondAnswer = newScanner.next();
				if(answer2.equals("1") && secondAnswer.equals("temperature")) {
					sensor.tempR1();
				} else if(answer2.equals("1") && secondAnswer.equals("humidity")) {
					sensor.humR1();
				} else if(answer2.equals("2") && secondAnswer.equals("temperature")) {
					sensor.tempR2();
				} else if(answer2.equals("2") && secondAnswer.equals("humidity")) { 
					sensor.humR2();
				} else if(answer2.equals("3") && secondAnswer.equals("temperature")) {  
					sensor.tempR3();
				} else if(answer2.equals("3") && secondAnswer.equals("humidity")) {
					sensor.humR3();
				} else {
					System.out.println("We have encountered an error with what you have inputted, please try again.");
				}
			}  else if(answer.equals("publish")) {
				System.out.println("Do you want instructions on the AC, blinds, laptop or windows");
				String answer3 = newScanner.next();
				if(answer3.equals("AC")) {
					actuator.AC();
				} else if(answer3.equals("blinds")) {
					actuator.blinds();
				} else if(answer3.equals("laptop")) {
					actuator.laptop();
				} else if(answer3.equals("windows")) {
					actuator.windows();
				} else {
					System.out.println("We have encountered an error with what you have inputted, please try again.");
				}
				//actuator. where it will send to publish which will send onto broker
	
			}
		}
	}

}

