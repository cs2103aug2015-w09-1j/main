package view;

import java.io.IOException;
import java.util.ArrayList;

import controller.Controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.EventTask;
import model.FloatingTask;
import model.Task;
import util.Storage;

public class TaskViewController {
    @FXML
    private TableView<TaskBean> taskTable;
    @FXML
    private TableColumn<TaskBean, String> taskNameColumn;
    @FXML
    private TableColumn<TaskBean, Integer> taskIdColumn;
    @FXML
    private TableColumn<TaskBean, String> startColumn;
    @FXML
    private TableColumn<TaskBean, String> endColumn;
    
    @FXML
    private TextField textField;
    
    @FXML
    private Text text = new Text();

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
    	MainApp.setTaskData(Controller.getTaskList());
    	updateColumn(taskNameColumn);
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        taskIdColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskId().asObject());
        startColumn.setCellValueFactory(cellData -> cellData.getValue().getStart());
        endColumn.setCellValueFactory(cellData -> cellData.getValue().getEnd());
        text.setText("Welcome to SilentJarvis");
        text.setFill(Color.RED);
    	text.setX(10.0f);
    	text.setY(-15.0f);
    	text.setCache(true);
    	
    	text.setFont(Font.font(null, FontWeight.BOLD, 20));
    	
    	Reflection r = new Reflection();
    	r.setFraction(0.7f);
    	 
    	text.setEffect(r);
    }
    
    private void updateColumn(TableColumn<TaskBean, String> col) {
        col.setCellFactory(new Callback<TableColumn<TaskBean, String>, 
                TableCell<TaskBean, String>>()
                {
                    @Override
                    public TableCell<TaskBean, String> call(
                            TableColumn<TaskBean, String> param)
                    {
                        return new TableCell<TaskBean, String>()
                        {
                            @Override
                            protected void updateItem(String item, boolean empty)
                            {
                            	super.updateItem(item, empty);
                                if (!empty)
                                {
                                    int currentIndex = indexProperty().getValue();
                                    StringProperty type = param
                                            .getTableView().getItems()
                                            .get(currentIndex).getType();
                                    String temp = type.toString();
                                    String taskType = temp.substring(23, temp.length() - 1); 
                                    
                                    if (taskType.equals("Completed") || taskType == "Completed") {
                                        setTextFill(Color.GRAY);
                                        setText(item);
                                    } else if(taskType.equals("Archived") || taskType == "Archived") {
                                    	setTextFill(Color.RED);
                                    	setText(item);
                                    } else {
                                        setTextFill(Color.GREEN);
                                        setText(item);
                                    }
                                    
                                }
                            }
                        };
                    }
                });
    }
    
    public void onEnter() throws IOException {
    	String input = textField.getText();
    	String feedback = feedbackMsg(input);
    	Controller.executeCMD(input);
    	MainApp.setTaskData(Controller.getTaskList());
    	text.setText(feedback);
    	updateColumn(taskNameColumn);
    	
    	textField.clear();
    }
    
    private String getFirstWord(String text) {
        if (text.indexOf(' ') > -1) { // Check if there is more than one word.
          return text.substring(0, text.indexOf(' ')); // Extract first word.
        } else {
          return text; // Text is the first word itself.
        }
    }
    
    private String feedbackMsg(String input) {
    	String firstWord = getFirstWord(input);
    	String answer = "Tasks";
    	switch(firstWord) {
    	case "add": answer = "New Task added";
    	            break;
    	case "delete": answer = "Task deleted";
    				break;
    	case "search": answer = "Search for " + input.substring(input.indexOf(" ")).trim();
    				break;
    	case "load": answer = "Loaded from the default fxml";
    				break;
    	case "save": answer = "Saved into " + Storage.getInstance().getfileName();
    				break;
    	case "display": answer = "All tasks displayed";
    				break;
    	case "edit": answer = "Task edited";
    				break;
    	case "complete": answer = "Task completed";
					break;
    	case "archive": answer = "Task archived";
					break;
    	case "show": answer = "All " + input.substring(input.indexOf(" ")).trim() + " tasks shown";
					break;
    	case "unarchive": answer = "Task unarchived";
					break;
    	case "set" : 
    		String[] temp = input.split(" ", 2);
    		String[] temp1 = temp[1].trim().split(" ", 2);
    		answer = temp1[0].trim().toUpperCase() + " set to " + temp1[1].trim();
    		break;
    	}
    	return answer;
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
