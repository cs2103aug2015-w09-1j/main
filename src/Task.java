import java.io.Serializable;
import java.util.*;

/**
 * @author Sim Thiam Guan, Calvin
 */

public abstract class Task implements Serializable{
	
	private static final long serialVersionUID = 5618347644760861490L;
	protected String task_id;
	protected String task_name;
	protected String description;
	
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
	
	@Override
	public String toString() {
		return new StringBuffer(" task_id : ").append(this.task_id)
		              .append(" task_name : ").append(this.task_name)
			    .append(" description : ").append(this.description).toString();
	}
}
