package meteorite.todo.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Task.
 *
 * @author Jason
 */
public class Task {
	
	private final StringProperty taskName;
    private final StringProperty description;
    private final IntegerProperty taskid;
    private final ObjectProperty<LocalDate> dueDate;
    private final StringProperty startTime;
    private final StringProperty endTime;

    /**
     * Default constructor.
     */
    public Task() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     */
    public Task(String taskName, String description) {
    	this.taskName = new SimpleStringProperty(taskName);
        this.description = new SimpleStringProperty(description);

        // Some initial dummy data, just for convenient testing.
        this.startTime = new SimpleStringProperty("start time");
        this.taskid = new SimpleIntegerProperty(1234);
        this.endTime = new SimpleStringProperty("end time");
        this.dueDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
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

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }
}
