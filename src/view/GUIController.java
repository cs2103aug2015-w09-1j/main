package view;

import java.io.IOException;
import java.util.ArrayList;
import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.*;

public class GUIController {
	static ArrayList<Task> TaskList;

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
		StackPane event =new StackPane();
		event.setPrefSize(140, 45);
		
		
		ImageView backgroung = new ImageView(GUIMain.taskImage);
		Group back = new Group();
		back.getChildren().addAll(backgroung, event);
		
		String temp;
		
		Label name = new Label();
		temp=" "+Integer.valueOf(i+1).toString()+". "+task.getTaskName();
		name.setText(temp);
		
		event.getChildren().add(name);
		StackPane.setAlignment(name, Pos.TOP_LEFT);
		
		Label info1 = new Label();
		temp="     S: "+task.getStartDate()+" "+task.getStartTime();
		info1.setText(temp);
		event.getChildren().add(info1);
		StackPane.setAlignment(info1, Pos.CENTER_LEFT);
		
		Label info2 = new Label();
		temp="     E: "+task.getEndDate()+" "+task.getEndTime();
		info2.setText(temp);
		event.getChildren().add(info2);
		StackPane.setAlignment(info2, Pos.BOTTOM_LEFT);
		GUIMain.TaskDisplayGrid.add(event, i%2, i/2);
	}

	private static void displayAFloatingTask(int i, FloatingTask task) {
		StackPane floating =new StackPane();
		floating.setPrefSize(140, 15);
		
		ImageView backgroung = new ImageView(GUIMain.taskImage);
		Group back = new Group();
		back.getChildren().addAll(backgroung, floating);
		
		String temp;
		
		Label name = new Label();
		temp=" "+Integer.valueOf(i+1).toString()+". "+task.getTaskName();
		name.setText(temp);
		
		floating.getChildren().add(name);
		StackPane.setAlignment(name, Pos.TOP_LEFT);
		
		GUIMain.TaskDisplayGrid.add(floating, i%2, i/2);
	}

	private static void displayADeadlineTask(int i, DeadlineTask task) {
		StackPane deadline =new StackPane();
		deadline.setPrefSize(140, 45);
		
		ImageView backgroung = new ImageView(GUIMain.taskImage);
		Group back = new Group();
		back.getChildren().addAll(backgroung, deadline);
		
		String temp;
		
		Label name = new Label();
		temp=" "+Integer.valueOf(i+1).toString()+". "+task.getTaskName();
		name.setText(temp);
		
		deadline.getChildren().add(name);
		StackPane.setAlignment(name, Pos.TOP_LEFT);
		
		Label info = new Label();
		temp="     By: "+task.getDeadlineDate()+" "+task.getDeadlineTime();
		info.setText(temp);
		deadline.getChildren().add(info);
		StackPane.setAlignment(info, Pos.BOTTOM_LEFT);
		
		GUIMain.TaskDisplayGrid.add(deadline, i%2, i/2);
		
	}

	private static String getCommandType(String command, int i) {
		return command.trim().split("\\s+")[i];
	}

	public static void execute(String command) {
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

	private static void executeUnComOrArc(String command) {
		try {
			Controller.executeCMD(command);
			TaskList = Controller.getTaskList();
			showList();
			GUIMain.showAll();
			GUIMain.showUnComOrArc();;
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeArchive(String command) {
		try {
			Controller.executeCMD(command);
			TaskList = Controller.getTaskList();
			showList();
			GUIMain.showAll();
			GUIMain.showArchive();
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeComplete(String command) {
		try {
			Controller.executeCMD(command);
			TaskList = Controller.getTaskList();
			showList();
			GUIMain.showAll();
			GUIMain.showComplete();
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeUpdate(String command) {
		try {
			Controller.executeCMD(command);
			TaskList = Controller.getTaskList();
			showList();
			GUIMain.showAll();
			GUIMain.showUpdate();
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeAll(String command) {
		try {
			Controller.executeCMD(command);
			TaskList = Controller.getTaskList();
			showList();
			GUIMain.showAll();
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeSave(String command) {
		try {
			Controller.executeCMD(command);
			GUIMain.showSave();
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeLoad(String command) {
		try {
			Controller.executeCMD(command);
			TaskList = Controller.getTaskList();
			showList();
			GUIMain.showAll();
			GUIMain.showLoad();
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeSearch(String command) {
		try {
			Controller.executeCMD(command);
			TaskList = Controller.getTaskList();
			showList();
			GUIMain.showSearch();;
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeDelete(String command) {
		try {
			Controller.executeCMD(command);
			TaskList = Controller.getTaskList();
			showList();
			GUIMain.showAll();
			GUIMain.showDelete();
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeShowArchived(String command) {
		try {
			Controller.executeCMD(command);
			TaskList = Controller.getArchivedList();
			showList();
			GUIMain.showArchived();
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeSet(String command) {
		try {
			Controller.executeCMD(command);
			switch (getCommandType(command, 1)) {
			case "path":
				GUIMain.showSetPath();
				break;
			case "filename":
				GUIMain.showSetFilename();
				break;
			}
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

	private static void executeAdd(String command) {
		try {
			Controller.executeCMD(command);
			TaskList = Controller.getTaskList();
			showList();
			GUIMain.showAll();
			GUIMain.showAdd();
		} catch (IOException e) {
			GUIMain.showError();
		}
	}

}
