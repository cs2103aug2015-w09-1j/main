package meteorite.todo;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import meteorite.todo.model.Logic;
import meteorite.todo.model.Task;
import meteorite.todo.view.TaskViewController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private static Logic logic = new Logic();
    

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        getLogic().addTask(new Task("CS2103T Do GUI"));
        getLogic().addTask(new Task("CS2103T Push code to repo"));
        getLogic().addTask(new Task("CS2010 Do Problem Set 3"));
        getLogic().addTask(new Task("ST2334 Revise for midterm"));
        getLogic().addTask(new Task("CS2106 Revise for midterm"));
        getLogic().addTask(new Task("Misc Finish web portfolio"));
    }

    /**
     * Returns the data as an observable list of Tasks. 
     * @return
     */
    public ObservableList<Task> getTaskData() {
        return getLogic().getTaskLst();
    }

    // ... THE REST OF THE CLASS ...

    public static Logic getLogic() {
		return logic;
	}

	public void setLogic(Logic logic) {
		this.logic = logic;
	}

	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ToDoApp");

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
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
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
            loader.setLocation(MainApp.class.getResource("view/TaskView.fxml"));
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
