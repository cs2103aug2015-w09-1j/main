/*@@author Liang Yuan(A0133975W)*/
package view;

import java.util.ArrayList;
import controller.Controller;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DeadlineTask;
import model.EventTask;
import model.FloatingTask;
import model.Task;
import util.Storage;

public class GUIView{
	private int taskCount;
	private int gridRowCount;
	private Image deadlineImage;
	private Image eventImage;
	private Image floatingImage;
	private Image completeImage;
	private Image archivedImage;
	private Image helpImage;
	private Image floatingTitle;
	private Image todayTitle;
	private Image followingTitle;
	private Image seeMoreTitle;
	private Image backgroundImage;
	private Image iconImage;
	private static GUIView theOne;
	private GridPane TaskDisplayGrid;
	protected TextField userCommandBlock;
	protected ScrollPane TaskDisplayBlock;
	private Label message;
	private Label signal;	
	final private Font HELP_TITLE_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 17);
	final private Font HELP_CONTENT_FONT = Font.font("Stencil Std", FontWeight.NORMAL, FontPosture.REGULAR, 14);
	final private Font TASK_NAME_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 17);
	final private Font TASK_INFO_FONT = Font.font("Stencil Std", FontWeight.NORMAL, FontPosture.REGULAR, 17);
	final private Font MESSAGE_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 22);
	final private Font SIGNAL_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 14);
	final private Color FLOATING_COLOR = Color.web("#039ed3");
	final private Color EVENT_COLOR = Color.web("#17a42a");
	final private Color DEADLINE_COLOR = Color.web("#b9ac1d");
	final private Color COMPLETED_COLOR = Color.web("#898989");
	final private Color ARCHIVED_COLOR = Color.web("#ae31f6");
	final private Color COMMON_COLOR = Color.web("#039ed3");
	final private Color WARNING_COLOR = Color.web("#ff0000");
	final private Color SAFE_COLOR = Color.web("#17a42a");

	private GUIView(){
	}
	
	protected static GUIView getInstance() {
		if (theOne == null) {
			theOne = new GUIView();
		}
		return theOne;
	}
	
	protected void buildGUI(Stage primaryStage) throws Exception {		
		loadImage();

		initialStage(primaryStage);

		primaryStage.show();

		buildView(primaryStage);

		showPartitionList(1);
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
		userCommandBlock.setId("text-field");
		userCommandBlock.requestFocus();
		mainGrid.add(userCommandBlock, 0, 3);

		Scene mainScene = new Scene(holdBack, 820, 660);
		primaryStage.setScene(mainScene);

		GUIController.dragStage(mainGrid, primaryStage);
	}

	private void buildSysMsgBlk(StackPane SystemMessageBlock) {
		SystemMessageBlock.setPrefSize(770, 40);

		message = new Label();
		message.setId("msg");
		message.setFont(MESSAGE_FONT);

		signal = new Label();
		signal.setId("sig");
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
		TaskDisplayGrid.setId("task-grid");
		TaskDisplayGrid.setHgap(3);
		TaskDisplayGrid.setVgap(3);
		TaskDisplayGrid.setPadding(new Insets(4, 4, 4, 4));

		TaskDisplayBlock.setContent(TaskDisplayGrid);
	}

	protected void showWelcome() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Welcome to SilentJarvis! Recent tasks are listed below");

		signal.setText("");
	}

	protected void showError() {
		message.setTextFill(WARNING_COLOR);
		message.setText("Error! Invalid or wrong format of command.");

		signal.setText("");
	}

	protected void showToday() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Today's tasks are listed below");

		signal.setText("");
	}

	protected void showSetFilename() {
		message.setTextFill(COMMON_COLOR);
		message.setText("New filename: " + Storage.getInstance().getfileName());

		signal.setText("Set successfully!");
	}

	protected void showSetPath() {
		message.setTextFill(COMMON_COLOR);
		message.setText("New path: " + Storage.getInstance().getPath());

		signal.setText("Set successfully!");
	}

	protected void showAll() {
		message.setTextFill(COMMON_COLOR);
		message.setText("All tasks are listed below");

		signal.setText("");
	}

	protected void showArchived() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Archived tasks are listed below");

		signal.setText("");
	}

	protected void showCompleted() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Completed tasks are listed below");

		signal.setText("");
	}

	protected void showSearch() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Search results");

		signal.setText("");
	}

	protected void showFloating() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Floating tasks are listed below");

		signal.setText("");
	}

	protected void showBy() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Tasks before selected date are listed below");

		signal.setText("");
	}

	protected void showOn() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Tasks on selected date are listed below");

		signal.setText("");
	}
	
	protected void showSave() {
		signal.setText("Saved to " + Storage.getInstance().getPath() + Storage.getInstance().getfileName());
	}

	protected void showAdd() {
		signal.setText("New task added!");
	}

	protected void showDelete() {
		signal.setText("Task Deleted!");
	}

	protected void showUpdate() {
		signal.setText("Task Edited!");
	}

	protected void showUndo() {
		signal.setText("Undo successfully!");
	}

	protected void showLoad() {
		signal.setText("Loaded from " + Storage.getInstance().getPath() + Storage.getInstance().getfileName());
	}

	protected void showComplete() {
		signal.setText("Task complete!");
	}

	protected void showUnComOrArc() {
		signal.setText("Task recovered!");
	}

	protected void showArchive() {
		signal.setText("Task archived!");
	}

	protected void showClear() {
		signal.setText("All tasks selected have been cleared.");
	}

	protected void showHelp(String string) {
		GridPane popUpGrid = new GridPane();
		popUpGrid.setVgap(15);
		popUpGrid.setPadding(new Insets(30, 30, 30, 30));

		ImageView helpBack = new ImageView(helpImage);
		Group holdBack = new Group();
		holdBack.getChildren().addAll(helpBack, popUpGrid);

		Text helpTitle = new Text("Sample format:");
		helpTitle.setId("help-title");
		helpTitle.setFont(HELP_TITLE_FONT);
		helpTitle.setFill(FLOATING_COLOR);
		popUpGrid.add(helpTitle, 0, 0);
		
		Text helpString = new Text(string);
		helpString.setId("help-string");
		helpString.setFont(HELP_CONTENT_FONT);
		helpString.setFill(FLOATING_COLOR);
		popUpGrid.add(helpString, 0, 1);

		Text helpEnd = new Text("Press <ESC> to close.");
		helpEnd.setId("help-end");
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
		
		GUIController.escClose(secondScene,secondStage) ;
		
		GUIController.dragStage(popUpGrid, secondStage);
	}
	
	protected void showPartitionList(int type) {
		gridRowCount = 0;
		taskCount=0;
		
		TaskDisplayGrid.getChildren().clear();		
		
		displayTodayTitle();
		showList(Controller.getTodayTaskList(),type);

		displayFollowingTitle();
		showList(Controller.getFollowingDayTaskList(),type);
		
		displayFloatingTitle();
		showList(Controller.getFloatingTaskList(),type);
	}

	protected void showGettedList(ArrayList<Task> taskList) {
		gridRowCount=0;
		taskCount=0;
		
		TaskDisplayGrid.getChildren().clear();

		showList(taskList,0);
	}
	
	private void showList(ArrayList<Task> taskList,int type){
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
	
	private void displayTodayTitle(){
		GridPane todayPane = new GridPane();
		todayPane.setPrefSize(760, 30);
		Group todayGroup = new Group();
		todayGroup.setId("today-group");
		ImageView todayBack = new ImageView(todayTitle);
		todayGroup.getChildren().addAll(todayBack, todayPane);
		TaskDisplayGrid.add(todayGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private void displayFollowingTitle(){
		GridPane FollowingPane = new GridPane();
		FollowingPane.setPrefSize(760, 30);
		Group FollowingGroup = new Group();
		FollowingGroup.setId("following-group");
		ImageView FollowingBack = new ImageView(followingTitle);
		FollowingGroup.getChildren().addAll(FollowingBack, FollowingPane);
		TaskDisplayGrid.add(FollowingGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private void displayFloatingTitle(){
		GridPane FloatingPane = new GridPane();
		FloatingPane.setPrefSize(760, 30);
		Group FloatingGroup = new Group();
		FloatingGroup.setId("floating-group");
		ImageView FloatingBack = new ImageView(floatingTitle);
		FloatingGroup.getChildren().addAll(FloatingBack, FloatingPane);
		TaskDisplayGrid.add(FloatingGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private void displaySeeMore(){
		GridPane seeMorePane = new GridPane();
		seeMorePane.setPrefSize(760, 30);
		Group seeMoreGroup = new Group();
		seeMoreGroup.setId("see-group");
		ImageView seeMoreBack = new ImageView(seeMoreTitle);
		seeMoreGroup.getChildren().addAll(seeMoreBack, seeMorePane);
		TaskDisplayGrid.add(seeMoreGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private void displayAEventTask(EventTask task) {
		GridPane event = new GridPane();
		event.setId("event");
		event.setPrefSize(760, 30);
		event.setHgap(1);
		event.setVgap(1);
		event.setPadding(new Insets(4, 1, 1, 1));

		Group holdBack = new Group();
		ImageView eventBack;
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			eventBack = new ImageView(completeImage);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			eventBack = new ImageView(archivedImage);
		} else {
			eventBack = new ImageView(eventImage);
		}
		holdBack.getChildren().addAll(eventBack, event);

		GridPane nameGrid = new GridPane();
		nameGrid.setPrefSize(375, 25);	
		Text name = new Text();
		name.setId("event-name");
		name.setText(" " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName());
		name.setFont(TASK_NAME_FONT);
		name.setFill(EVENT_COLOR);
		nameGrid.add(name, 0, 0);
		event.add(nameGrid, 0, 0);

		Text info = new Text();
		info.setId("event-info");
		info.setText("  S:  " + task.getStartDate() + " " + task.getStartTime() + "    E:  " + task.getEndDate() + " "
				+ task.getEndTime());
		info.setFont(TASK_INFO_FONT);
		info.setFill(EVENT_COLOR);
		event.add(info, 1, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(COMPLETED_COLOR);
			info.setFill(COMPLETED_COLOR);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(ARCHIVED_COLOR);
			info.setFill(ARCHIVED_COLOR);
		}

		TaskDisplayGrid.add(holdBack, 0, gridRowCount);
		gridRowCount++;
	}

	private void displayAFloatingTask(FloatingTask task) {
		GridPane floating = new GridPane();
		floating.setId("floating");
		floating.setPrefSize(760, 30);
		floating.setHgap(1);
		floating.setVgap(1);
		floating.setPadding(new Insets(4, 1, 1, 1));

		Group holdBack = new Group();
		ImageView floatingBack;
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			floatingBack = new ImageView(completeImage);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			floatingBack = new ImageView(archivedImage);
		} else {
			floatingBack = new ImageView(floatingImage);
		}
		holdBack.getChildren().addAll(floatingBack, floating);

		Text name = new Text();
		name.setId("floating-name");
		name.setText(" " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName());
		name.setFont(TASK_NAME_FONT);
		name.setFill(FLOATING_COLOR);
		floating.add(name, 0, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(COMPLETED_COLOR);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(ARCHIVED_COLOR);
		}

		TaskDisplayGrid.add(holdBack, 0, gridRowCount);
		gridRowCount++;
	}

	private void displayADeadlineTask(DeadlineTask task) {
		GridPane deadline = new GridPane();
		deadline.setPrefSize(760, 30);
		deadline.setHgap(1);
		deadline.setVgap(1);
		deadline.setPadding(new Insets(4, 1, 1, 1));

		Group back = new Group();
		ImageView deadlineBack;
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			deadlineBack = new ImageView(completeImage);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			deadlineBack = new ImageView(archivedImage);
		} else {
			deadlineBack = new ImageView(deadlineImage);
		}
		back.getChildren().addAll(deadlineBack, deadline);

		GridPane nameGrid = new GridPane();
		nameGrid.setPrefSize(550, 25);
		Text name = new Text();
		name.setId("deadline-name");
		name.setText(" " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName());
		name.setFont(TASK_NAME_FONT);
		name.setFill(DEADLINE_COLOR);
		nameGrid.add(name, 0, 0);
		deadline.add(nameGrid, 0, 0);

		Text info = new Text();
		info.setId("deadline-info");
		info.setText("    By: " + task.getDeadlineDate() + " " + task.getDeadlineTime());
		info.setFont(TASK_INFO_FONT);
		info.setFill(DEADLINE_COLOR);
		deadline.add(info, 1, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(COMPLETED_COLOR);
			info.setFill(COMPLETED_COLOR);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(ARCHIVED_COLOR);
			info.setFill(ARCHIVED_COLOR);
		}

		TaskDisplayGrid.add(back, 0, gridRowCount);
		gridRowCount++;
	}
}