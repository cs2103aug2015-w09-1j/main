package view;

import java.io.IOException;
import java.util.ArrayList;
import controller.Controller;
import model.*;

public class GUIController {
	static ArrayList<Task> TaskList;

	protected static void showRecentList() {
		TaskList = Controller.getTodayTaskList();
		showList();
	}

	private static void showList() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	private static void executeLoad(String command) {
		// TODO Auto-generated method stub

	}

	private static void executeSearch(String command) {
		// TODO Auto-generated method stub

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
