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
		for (int i = 0; i < this.tasks.size(); i++) {
			TaskMemory.getInstance().Add(this.tasks.get(i));
		}
	}

	@Override
	public void undo() {
		if (this.undoable()) {

		}
	}

}
