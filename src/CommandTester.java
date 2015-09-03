import java.util.*;
public class CommandTester {

	public static void main(String[] args) {
		Task t;
		Command c = new Command();
		t = c.addTask("Do code for CS2103", "Event, Deadline and Floating Task.", "03/09/2015", "1800", "2200");
		
		
		System.out.println("Event: " + t.getTaskName());
		System.out.println("Description: "+t.getDescription());
		System.out.println("By: "+t.getDate());
		System.out.println("Start Time: "+t.getStartTtime());
		System.out.println("End Time: "+t.getEndTime());
	}

}
