package command;

import java.util.ArrayList;

import model.Task;

public class UpdateBulkTask extends UpdateCommand {
	private ArrayList<Task> thisTask;
	private ArrayList<Task> newTask;

	public UpdateBulkTask(ArrayList<Task> thisTask, ArrayList<Task> newTask) {
		this.thisTask = thisTask;
		this.newTask = newTask;
	}

	@Override
	public void execute() {

		for (Task t : this.thisTask){
			TaskMemory.getInstance().Remove(t);
		}
		
		for(Task t : this.newTask){
			TaskMemory.getInstance().Add(t);
		}

	}

	@Override
	public void undo() {
		if (this.undoable()) {
			
			DeleteBulkTask delete = new DeleteBulkTask(this.newTask);
			delete.execute();
			CreateBulkTask create = new CreateBulkTask(this.thisTask);
			create.execute();
			
		}
	}
}
