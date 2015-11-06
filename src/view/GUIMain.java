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
	private static double xOffset = 0;
	private static double yOffset = 0;
	private Image backgroundImage;
	private Image iconImage;
	private static Image deadlineImage;
	private static Image eventImage;
	private static Image floatingImage;
	private static Image completeImage;
	private static Image archivedImage;
	private static Image helpImage;
	private static Image floating;
	private static Image today;
	private static Image following;
	private static Image seeMore;
	private static GridPane TaskDisplayGrid;
	private ScrollPane TaskDisplayBlock;
	protected static TextField userCommandBlock;
	final private Font messageFont = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 22);
	final private Font signalFont = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 14);
	final private static Font helpTitleFont = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 17);
	final private static Font helpContentFont = Font.font("Stencil Std", FontWeight.NORMAL, FontPosture.REGULAR, 14);
	final private static Font taskNameFont = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 17);
	final private static Font taskInfoFont = Font.font("Stencil Std", FontWeight.NORMAL, FontPosture.REGULAR, 17);
	final private static Color floatingColor = Color.web("#039ed3");
	final private static Color eventColor = Color.web("#17a42a");
	final private static Color deadlineColor = Color.web("#b9ac1d");
	final private static Color completeColor = Color.web("#898989");
	final private static Color archivedColor = Color.web("#ae31f6");
	final private static Color commonColor = Color.web("#039ed3");
	final private Color safeColor = Color.web("#17a42a");
	final private static Color warningColor = Color.web("#ff0000");
	private static Label message;
	private static Label signal;
	final private GUIController theGUIController= GUIController.getInstance();
	private static int taskCount;
	
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

		getCommand();
	}

	private void loadImage() {

		backgroundImage = new Image(getClass().getResourceAsStream("back3.png"));
		iconImage = new Image(getClass().getResourceAsStream("icon.png"));
		deadlineImage = new Image(getClass().getResourceAsStream("deadlineTask.png"));
		eventImage = new Image(getClass().getResourceAsStream("eventTask.png"));
		floatingImage = new Image(getClass().getResourceAsStream("floatingTask.png"));
		completeImage = new Image(getClass().getResourceAsStream("completeTask.png"));
		archivedImage = new Image(getClass().getResourceAsStream("archivedTask.png"));
		floating = new Image(getClass().getResourceAsStream("floating.png"));
		today = new Image(getClass().getResourceAsStream("today.png"));
		following = new Image(getClass().getResourceAsStream("following.png"));
		seeMore = new Image(getClass().getResourceAsStream("seeMore.png"));
		helpImage =new Image(getClass().getResourceAsStream("help.png"));
	}

	private void initialStage(Stage primaryStage) {

		primaryStage.setTitle("Silent Jarvis");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(iconImage);
	}

	private void buildView(Stage primaryStage) {

		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(20);
		grid.setPadding(new Insets(10, 5, 5, 25));

		ImageView backgroung = new ImageView(backgroundImage);
		Group back = new Group();
		back.getChildren().addAll(backgroung, grid);

		StackPane SystemMessageBlock = new StackPane();
		buildSysMsgBlk(SystemMessageBlock);
		grid.add(SystemMessageBlock, 0, 1);

		TaskDisplayBlock = new ScrollPane();
		buildTskDisBlk();
		grid.add(TaskDisplayBlock, 0, 2);

		userCommandBlock = new TextField();
		userCommandBlock.requestFocus();
		grid.add(userCommandBlock, 0, 3);

		Scene scene = new Scene(back, 820, 660);
		primaryStage.setScene(scene);

		dragStage(grid, primaryStage);
	}

	private void buildSysMsgBlk(StackPane SystemMessageBlock) {

		SystemMessageBlock.setPrefSize(770, 40);

		message = new Label();
		message.setFont(messageFont);

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

	private void getCommand() {

		userCommandBlock.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					String command = userCommandBlock.getText();

					if (!command.equals("")) {
						try {
							theGUIController.execute(command);
						} catch (Exception e) {
							showError();
						}
						userCommandBlock.clear();

					}
				}

				if (event.getCode().equals(KeyCode.UP)) {
					TaskDisplayBlock.setVvalue(TaskDisplayBlock.getVvalue() - 0.1f);
				}
				if (event.getCode().equals(KeyCode.DOWN)) {
					TaskDisplayBlock.setVvalue(TaskDisplayBlock.getVvalue() + 0.1f);
				}
			}

		});
	}

	protected static void showWelcome() {
		message.setTextFill(commonColor);
		message.setText("Welcome to SilentJarvis! Recent tasks are listed below");

		signal.setText("");
	}

	protected static void showError() {
		message.setTextFill(warningColor);
		message.setText("Error! Invalid or wrong format of command.");

		signal.setText("");
	}

	protected static void showToday() {
		message.setTextFill(commonColor);
		message.setText("Today's tasks are listed below");

		signal.setText("");
	}

	protected static void showSetFilename() {
		message.setTextFill(commonColor);
		message.setText("New filename: " + Storage.getInstance().getfileName());

		signal.setText("Set successfully!");
	}

	protected static void showSetPath() {
		message.setTextFill(commonColor);
		message.setText("New path: " + Storage.getInstance().getPath());

		signal.setText("Set successfully!");
	}

	protected static void showAll() {
		message.setTextFill(commonColor);
		message.setText("All tasks are listed below");

		signal.setText("");
	}

	protected static void showArchived() {
		message.setTextFill(commonColor);
		message.setText("Archived tasks are listed below");

		signal.setText("");
	}

	protected static void showSearch() {
		message.setTextFill(commonColor);
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

	protected static void showFloating() {
		message.setTextFill(commonColor);
		message.setText("Floating tasks are listed below");

		signal.setText("");
	}

	protected static void showClear() {
		signal.setText("All tasks selected have been cleared.");
	}

	protected static void showBy() {
		message.setTextFill(commonColor);
		message.setText("Tasks before selected date are listed below");

		signal.setText("");
	}

	protected static void showOn() {
		message.setText("Tasks on selected date are listed below");

		signal.setText("");
	}

	protected static void showError(String errorMessage) {
		message.setTextFill(warningColor);
		message.setText("Error: " + errorMessage);

		signal.setText("");
	}

	protected static void showHelp(String string) {
		GridPane popUp = new GridPane();
		popUp.setVgap(15);
		popUp.setPadding(new Insets(30, 30, 30, 30));

		ImageView backgroung = new ImageView(helpImage);
		Group back = new Group();
		back.getChildren().addAll(backgroung, popUp);

		Text helpTitle = new Text("Sample format:");
		helpTitle.setFont(helpTitleFont);
		helpTitle.setFill(floatingColor);
		popUp.add(helpTitle, 0, 0);
		
		Text helpString = new Text(string);
		helpString.setFont(helpContentFont);
		helpString.setFill(floatingColor);
		popUp.add(helpString, 0, 1);

		Text helpEnd = new Text("Press <ESC> to close.");
		helpEnd.setFont(helpContentFont);
		helpEnd.setFill(floatingColor);
		popUp.add(helpEnd, 0, 2);
		
		Stage secondWindow = new Stage();
		secondWindow.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(back, 340, 340);
		secondWindow.setScene(scene);
		secondWindow.show();
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent t) {
				if (t.getCode() == KeyCode.ESCAPE) {
					secondWindow.close();
				}
			}
		});

		popUp.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});

		popUp.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				secondWindow.setX(event.getScreenX() - xOffset);
				secondWindow.setY(event.getScreenY() - yOffset);
			}
		});
	}

	private void dragStage(GridPane grid,final Stage primaryStage) {

		grid.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});

		grid.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - xOffset);
				primaryStage.setY(event.getScreenY() - yOffset);
			}
		});
	}
	
	protected static void showPartitionList(int welcome) {
		GUIMain.TaskDisplayGrid.getChildren().clear();
		
		int count = 0;
		taskCount=0;
		GridPane todayPane = new GridPane();
		todayPane.setPrefSize(760, 30);
		Group todayGroup = new Group();
		ImageView todayBack = new ImageView(GUIMain.today);
		todayGroup.getChildren().addAll(todayBack, todayPane);
		GUIMain.TaskDisplayGrid.add(todayGroup, 0, count);
		count++;

		ArrayList<Task> TodayList = Controller.getTodayTaskList();
		int TodaySize=TodayList.size();
		for (int i = 0; i < TodaySize; i++) {
			Task task = TodayList.get(i);
			taskCount++;
			if (task instanceof DeadlineTask) {
				displayADeadlineTask(count, (DeadlineTask) task);
			} else if (task instanceof FloatingTask) {
				displayAFloatingTask(count, (FloatingTask) task);
			} else if (task instanceof EventTask) {
				displayAEventTask(count, (EventTask) task);
			}
			count++;
			if ((TodaySize>3)&&(welcome==1)&&(i == 2)) {
				GridPane seeMorePane = new GridPane();
				seeMorePane.setPrefSize(760, 30);
				Group seeMoreGroup = new Group();
				ImageView seeMoreBack = new ImageView(GUIMain.seeMore);
				seeMoreGroup.getChildren().addAll(seeMoreBack, seeMorePane);
				GUIMain.TaskDisplayGrid.add(seeMoreGroup, 0, count);
				count++;
				taskCount+=TodaySize-i-1;
				break;
			}
		}

		GridPane FollowingPane = new GridPane();
		FollowingPane.setPrefSize(760, 30);
		Group FollowingGroup = new Group();
		ImageView FollowingBack = new ImageView(GUIMain.following);
		FollowingGroup.getChildren().addAll(FollowingBack, FollowingPane);
		GUIMain.TaskDisplayGrid.add(FollowingGroup, 0, count);
		count++;

		ArrayList<Task> FollowingList = Controller.getFollowingDayTaskList();
		int FollowingSize=FollowingList.size();
		for (int i = 0; i < FollowingSize; i++) {
			Task task = FollowingList.get(i);
			taskCount++;
			if (task instanceof DeadlineTask) {
				displayADeadlineTask(count, (DeadlineTask) task);
			} else if (task instanceof FloatingTask) {
				displayAFloatingTask(count, (FloatingTask) task);
			} else if (task instanceof EventTask) {
				displayAEventTask(count, (EventTask) task);
			}
			count++;
			if ((FollowingSize>3)&&(welcome==1)&&(i == 2)) {
				GridPane seeMorePane = new GridPane();
				seeMorePane.setPrefSize(760, 30);
				Group seeMoreGroup = new Group();
				ImageView seeMoreBack = new ImageView(GUIMain.seeMore);
				seeMoreGroup.getChildren().addAll(seeMoreBack, seeMorePane);
				GUIMain.TaskDisplayGrid.add(seeMoreGroup, 0, count);
				count++;
				taskCount+=FollowingSize-i-1;
				break;
			}
		}
		
		GridPane FloatingPane = new GridPane();
		FloatingPane.setPrefSize(760, 30);
		Group FloatingGroup = new Group();
		ImageView FloatingBack = new ImageView(GUIMain.floating);
		FloatingGroup.getChildren().addAll(FloatingBack, FloatingPane);
		GUIMain.TaskDisplayGrid.add(FloatingGroup, 0, count);
		count++;
		
		ArrayList<Task> FloatingList = Controller.getFloatingTaskList();
		int FloatingSize=FloatingList.size();
		for (int i = 0; i < FloatingSize; i++) {
			Task task = FloatingList.get(i);
			taskCount++;
			if (task instanceof DeadlineTask) {
				displayADeadlineTask(count, (DeadlineTask) task);
			} else if (task instanceof FloatingTask) {
				displayAFloatingTask(count, (FloatingTask) task);
			} else if (task instanceof EventTask) {
				displayAEventTask(count, (EventTask) task);
			}
			count++;
			if ((FloatingSize>3)&&(welcome==1)&&(i == 2)) {
				GridPane seeMorePane = new GridPane();
				seeMorePane.setPrefSize(760, 30);
				Group seeMoreGroup = new Group();
				ImageView seeMoreBack = new ImageView(GUIMain.seeMore);
				seeMoreGroup.getChildren().addAll(seeMoreBack, seeMorePane);
				GUIMain.TaskDisplayGrid.add(seeMoreGroup, 0, count);
				count++;
				taskCount+=FloatingList.size()-i-1;
				break;
			}
		}
	}

	protected static void showList(ArrayList<Task> TaskList) {
		int Arraysize = TaskList.size();

		GUIMain.TaskDisplayGrid.getChildren().clear();

		taskCount=0;
		
		for (int i = 0; i < Arraysize; i++) {
			Task task = TaskList.get(i);
			taskCount++;
			if (task instanceof DeadlineTask) {
				displayADeadlineTask(i, (DeadlineTask) task);
			} else if (task instanceof FloatingTask) {
				displayAFloatingTask(i, (FloatingTask) task);
			} else if (task instanceof EventTask) {
				displayAEventTask(i, (EventTask) task);
			}
		}
	}

	private static void displayAEventTask(int i, EventTask task) {
		GridPane event = new GridPane();
		event.setPrefSize(760, 30);
		event.setHgap(1);
		event.setVgap(1);
		event.setPadding(new Insets(4, 1, 1, 1));

		Group back = new Group();
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			ImageView combackg = new ImageView(GUIMain.completeImage);
			back.getChildren().addAll(combackg, event);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			ImageView backgroung = new ImageView(GUIMain.archivedImage);
			back.getChildren().addAll(backgroung, event);
		} else {
			ImageView backgroung = new ImageView(GUIMain.eventImage);
			back.getChildren().addAll(backgroung, event);
		}
		String temp;

		GridPane nameGrid = new GridPane();
		nameGrid.setPrefSize(375, 25);
		Text name = new Text();
		temp = " " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName();
		name.setText(temp);
		name.setFont(taskNameFont);
		name.setFill(GUIMain.eventColor);
		nameGrid.add(name, 0, 0);
		event.add(nameGrid, 0, 0);

		Text info = new Text();
		temp = "  S:  " + task.getStartDate() + " " + task.getStartTime() + "    E:  " + task.getEndDate() + " "
				+ task.getEndTime();
		info.setText(temp);
		info.setFont(taskInfoFont);
		info.setFill(GUIMain.eventColor);
		event.add(info, 1, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(GUIMain.completeColor);
			info.setFill(GUIMain.completeColor);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(GUIMain.archivedColor);
			info.setFill(GUIMain.archivedColor);
		}

		GUIMain.TaskDisplayGrid.add(back, 0, i);
	}

	private static void displayAFloatingTask(int i, FloatingTask task) {
		GridPane floating = new GridPane();
		floating.setPrefSize(760, 30);
		floating.setHgap(1);
		floating.setVgap(1);
		floating.setPadding(new Insets(4, 1, 1, 1));

		Group back = new Group();
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			ImageView combackg = new ImageView(GUIMain.completeImage);
			back.getChildren().addAll(combackg, floating);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			ImageView backgroung = new ImageView(GUIMain.archivedImage);
			back.getChildren().addAll(backgroung, floating);
		} else {
			ImageView backgroung = new ImageView(GUIMain.floatingImage);
			back.getChildren().addAll(backgroung, floating);
		}

		String temp;

		Text name = new Text();
		temp = " " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName();
		name.setText(temp);
		name.setFont(taskNameFont);
		name.setFill(GUIMain.floatingColor);
		floating.add(name, 0, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(GUIMain.completeColor);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(GUIMain.archivedColor);
		}

		GUIMain.TaskDisplayGrid.add(back, 0, i);
	}

	private static void displayADeadlineTask(int i, DeadlineTask task) {
		GridPane deadline = new GridPane();
		deadline.setPrefSize(760, 30);
		deadline.setHgap(1);
		deadline.setVgap(1);
		deadline.setPadding(new Insets(4, 1, 1, 1));

		Group back = new Group();
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			ImageView combackg = new ImageView(GUIMain.completeImage);
			back.getChildren().addAll(combackg, deadline);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			ImageView backgroung = new ImageView(GUIMain.archivedImage);
			back.getChildren().addAll(backgroung, deadline);
		} else {
			ImageView backgroung = new ImageView(GUIMain.deadlineImage);
			back.getChildren().addAll(backgroung, deadline);
		}
		String temp;

		GridPane nameGrid = new GridPane();
		nameGrid.setPrefSize(550, 25);
		temp = " " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName();
		Text name = new Text(temp);
		name.setFont(taskNameFont);
		name.setFill(GUIMain.deadlineColor);
		nameGrid.add(name, 0, 0);
		deadline.add(nameGrid, 0, 0);

		temp = "    By: " + task.getDeadlineDate() + " " + task.getDeadlineTime();
		Text info = new Text(temp);
		info.setText(temp);
		info.setFont(taskInfoFont);
		info.setFill(GUIMain.deadlineColor);
		deadline.add(info, 1, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(GUIMain.completeColor);
			info.setFill(GUIMain.completeColor);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(GUIMain.archivedColor);
			info.setFill(GUIMain.archivedColor);
		}

		GUIMain.TaskDisplayGrid.add(back, 0, i);

	}
}