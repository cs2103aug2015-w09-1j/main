package view;

import java.io.IOException;
import java.util.ArrayList;
import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.*;

public class GUIController {
	static ArrayList<Task> TaskList;
	private static Font taskNameFont = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 17);
	private static Font taskInfoFont = Font.font("Stencil Std", FontWeight.NORMAL, FontPosture.REGULAR, 17);

	protected static void showRecentList() {
		TaskList = Controller.getTaskList();
		showList();
	}

	private static void showList() {
		int Arraysize = TaskList.size();

		GUIMain.TaskDisplayGrid.getChildren().clear();

		for (int i = 0; i < Arraysize; i++) {
			Task task = TaskList.get(i);

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
		} else if(task.getTaskType().equals("Archived") || task.getTaskType() == "Archived"){
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
		temp = " " + Integer.valueOf(i + 1).toString() + ". " + task.getTaskName();
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
		}else if(task.getTaskType().equals("Archived") || task.getTaskType() == "Archived"){
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
		} else if(task.getTaskType().equals("Archived") || task.getTaskType() == "Archived"){
			ImageView backgroung = new ImageView(GUIMain.archivedImage);
			back.getChildren().addAll(backgroung, floating);
		} else {
			ImageView backgroung = new ImageView(GUIMain.floatingImage);
			back.getChildren().addAll(backgroung, floating);
		}

		String temp;

		Text name = new Text();
		temp = " " + Integer.valueOf(i + 1).toString() + ". " + task.getTaskName();
		name.setText(temp);
		name.setFont(taskNameFont);
		name.setFill(GUIMain.floatingColor);
		floating.add(name, 0, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(GUIMain.completeColor);
		}else if(task.getTaskType().equals("Archived") || task.getTaskType() == "Archived"){
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
		} else if(task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			ImageView backgroung = new ImageView(GUIMain.archivedImage);
			back.getChildren().addAll(backgroung, deadline);
		} else {
			ImageView backgroung = new ImageView(GUIMain.deadlineImage);
			back.getChildren().addAll(backgroung, deadline);
		}
		String temp;

		GridPane nameGrid = new GridPane();
		nameGrid.setPrefSize(550, 25);
		temp = " " + Integer.valueOf(i + 1).toString() + ". " + task.getTaskName();
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
		}else if(task.getTaskType().equals("Archived") || task.getTaskType() == "Archived"){
			name.setFill(GUIMain.archivedColor);
			info.setFill(GUIMain.archivedColor);
		}

		GUIMain.TaskDisplayGrid.add(back, 0, i);

	}

	private static String getCommandType(String command, int i) {
		return command.trim().split("\\s+")[i];
	}

	public static void execute(String command) throws Exception {
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
			GUIMain.showError();
			break;
		}
	}

	private static void executeClear(String command) {
		Controller.executeCMD(command);	
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showClear();
	}

	private static void executeHelp(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeExit(String command) {
		Controller.executeCMD(command);	
	}

	private static void executeUndo(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showUndo();
	}

	private static void executeUnComOrArc(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showUnComOrArc();
	}

	private static void executeArchive(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showArchive();
	}

	private static void executeComplete(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showComplete();
	}

	private static void executeUpdate(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showUpdate();
	}

	private static void executeAll(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
	}

	private static void executeSave(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.showSave();
	}

	private static void executeLoad(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showLoad();
	}

	private static void executeSearch(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showSearch();
	}

	private static void executeDelete(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showDelete();
	}

	private static void executeShow(String command) throws IOException {
		Controller.executeCMD(command);
		switch (getCommandType(command, 1)) {
		case "archived":
			TaskList = Controller.getArchivedList();
			showList();
			GUIMain.showArchived();
			break;
		case "floating":
			TaskList = Controller.getFloatingTaskList();
			showList();
			GUIMain.showFloating();
			break;
		}
	}

	private static void executeSet(String command) throws IOException {
		Controller.executeCMD(command);
		switch (getCommandType(command, 1)) {
		case "path":
			GUIMain.showSetPath();
			break;
		case "filename":
			GUIMain.showSetFilename();
			break;
		}
	}

	private static void executeAdd(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showAdd();
	}

}
