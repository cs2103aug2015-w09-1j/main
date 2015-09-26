import java.util.*;
/**
 * @author Sim Thiam Guan, Calvin
 */
public class DeadlineTask extends Task {
	private static final long serialVersionUID = -5756742526618058323L;
	private String deadline_date;
	private String deadline_time;
	
	public DeadlineTask(){
		
	}
	
	public DeadlineTask(String task_id, String task_name, String description, String deadline_date, String deadline_time){
		super(task_id, task_name, description);
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
	
	@Override
	public String toString() {
		return new StringBuffer(" task_id : ").append(this.task_id)
		              .append(" task_name : ").append(this.task_name)
			    .append(" description : ").append(this.description)
			  .append(" deadline_date : ").append(this.deadline_date)
			  .append(" deadline_time : ").append(this.deadline_time).toString();
	}
}
