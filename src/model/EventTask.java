package model;
import java.util.*;


/**
 * @author Sim Thiam Guan, Calvin
 */

public class EventTask extends Task {
	private String date;
	private String start_time;
	private String end_time;
	
	public EventTask(){
		super();
	}
	
	public EventTask(String task_id, String task_name, String description, String date, String start_time, String end_time){
		super(task_id, task_name, description);
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
