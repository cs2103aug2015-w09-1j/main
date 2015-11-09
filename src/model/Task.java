package model;

import java.util.*;

import javafx.beans.property.StringProperty;

/*
 * This class is the abstract class of Deadline, Event and Floating Class.
 * @@author A0111947E
 */

public abstract class Task {
	private String task_name;
	private String task_type;
	public Task() {

	}

	public Task(String task_name, String task_type) {
		this.task_name = task_name;
		this.task_type = task_type;
	}

	public String getTaskName() {
		return this.task_name;
	}

	public void setTaskName(String task_name) {
		this.task_name = task_name;
	}
	
	public String getTaskType(){
		return this.task_type;
	}
	
	public void setTaskType(String task_type){
		this.task_type = task_type;
	}

	@Override
	public boolean equals(Object task) {
		// if the task is compared with itself then return true
		if (task == this) {
			return true;
		}
		/*
		 * Check if task is an instance of Task or not "null instanceof [type]"
		 * also returns false
		 */
		if (!(task instanceof DeadlineTask) || !(task instanceof FloatingTask)
				|| !(task instanceof EventTask)) {
			return false;
		}

		Task comparingTask = (Task) task;

		return (this.getTaskName().equals(comparingTask.getTaskName()));
	}
	
}
