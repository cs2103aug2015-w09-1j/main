package command;
import java.util.Comparator;

import model.*;

/*
 * @@author A0111947E
 */
public class TaskNameComparator implements Comparator<Task> {

	@Override
	public int compare(Task arg0, Task arg1) {
		return arg0.getTaskName().compareTo(arg1.getTaskName());
	}

}
