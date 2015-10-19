package model;



import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import meteorite.todo.util.DateUtil;

/**
 * Model class for a Task.
 *
 * @author Jason
 */
public class Task1 {
	
	private final StringProperty taskName;
    private final ObjectProperty<LocalDate> endDate;
    private final IntegerProperty taskid;
    private final ObjectProperty<LocalDate> startDate;
    private final StringProperty startTime;
    private final StringProperty endTime;

    /**
     * Default constructor.
     */
    public Task() {
        this(null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param taskName
     * @param description
     */
    public Task(String taskName) {
    	this.taskName = new SimpleStringProperty(taskName);

        // Some initial dummy data, just for convenient testing.
        this.startTime = new SimpleStringProperty("");
        this.taskid = new SimpleIntegerProperty(-1);
        this.endTime = new SimpleStringProperty("");
        this.startDate = new SimpleObjectProperty<LocalDate>();
        this.endDate = new SimpleObjectProperty<LocalDate>();
    }
    
    /**
     * Constructor with all attributes 
     *
     */
    public Task (String taskName, String startTime, String endTime, String startDate, String endDate) {
    	this.taskName = new SimpleStringProperty(taskName);
    	this.endDate = new SimpleObjectProperty<LocalDate>(DateUtil.parse(endDate));
    	this.startTime = new SimpleStringProperty(startTime);
    	this.endTime = new SimpleStringProperty(endTime);
    	this.startDate = new SimpleObjectProperty<LocalDate>(DateUtil.parse(startDate));
    	this.taskid = new SimpleIntegerProperty(-1);
    }

    public LocalDate getEndDate() {
        return endDate.get();
    }

    public void setDescription(LocalDate endDate) {
        this.endDate.set(endDate);
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }
    
    public String getTaskName() {
        return taskName.get();
    }

    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    public StringProperty taskNameProperty() {
        return taskName;
    }

    public String getStartTime() {
        return startTime.get();
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }

    public String getEndTime() {
        return endTime.get();
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }

    public int getTaskId() {
        return taskid.get();
    }

    public void setTaskId(int taskId) {
        this.taskid.set(taskId);
    }

    public IntegerProperty taskIdProperty() {
        return taskid;
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }
}