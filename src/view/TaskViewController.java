package view;

import java.io.IOException;
import java.util.ArrayList;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.EventTask;
import model.FloatingTask;
import model.Task;

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
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        taskIdColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskId().asObject());
        startColumn.setCellValueFactory(cellData -> cellData.getValue().getStart());
        endColumn.setCellValueFactory(cellData -> cellData.getValue().getEnd());
        text.setFill(Color.RED);
    	text.setX(10.0f);
    	text.setY(-15.0f);
    	text.setCache(true);
    	
    	text.setFont(Font.font(null, FontWeight.BOLD, 20));
    	
    	Reflection r = new Reflection();
    	r.setFraction(0.7f);
    	 
    	text.setEffect(r);
    }
    
    public void onEnter() throws IOException {
    	String input = textField.getText();
    	String feedback = feedbackMsg(input);
    	Controller.executeCMD(input);
    	MainApp.setTaskData(Controller.getTaskList());
    	text.setText(feedback);    	
    	
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
    	String answer = "";
    	switch(firstWord) {
    	case "add": answer = "New Task added";
    	            break;
    	case "delete": answer = "Task delete";
    				break;
    	case "search": answer = "Search for " + input.substring(input.indexOf(" "));
    				break;
    	case "load": answer = "Loaded from the default fxml";
    				break;
    	case "save": answer = "Saved into silentjarvis.fxml";
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
