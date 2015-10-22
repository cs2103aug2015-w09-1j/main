package command;

import java.util.ArrayList;

import model.Task;

public class CreateBulkTask extends CreateBulkCommand {
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
		if (this.undoable()) {

		}
	}

}
