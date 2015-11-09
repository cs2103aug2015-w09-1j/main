package command;

import java.util.Comparator;

import model.*;

/*
 * @@author A0111947E
 */
public class DateComparator implements Comparator<Task> {

	@Override
	public int compare(Task arg0, Task arg1) {
		if(arg0 instanceof DeadlineTask && arg1 instanceof EventTask){
			String deadlineDateTime = ((DeadlineTask) arg0).getDeadlineDate() + " " + ((DeadlineTask) arg0).getDeadlineTime();
			String eventDateTime = ((EventTask) arg1).getEndDate() + " " + ((EventTask) arg1).getEndTime();
			return deadlineDateTime.compareTo(eventDateTime);
		} else if(arg0 instanceof DeadlineTask && arg1 instanceof DeadlineTask){
			String deadlineDateTime = ((DeadlineTask) arg0).getDeadlineDate() + " " + ((DeadlineTask) arg0).getDeadlineTime();
			String deadlineDateTime2 = ((DeadlineTask) arg1).getDeadlineDate() + " " + ((DeadlineTask) arg1).getDeadlineTime();
			return deadlineDateTime.compareTo(deadlineDateTime2);
		} else if(arg0 instanceof EventTask && arg1 instanceof DeadlineTask){
			String deadlineDateTime = ((DeadlineTask) arg1).getDeadlineDate() + " " + ((DeadlineTask) arg1).getDeadlineTime();
			String eventDateTime = ((EventTask) arg0).getEndDate() + " " + ((EventTask) arg0).getEndTime();
			return eventDateTime.compareTo(deadlineDateTime);
		} else if(arg0 instanceof EventTask && arg1 instanceof EventTask){
			String eventDateTime = ((EventTask) arg0).getEndDate() + " " + ((EventTask) arg0).getEndTime();
			String eventDateTime2 = ((EventTask) arg1).getEndDate() + " " + ((EventTask) arg1).getEndTime();
			return eventDateTime.compareTo(eventDateTime2);
		}
		return 0;
	}

}
