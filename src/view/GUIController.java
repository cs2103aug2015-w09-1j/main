package view;

import java.io.IOException;
import java.util.ArrayList;
import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.*;

public class GUIController {
	static ArrayList<Task> TaskList;
	private static Font taskNameFont = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 12);
	private static Font taskInfoFont = Font.font("Stencil Std", FontWeight.NORMAL, FontPosture.REGULAR, 12);
	
	protected static void showRecentList() {
		TaskList = Controller.getTaskList();
		showList();
	}

	private static void showList() {
		int Arraysize=TaskList.size();
		
		GUIMain.TaskDisplayGrid.getChildren().clear();
		
		for(int i=0;i<Arraysize;i++){
			Task task=TaskList.get(i);
			
			if(task instanceof DeadlineTask){
				displayADeadlineTask(i,(DeadlineTask)task);
			}else if(task instanceof FloatingTask){
				displayAFloatingTask(i,(FloatingTask)task);
			}else if(task instanceof EventTask){
				displayAEventTask(i,(EventTask)task);
			}
		}
	}

	private static void displayAEventTask(int i, EventTask task) {
		GridPane event =new GridPane();
		event.setPrefSize(140, 45);
		event.setHgap(1);
		event.setVgap(1);
		event.setPadding(new Insets(1, 1, 1, 1));

		ImageView backgroung = new ImageView(GUIMain.taskImage);
		Group back = new Group();
		back.getChildren().addAll(backgroung, event);
		
		String temp;
		
		Text name = new Text();
		temp=" "+Integer.valueOf(i+1).toString()+". "+task.getTaskName();
		name.setText(temp);
		name.setFont(taskNameFont);
		name.setFill(GUIMain.commonColor);
		event.add(name, 0, 0);
		
		temp="    S: "+task.getStartDate()+" "+task.getStartTime();
		Text info1 = new Text(temp);
		info1.setText(temp);
		info1.setFont(taskInfoFont);
		info1.setFill(GUIMain.commonColor);
		event.add(info1, 0, 1);
		
		temp="    E: "+task.getEndDate()+" "+task.getEndTime();
		Text info2 = new Text(temp);
		info2.setText(temp);
		info2.setFont(taskInfoFont);
		info2.setFill(GUIMain.commonColor);
		event.add(info2, 0, 2);
		
		GUIMain.TaskDisplayGrid.add(back, i%2, i/2);
	}

	private static void displayAFloatingTask(int i, FloatingTask task) {
		GridPane floating =new GridPane();
		floating.setPrefSize(140, 15);
		floating.setHgap(1);
		floating.setVgap(1);
		floating.setPadding(new Insets(2, 2, 2, 2));
		
		ImageView backgroung = new ImageView(GUIMain.taskImage);
		Group back = new Group();
		back.getChildren().addAll(backgroung, floating);
		
		String temp;
		
		temp=" "+Integer.valueOf(i+1).toString()+". "+task.getTaskName();
		Text name = new Text(temp);
		name.setFont(taskNameFont);
		name.setFill(GUIMain.commonColor);
		floating.add(name, 0, 0);
		
		GUIMain.TaskDisplayGrid.add(back, i%2, i/2);
	}

	private static void displayADeadlineTask(int i, DeadlineTask task) {
		GridPane deadline =new GridPane();
		deadline.setPrefSize(140, 45);
		deadline.setHgap(1);
		deadline.setVgap(1);
		deadline.setPadding(new Insets(2, 2, 2, 2));
		
		
		ImageView backgroung = new ImageView(GUIMain.taskImage);
		Group back = new Group();
		back.getChildren().addAll(backgroung, deadline);
		
		String temp;
		
		temp=" "+Integer.valueOf(i+1).toString()+". "+task.getTaskName();
		Text name = new Text(temp);
		name.setFont(taskNameFont);
		name.setFill(GUIMain.commonColor);
		deadline.add(name, 0, 0);
		
		temp="    By: "+task.getDeadlineDate()+" "+task.getDeadlineTime();
		Text info = new Text(temp);
		info.setText(temp);
		info.setFont(taskInfoFont);
		info.setFill(GUIMain.commonColor);
		deadline.add(info, 0, 1);
		
		GUIMain.TaskDisplayGrid.add(back, i%2, i/2);
		
	}

	private static String getCommandType(String command, int i) {
		return command.trim().split("\\s+")[i];
	}

	public static void execute(String command) throws IOException {
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
		case "unarchive":
			executeUnComOrArc(command);
			break;
		case "uncomplete":
			executeUnComOrArc(command);
			break;
		case "set":
			executeSet(command);
			break;
		case "show":
			executeShowArchived(command);
			break;
		default:
			break;
		}
	}

	private static void executeUnComOrArc(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showUnComOrArc();;
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
		GUIMain.showSearch();;
	}

	private static void executeDelete(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getTaskList();
		showList();
		GUIMain.showAll();
		GUIMain.showDelete();
	}

	private static void executeShowArchived(String command) throws IOException {
		Controller.executeCMD(command);
		TaskList = Controller.getArchivedList();
		showList();
		GUIMain.showArchived();
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
