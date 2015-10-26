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
		parser = new CommandParser(input.trim());

		String cmdType = parser.getCommandType();
		String task_name = parser.getTaskName();
		String start_date = parser.getStartDate();
		String start_time = parser.getStartTime();
		String end_date = parser.getEndDate();
		String end_time = parser.getEndTime();
		String keyword = parser.getDeleteMode();
		int task_index = parser.getId();
		int[] _listIndex = parser.getDeleteIDs();
		String search_word = parser.getSearchWord();
		String search_byDate = parser.getSearchByDate();
		String search_onDate = parser.getSearchOnDate();
		String path = parser.getStoragePath();
		String editAttr = parser.getEditAttribute();
		String editInfo = parser.getEditInfo();
		String showOption = parser.getShowOption();
		String showByDate = parser.getShowByDate();
		String showDate = parser.getShowDate();
		String showEndDate = parser.getShowEndDate();
		String showStartDate = parser.getShowStartDate();
		
		
		switch (cmdType.trim()) {
		case "add":
			logic.executeCreateTask(task_name, start_date, start_time,
					end_date, end_time);
			displayList = TaskMemory.getInstance().getTaskList();
			break;
		case "delete":

			if (keyword != null) {
				logic.executeDeleteBulkTask(displayList);
			} else {
				if (_listIndex != null) {
					logic.executeDeleteBulkTasksById(displayList, _listIndex);
				} else {
					logic.executeDeleteTask(displayList, task_index);
				}
			}

			displayList = TaskMemory.getInstance().getTaskList();
			break;

		case "edit":
			if (editAttr != null && editInfo != null) {
				logic.executeUpdateTaskByAttribute(displayList, task_index, editAttr, editInfo);

			} else {
				logic.executeUpdateTask(displayList, task_name, start_date, start_time, end_date,
						end_time, null, task_index);
			}
			displayList = TaskMemory.getInstance().getTaskList();
			break;

		case "search":
			
			if(search_word != null){
			displayList = logic.searchTaskByKeyword(displayList,
					search_word.trim());
			}else if(search_byDate != null){				
				displayList = logic.searchTaskByDate(displayList, search_byDate.trim());
			}else if(search_onDate != null){
				displayList = logic.searchTaskOnDate(displayList, search_onDate.trim());
			}

			break;

		case "display":
			ArrayList<Task> list = TaskMemory.getInstance().getTaskList();
			displayList = list;

			break;

		case "undo":
			logic.undo();
			displayList = TaskMemory.getInstance().getTaskList();
			break;

		case "set":
			Storage.getInstance().setPath(path);
			Storage.getInstance().setfileName("silentjarvis.fxml");
			break;
			
		case "archive":
			editAttr = "taskType";
			editInfo = "Archived";
			logic.executeUpdateTaskByAttribute(displayList, task_index, editAttr, editInfo);
			displayList = TaskMemory.getInstance().getTaskList();
			break;
			
		case "complete":
			editAttr = "taskType";
			editInfo = "Completed";
			logic.executeUpdateTaskByAttribute(displayList, task_index, editAttr, editInfo);
			displayList = TaskMemory.getInstance().getTaskList();
			break;
			
		case "show":
			
			
			break;

		case "load":

			displayList = Storage.getInstance().load();
			TaskMemory.getInstance().setTaskList(displayList);
			break;

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
	
	public static ArrayList<Task> getFloatingTaskList(){
		return TaskMemory.getInstance().getFloatingTask();
	}
	
	public static ArrayList<Task> getFollowingWeekTaskList(){
		return TaskMemory.getInstance().getFollowingWeekTask();
	}
	
	public static ArrayList<Task> getOtherTaskList(){
		return TaskMemory.getInstance().getOtherTask();
	}
	// public static void inputCommand() throws IOException {
	// System.out.print("command: ");
	// String command = scanner.nextLine();
	// parser.Parser parse = new Parser(command);
	// String cmdType = parse.getCommandType();
	// executeCMD(cmdType);
	// }

}
