package command;

import java.util.Comparator;

import model.*;

public class DateComparator implements Comparator<Task> {

	@Override
	public int compare(Task arg0, Task arg1) {
		if(arg0 instanceof DeadlineTask && arg1 instanceof EventTask){
			return ((DeadlineTask) arg0).getDeadlineDate().compareTo(((EventTask) arg1).getEndDate());
		} else if(arg0 instanceof DeadlineTask && arg1 instanceof DeadlineTask){
			return ((DeadlineTask) arg0).getDeadlineDate().compareTo(((DeadlineTask) arg1).getDeadlineDate());
		} else if(arg0 instanceof EventTask && arg1 instanceof DeadlineTask){
			return ((EventTask) arg0).getEndDate().compareTo(((DeadlineTask) arg1).getDeadlineDate());
		} else if(arg0 instanceof EventTask && arg1 instanceof EventTask){
			return ((EventTask) arg0).getEndDate().compareTo(((EventTask) arg1).getEndDate());
		}
		return 0;
	}

}
