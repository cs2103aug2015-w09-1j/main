package command;

import java.util.Comparator;

import model.DeadlineTask;
import model.EventTask;
import model.Task;

public class TimeComparator implements Comparator<Task> {

	@Override
	public int compare(Task arg0, Task arg1) {
		if(arg0 instanceof DeadlineTask && arg1 instanceof EventTask){
			return ((DeadlineTask) arg0).getDeadlineTime().compareTo(((EventTask) arg1).getEndTime());
		} else if(arg0 instanceof DeadlineTask && arg1 instanceof DeadlineTask){
			return ((DeadlineTask) arg0).getDeadlineTime().compareTo(((DeadlineTask) arg1).getDeadlineTime());
		} else if(arg0 instanceof EventTask && arg1 instanceof DeadlineTask){
			return ((EventTask) arg0).getEndTime().compareTo(((DeadlineTask) arg1).getDeadlineTime());
		} else if(arg0 instanceof EventTask && arg1 instanceof EventTask){
			return ((EventTask) arg0).getEndTime().compareTo(((EventTask) arg1).getEndTime());
		}
		return 0;
	}

}