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
		 System.out.println(tasks.size());
		 for(int i = this.tasks.size(); i > 0; i--){
			 TaskMemory.getInstance().Remove(this.tasks.get(i-1));
		 }
	}

	@Override
	public void undo() {
		if (this.undoable()) {
			System.out.println(this.tasks.size());
				CreateBulkTask createbulk = new CreateBulkTask(this.tasks);
				createbulk.execute();
		}
	}

}