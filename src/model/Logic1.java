package meteorite.todo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import meteorite.todo.util.CommandParser;

public class Logic {
	private static ObservableList<Task> taskLst = FXCollections.observableArrayList();
	private CommandParser parser = new CommandParser();
	
	public void addTask(Task task) {
		Logic.taskLst.add(task);
		rearrId();
	}
	
    public static void rearrId() {
    	for (Task t:taskLst) {
    		t.setTaskId(taskLst.indexOf(t) + 1);
    	}
    }
    
    public void deleteTask(int index) {
    	Logic.taskLst.remove(index - 1);
    	rearrId();
    }
    
    public void executeCmd(String cmdType) {
    	switch(cmdType) {
    	case "add": addTask(new Task(parser.getTaskName()));
    	            break;
    	case "delete": deleteTask(parser.getId());
    	            break;
    	}
    }
    
    public void setParser(CommandParser ps) {
    	this.parser = ps;
    }
    
    public CommandParser getParser() {
    	return parser;
    }

    public ObservableList<Task> getTaskLst() {
    	return taskLst;
    }

}
