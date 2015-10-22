package controller;

import java.util.*;

import command.ICommand;
import command.TaskMemory;
import model.*;

public class Logic {
	private static String BUILDTASK_MESSAGE = "Unable to add task.";
	private static String DELETETASK_MESSAGE = "Unable to delete task";
	private static String UPDATETASK_MESSAGE = "Unable to update task";
	// whoever inherit interface, is able to add in to the stack. It is for Undo
	// purposes
	private static Stack<ICommand> processStack = null;

	public Logic() {
		processStack = new Stack<ICommand>();
	}

	public void pushToProcessStack(ICommand command) {
		if (command.undoable()) {
			processStack.push(command);
		}
	}

	public void undo() {
		if (processStack.isEmpty()) {

		} else {
			processStack.pop().undo();
		}
	}
	
	// Building a task
	public Task buildTask(String task_name, String start_date, String end_date,
			String start_time, String end_time) {
		Task task = null;
		
		try {
			if (task_name.isEmpty()) {
				return null; // fail to add task.
			} else {

				if (start_date != null && end_date != null
						&& start_time != null && end_time != null) {
					// event task
					task = new EventTask(task_name, start_date, end_date,
							start_time, end_time);
				} else {
					if (start_date == null && end_date == null
							&& start_time == null && end_time == null) {
						// floating task
						task = new FloatingTask(task_name);
					} else {
						// deadline task
						task = new DeadlineTask(task_name, end_date, end_time);
					}
				}

			}
		} catch (Exception e) {
			return null;
		}
		return task;
	}

	// Deleting a task
	public Task deleteTask(ArrayList<Task> currentList, int index) {
		Task task = null;
		try {
			ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
			task = currentList.get(index - 1);
			for(Task t : taskList){
				if(task.equals(t)){
					task = t;
				}
			}	
		} catch (Exception e) {
			//System.out.println(DELETETASK_MESSAGE);
			return null;
		}
		return task;
	}

	// Updating a task
	public Task updateTask(ArrayList<Task> currentList, int index) {
		Task task = null;
		try {
			// do a search to find out which task i want to update.
			task = getSearchTaskById(currentList, index-1);
			
		} catch (Exception e) {
			return null;
		}
		return task;
	}

	// Searching for tasks , return the filtered arraylist based on the keyword
	public ArrayList<Task> searchTask(ArrayList<Task> currentList, String keyword) {
		ArrayList<Task> taskOfSearchedList = new ArrayList<Task>();
		try {
			ArrayList<Task> taskList = currentList;
			for (Task t : taskList) {
				if (t.getTaskName() != null
						&& t.getTaskName().toString().contains(keyword)) {
					taskOfSearchedList.add(t);
				}
			}

		} catch (Exception e) {

		}

		return taskOfSearchedList;
	}
	
	// Search for specific task based on the index of the current display list and return the same task from the Task List
	// return int
	public Task getSearchTaskById(ArrayList<Task> currentList, int index){
		Task results = null;
		try{
			ArrayList<Task> TaskList = TaskMemory.getInstance().getTaskList();
			for(int i = 0; i < TaskList.size() ; i++){
				if(currentList.get(index).equals(TaskList.get(i))){
					results = TaskList.get(i);
				}
			}
		}catch(Exception e){
			
		}
		return results;
	}

	

	// public String displayTask(ArrayList<Task> listOfTask) {
	// String output = "";
	// try {
	// if (!listOfTask.isEmpty()) {
	// for (int i = 0; i < listOfTask.size(); i++) {
	// output += (i + 1) + ". " + listOfTask.get(i).getTaskName()
	// + "\r\n";
	//
	// if (listOfTask.get(i) instanceof EventTask) {
	// EventTask et = (EventTask) listOfTask.get(i);
	// output += "Date/Time: " + et.getStartDate() + " - "
	// + et.getEndDate() + " , " + et.getStartTime()
	// + " - " + et.getEndTime() + "\r\n";
	// } else if (listOfTask.get(i) instanceof DeadlineTask) {
	// DeadlineTask dt = (DeadlineTask) listOfTask.get(i);
	// output += "Deadline: " + dt.getDeadlineDate() + ", "
	// + dt.getDeadlineTime() + "\r\n";
	// } else if (listOfTask.get(i) instanceof FloatingTask) {
	//
	// }
	// output += "\r\n";
	// }
	// } else {
	// output = "Task list is empty.";
	// }
	//
	// } catch (IndexOutOfBoundsException e) {
	// System.err.println("IndexOutOfBoundsException: " + e.getMessage());
	// }
	// return output;
	// }
}
