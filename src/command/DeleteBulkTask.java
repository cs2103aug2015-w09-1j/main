package command;

import java.util.ArrayList;

import model.Task;

public class DeleteBulkTask extends DeleteCommand {
	private ArrayList<Task> tasks;
	
	public DeleteBulkTask(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public void execute() {
		// Firstly, delete into the local memory
		 for(Task t: this.tasks) {
			 TaskMemory.getInstance().Remove(t);
		 }
	}

	@Override
	public void undo() {
		if (this.undoable()) {
			//System.out.println(this.tasks.size());
				CreateBulkTask createbulk = new CreateBulkTask(this.tasks);
				createbulk.execute();
		}
	}

}