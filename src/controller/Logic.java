package controller;

import java.util.*;
import model.*;

public class Logic {

	public static Task buildTask(String task_name, String start_date, String end_date,
			String start_time, String end_time) {
		try {
			if (task_name == null) {
				return null; // fail to add task.
			} else {
				Task task = null;
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

				return task;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	// public ArrayList<Task> deleteTask(ArrayList<Task> listOfTask, String
	// index){
	// try{
	// if(!listOfTask.isEmpty()){
	// for(int i = 0; i < listOfTask.size(); i++){
	// if(listOfTask.get(i).getTaskId().equals(index)){
	// listOfTask.remove(i);
	// }
	// }
	// }
	//
	// }catch (IndexOutOfBoundsException e) {
	// System.err.println("IndexOutOfBoundsException: " + e.getMessage());
	// }
	// return listOfTask;
	// }

	public String displayTask(ArrayList<Task> listOfTask) {
		String output = "";
		try {
			if (!listOfTask.isEmpty()) {
				for (int i = 0; i < listOfTask.size(); i++) {
					output += (i + 1) + ". " + listOfTask.get(i).getTaskName()
							+ "\r\n";

					if (listOfTask.get(i) instanceof EventTask) {
						EventTask et = (EventTask) listOfTask.get(i);
						output += "Date/Time: " + et.getStartDate() + " - "
								+ et.getEndDate() + " , " + et.getStartTime()
								+ " - " + et.getEndTime() + "\r\n";
					} else if (listOfTask.get(i) instanceof DeadlineTask) {
						DeadlineTask dt = (DeadlineTask) listOfTask.get(i);
						output += "Deadline: " + dt.getDeadlineDate() + ", "
								+ dt.getDeadlineTime() + "\r\n";
					} else if (listOfTask.get(i) instanceof FloatingTask) {

					}
					output += "\r\n";
				}
			} else {
				output = "Task list is empty.";
			}

		} catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
		}
		return output;
	}
}
