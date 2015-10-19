package model;

import java.util.*;

import javafx.beans.property.StringProperty;

/**
 * @author Sim Thiam Guan, Calvin
 */

public abstract class Task {
	private StringProperty task_name;
	private StringProperty task_type;
	public Task() {

	}

	public Task(StringProperty task_name, StringProperty task_type) {
		this.task_name = task_name;
		this.task_type = task_type;
	}

	public StringProperty getTaskName() {
		return this.task_name;
	}

	public void setTaskName(StringProperty task_name) {
		this.task_name = task_name;
	}
	
	public StringProperty getTaskType(){
		return this.task_type;
	}
	
	public void setTaskType(StringProperty task_type){
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
