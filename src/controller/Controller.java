package controller;

import java.util.*;
import java.io.IOException;

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
	//private static ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
	private static ArrayList<Task> displayList = TaskMemory.getInstance().getTaskList();
	public static Controller getInstance() {
		if (_instance == null) {
			_instance = new Controller();
		}
		return _instance;
	}

	// private static final String MESSAGE_ERROR_COMMAND =
	// "Unrecongnised command entered";

	// private static ArrayList<String> content = new ArrayList<String>();

	public static void executeCMD(String input) throws IOException {
		// Parser : get from parser(input)
		String cmdType = input;
		Task task = null;
		
		String task_name = "Need to do business";
		// other parameter
		switch (cmdType) {
		case "add": {
			task = logic.buildTask(task_name, null, null, null, null);
			CreateTask create = new CreateTask(task);
			create.execute();
			logic.pushToProcessStack(create);
			displayList = TaskMemory.getInstance().getTaskList();
			break;
		}
		case "delete": {
			task = logic.deleteTask(displayList, 3);
			DeleteTask delete = new DeleteTask(task);
			delete.execute();
			logic.pushToProcessStack(delete);
			displayList = TaskMemory.getInstance().getTaskList();
			break;
		}
		case "edit": {
			Task deleteTask = logic.updateTask(displayList, 1);
			task_name = "Starbucks";
			Task updateTask = logic
					.buildTask(task_name, null, null, null, null);

			UpdateTask update = new UpdateTask(deleteTask, updateTask);
			update.execute();
			logic.pushToProcessStack(update);
			displayList = TaskMemory.getInstance().getTaskList();
			break;
		}
		case "search": {
			ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
			displayList = logic.searchTask(taskList, "2103");
			
			break;
		}
		case "showall":{
			ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
			displayList = taskList;
			
			break;
		}
		case "undo": {
			logic.undo();
			displayList = TaskMemory.getInstance().getTaskList();
			break;
		}
		case "exit": {
			break;
		}
		}
		//displayList = taskList;
	}

	public static ArrayList<Task> getTaskList() {
		return displayList;
	}

	// public static void inputCommand() throws IOException {
	// System.out.print("command: ");
	// String command = scanner.nextLine();
	// parser.Parser parse = new Parser(command);
	// String cmdType = parse.getCommandType();
	// executeCMD(cmdType);
	// }

}
