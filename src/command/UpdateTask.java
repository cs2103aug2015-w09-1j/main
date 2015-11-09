package command;

import model.Task;

/*
 * 
 * 
 * @@author A0111947E
 */
public class UpdateTask extends UpdateCommand {
	private Task thisTask;
	private Task newTask;

	public UpdateTask(Task thisTask, Task newTask) {
		this.thisTask = thisTask;
		this.newTask = newTask;
	}

	@Override
	public void execute() {

		DeleteTask delete = new DeleteTask(this.thisTask);
		delete.execute();

		CreateTask create = new CreateTask(this.newTask);
		create.execute();

	}

	@Override
	public void undo() {
		if (this.undoable()) {
			
			CreateTask create = new CreateTask(this.thisTask);
			create.execute();
			DeleteTask delete = new DeleteTask(this.newTask);
			delete.execute();
		}
	}

}
