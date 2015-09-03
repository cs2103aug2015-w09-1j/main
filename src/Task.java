import java.util.*;

/**
 * @author Sim Thiam Guan, Calvin
 */

public abstract class Task {
	private int id;
	private String task_name;
	
	public Task(int id, String task_name){
		this.id = id;
		this.task_name = task_name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
}
