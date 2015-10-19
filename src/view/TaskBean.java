package view;

import javafx.beans.property.*;
import model.Task;

/**
 * This class is a wrapper class for Task.
 * It utilises Properties so that JavaFX can display the information easily.
 * 
 * https://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.
 * htm
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
	
	public TaskBean(Task task) {
		this.task = task;
		this.name = new SimpleStringProperty(task.getTaskName());
	}
}
