package command;

import java.util.ArrayList;

import model.Task;

public class SearchTask extends SearchCommand {
	private ArrayList<Task> searchedList; 
	
	public SearchTask(){
		searchedList = new ArrayList<Task>();
	}
	
	@Override
	public void execute() {
		ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
		
		
	}

	@Override
	public void undo() {
		// false, unable to do a undo for search
		
	}

}
