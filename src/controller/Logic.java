package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import command.CreateTask;
import command.DeleteBulkTask;
import command.DeleteTask;
import command.ICommand;
import command.TaskMemory;
import command.UpdateTask;
import model.*;

public class Logic {
	// whoever inherit interface, is able to add in to the stack. It is for Undo
	// purposes
	private static Stack<ICommand> processStack = null;

	public Logic() {
		processStack = new Stack<ICommand>();
	}

	private void pushToProcessStack(ICommand command) {
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

	// execute create task
	public void executeCreateTask(String task_name, String start_date,
			String start_time, String end_date, String end_time) {
		Task task;
		task = buildTask(task_name.trim(), start_date, end_date, start_time,
				end_time, "null");
		CreateTask create = new CreateTask(task);
		create.execute();
		pushToProcessStack(create);
	}

	// execute delete task
	public void executeDeleteTask(ArrayList<Task> displayList, int task_index) {
		Task task;
		task = deleteTask(displayList, task_index);
		DeleteTask delete = new DeleteTask(task);
		delete.execute();
		pushToProcessStack(delete);
	}

	// execute deleting tasks with multiple id
	public void executeDeleteBulkTasksById(ArrayList<Task> currentList,
			int[] _listIndex) {
		ArrayList<Task> deleteBulkArray = new ArrayList<Task>();
		deleteBulkArray = getTaskByMutlipleId(currentList, _listIndex);
		DeleteBulkTask deletebulk = new DeleteBulkTask(deleteBulkArray);
		deletebulk.execute();
		pushToProcessStack(deletebulk);
	}

	// execute deleting all tasks in the current view list
	public void executeDeleteBulkTask(ArrayList<Task> currentList) {
		ArrayList<Task> deleteBulkArray = new ArrayList<Task>();

		for (Task t : currentList) {
			deleteBulkArray.add(t);
		}
		DeleteBulkTask deletebulk = new DeleteBulkTask(deleteBulkArray);
		deletebulk.execute();
		pushToProcessStack(deletebulk);
	}

	// Building a task
	private Task buildTask(String task_name, String start_date,
			String end_date, String start_time, String end_time,
			String task_type) {
		Task task = null;

		try {
			if (task_name.isEmpty()) {
				return null; // fail to add task.
			} else {

				if (start_date != null && end_date != null
						&& start_time != null && end_time != null) {
					// event task
					task = new EventTask(task_name, start_date, end_date,
							start_time, end_time, task_type);
				} else {
					if (start_date == null && end_date == null
							&& start_time == null && end_time == null) {
						// floating task
						task = new FloatingTask(task_name, task_type);
					} else {
						// deadline task
						task = new DeadlineTask(task_name, end_date, end_time,
								task_type);
					}
				}

			}
		} catch (Exception e) {
			return null;
		}
		return task;
	}

	// Deleting a task
	private Task deleteTask(ArrayList<Task> currentList, int index) {
		Task task = null;
		try {
			ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
			task = currentList.get(index - 1);
			for (Task t : taskList) {
				if (task.equals(t)) {
					task = t;
				}
			}
		} catch (Exception e) {
			// System.out.println(DELETETASK_MESSAGE);
			return null;
		}
		return task;
	}

	// excute update task
	public void executeUpdateTask(ArrayList<Task> currentList,
			String task_name, String start_date, String start_time,
			String end_date, String end_time, String task_type, int task_index) {
		Task deleteTask = SearchTaskById(currentList, task_index);
		Task updateTask = buildTask(task_name.trim(), start_date, end_date,
				start_time, end_time, task_type);

		UpdateTask update = new UpdateTask(deleteTask, updateTask);
		update.execute();
		pushToProcessStack(update);
	}

	// execute update task by certain attribute
	public void executeUpdateTaskByAttribute(ArrayList<Task> currentList,
			int task_index, String editAttr, String editInfo) {
		Task task = SearchTaskById(currentList, task_index);
		String task_name = null;
		String start_date = null;
		String end_date = null;
		String start_time = null;
		String end_time = null;
		String task_type = null;
		
		if (task instanceof EventTask) {
			task_name = task.getTaskName();
			start_date = ((EventTask) task).getStartDate();
			end_date = ((EventTask) task).getEndDate();
			start_time = ((EventTask) task).getStartTime();
			end_time = ((EventTask) task).getEndTime();
			task_type = task.getTaskType();

			if (editAttr.equalsIgnoreCase("startDate")) {
				start_date = editInfo;
			} else if (editAttr.equalsIgnoreCase("endDate")) {
				end_date = editInfo;
			} else if (editAttr.equalsIgnoreCase("startTime")) {
				start_time = editInfo;
			} else if (editAttr.equalsIgnoreCase("endTime")) {
				end_time = editInfo;
			} else if (editAttr.equalsIgnoreCase("taskName")) {
				task_name = editInfo;
			} else if (editAttr.equalsIgnoreCase("taskType")) {
				task_type = editInfo;
			}
		} else if (task instanceof DeadlineTask) {
			task_name = task.getTaskName();
			end_date = ((DeadlineTask) task).getDeadlineDate();
			end_time = ((DeadlineTask) task).getDeadlineTime();
			task_type = task.getTaskType();

			if (editAttr.equalsIgnoreCase("endDate")) {
				end_date = editInfo;
			} else if (editAttr.equalsIgnoreCase("endTime")) {
				end_time = editInfo;
			} else if (editAttr.equalsIgnoreCase("taskName")) {
				task_name = editInfo;
			} else if (editAttr.equalsIgnoreCase("taskType")) {
				task_type = editInfo;
			}
		} else if (task instanceof FloatingTask) {
			task_name = task.getTaskName();
			task_type = task.getTaskType();

			if (editAttr.equalsIgnoreCase("taskName")) {
				task_name = editInfo;
			} else if (editAttr.equalsIgnoreCase("taskType")) {
				task_type = editInfo;
			}
		}

		executeUpdateTask(currentList, task_name, start_date, start_time,
				end_date, end_time, task_type, task_index);
	}

	// get tasks by a number of index, return arraylist<task>
	private ArrayList<Task> getTaskByMutlipleId(ArrayList<Task> currentList,
			int[] index) {
		ArrayList<Task> taskOfSearcedList = new ArrayList<Task>();
		try {
			for (int i = 0; i < index.length; i++) {
				taskOfSearcedList.add(currentList.get(index[i] - 1));
			}

		} catch (Exception e) {

		}
		return taskOfSearcedList;
	}
	
	// search between dates
	public ArrayList<Task> searchTaskBetweenDate(ArrayList<Task> currentList, String first_date, String second_date){
		ArrayList<Task> taskOfSearchedList = new ArrayList<Task>();
		try {
			for (Task t : currentList) {
				if (t.getTaskType() != "Archived") {
					if (t instanceof DeadlineTask) {
						if (((DeadlineTask) t).getDeadlineDate().compareTo(
								first_date) >= 0 && ((DeadlineTask) t).getDeadlineDate().compareTo(
										second_date) <= 0) {
							taskOfSearchedList.add(t);
						}
					} else if (t instanceof EventTask) {
						if (((EventTask) t).getEndDate().compareTo(first_date) >= 0 && ((EventTask) t).getEndDate().compareTo(second_date) <= 0) {
							taskOfSearchedList.add(t);
						}
					}
				}
			}

		} catch (Exception e) {

		}
		return taskOfSearchedList;
	}

	// Search on date
	public ArrayList<Task> searchTaskOnDate(ArrayList<Task> currentList,
			String end_date) {
		ArrayList<Task> taskOfSearchedList = new ArrayList<Task>();
		try {
			// get all task on the search date.
			for (Task t : currentList) {
				if (t.getTaskType() != "Archived") {
					if (t instanceof DeadlineTask) {
						if (((DeadlineTask) t).getDeadlineDate().compareTo(
								end_date) == 0) {
							taskOfSearchedList.add(t);
						}
					} else if (t instanceof EventTask) {
						if (((EventTask) t).getEndDate().compareTo(end_date) == 0) {
							taskOfSearchedList.add(t);
						}
					}
				}
			}

		} catch (Exception e) {

		}
		return taskOfSearchedList;
	}

	// Search by date
	public ArrayList<Task> searchTaskByDate(ArrayList<Task> currentList,
			String end_date) {
		ArrayList<Task> taskOfSearchedList = new ArrayList<Task>();
		try {
			// get all task by the end date
			// search by tomorrow |search by 2015-10-10
			String dateNow = LocalDate.now().toString();
			for (Task t : currentList) {
				if (t.getTaskType() != "Archived") {
					if (t instanceof DeadlineTask) {
						if (((DeadlineTask) t).getDeadlineDate().compareTo(
								dateNow) >= 0
								&& ((DeadlineTask) t).getDeadlineDate()
										.compareTo(end_date) <= 0) {
							taskOfSearchedList.add(t);
						}
					} else if (t instanceof EventTask) {
						if (((EventTask) t).getEndDate().compareTo(dateNow) >= 0
								&& ((EventTask) t).getEndDate().compareTo(
										end_date) <= 0) {
							taskOfSearchedList.add(t);
						}
					}
				}
			}
		} catch (Exception e) {

		}
		return taskOfSearchedList;
	}

	// Searching for tasks , return the filtered arraylist based on the keyword
	public ArrayList<Task> searchTaskByKeyword(ArrayList<Task> currentList,
			String keyword) {
		ArrayList<Task> taskOfSearchedList = new ArrayList<Task>();
		try {
			ArrayList<Task> taskList = currentList;

			// Tokenize the String with a regular expression in Split.
			String[] tokens = keyword.split("[,\\ ]");
			for (String token : tokens) {
				for (Task t : taskList) {
					if(!taskOfSearchedList.contains(t)){
						if (t.getTaskName() != null
								&& t.getTaskName().toString().contains(token)
								&& t.getTaskType() != "Archived") {
							taskOfSearchedList.add(t);
						}
					}
				}
			}

		} catch (Exception e) {

		}

		return taskOfSearchedList;
	}

	// Search for specific task based on the index of the current display list
	// and return the same task from the Task List
	private Task SearchTaskById(ArrayList<Task> currentList, int index) {
		Task results = null;
		try {
			ArrayList<Task> TaskList = TaskMemory.getInstance().getTaskList();
			for (int i = 0; i < TaskList.size(); i++) {
				if (currentList.get(index - 1).equals(TaskList.get(i))) {
					results = TaskList.get(i);
				}
			}
		} catch (Exception e) {

		}
		return results;
	}
}
