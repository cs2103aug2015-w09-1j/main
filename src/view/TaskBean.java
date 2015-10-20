package view;

import javafx.beans.property.*;
import model.DeadlineTask;
import model.EventTask;
import model.FloatingTask;
import model.Task;

/**
 * This class is a wrapper class for Task.
 * It utilises Properties so that JavaFX can display the information easily.
 * 
 * https://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.
 * html
 * 
 * @author Jason Chau
 * 
 */

public class TaskBean {
	private Task task;
	private StringProperty name;
	private StringProperty type;
	private StringProperty start_time;
	private StringProperty end_time;
	private StringProperty start_date;
	private StringProperty end_date;
	private IntegerProperty taskId;
	
	public TaskBean(Task task) {
		this.setTask(task);
		this.setName(new SimpleStringProperty(task.getTaskName()));
		this.setTaskId(new SimpleIntegerProperty(-1));
		if (task instanceof FloatingTask) { //floating
			this.setStart_time(new SimpleStringProperty(""));
			this.setEnd_time(new SimpleStringProperty(""));
			this.setStart_date(new SimpleStringProperty(""));
			this.setEnd_date(new SimpleStringProperty(""));
		} else if (task instanceof DeadlineTask) { //deadline
			this.setStart_time(new SimpleStringProperty(""));
			this.setEnd_time(new SimpleStringProperty(((DeadlineTask) task).getDeadlineTime()));
			this.setStart_date(new SimpleStringProperty(""));
			this.setEnd_date(new SimpleStringProperty(((DeadlineTask) task).getDeadlineDate()));
		} else { //event
			this.setStart_time(new SimpleStringProperty(((EventTask) task).getStartTime()));
			this.setEnd_time(new SimpleStringProperty(((EventTask) task).getEndTime()));
			this.setStart_date(new SimpleStringProperty(((EventTask) task).getStartDate()));
			this.setEnd_date(new SimpleStringProperty(((EventTask) task).getEndDate()));
		}
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public StringProperty getStart_time() {
		return start_time;
	}

	public void setStart_time(StringProperty start_time) {
		this.start_time = start_time;
	}

	public StringProperty getEnd_time() {
		return end_time;
	}

	public void setEnd_time(StringProperty end_time) {
		this.end_time = end_time;
	}

	public StringProperty getType() {
		return type;
	}

	public void setType(StringProperty type) {
		this.type = type;
	}

	public StringProperty getStart_date() {
		return start_date;
	}

	public void setStart_date(StringProperty start_date) {
		this.start_date = start_date;
	}

	public StringProperty getEnd_date() {
		return end_date;
	}

	public void setEnd_date(StringProperty end_date) {
		this.end_date = end_date;
	}

	public IntegerProperty getTaskId() {
		return taskId;
	}

	public void setTaskId(IntegerProperty taskId) {
		this.taskId = taskId;
	}
}
