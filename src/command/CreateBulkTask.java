package command;

import java.util.ArrayList;

import model.Task;

/*
 * This class is to execute multiple create 
 * 
 * @@author Jason (A0127830J)
 */
public class CreateBulkTask extends CreateCommand {
	private ArrayList<Task> tasks;

	public CreateBulkTask(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public void execute() {
		for (Task t : this.tasks) {
			TaskMemory.getInstance().Add(t);
		}
	}

	@Override
	public void undo() {

	}

}
