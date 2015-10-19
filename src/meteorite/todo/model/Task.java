package meteorite.todo.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
<<<<<<< HEAD
=======
import meteorite.todo.util.DateUtil;
>>>>>>> feature-UI

/**
 * Model class for a Task.
 *
 * @author Jason
 */
public class Task {
	
	private final StringProperty taskName;
<<<<<<< HEAD
    private final ObjectProperty<LocalDate> startDate;
    private final IntegerProperty taskid;
    private final ObjectProperty<LocalDate> endDate;
=======
    private final StringProperty description;
    private final IntegerProperty taskid;
    private final ObjectProperty<LocalDate> dueDate;
>>>>>>> feature-UI
    private final StringProperty startTime;
    private final StringProperty endTime;

    /**
     * Default constructor.
     */
    public Task() {
<<<<<<< HEAD
        this(null);
=======
        this(null, null);
>>>>>>> feature-UI
    }

    /**
     * Constructor with some initial data.
     * 
<<<<<<< HEAD
     * @param firstName
     */
    public Task(String taskName) {
    	this.taskName = new SimpleStringProperty(taskName);
=======
     * @param taskName
     * @param description
     */
    public Task(String taskName, String description) {
    	this.taskName = new SimpleStringProperty(taskName);
        this.description = new SimpleStringProperty(description);
>>>>>>> feature-UI

        // Some initial dummy data, just for convenient testing.
        this.startTime = new SimpleStringProperty("start time");
        this.taskid = new SimpleIntegerProperty(-1);
        this.endTime = new SimpleStringProperty("end time");
<<<<<<< HEAD
        this.startDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.endDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
    }

    public LocalDate getEndDate() {
        return endDate.get();
    }

    public void setEndDate(LocalDate date) {
        this.endDate.set(date);
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
=======
        this.dueDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }
    
    /**
     * Constructor with all attributes 
     *
     */
    public Task (String taskName, String description, String startTime, String endTime, String dueDate) {
    	this.taskName = new SimpleStringProperty(taskName);
    	this.description = new SimpleStringProperty(description);
    	this.startTime = new SimpleStringProperty(startTime);
    	this.endTime = new SimpleStringProperty(endTime);
    	this.dueDate = new SimpleObjectProperty<LocalDate>(DateUtil.parse(dueDate));
    	this.taskid = new SimpleIntegerProperty(-1);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
>>>>>>> feature-UI
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

<<<<<<< HEAD
    public LocalDate getStartDate() {
        return startDate.get();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
=======
    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
>>>>>>> feature-UI
    }
}
