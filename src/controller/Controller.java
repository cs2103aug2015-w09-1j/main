package controller;

import java.util.*;
import java.io.IOException;

import javafx.application.Platform;
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
			.getCombinedTaskList();
	private static CommandParser parser = null;

	public static Controller getInstance() {
		if (_instance == null) {
			_instance = new Controller();
		}
		return _instance;
	}

	public static void executeCMD(String input) {

		try {
			parser = new CommandParser(input.trim());

			String _cmdType = parser.getCommandType();
			String _taskName = parser.getTaskName();
			String _startDate = parser.getStartDate();
			String _startTime = parser.getStartTime();
			String _endDate = parser.getEndDate();
			String _endTime = parser.getEndTime();
			String _keyword = parser.getDeleteMode();
			int _taskIndex = parser.getId();
			int[] _listIndex = parser.getDeleteIDs();
			String _searchWord = parser.getSearchWord();
			String _searchByDate = parser.getSearchByDate();
			String _searchOnDate = parser.getSearchOnDate();
			String _editAttr = parser.getEditAttribute();
			String _editInfo = parser.getEditInfo();
			String _showOption = parser.getShowOption();
			String _showByDate = parser.getShowByDate();
			String _showDate = parser.getShowDate();
			String _showEndDate = parser.getShowEndDate();
			String _showStartDate = parser.getShowStartDate();
			String _searchStartDate = parser.getSearchStartDate();
			String _searchEndDate = parser.getSearchEndDate();
			String _displayMode = parser.getDisplayMode();

			switch (_cmdType.trim()) {
			case "add":
				logic.executeCreateTask(_taskName, _startDate, _startTime,
						_endDate, _endTime);
				displayList = TaskMemory.getInstance().getCombinedTaskList();
				break;
			case "delete":

				if (_keyword != null) {
					logic.deleteAllTask(displayList);
				} else {
					if (_listIndex != null) {
						logic.deleteMultipleTask(displayList, _listIndex);
					} else {
						logic.executeDeleteTask(displayList, _taskIndex);
					}
				}

				displayList = TaskMemory.getInstance().getCombinedTaskList();
				break;

			case "edit":
				int[] index = { _taskIndex };
				if (_editAttr != null && _editInfo != null) {
					logic.executeUpdateTaskByAttribute(displayList, index,
							_editAttr, _editInfo);

				} else {
					logic.executeUpdateTask(displayList, _taskName, _startDate,
							_startTime, _endDate, _endTime, "null", _taskIndex);
				}
				displayList = TaskMemory.getInstance().getNoArchivedList();
				break;

			case "search":

				if (_searchWord != null) {
					displayList = logic.searchTaskByKeyword(displayList,
							_searchWord.trim());
				} else if (_searchByDate != null) {
					displayList = logic.searchTaskByDate(displayList,
							_searchByDate.trim());
				} else if (_searchOnDate != null) {
					displayList = logic.searchTaskOnDate(displayList,
							_searchOnDate.trim());
				} else if (_searchStartDate != null && _searchEndDate != null) {
					displayList = logic.searchTaskBetweenDate(displayList,
							_searchStartDate, _searchEndDate);
				}

				break;

			case "display":

				if (_displayMode == null) {
					ArrayList<Task> list = TaskMemory.getInstance().
							getCombinedTaskList();
					displayList = list;
				}

				break;

			case "undo":
				logic.undo();
				displayList = TaskMemory.getInstance().getCombinedTaskList();
				break;

			case "set":
				String _path = parser.getStoragePath();
				String _filename = parser.getStorageFileName();
				if (_path != null) {
					Storage.getInstance().setPath(_path);
				} else if (_filename != null) {
					Storage.getInstance().setfileName(_filename);
				}
				break;

			case "archive":
				int[] _archiveIndex = parser.getArchivedIDs();
				_editAttr = "taskType";
				_editInfo = "Archived";
				logic.executeUpdateTaskByAttribute(displayList, _archiveIndex,
						_editAttr, _editInfo);
				displayList = TaskMemory.getInstance().getCombinedTaskList();
				break;

			case "unarchived":
				int[] _unarchiveIndex = parser.getUnarchivedIDs();
				_editAttr = "taskType";
				_editInfo = "null";
				logic.executeUpdateTaskByAttribute(displayList,
						_unarchiveIndex, _editAttr, _editInfo);
				displayList = TaskMemory.getInstance().getCombinedTaskList();
				break;

			case "complete":
				int[] _completeIndex = parser.getCompleteIDs();
				_editAttr = "taskType";
				_editInfo = "Completed";
				logic.executeUpdateTaskByAttribute(displayList, _completeIndex,
						_editAttr, _editInfo);
				displayList = TaskMemory.getInstance().getCombinedTaskList();
				break;

			case "uncomplete":
				int[] _uncompleteIndex = parser.getUncompleteIDs();
				_editAttr = "taskType";
				_editInfo = "null";
				logic.executeUpdateTaskByAttribute(displayList,
						_uncompleteIndex, _editAttr, _editInfo);
				displayList = TaskMemory.getInstance().getCombinedTaskList();
				break;

			case "show":

				if (_showByDate != null) {
					displayList = logic.searchTaskByDate(displayList,
							_showByDate);
				} else if (_showDate != null) {
					displayList = logic
							.searchTaskOnDate(displayList, _showDate);
				} else if (_showStartDate != null && _showEndDate != null) {
					displayList = logic.searchTaskBetweenDate(displayList,
							_showStartDate, _showEndDate);
				} else if (_showOption != null) {
					if (_showOption.equalsIgnoreCase("floating")) {
						displayList = TaskMemory.getInstance()
								.getFloatingTask();
					} else if (_showOption.equalsIgnoreCase("archived")) {
						displayList = getArchivedList();
					}
				}
				break;

			case "load":
				displayList = TaskMemory.getInstance().getCombinedTaskList();

				break;
			case "help":
				String _helpString = parser.getHelpString();
				getHelpString(_helpString);
				break;

			case "save":
				logic.save();
				break;
				
			case "clear":
				logic.deleteAllTask(displayList);
				displayList = TaskMemory.getInstance().getCombinedTaskList();
				break;

			case "exit":
				logic.save();
				// close program
				Platform.exit();
				break;

			}
		} catch (Exception e) {
			String msg = "ERROR";
			getErrorMessage(msg);
		}
	}

	public static ArrayList<Task> getTaskList() {

		return displayList;
	}

	public static int getSize() {
		return displayList.size();
	}
	
	public static String getErrorMessage(String errorMessage){
		return errorMessage;
	}

	public static String getHelpString(String helpMessage) {
		return helpMessage;
	}

	public static ArrayList<Task> getFloatingTaskList() {
		return TaskMemory.getInstance().getFloatingTask();
	}

	public static ArrayList<Task> getFollowingDayTaskList() {
		return TaskMemory.getInstance().getFollowingDayTask();
	}

	public static ArrayList<Task> getOtherTaskList() {
		return TaskMemory.getInstance().getOtherTask();
	}

	public static ArrayList<Task> getArchivedList() {
		return TaskMemory.getInstance().getArchivedList();
	}

	public static ArrayList<Task> getNoArchivedList() {
		return TaskMemory.getInstance().getNoArchivedList();
	}

	public static ArrayList<Task> getTodayTaskList() {
		return TaskMemory.getInstance().getTodayTaskList();
	}

	public static ArrayList<Task> getDueTaskList() {
		return TaskMemory.getInstance().getDueTask();
	}
	
	public static ArrayList<Task> getCombinedTaskList(){
		return TaskMemory.getInstance().getCombinedTaskList();
	}

}
