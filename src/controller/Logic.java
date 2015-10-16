package controller;

import java.util.*;

import command.ICommand;
import command.TaskMemory;
import model.*;

public class Logic {
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
			if (task_name == null) {
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
			System.out.println("Unable to add task");
		}
		return task;
	}

	// Deleting a task
	public Task deleteTask(int index) {
		Task task = null;
		try {
			ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
			task = taskList.get(index - 1);
		} catch (Exception e) {
			System.out.println("Unable to delete task");
		}
		return task;

	}

	// Updating a task
	public Task updateTask(ArrayList<Task> currentList, int index) {
		Task task = null;
		try {
			// 1.do a search to find out which task i want to update.
			int taskIndex = getSearchTaskById(currentList, index);
			// 2.delete this particular task and create the edited task.
			task = deleteTask(taskIndex);
			// 3.do a sort
			

		} catch (Exception e) {

		}
		return task;
	}

	// Searching for tasks , return the filtered arraylist based on the keyword
	public ArrayList<Task> searchTask(ArrayList<Task> taskList, String keyword) {
		ArrayList<Task> taskOfSearchedList = new ArrayList<Task>();
		try {
			for (Task t : taskList) {
				if (t.getTaskName() != null
						&& t.getTaskName().contains(keyword)) {
					taskOfSearchedList.add(t);
				}
			}

		} catch (Exception e) {

		}

		return taskOfSearchedList;
	}
	
	// Search for specific task based on the index of the current display list and return the same task from the Task List
	// return int
	public int getSearchTaskById(ArrayList<Task> currentList, int index){
		int results = -1;
		try{
			ArrayList<Task> TaskList = TaskMemory.getInstance().getTaskList();
			for(int i = 0; i < TaskList.size() ; i++){
				if(currentList.get(index).equals(TaskList.get(i))){
					results = i;
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
