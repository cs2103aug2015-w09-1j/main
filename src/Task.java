import java.util.*;

/**
 * @author Sim Thiam Guan, Calvin
 */

public abstract class Task {
	private String task_id;
	private String task_name;
	private String description;
	
	public Task(){
		
	}
	
	public Task(String task_id, String task_name, String description){
		this.task_id = task_id;
		this.task_name = task_name;
		this.description = description;
	}
	
	
	public String getTaskId() {
		return task_id;
	}

	public void setTaskId(String task_id) {
		this.task_id = task_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTaskName() {
		return task_name;
	}
	public void setTaskName(String task_name) {
		this.task_name = task_name;
	}
}
