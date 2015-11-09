//@@author ZhouYou(A0133976U)
package model;
import java.util.*;
/*
 * This class models a Deadline Task
 * It inherits from a task model and contains extra information 
 * including date and time as deadline
 */
public class DeadlineTask extends Task {
	/***************Attributes********************/
	private String deadline_date;
	private String deadline_time;
	
	/***************Constructor********************/
	public DeadlineTask(){
		super();
	}
	/***************Accessors and mutators********************/
	public DeadlineTask(String task_name, String deadline_date, String deadline_time, String task_type){
		super(task_name, task_type);
		this.deadline_date = deadline_date;
		this.deadline_time = deadline_time;
	}

	public String getDeadlineDate() {
		return deadline_date;
	}

	public void setDeadlineDate(String deadline_date) {
		this.deadline_date = deadline_date;
	}
	
	public String getDeadlineTime() {
		return deadline_time;
	}

	public void setDeadlineTime(String deadline_time) {
		this.deadline_time = deadline_time;
	}
}
