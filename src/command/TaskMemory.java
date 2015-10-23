package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Storage;
import model.DeadlineTask;
import model.EventTask;
import model.FloatingTask;
import model.Task;

public class TaskMemory {
	private static TaskMemory _instance;
	private ArrayList<Task> taskList;

//	Task taskA = new EventTask("Have Meeting With Kiwi 2103", "15-10-2015",
//			"15-10-2015", "1200", "1500");
//	Task taskB = new FloatingTask("Eating Dinner w Kenneth.");
//	Task taskC = new DeadlineTask("Finish 2103 project.", "16-10-2015", "2359");
//	Task taskD = new DeadlineTask("2103 project.", "16-10-2015", "2359");

	public TaskMemory() {
		Storage.getInstance().setfileName("silentjarvis.fxml");
		this.taskList = Storage.getInstance().load();
	}

	public ArrayList<Task> getTaskList() {
		Collections.sort(this.taskList, new CustomComparator());
		return this.taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}
	
	public void sort(ArrayList<Task> taskList){
		
	}

	public static TaskMemory getInstance() {
		if (_instance == null) {
			_instance = new TaskMemory();
		}
		return _instance;
	}

	public void Add(Task task) {
		this.taskList.add(task);
	}

	public void Remove(Task task) {
		if(task instanceof DeadlineTask){
			this.taskList.remove(task);
		}else if(task instanceof FloatingTask){
			this.taskList.remove(task);
		}else if(task instanceof EventTask){
			this.taskList.remove(task);
		}
		
	}

	public int getSize() {
		return taskList.size();
	}
}
