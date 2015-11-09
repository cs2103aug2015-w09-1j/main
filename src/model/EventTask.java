//@@author ZhouYou(A0133976U)
package model;

import java.util.*;

/*
 * This class models a event Task
 * It inherits from a task model and contains extra information 
 * including starting date and time and ending date and time
 */

public class EventTask extends Task {

	/***************Attributes********************/
	private String start_time;
	private String end_time;
	private String start_date;
	private String end_date;
	
	/***************Constructors********************/
	public EventTask() {
		super();
	}

	public EventTask(String task_name, String start_date, String end_date,
			String start_time, String end_time, String task_type) {
		super(task_name, task_type);
		this.start_date = start_date;
		this.end_date = end_date;
		this.start_time = start_time;
		this.end_time = end_time;

	}
	/***************Accessors and mutators********************/
	public String getStartDate() {
		return start_date;
	}

	public void setStartDate(String start_date) {
		this.start_date = start_date;
	}

	public String getEndDate() {
		return end_date;
	}

	public void setEndDate(String end_date) {
		this.end_date = end_date;
	}

	public String getStartTime() {
		return start_time;
	}

	public void setStartTime(String start_time) {
		this.start_time = start_time;
	}

	public String getEndTime() {
		return end_time;
	}

	public void setEndTime(String end_time) {
		this.end_time = end_time;
	}
}
