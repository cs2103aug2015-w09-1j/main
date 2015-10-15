package command;

import java.util.ArrayList;

import controller.Logic;
import model.Task;

public class CreateTask extends CreateCommand {
	private Task task;

	public CreateTask() {

	}

	public CreateTask(Task task) {
		this.task = task;
	}

	@Override
	public void execute() {
		// Firstly, Add into the local memory

		// Secondly, build this task and add into the storage
		task = Logic.buildTask("Have a date with xxx", null, "2015-10-15", null, "1800");
		System.out.println("Task created");
		// Call storage API
		
	}

	@Override
	public void undo() {
		if (this.undoable()) {
			DeleteTask delete = new DeleteTask(this.task);
			delete.execute();
		}
	}

}
