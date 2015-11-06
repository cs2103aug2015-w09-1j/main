package view;

import java.io.IOException;
import controller.Controller;

public class GUIController {

	protected static GUIController theOne = null;

	protected static GUIController getInstance() {
		if (theOne == null) {
			theOne = new GUIController();
		}
		return theOne;
	}

	private String getCommandType(String command, int i) {
		return command.trim().split("\\s+")[i];
	}

	protected void execute(String command) throws Exception {
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

	private void executeClear(String command) {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showPartitionList(0);
		GUIMain.showAll();
		GUIMain.showClear();
	}

	private void executeHelp(String command) {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showHelp(Controller.getHelpString());
	}

	private void executeExit(String command) {
		Controller.executeCMD(command);
	}

	private void executeUndo(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showPartitionList(0);
		GUIMain.showAll();
		GUIMain.showUndo();
	}

	private void executeUnComOrArc(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showPartitionList(0);
		GUIMain.showAll();
		GUIMain.showUnComOrArc();
	}

	private void executeArchive(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showPartitionList(0);
		GUIMain.showAll();
		GUIMain.showArchive();
	}

	private void executeComplete(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showPartitionList(0);
		GUIMain.showAll();
		GUIMain.showComplete();
	}

	private void executeUpdate(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showPartitionList(0);
		GUIMain.showAll();
		GUIMain.showUpdate();
	}

	private void executeAll(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showPartitionList(0);
		GUIMain.showAll();
	}

	private void executeSave(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showSave();
	}

	private void executeLoad(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showPartitionList(0);
		GUIMain.showAll();
		GUIMain.showLoad();
	}

	private void executeSearch(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showGettedList(Controller.getTaskList());
		GUIMain.showSearch();
	}

	private void executeDelete(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showPartitionList(0);
		GUIMain.showAll();
		GUIMain.showDelete();
	}

	private void executeShow(String command) throws IOException {
		Controller.executeCMD(command);
		switch (getCommandType(command, 1)) {
		case "archived":
			GUIMain.userCommandBlock.clear();
			GUIMain.showGettedList(Controller.getArchivedList());
			GUIMain.showArchived();
			break;
		case "floating":
			GUIMain.userCommandBlock.clear();
			GUIMain.showGettedList(Controller.getFloatingTaskList());
			GUIMain.showFloating();
			break;
		case "by":
			GUIMain.userCommandBlock.clear();
			GUIMain.showGettedList(Controller.getTaskList());
			GUIMain.showBy();
			break;
		case "on":
			GUIMain.userCommandBlock.clear();
			GUIMain.showGettedList(Controller.getTaskList());
			GUIMain.showOn();
			break;
		case "today":
			GUIMain.userCommandBlock.clear();
			GUIMain.showGettedList(Controller.getTodayTaskList());
			GUIMain.showToday();
			break;
		default:
			GUIMain.showError();
			break;
		}
	}

	private void executeSet(String command) throws IOException {
		Controller.executeCMD(command);
		switch (getCommandType(command, 1)) {
		case "path":
			GUIMain.userCommandBlock.clear();
			GUIMain.showSetPath();
			break;
		case "filename":
			GUIMain.userCommandBlock.clear();
			GUIMain.showSetFilename();
			break;
		default:
			GUIMain.showError();
			break;
		}
	}

	private void executeAdd(String command) throws IOException {
		Controller.executeCMD(command);
		GUIMain.userCommandBlock.clear();
		GUIMain.showPartitionList(0);
		GUIMain.showAll();
		GUIMain.showAdd();
	}
}
