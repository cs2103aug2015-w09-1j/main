import java.util.*;

/**
 * @author Sim Thiam Guan, Calvin
 */

public abstract class Task {
	private String task_name;
	private String description;
	private String date;
	private String start_time;
	private String end_time;
	
	public Task(String task_name, String description, String date, String start_time, String end_time){
		this.task_name = task_name;
		this.description = description;
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTtime() {
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

	public String getTaskName() {
		return task_name;
	}
	public void setTaskName(String task_name) {
		this.task_name = task_name;
	}
}
