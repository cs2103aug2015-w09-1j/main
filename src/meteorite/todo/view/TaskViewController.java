package meteorite.todo.view;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import meteorite.todo.MainApp;
import meteorite.todo.model.Task;
import meteorite.todo.util.CommandParser;

public class TaskViewController {
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> descriptionColumn;
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    @FXML
    private TableColumn<Task, Integer> taskIdColumn;
    @FXML
    private TableColumn<Task, String> startTimeColumn;
    @FXML
    private TableColumn<Task, String> endTimeColumn;
    @FXML
    private TableColumn<Task, LocalDate> dueDateColumn;
    
    @FXML
    private TextField textField;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TaskViewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());
        taskIdColumn.setCellValueFactory(cellData -> cellData.getValue().taskIdProperty().asObject());
        startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());
        endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
        dueDateColumn.setCellValueFactory(cellData -> cellData.getValue().dueDateProperty());
    }
    
    public void onEnter() {
    	String input = textField.getText();
    	CommandParser cp = new CommandParser(input);
    	String cmdType = cp.getCommandType();
    	switch(cmdType) {
    		case "add": 
    			MainApp.addTask(new Task(cp.getTaskName(), cp.getDescription(), cp.getStartTime(), cp.getEndTime(), cp.getDueDate()));
    			break;
    		case "delete":
    			MainApp.taskData.remove(Integer.parseInt(cp.getIndex()) - 1);
    			MainApp.rearrId();
    			break;
    	}
    	
    	textField.clear();
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        taskTable.setItems(mainApp.getTaskData());
    }
}
