package model;
import java.util.*;

/**
 * @author Sim Thiam Guan, Calvin
 */

public abstract class Task {
	private String task_name;
	
	public Task(){
		
	}
	
	public Task(String task_name){
		this.task_name = task_name;
	}
	
	
	public String getTaskName() {
		return task_name;
	}
	public void setTaskName(String task_name) {
		this.task_name = task_name;
	}
}
