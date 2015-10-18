package command;

import model.Task;

public class DeleteTask extends DeleteCommand {
	private Task task;
	public DeleteTask(Task task) {
		this.task = task;
	}

	@Override
	public void execute() {
		// Firstly, delete into the local memory
		 TaskMemory.getInstance().Remove(this.task);
		
		// Secondly, delete this task into the storage
		// Call storage API
	}

	@Override
	public void undo() {
		if (this.undoable()) {
			CreateTask create = new CreateTask(this.task);
			create.execute();
		}

	}

}
