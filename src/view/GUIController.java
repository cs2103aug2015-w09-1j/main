package view;

import java.util.ArrayList;

import controller.Controller;
import model.*;

public class GUIController {
	static ArrayList<Task> TaskList;

	public static void start() {
		showRecentList();
	}

	private static void showRecentList() {
		TaskList = Controller.getTodayTaskList();
		
	}

	private void showFeedback(String command) {
		String commandType = getCommandType(command, 0);
		switch (commandType) {
		case "add":
			GUIMain.showAdd();
			break;
		case "delete":
			GUIMain.showDelete();
			break;
		case "search":
			GUIMain.showSearch();
			break;
		case "load":
			GUIMain.showLoad();
			break;
		case "save":
			GUIMain.showSave();
			break;
		case "display":
			GUIMain.showAll();
			break;
		case "edit":
			GUIMain.showUpdate();
			break;
		case "complete":
			GUIMain.showComplete();
			break;
		case "archive":
			GUIMain.showArchive();
			break;
		case "unarchive":
			GUIMain.showUnComOrArc();
			break;
		case "uncomplete":
			GUIMain.showUnComOrArc();
			break;
		case "set":
			switch (getCommandType(command, 1)) {
			case "path":
				GUIMain.showSetPath();
				break;
			case "filename":
				GUIMain.showSetFilename();
				break;
			}
			break;
		case "show":
			GUIMain.showArchived();
		default:
			break;
		}
	}

	private String getCommandType(String command, int i) {
		return command.trim().split("\\s+")[i];
	}

}
