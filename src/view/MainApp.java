package view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Task;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private static ObservableList<TaskBean> taskList = FXCollections.observableArrayList();

    

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data

    }

    /**
     * Returns the data as an observable list of Tasks. 
     * @return
     */
    public ObservableList<TaskBean> getTaskData() {
        return taskList;
    }
    
    public static void setTaskData(ArrayList<Task> data) {
        taskList.clear();
    	for (Task t:data) {
        	taskList.add(new TaskBean(t));
        }
        rearrId();
    }
    
    private static void rearrId() {
    	for(int i = 0;i<taskList.size();i++) {
    		taskList.get(i).setTaskId(new SimpleIntegerProperty(i + 1));
    	}
    }



	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SilentJarvis");

        initRootLayout();

        showTaskOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the task overview inside the root layout.
     */
    public void showTaskOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TaskView.fxml"));
            AnchorPane taskOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(taskOverview);
            
            //Give the controller access to the main app
            TaskViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
