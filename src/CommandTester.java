import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommandTester {

	public static void main(String[] args) {
		ArrayList<Task> listOfTask = new ArrayList<Task>();
		
		Task e1 = new Logic().addTask("1", "Do code for CS2103",	"Event, Deadline and Floating Task.", "03/09/2015", "18:00", "22:00");
		Task e2 = new Logic().addTask("2", "Eat Dinner", null, null, null, null);	
		Task e3 = new Logic().addTask("3", "Watch Movie", "Avenagers 2", "04/09/2015", null, "2200");
		Task e4 = new Logic().addTask("4", "Do MA1101R",	"Revise on Matrices.", "03/09/2015", "1500", "1600");
		Task e5 = new Logic().addTask("5", "Do CS2101",	"OP1", null, null, null);
//			if(e instanceof EventTask) {
//				EventTask t = (EventTask) e;
//				listOfTask.add(t);
//			} else if (e instanceof FloatingTask) {
//				FloatingTask t = (FloatingTask) e;
//				listOfTask.add(t);
//			} else if (e instanceof DeadlineTask) {
//				DeadlineTask t = (DeadlineTask) e;
//				listOfTask.add(t);
//			}
		EventTask t1 = (EventTask) e1;
		FloatingTask t2 = (FloatingTask) e2;
		DeadlineTask t3 = (DeadlineTask) e3;
		
		listOfTask.add(t1);
		listOfTask.add(t2);
		listOfTask.add(t3);
		listOfTask.add(e4);
		listOfTask.add(e5);
		new Logic().deleteTask(listOfTask, "1");	
		new Logic().deleteTask(listOfTask, "2");
		new Logic().deleteTask(listOfTask, "3");	
		new Logic().deleteTask(listOfTask, "4");
		new Logic().deleteTask(listOfTask, "5");	
		//new LogicCommand().deleteTask(listOfTask, getNumToDelete("1"));	
		System.out.println(new Logic().displayTask(listOfTask));
	}
	
	private static int getNumToDelete(String command) {
		int numDelete = Integer.parseInt(command);
		return numDelete;
	}

}
