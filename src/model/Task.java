package model;

import java.util.*;

/**
 * @author Sim Thiam Guan, Calvin
 */

public abstract class Task {
	private String task_name;

	public Task() {

	}

	public Task(String task_name) {
		this.task_name = task_name;
	}

	public String getTaskName() {
		return task_name;
	}

	public void setTaskName(String task_name) {
		this.task_name = task_name;
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
