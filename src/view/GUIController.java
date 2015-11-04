package view;

import java.util.ArrayList;
import controller.Controller;
import model.*;

public class GUIController {
	static ArrayList<Task> TaskList;

	protected static void showRecentList() {
		TaskList = Controller.getTodayTaskList();		
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
			executeUnarchive(command);
			break;
		case "uncomplete":
			executeUncomplete(command);
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

	private static void executeUncomplete(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeUnarchive(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeArchive(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeComplete(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeUpdate(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeAll(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeSave(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeLoad(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeSearch(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeDelete(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeShowArchived(String command) {
		// TODO Auto-generated method stub
		
	}

	private static void executeSet(String command) {
		switch (getCommandType(command, 1)) {
		case "path":
			GUIMain.showSetPath();
			break;
		case "filename":
			GUIMain.showSetFilename();
			break;
		}
	}

	private static void executeAdd(String command) {
		// TODO Auto-generated method stub
		
	}

}
