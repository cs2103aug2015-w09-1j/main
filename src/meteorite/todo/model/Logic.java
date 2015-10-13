package meteorite.todo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Logic {
	private static ObservableList<Task> taskLst = FXCollections.observableArrayList();
	
	public void addTask(String taskName, String startTime, String endTime, String startDate, String endDate) {
		Task e = new Task(taskName, startTime, endTime, startDate, endDate);
		Logic.taskLst.add(e);
		rearrId();
	}
	
    public static void rearrId() {
    	for (Task t:taskLst) {
    		t.setTaskId(taskLst.indexOf(t) + 1);
    	}
    }

}
