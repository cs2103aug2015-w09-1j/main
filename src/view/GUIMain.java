package view;

import java.util.ArrayList;

import controller.Controller;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DeadlineTask;
import model.EventTask;
import model.FloatingTask;
import model.Task;
import util.Storage;

public class GUIMain extends Application {
	private static int taskCount;
	private static int gridRowCount;
	private static Image deadlineImage;
	private static Image eventImage;
	private static Image floatingImage;
	private static Image completeImage;
	private static Image archivedImage;
	private static Image helpImage;
	private static Image floatingTitle;
	private static Image todayTitle;
	private static Image followingTitle;
	private static Image seeMoreTitle;
	private Image backgroundImage;
	private Image iconImage;
	final private static GUIController GUI_CONTROLLOR= GUIController.getInstance();
	protected static Stage primaryStage;
	protected static TextField userCommandBlock;
	private static GridPane TaskDisplayGrid;
	protected static ScrollPane TaskDisplayBlock;
	private static Label message;
	private static Label signal;	
	final private static Font HELP_TITLE_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 17);
	final private static Font HELP_CONTENT_FONT = Font.font("Stencil Std", FontWeight.NORMAL, FontPosture.REGULAR, 14);
	final private static Font TASK_NAME_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 17);
	final private static Font TASK_INFO_FONT = Font.font("Stencil Std", FontWeight.NORMAL, FontPosture.REGULAR, 17);
	final private Font MESSAGE_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 22);
	final private Font SIGNAL_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 14);
	final private static Color FLOATING_COLOR = Color.web("#039ed3");
	final private static Color EVENT_COLOR = Color.web("#17a42a");
	final private static Color DEADLINE_COLOR = Color.web("#b9ac1d");
	final private static Color COMPLETED_COLOR = Color.web("#898989");
	final private static Color ARCHIVED_COLOR = Color.web("#ae31f6");
	final private static Color COMMON_COLOR = Color.web("#039ed3");
	final private static Color WARNING_COLOR = Color.web("#ff0000");
	final private Color SAFE_COLOR = Color.web("#17a42a");	

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		loadImage();

		initialStage(primaryStage);

		primaryStage.show();

		buildView(primaryStage);

		showPartitionList(1);

		GUI_CONTROLLOR.getCommand();
	}

	private void loadImage() {
		backgroundImage = new Image(getClass().getResourceAsStream("back3.png"));
		iconImage = new Image(getClass().getResourceAsStream("icon.png"));
		deadlineImage = new Image(getClass().getResourceAsStream("deadlineTask.png"));
		eventImage = new Image(getClass().getResourceAsStream("eventTask.png"));
		floatingImage = new Image(getClass().getResourceAsStream("floatingTask.png"));
		completeImage = new Image(getClass().getResourceAsStream("completeTask.png"));
		archivedImage = new Image(getClass().getResourceAsStream("archivedTask.png"));
		floatingTitle = new Image(getClass().getResourceAsStream("floating.png"));
		todayTitle = new Image(getClass().getResourceAsStream("today.png"));
		followingTitle = new Image(getClass().getResourceAsStream("following.png"));
		seeMoreTitle = new Image(getClass().getResourceAsStream("seeMore.png"));
		helpImage =new Image(getClass().getResourceAsStream("help.png"));
	}

	private void initialStage(Stage primaryStage) {
		primaryStage.setTitle("Silent Jarvis");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(iconImage);
	}

	private void buildView(Stage primaryStage) {
		GridPane mainGrid = new GridPane();
		mainGrid.setHgap(5);
		mainGrid.setVgap(20);
		mainGrid.setPadding(new Insets(10, 5, 5, 25));

		ImageView mainBack = new ImageView(backgroundImage);
		Group holdBack = new Group();
		holdBack.getChildren().addAll(mainBack, mainGrid);

		StackPane SystemMessageBlock = new StackPane();
		buildSysMsgBlk(SystemMessageBlock);
		mainGrid.add(SystemMessageBlock, 0, 1);

		TaskDisplayBlock = new ScrollPane();
		buildTskDisBlk();
		mainGrid.add(TaskDisplayBlock, 0, 2);

		userCommandBlock = new TextField();
		userCommandBlock.requestFocus();
		mainGrid.add(userCommandBlock, 0, 3);

		Scene mainScene = new Scene(holdBack, 820, 660);
		primaryStage.setScene(mainScene);

		GUI_CONTROLLOR.dragStage(mainGrid, primaryStage);
	}

	private void buildSysMsgBlk(StackPane SystemMessageBlock) {
		SystemMessageBlock.setPrefSize(770, 40);

		message = new Label();
		message.setFont(MESSAGE_FONT);

		signal = new Label();
		signal.setFont(SIGNAL_FONT);
		signal.setTextFill(SAFE_COLOR);

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

	protected static void showWelcome() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Welcome to SilentJarvis! Recent tasks are listed below");

		signal.setText("");
	}

	protected static void showError() {
		message.setTextFill(WARNING_COLOR);
		message.setText("Error! Invalid or wrong format of command.");

		signal.setText("");
	}

	protected static void showToday() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Today's tasks are listed below");

		signal.setText("");
	}

	protected static void showSetFilename() {
		message.setTextFill(COMMON_COLOR);
		message.setText("New filename: " + Storage.getInstance().getfileName());

		signal.setText("Set successfully!");
	}

	protected static void showSetPath() {
		message.setTextFill(COMMON_COLOR);
		message.setText("New path: " + Storage.getInstance().getPath());

		signal.setText("Set successfully!");
	}

	protected static void showAll() {
		message.setTextFill(COMMON_COLOR);
		message.setText("All tasks are listed below");

		signal.setText("");
	}

	protected static void showArchived() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Archived tasks are listed below");

		signal.setText("");
	}

	protected static void showCompleted() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Completed tasks are listed below");

		signal.setText("");
	}

	protected static void showSearch() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Search results");

		signal.setText("");
	}

	protected static void showFloating() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Floating tasks are listed below");

		signal.setText("");
	}

	protected static void showBy() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Tasks before selected date are listed below");

		signal.setText("");
	}

	protected static void showOn() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Tasks on selected date are listed below");

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

	protected static void showClear() {
		signal.setText("All tasks selected have been cleared.");
	}

	protected static void showHelp(String string) {
		GridPane popUpGrid = new GridPane();
		popUpGrid.setVgap(15);
		popUpGrid.setPadding(new Insets(30, 30, 30, 30));

		ImageView helpBack = new ImageView(helpImage);
		Group holdBack = new Group();
		holdBack.getChildren().addAll(helpBack, popUpGrid);

		Text helpTitle = new Text("Sample format:");
		helpTitle.setFont(HELP_TITLE_FONT);
		helpTitle.setFill(FLOATING_COLOR);
		popUpGrid.add(helpTitle, 0, 0);
		
		Text helpString = new Text(string);
		helpString.setFont(HELP_CONTENT_FONT);
		helpString.setFill(FLOATING_COLOR);
		popUpGrid.add(helpString, 0, 1);

		Text helpEnd = new Text("Press <ESC> to close.");
		helpEnd.setFont(HELP_CONTENT_FONT);
		helpEnd.setFill(FLOATING_COLOR);
		popUpGrid.add(helpEnd, 0, 2);
		
		Stage secondStage = new Stage();
		secondStage.initStyle(StageStyle.UNDECORATED);
		Scene secondScene = new Scene(holdBack, 340, 340);
		secondStage.setScene(secondScene);
		secondStage.show();
		
		secondScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if (t.getCode() == KeyCode.ESCAPE) {
					secondStage.close();
				}
			}
		});

		//GUI_CONTROLLOR.escClose(popUpGrid, secondStage);
		
		GUI_CONTROLLOR.dragStage(popUpGrid, secondStage);
	}
	
	protected static void showPartitionList(int type) {
		gridRowCount = 0;
		taskCount=0;
		
		GUIMain.TaskDisplayGrid.getChildren().clear();		
		
		displayTodayTitle();
		
		showList(Controller.getTodayTaskList(),type);

		displayFollowingTitle();
		showList(Controller.getFollowingDayTaskList(),type);
		
		displayFloatingTitle();
		showList(Controller.getFloatingTaskList(),type);
	}

	protected static void showGettedList(ArrayList<Task> taskList) {
		gridRowCount=0;
		taskCount=0;
		
		GUIMain.TaskDisplayGrid.getChildren().clear();

		showList(taskList,0);
	}
	
	private static void showList(ArrayList<Task> taskList,int type){
		int taskSize=taskList.size();
		
		for (int i = 0; i < taskSize; i++) {
			Task task = taskList.get(i);
			
			taskCount++;
			
			if (task instanceof DeadlineTask) {
				displayADeadlineTask((DeadlineTask) task);
			} else if (task instanceof FloatingTask) {
				displayAFloatingTask((FloatingTask) task);
			} else if (task instanceof EventTask) {
				displayAEventTask((EventTask) task);
			}
			
			if ((taskSize>3)&&(type==1)&&(i == 2)) {
				displaySeeMore();
				taskCount+=taskSize-i-1;
				break;
			}
		}
	}
	
	private static void displayTodayTitle(){
		GridPane todayPane = new GridPane();
		todayPane.setPrefSize(760, 30);
		Group todayGroup = new Group();
		ImageView todayBack = new ImageView(GUIMain.todayTitle);
		todayGroup.getChildren().addAll(todayBack, todayPane);
		GUIMain.TaskDisplayGrid.add(todayGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private static void displayFollowingTitle(){
		GridPane FollowingPane = new GridPane();
		FollowingPane.setPrefSize(760, 30);
		Group FollowingGroup = new Group();
		ImageView FollowingBack = new ImageView(GUIMain.followingTitle);
		FollowingGroup.getChildren().addAll(FollowingBack, FollowingPane);
		GUIMain.TaskDisplayGrid.add(FollowingGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private static void displayFloatingTitle(){
		GridPane FloatingPane = new GridPane();
		FloatingPane.setPrefSize(760, 30);
		Group FloatingGroup = new Group();
		ImageView FloatingBack = new ImageView(GUIMain.floatingTitle);
		FloatingGroup.getChildren().addAll(FloatingBack, FloatingPane);
		GUIMain.TaskDisplayGrid.add(FloatingGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private static void displaySeeMore(){
		GridPane seeMorePane = new GridPane();
		seeMorePane.setPrefSize(760, 30);
		Group seeMoreGroup = new Group();
		ImageView seeMoreBack = new ImageView(GUIMain.seeMoreTitle);
		seeMoreGroup.getChildren().addAll(seeMoreBack, seeMorePane);
		GUIMain.TaskDisplayGrid.add(seeMoreGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private static void displayAEventTask(EventTask task) {
		GridPane event = new GridPane();
		event.setPrefSize(760, 30);
		event.setHgap(1);
		event.setVgap(1);
		event.setPadding(new Insets(4, 1, 1, 1));

		Group holdBack = new Group();
		ImageView eventBack;
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			eventBack = new ImageView(GUIMain.completeImage);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			eventBack = new ImageView(GUIMain.archivedImage);
		} else {
			eventBack = new ImageView(GUIMain.eventImage);
		}
		holdBack.getChildren().addAll(eventBack, event);

		GridPane nameGrid = new GridPane();
		nameGrid.setPrefSize(375, 25);	
		Text name = new Text();
		name.setText(" " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName());
		name.setFont(TASK_NAME_FONT);
		name.setFill(GUIMain.EVENT_COLOR);
		nameGrid.add(name, 0, 0);
		event.add(nameGrid, 0, 0);

		Text info = new Text();
		info.setText("  S:  " + task.getStartDate() + " " + task.getStartTime() + "    E:  " + task.getEndDate() + " "
				+ task.getEndTime());
		info.setFont(TASK_INFO_FONT);
		info.setFill(GUIMain.EVENT_COLOR);
		event.add(info, 1, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(GUIMain.COMPLETED_COLOR);
			info.setFill(GUIMain.COMPLETED_COLOR);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(GUIMain.ARCHIVED_COLOR);
			info.setFill(GUIMain.ARCHIVED_COLOR);
		}

		GUIMain.TaskDisplayGrid.add(holdBack, 0, gridRowCount);
		gridRowCount++;
	}

	private static void displayAFloatingTask(FloatingTask task) {
		GridPane floating = new GridPane();
		floating.setPrefSize(760, 30);
		floating.setHgap(1);
		floating.setVgap(1);
		floating.setPadding(new Insets(4, 1, 1, 1));

		Group holdBack = new Group();
		ImageView floatingBack;
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			floatingBack = new ImageView(GUIMain.completeImage);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			floatingBack = new ImageView(GUIMain.archivedImage);
		} else {
			floatingBack = new ImageView(GUIMain.floatingImage);
		}
		holdBack.getChildren().addAll(floatingBack, floating);

		Text name = new Text();
		name.setText(" " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName());
		name.setFont(TASK_NAME_FONT);
		name.setFill(GUIMain.FLOATING_COLOR);
		floating.add(name, 0, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(GUIMain.COMPLETED_COLOR);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(GUIMain.ARCHIVED_COLOR);
		}

		GUIMain.TaskDisplayGrid.add(holdBack, 0, gridRowCount);
		gridRowCount++;
	}

	private static void displayADeadlineTask(DeadlineTask task) {
		GridPane deadline = new GridPane();
		deadline.setPrefSize(760, 30);
		deadline.setHgap(1);
		deadline.setVgap(1);
		deadline.setPadding(new Insets(4, 1, 1, 1));

		Group back = new Group();
		ImageView deadlineBack;
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			deadlineBack = new ImageView(GUIMain.completeImage);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			deadlineBack = new ImageView(GUIMain.archivedImage);
		} else {
			deadlineBack = new ImageView(GUIMain.deadlineImage);
		}
		back.getChildren().addAll(deadlineBack, deadline);

		GridPane nameGrid = new GridPane();
		nameGrid.setPrefSize(550, 25);
		Text name = new Text();
		name.setText(" " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName());
		name.setFont(TASK_NAME_FONT);
		name.setFill(GUIMain.DEADLINE_COLOR);
		nameGrid.add(name, 0, 0);
		deadline.add(nameGrid, 0, 0);

		Text info = new Text();
		info.setText("    By: " + task.getDeadlineDate() + " " + task.getDeadlineTime());
		info.setFont(TASK_INFO_FONT);
		info.setFill(GUIMain.DEADLINE_COLOR);
		deadline.add(info, 1, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(GUIMain.COMPLETED_COLOR);
			info.setFill(GUIMain.COMPLETED_COLOR);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(GUIMain.ARCHIVED_COLOR);
			info.setFill(GUIMain.ARCHIVED_COLOR);
		}

		GUIMain.TaskDisplayGrid.add(back, 0, gridRowCount);
		gridRowCount++;
	}
}