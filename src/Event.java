import java.util.*;


/**
 * @author Sim Thiam Guan, Calvin
 */

public class Event extends Task {
	private String description;
	private Date start;
	private Date end;
	
	public Event(int id, String task_name, String description, Date start, Date end){
		super(id, task_name);
		this.description = description;
		this.start = start;
		this.end = end;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
