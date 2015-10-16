package command;

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
		
		System.out.println("Task created");
		// Firstly, Add into the local memory
		TaskMemory.getInstance().Add(this.task);
		System.out.println(TaskMemory.getInstance().getSize());
		// Secondly, build this task and add into the storage
		
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
