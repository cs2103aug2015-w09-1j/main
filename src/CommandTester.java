import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommandTester {

	public static void main(String[] args) {
		ArrayList<Task> listOfTask = new ArrayList<Task>();
		
		//Task e = new Command().addTask("1", "Do code for CS2103",	"Event, Deadline and Floating Task.", "03/09/2015", "18:00", "22:00");
		//Task e = new Command().addTask("1", "Eat Dinner", null, null, null, null);	
		Task e = new LogicCommand().addTask("1", "Watch Movie", "Avenagers 2", "04/09/2015", null, "2200");
			if(e instanceof EventTask) {
				EventTask t = (EventTask) e;
				listOfTask.add(t);
			} else if (e instanceof FloatingTask) {
				FloatingTask t = (FloatingTask) e;
				listOfTask.add(t);
			} else if (e instanceof DeadlineTask) {
				DeadlineTask t = (DeadlineTask) e;
				listOfTask.add(t);
			}
			
			for(Task t : listOfTask){
				System.out.println(t.getTaskName());
			}
			
	}

}
