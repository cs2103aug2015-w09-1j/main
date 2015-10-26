package command;

import java.time.LocalDate;
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

	public TaskMemory() {
		Storage.getInstance().setfileName("silentjarvis.fxml");
		this.taskList = Storage.getInstance().load();
	}

	public ArrayList<Task> getTaskList() {
		Collections.sort(this.taskList, new TaskNameComparator());
		return this.taskList;
	}

	public ArrayList<Task> getFloatingTask() {
		ArrayList<Task> floatingTaskList = new ArrayList<Task>();
		try{
			for (Task floatingTask : this.taskList) {
				if (floatingTask instanceof FloatingTask) {
					if(floatingTask.getTaskType() != "Archived"){
						floatingTaskList.add((FloatingTask) floatingTask);
					}
				}
			}
			Collections.sort(floatingTaskList, new TaskNameComparator());
			return floatingTaskList;
		}catch(Exception e){
			return null;
		}
		
	}

	public ArrayList<Task> getFollowingWeekTask() {
		
		ArrayList<Task> followingWeekList = new ArrayList<Task>();
		try{
			String followWeekDate = LocalDate.now().plusDays(7).toString();
			String dateNow = LocalDate.now().toString();
			for (Task t : this.taskList) {
				if (t instanceof DeadlineTask) {
					if (((DeadlineTask) t).getDeadlineDate().compareTo(dateNow) >= 0
							&& ((DeadlineTask) t).getDeadlineDate().compareTo(
									followWeekDate) <= 0) {
						if(t.getTaskType() != "Archived"){
							followingWeekList.add(t);
						}
					}
				} else if (t instanceof EventTask) {
					if (((EventTask) t).getEndDate().compareTo(dateNow) >= 0
							&& ((EventTask) t).getEndDate().compareTo(
									followWeekDate) <= 0) {
						if(t.getTaskType() != "Archived"){
							followingWeekList.add(t);
						}
					}
				}
			}
			Collections.sort(followingWeekList, new DateComparator());
			return followingWeekList;
		}catch(Exception e){
			return null;
		}
	}

	public ArrayList<Task> getOtherTask() {
		ArrayList<Task> otherTaskList = new ArrayList<Task>();
		try{
			String followWeekDate = LocalDate.now().plusDays(7).toString();
			String dateNow = LocalDate.now().toString();
			for (Task t : this.taskList) {
				if (t instanceof DeadlineTask) {
					if (((DeadlineTask) t).getDeadlineDate().compareTo(dateNow) >= 0
							&& ((DeadlineTask) t).getDeadlineDate().compareTo(
									followWeekDate) > 0) {
						if(t.getTaskType() != "Archived"){
							otherTaskList.add(t);
						}
						
					}
				} else if (t instanceof EventTask) {
					if (((EventTask) t).getEndDate().compareTo(dateNow) >= 0
							&& ((EventTask) t).getEndDate().compareTo(
									followWeekDate) > 0) {
						if(t.getTaskType() != "Archived"){
							otherTaskList.add(t);
						}
					}
				}
			}
			Collections.sort(otherTaskList, new DateComparator());
			return otherTaskList;
		}catch(Exception e){
			return null;
		}
	}
	
	public ArrayList<Task> getArchivedList(){
		ArrayList<Task> archivedList = new ArrayList<Task>();
		try{
			for (Task t : this.taskList) {
				if(t.getTaskType() == "Archived"){
					archivedList.add(t);
				}
			}
			
			return archivedList;
		}catch(Exception e){
			return null;
		}
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}

	public void sort(ArrayList<Task> taskList) {

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
		if (task instanceof DeadlineTask) {
			this.taskList.remove(task);
		} else if (task instanceof FloatingTask) {
			this.taskList.remove(task);
		} else if (task instanceof EventTask) {
			this.taskList.remove(task);
		}

	}

	public int getSize() {
		return taskList.size();
	}
}
