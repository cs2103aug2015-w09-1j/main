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
    private TableColumn<TaskBean, String> startTimeColumn;
    @FXML
    private TableColumn<TaskBean, String> endTimeColumn;
    @FXML
    private TableColumn<TaskBean, String> startDateColumn;
    @FXML
    private TableColumn<TaskBean, String> endDateColumn;
    
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
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        taskIdColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskId().asObject());
        startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getStart_time());
        endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getEnd_time());
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().getStart_date());
        endDateColumn.setCellValueFactory(cellData -> cellData.getValue().getEnd_date());
    }
    
    public void onEnter() throws IOException {
    	String input = textField.getText();
    	Controller.executeCMD(input);
    	MainApp.setTaskData(Controller.getTaskList());
    	
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
