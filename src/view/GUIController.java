package view;

import java.io.IOException;
import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUIController extends Application {
	private static double xOffset = 0;
	private static double yOffset = 0;
	final private static GUIView GUI_VIEW = GUIView.getInstance();
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage Stage) throws Exception {
		GUI_VIEW.buildGUI(Stage);
		getCommand();
	}
	
	private void getCommand() {
		GUI_VIEW.userCommandBlock.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					String command = GUI_VIEW.userCommandBlock.getText();

					if (!command.equals("")) {
						try {
							execute(command);
						} catch (Exception e) {
							GUI_VIEW.showError();
						}
					}
				}

				if (event.getCode().equals(KeyCode.UP)) {
					GUI_VIEW.TaskDisplayBlock.setVvalue(GUI_VIEW.TaskDisplayBlock.getVvalue() - 0.1f);
				}
				
				if (event.getCode().equals(KeyCode.DOWN)) {
					GUI_VIEW.TaskDisplayBlock.setVvalue(GUI_VIEW.TaskDisplayBlock.getVvalue() + 0.1f);
				}
			}
		});
	}
	
	protected static void dragStage(GridPane grid,final Stage stage) {
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
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
			}
		});
	}
	
	protected static void escClose(Scene scene,final Stage stage) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent t) {
			if (t.getCode() == KeyCode.ESCAPE) {
				stage.close();
			}
		}
	});
	}
	
	private String getCommandType(String command, int i) {
		return command.trim().split("\\s+")[i];
	}

	private void execute(String command) throws Exception {
		String commandType = getCommandType(command, 0);
		switch (commandType) {
		case "add":
			executeAdd(command);
			break;
		case "delete":
			executeDelete(command);
			break;
		case "search":
			executeSearch(command);
			break;
		case "load":
			executeLoad(command);
			break;
		case "save":
			executeSave(command);
			break;
		case "display":
			executeAll(command);
			break;
		case "edit":
			executeUpdate(command);
			break;
		case "complete":
			executeComplete(command);
			break;
		case "archive":
			executeArchive(command);
			break;
		case "unarchived":
			executeUnComOrArc(command);
			break;
		case "uncomplete":
			executeUnComOrArc(command);
			break;
		case "set":
			executeSet(command);
			break;
		case "show":
			executeShow(command);
			break;
		case "undo":
			executeUndo(command);
			break;
		case "exit":
			executeExit(command);
			break;
		case "help":
			executeHelp(command);
			break;
		case "clear":
			executeClear(command);
			break;
		default:
			GUI_VIEW.showError();
			break;
		}
	}

	private void executeClear(String command) {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showClear();
	}

	private void executeHelp(String command) {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showHelp(Controller.getHelpString());
	}

	private void executeExit(String command) {
		Controller.executeCMD(command);
	}

	private void executeUndo(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showUndo();
	}

	private void executeUnComOrArc(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showUnComOrArc();
	}

	private void executeArchive(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showArchive();
	}

	private void executeComplete(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showComplete();
	}

	private void executeUpdate(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showUpdate();
	}

	private void executeAll(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
	}

	private void executeSave(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showSave();
	}

	private void executeLoad(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showLoad();
	}

	private void executeSearch(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showGettedList(Controller.getTaskList());
		GUI_VIEW.showSearch();
	}

	private void executeDelete(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showDelete();
	}

	private void executeShow(String command) throws IOException {
		Controller.executeCMD(command);
		switch (getCommandType(command, 1)) {
		case "archived":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getArchivedList());
			GUI_VIEW.showArchived();
			break;
		case "complete":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getCompletedList());
			GUI_VIEW.showCompleted();
			break;
		case "floating":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getFloatingTaskList());
			GUI_VIEW.showFloating();
			break;
		case "by":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getTaskList());
			GUI_VIEW.showBy();
			break;
		case "on":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getTaskList());
			GUI_VIEW.showOn();
			break;
		case "today":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getTodayTaskList());
			GUI_VIEW.showToday();
			break;
		default:
			GUI_VIEW.showError();
			break;
		}
	}

	private void executeSet(String command) throws IOException {
		Controller.executeCMD(command);
		switch (getCommandType(command, 1)) {
		case "path":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showSetPath();
			break;
		case "filename":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showSetFilename();
			break;
		default:
			GUI_VIEW.showError();
			break;
		}
	}

	private void executeAdd(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showAdd();
	}
}
