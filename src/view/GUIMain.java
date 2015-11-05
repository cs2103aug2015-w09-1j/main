package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Storage;

public class GUIMain extends Application {
	protected static String command;
	protected static String returnCommand;
	protected static double xOffset = 0;
	protected static double yOffset = 0;
	protected static Image backgroundImage;
	protected static Image iconImage;
	protected static Image deadlineImage;
	protected static Image eventImage;
	protected static Image floatingImage;
	protected static Image completeImage;
	protected static Image archivedImage;
	protected static Image upcomingImage;
	protected static String title = "Silent Jarvis";
	protected static GridPane grid;
	protected static GridPane TaskDisplayGrid;
	protected static StackPane SystemMessageBlock;
	protected static ScrollPane TaskDisplayBlock;
	protected static TextField userCommandBlock;
	private static Font messageFont = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 22);
	private static Font signalFont = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 14);
	protected static Color floatingColor = Color.web("#039ed3");
	protected static Color eventColor = Color.web("#17a42a");
	protected static Color deadlineColor = Color.web("#b9ac1d");
	protected static Color completeColor = Color.web("#898989");
	protected static Color archivedColor = Color.web("#ae31f6");
	protected static Color upcomingColor = Color.web("#ff0000");
	private static Color commonColor = Color.web("#039ed3");
	private static Color safeColor = Color.web("#94df11");
	private static Label message;
	private static Label signal;

	public static void main(String args[]) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		loadImage();

		initialStage(primaryStage);

		primaryStage.show();

		buildView(primaryStage);
		
		GUIController.showRecentList();;

		getCommand();
	}

	private void loadImage() {

		backgroundImage = new Image(getClass().getResourceAsStream("back3.png"));
		iconImage = new Image(getClass().getResourceAsStream("icon.png"));
		deadlineImage = new Image(getClass().getResourceAsStream("deadlineTask.png"));;
		eventImage = new Image(getClass().getResourceAsStream("eventTask.png"));;
		floatingImage = new Image(getClass().getResourceAsStream("floatingTask.png"));;
		completeImage = new Image(getClass().getResourceAsStream("completeTask.png"));;
		archivedImage = new Image(getClass().getResourceAsStream("archivedTask.png"));;
		upcomingImage = new Image(getClass().getResourceAsStream("upcomingTask.png"));;
	}

	private void initialStage(Stage primaryStage) {

		primaryStage.setTitle(title);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(iconImage);
	}

	private void buildView(Stage primaryStage) {

		grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(20);
		grid.setPadding(new Insets(10, 5, 5, 25));

		ImageView backgroung = new ImageView(backgroundImage);
		Group back = new Group();
		back.getChildren().addAll(backgroung, grid);

		SystemMessageBlock = new StackPane();
		buildSysMsgBlk();
		grid.add(SystemMessageBlock, 0, 1);

		TaskDisplayBlock = new ScrollPane();
		buildTskDisBlk();
		grid.add(TaskDisplayBlock, 0, 2);

		userCommandBlock = new TextField();
		userCommandBlock.requestFocus();
		grid.add(userCommandBlock, 0, 3);

		DragController.dragStage(grid, primaryStage);

		Scene scene = new Scene(back, 820, 660);
		primaryStage.setScene(scene);
	}

	private void buildSysMsgBlk() {

		SystemMessageBlock.setPrefSize(770, 40);

		message = new Label();
		message.setFont(messageFont);
		message.setTextFill(commonColor);

		signal = new Label();
		signal.setFont(signalFont);
		signal.setTextFill(safeColor);

		SystemMessageBlock.getChildren().add(message);
		StackPane.setAlignment(message, Pos.TOP_LEFT);

		SystemMessageBlock.getChildren().add(signal);
		StackPane.setAlignment(signal, Pos.BOTTOM_RIGHT);

		showWelcome();
	}

	private void buildTskDisBlk() {

		TaskDisplayBlock.setPrefSize(770, 500);
		TaskDisplayBlock.setOpacity(0.9);
		TaskDisplayBlock.setHbarPolicy(ScrollBarPolicy.NEVER);

	    TaskDisplayGrid = new GridPane();
	    TaskDisplayGrid.setHgap(3);
	    TaskDisplayGrid.setVgap(3);
	    TaskDisplayGrid.setPadding(new Insets(4, 4, 4, 4));
	    
	    TaskDisplayBlock.setContent(TaskDisplayGrid);
	}

	private static void getCommand() {
		userCommandBlock.setOnKeyPressed(new EventHandler<KeyEvent>(){
	    	   @Override
	    	   public void handle(KeyEvent event) {
	    		   if(event.getCode().equals(KeyCode.ENTER)){
	    			   command = userCommandBlock.getText();
	    			   
	    			   if(!command.equals("")){
							try {
								GUIController.execute(command);
								userCommandBlock.clear();
							} catch (Exception e) {
								showError();
							}
						
	    			   }
	    		   }

	    		   if(event.getCode().equals(KeyCode.UP)){
	    			   TaskDisplayBlock.setVvalue(TaskDisplayBlock.getVvalue() - 0.3f);
	    		   }
	    		   if(event.getCode().equals(KeyCode.DOWN)){
	    			   TaskDisplayBlock.setVvalue(TaskDisplayBlock.getVvalue() + 0.3f);
	    		   }
	    	   }

	});}

	private void showWelcome() {

		message.setText("Welcome to SilentJarvis! Recent tasks are listed below");

		signal.setText("");
	}

	protected static void showError() {
		message.setText("Error! Check your command format.");
		
		signal.setText("");
	}

	protected static void showToday() {
		message.setText("Today's tasks are listed below");

		signal.setText("");
	}

	protected static void showSetFilename() {
		message.setText("New filename: "+Storage.getInstance().getfileName());

		signal.setText("Set successfully!");
	}

	protected static void showSetPath() {
		message.setText("New path: "+Storage.getInstance().getPath());

		signal.setText("Set successfully!");
	}

	protected static void showAll() {
		message.setText("All tasks are listed below");

		signal.setText("");
	}

	protected static void showArchived() {
		message.setText("Archived tasks are listed below");

		signal.setText("");
	}

	protected static void showSearch() {
		message.setText("Search results");

		signal.setText("");
	}

	protected static void showSave() {
		signal.setText("Saved to " + Storage.getInstance().getPath() + Storage.getInstance().getfileName());
	}

	protected static void showAdd() {
		signal.setText("New task added!");
	}

	protected static void showDelete() {
		signal.setText("Task Deleted!");
	}

	protected static void showUpdate() {
		signal.setText("Task Edited!");
	}

	protected static void showUndo() {
		signal.setText("Undo successfully!");
	}

	protected static void showLoad() {
		signal.setText("Loaded from " + Storage.getInstance().getPath() + Storage.getInstance().getfileName());
	}

	protected static void showComplete() {
		signal.setText("Task complete!");
	}

	protected static void showUnComOrArc() {
		signal.setText("Task recoverd!");
	}

	protected static void showArchive() {
		signal.setText("Task archived!");
	}
}