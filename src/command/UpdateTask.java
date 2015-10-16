package command;

import model.Task;

public class UpdateTask extends UpdateCommand {
	private Task task;

	public UpdateTask(Task task) {
		this.task = task;
	}

	@Override
	public void execute() {
		System.out.println("Task updated");

	}

	@Override
	public void undo() {
		if (this.undoable()) {
			
		}
	}

}
