package controller;

import java.util.*;
import java.io.IOException;

import util.CommandParser;
import util.Storage;
import command.*;
import model.*;

/**
 * 
 * Method to parse user commands, and pass parameters to UI, Storage and Logic
 * 
 */
public class Controller {
	private static Controller _instance;
	private static Logic logic = new Logic();

	private static ArrayList<Task> displayList = TaskMemory.getInstance()
			.getTaskList();
	private static CommandParser parser = null;

	public static Controller getInstance() {
		if (_instance == null) {
			_instance = new Controller();
		}
		return _instance;
	}

	public static void executeCMD(String input) throws IOException {
		// Parser : get from parser(input)
		parser = new CommandParser(input.trim());

		String cmdType = parser.getCommandType();
		String task_name = parser.getTaskName();
		String start_date = parser.getStartDate();
		String start_time = parser.getStartTime();
		String end_date = parser.getEndDate();
		String end_time = parser.getEndTime();
		String keyword = "all";
		int task_index = parser.getId();
		String search_word = parser.getSearchWord();
		Task task = null;

		// other parameter
		switch (cmdType.trim()) {
		case "add":
			task = logic.buildTask(task_name.trim(), start_date, end_date,
					start_time, end_time);
			CreateTask create = new CreateTask(task);
			create.execute();
			logic.pushToProcessStack(create);
			displayList = TaskMemory.getInstance().getTaskList();
			break;
		case "delete":

			if (keyword.equals("all") || keyword == "all") {
				ArrayList<Task> deleteBulkArray = new ArrayList<Task>();
				for (Task t:displayList) {
					deleteBulkArray.add(t);
				}
				DeleteBulkTask deletebulk = new DeleteBulkTask(deleteBulkArray);
				deletebulk.execute();
				logic.pushToProcessStack(deletebulk);
			} else {
				task = logic.deleteTask(displayList, task_index);
				DeleteTask delete = new DeleteTask(task);
				delete.execute();
				logic.pushToProcessStack(delete);
			}

			displayList = TaskMemory.getInstance().getTaskList();
			break;

		case "edit":
			Task deleteTask = logic.updateTask(displayList, task_index);
			Task updateTask = logic.buildTask(task_name.trim(), start_date,
					end_date, start_time, end_time);

			UpdateTask update = new UpdateTask(deleteTask, updateTask);
			update.execute();
			logic.pushToProcessStack(update);
			displayList = TaskMemory.getInstance().getTaskList();
			break;

		case "search":
			ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
			displayList = logic.searchTask(taskList, search_word.trim());

			break;

		case "display":
			ArrayList<Task> list = TaskMemory.getInstance().getTaskList();
			displayList = list;

			break;

		case "undo":
			logic.undo();
			displayList = TaskMemory.getInstance().getTaskList();
			break;

		case "load": {
			// Storage.getInstance().setPath("C:\\");
			Storage.getInstance().setfileName("silentjarvis.fxml");
			displayList = Storage.getInstance().load();
			TaskMemory.getInstance().setTaskList(displayList);
			break;
		}
		case "save":
			Storage.getInstance().setfileName("silentjarvis.fxml");
			Storage.getInstance().save(displayList);
			break;

		case "exit":
			break;

		}
	}

	public static ArrayList<Task> getTaskList() {
		return displayList;
	}

	public static int getSize() {
		return displayList.size();
	}
	// public static void inputCommand() throws IOException {
	// System.out.print("command: ");
	// String command = scanner.nextLine();
	// parser.Parser parse = new Parser(command);
	// String cmdType = parse.getCommandType();
	// executeCMD(cmdType);
	// }

}
