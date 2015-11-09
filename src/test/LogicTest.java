package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.*;

import org.junit.Test;

import command.TaskMemory;
import controller.Controller;
import controller.Logic;
import util.Storage;

/*
 * JUnit Testing
 * @@author A0111947E
 *
 */
public class LogicTest {
	// get instance from TaskMemory
	ArrayList<Task> taskList = TaskMemory.getInstance().getCombinedTaskList();

	// create logic object
	Logic logic = new Logic();

	@Test
	public void ClearTaskTest() {
		// calling delete all task logic
		logic.deleteAllTask(taskList);
		// call memory instance of combined task list.
		taskList = TaskMemory.getInstance().getCombinedTaskList();

		assertEquals(0, taskList.size());
		logic.save();

	}

	@Test
	public void FailAddTaskTest() {

		ClearTaskTest();
		assertEquals(0, taskList.size());

		// a null task will failed to create
		logic.executeCreateTask(null, null, null, null, null);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(0, taskList.size());
	}

	@Test
	public void AddEventTaskTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		// create a event task
		logic.executeCreateTask("Event task", "2015-11-05", "2359",
				"2015-11-11", "1000");
		// get instance
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(2, taskList.size());
		// assert event task attribute
		EventTask event = (EventTask) taskList.get(0);
		assertEquals("Event task", event.getTaskName());
		assertEquals("2015-11-05", event.getStartDate());
		assertEquals("2359", event.getStartTime());
		assertEquals("2015-11-11", event.getEndDate());
		assertEquals("1000", event.getEndTime());
		assertEquals("null", event.getTaskType());
	}

	@Test
	public void AddFloatingTaskTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());
		// create a floating task
		logic.executeCreateTask("Floating task", null, null, null, null);

		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(1, taskList.size());
		assertEquals("Floating task", taskList.get(0).getTaskName());
		assertEquals("null", taskList.get(0).getTaskType());
	}

	@Test
	public void AddDeadlineTaskTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		// create a deadline task
		logic.executeCreateTask("Deadline task", null, null, "2015-11-12",
				"1100");

		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(1, taskList.size());
		DeadlineTask deadline = (DeadlineTask) taskList.get(0);
		assertEquals("Deadline task", deadline.getTaskName());
		assertEquals("2015-11-12", deadline.getDeadlineDate());
		assertEquals("1100", deadline.getDeadlineTime());

	}

	@Test
	public void deleteTaskTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		logic.executeCreateTask("Float", null, null, null, null);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(1, taskList.size());

		// delete task index 1 which is Floating task
		logic.executeDeleteTask(taskList, 1);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(0, taskList.size());
	}

	@Test
	public void deleteMultipleTastTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		logic.executeCreateTask("Float", null, null, null, null);
		logic.executeCreateTask("Float2", null, null, null, null);

		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(2, taskList.size());

		// delete multiple task, index 1 and 2, which is Floating task. Float
		// and Float2
		int[] index = { 1, 2 };
		logic.deleteMultipleTask(taskList, index);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(0, taskList.size());
	}

	@Test
	public void searchByKeyWordTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		logic.executeCreateTask("Float", null, null, null, null);
		logic.executeCreateTask("Float2", null, null, null, null);
		logic.executeCreateTask("Home", null, null, null, null);
		logic.executeCreateTask("MEETING WITH BOSS", null, null, null, null);
		logic.executeCreateTask("BOSS", null, null, null, null);
		logic.executeCreateTask("HELLO", null, null, null, null);

		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(6, taskList.size());

		// search by keyword "BOSS"
		taskList = logic.searchTaskByKeyword(taskList, "BOSS");
		assertEquals(2, taskList.size());
		assertEquals("BOSS", taskList.get(0).getTaskName());
		assertEquals("MEETING WITH BOSS", taskList.get(1).getTaskName());

		taskList = TaskMemory.getInstance().getCombinedTaskList();
		// search by keyword "HoMe" with case sensitive
		taskList = logic.searchTaskByKeyword(taskList, "HoMe");
		assertEquals("Home", taskList.get(0).getTaskName());

		taskList = TaskMemory.getInstance().getCombinedTaskList();
		// search by key with word jumble up
		taskList = logic.searchTaskByKeyword(taskList, "BOSS WITH");
		assertEquals("BOSS", taskList.get(0).getTaskName());
		assertEquals("MEETING WITH BOSS", taskList.get(1).getTaskName());

	}

	@Test
	public void searchByDateTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		logic.executeCreateTask("Event1", "2016-12-01", "0800", "2016-12-12",
				"0800");
		logic.executeCreateTask("Event2", "2015-12-12", "0800", "2015-12-13",
				"0800");
		logic.executeCreateTask("Deadline1", null, null, "2015-12-25", "0800");

		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(3, taskList.size());

		// search task by date, it will give you a list of task with
		// deadline/end date before searched date.
		taskList = logic.searchTaskByDate(taskList, "2015-12-12");
		assertEquals(0, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();

		taskList = logic.searchTaskByDate(taskList, "2015-12-13");
		assertEquals(1, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();

		taskList = logic.searchTaskByDate(taskList, "2015-12-25");
		assertEquals(2, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();

		taskList = logic.searchTaskByDate(taskList, "2016-12-12");
		assertEquals(3, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();

	}

	@Test
	public void searchOnDateTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		logic.executeCreateTask("Event1", "2016-12-01", "0800", "2016-12-12",
				"0800");
		logic.executeCreateTask("Event2", "2015-12-12", "0800", "2015-12-13",
				"0800");
		logic.executeCreateTask("Deadline1", null, null, "2015-12-25", "0800");

		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(3, taskList.size());

		// search task on date. it will return a list of task with the specific
		// date given
		taskList = logic.searchTaskOnDate(taskList, "2015-12-12");
		assertEquals(0, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();

		taskList = logic.searchTaskOnDate(taskList, "2015-12-13");
		assertEquals(1, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();

		taskList = logic.searchTaskOnDate(taskList, "2015-12-25");
		assertEquals(1, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();

		taskList = logic.searchTaskOnDate(taskList, "2016-12-12");
		assertEquals(1, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();
	}

	@Test
	public void undoTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		logic.executeCreateTask("Event1", "2016-12-01", "0800", "2016-12-12",
				"0800");
		logic.executeCreateTask("Event2", "2015-12-12", "0800", "2015-12-13",
				"0800");
		logic.executeCreateTask("Deadline1", null, null, "2015-12-25", "0800");

		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(3, taskList.size());
		// test for undo
		logic.undo();
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(2, taskList.size());

		logic.undo();
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(1, taskList.size());

		logic.undo();
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(0, taskList.size());

		logic.undo();
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(6, taskList.size());

	}

	@Test
	public void searchBetweenDateTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		logic.executeCreateTask("Event1", "2016-12-01", "0800", "2016-12-12",
				"0800");
		logic.executeCreateTask("Event2", "2015-12-12", "0800", "2015-12-13",
				"0800");
		logic.executeCreateTask("Event3", "2015-12-13", "0800", "2015-12-14",
				"0800");
		logic.executeCreateTask("Event4", "2015-12-14", "0800", "2015-12-15",
				"0800");
		logic.executeCreateTask("Event5", "2015-12-15", "0800", "2015-12-16",
				"0800");
		logic.executeCreateTask("deadline", null, null, "2015-12-12", "0700");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(6, taskList.size());

		// search tasks between the dates.
		taskList = logic.searchTaskBetweenDate(taskList, "2015-12-12",
				"2015-12-14");
		assertEquals(3, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();

		taskList = logic.searchTaskBetweenDate(taskList, "2015-12-12",
				"2015-12-16");
		assertEquals(5, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();

		taskList = logic.searchTaskBetweenDate(taskList, "2015-12-12",
				"2016-12-16");
		assertEquals(6, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();

	}

	@Test
	public void updateTaskTest() {

		ClearTaskTest();
		assertEquals(0, taskList.size());

		logic.executeCreateTask("Float1", null, null, null, null);
		logic.executeCreateTask("Deadline1", null, null, "2015-12-13", "0800");
		logic.executeCreateTask("Event3", "2015-12-13", "0800", "2015-12-14",
				"0800");
		logic.executeCreateTask("Event4", "2015-12-14", "0800", "2015-12-15",
				"0800");
		logic.executeCreateTask("Event5", "2015-12-15", "0800", "2015-12-16",
				"0800");

		// Update the task bu index
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline1", taskList.get(0).getTaskName());
		DeadlineTask deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-13", deadline.getDeadlineDate());
		assertEquals("0800", deadline.getDeadlineTime());

		logic.executeUpdateTask(taskList, "Deadline2", null, null,
				"2015-12-14", "0700", "null", 1);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline2", taskList.get(0).getTaskName());
		deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-14", deadline.getDeadlineDate());
		assertEquals("0700", deadline.getDeadlineTime());

		assertEquals("Float1", taskList.get(4).getTaskName());

		logic.executeUpdateTask(taskList, "Home sweet Home", null, null, null,
				null, "null", 5);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Home sweet Home", taskList.get(4).getTaskName());
	}

	@Test
	public void updateTaskByAttrTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		logic.executeCreateTask("Float1", null, null, null, null);
		logic.executeCreateTask("Deadline1", null, null, "2015-12-13", "0800");
		logic.executeCreateTask("Event3", "2015-12-13", "0800", "2015-12-14",
				"0800");
		logic.executeCreateTask("Event4", "2015-12-14", "0800", "2015-12-15",
				"0800");
		logic.executeCreateTask("Event5", "2015-12-15", "0800", "2015-12-16",
				"0800");

		// update the task by index and the attribute
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline1", taskList.get(0).getTaskName());
		DeadlineTask deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-13", deadline.getDeadlineDate());
		assertEquals("0800", deadline.getDeadlineTime());
		assertEquals("null", deadline.getTaskType());

		int[] index = { 1 };
		logic.executeUpdateTaskByAttribute(taskList, index, "taskName",
				"Deadline4");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline4", taskList.get(0).getTaskName());
		deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-13", deadline.getDeadlineDate());
		assertEquals("0800", deadline.getDeadlineTime());
		assertEquals("null", deadline.getTaskType());

		logic.executeUpdateTaskByAttribute(taskList, index, "endTime", "0700");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline4", taskList.get(0).getTaskName());
		deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-13", deadline.getDeadlineDate());
		assertEquals("0700", deadline.getDeadlineTime());
		assertEquals("null", deadline.getTaskType());

		logic.executeUpdateTaskByAttribute(taskList, index, "endDate",
				"2015-12-11");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline4", taskList.get(0).getTaskName());
		deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-11", deadline.getDeadlineDate());
		assertEquals("0700", deadline.getDeadlineTime());
		assertEquals("null", deadline.getTaskType());

		logic.executeUpdateTaskByAttribute(taskList, index, "taskType",
				"Completed");
		logic.undo();
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline4", taskList.get(0).getTaskName());
		deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-11", deadline.getDeadlineDate());
		assertEquals("0700", deadline.getDeadlineTime());
		assertEquals("null", deadline.getTaskType());

		logic.executeUpdateTaskByAttribute(taskList, index, "taskType",
				"Archive");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline4", taskList.get(0).getTaskName());
		deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-11", deadline.getDeadlineDate());
		assertEquals("0700", deadline.getDeadlineTime());
		assertEquals("Archive", deadline.getTaskType());

		logic.undo();
		int[] index2 = { 2 };

		logic.executeUpdateTaskByAttribute(taskList, index2, "taskname",
				"Event100");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		EventTask event = (EventTask) taskList.get(1);
		assertEquals("2015-12-13", event.getStartDate());
		assertEquals("0800", event.getStartTime());
		assertEquals("2015-12-14", event.getEndDate());
		assertEquals("0800", event.getEndTime());

		logic.executeUpdateTaskByAttribute(taskList, index2, "startdate",
				"2015-12-11");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		event = (EventTask) taskList.get(1);
		assertEquals("2015-12-11", event.getStartDate());
		assertEquals("0800", event.getStartTime());
		assertEquals("2015-12-14", event.getEndDate());
		assertEquals("0800", event.getEndTime());

		logic.executeUpdateTaskByAttribute(taskList, index2, "starttime",
				"0900");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		event = (EventTask) taskList.get(1);
		assertEquals("2015-12-11", event.getStartDate());
		assertEquals("0900", event.getStartTime());
		assertEquals("2015-12-14", event.getEndDate());
		assertEquals("0800", event.getEndTime());

		logic.executeUpdateTaskByAttribute(taskList, index2, "enddate",
				"2015-12-13");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		event = (EventTask) taskList.get(1);
		assertEquals("2015-12-11", event.getStartDate());
		assertEquals("0900", event.getStartTime());
		assertEquals("2015-12-13", event.getEndDate());
		assertEquals("0800", event.getEndTime());

		logic.executeUpdateTaskByAttribute(taskList, index2, "endtime", "0900");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		event = (EventTask) taskList.get(1);
		assertEquals("2015-12-11", event.getStartDate());
		assertEquals("0900", event.getStartTime());
		assertEquals("2015-12-13", event.getEndDate());
		assertEquals("0900", event.getEndTime());

		logic.executeUpdateTaskByAttribute(taskList, index2, "taskType",
				"Completed");
		logic.undo();
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		event = (EventTask) taskList.get(1);
		assertEquals("2015-12-11", event.getStartDate());
		assertEquals("0900", event.getStartTime());
		assertEquals("2015-12-13", event.getEndDate());
		assertEquals("0900", event.getEndTime());

		logic.executeUpdateTaskByAttribute(taskList, index2, "taskType",
				"Archived");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(4, taskList.size());
		assertEquals("Event4", taskList.get(1).getTaskName());
		event = (EventTask) taskList.get(1);
		assertEquals("2015-12-14", event.getStartDate());
		assertEquals("0800", event.getStartTime());
		assertEquals("2015-12-15", event.getEndDate());
		assertEquals("0800", event.getEndTime());
		assertEquals("null", event.getTaskType());

		int[] index3 = { 4 };
		logic.executeUpdateTaskByAttribute(taskList, index3, "taskname",
				"floatthere");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(4, taskList.size());
		assertEquals("floatthere", taskList.get(3).getTaskName());
		assertEquals("null", taskList.get(3).getTaskType());

		logic.executeUpdateTaskByAttribute(taskList, index3, "tasktype",
				"Completed");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(3, taskList.size());

		logic.executeUpdateTaskByAttribute(taskList, index3, "taskType",
				"Archived");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(3, taskList.size());
	}

	@Test
	public void saveTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());

		logic.executeCreateTask("Float1", null, null, null, null);
		logic.executeCreateTask("Deadline1", null, null, "2015-12-13", "0800");
		logic.executeCreateTask("Event3", "2015-12-13", "0800", "2015-12-14",
				"0800");
		logic.executeCreateTask("Event4", "2015-12-14", "0800", "2015-12-15",
				"0800");
		logic.executeCreateTask("Event5", "2015-12-15", "0800", "2015-12-16",
				"0800");

		logic.save();
	}

	@Test
	public void taskListTest() throws Exception {
		ClearTaskTest();
		assertEquals(0, taskList.size());
		ArrayList<Task> getArchivedList = TaskMemory.getInstance()
				.getArchivedList();
		logic.deleteAllTask(getArchivedList);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		taskList = TaskMemory.getInstance().getCompletedList();
		logic.deleteAllTask(taskList);
		taskList = TaskMemory.getInstance().getCombinedTaskList();

		taskList = Controller.getFloatingTaskList();
		assertEquals(0, taskList.size());

		Controller.executeCMD("add Float1");
		Controller.executeCMD("add Deadline1 by 2015-12-13 0800");
		Controller
				.executeCMD("add Event3 from 2015-12-13 0800 to 2015-12-14 0800");
		Controller
				.executeCMD("add Event4 from 2015-12-14 0800 to 2015-12-15 0800");
		Controller
				.executeCMD("add Event5 from 2015-12-15 0800 to 2015-12-16 0800");

		taskList = Controller.getFloatingTaskList();
		assertEquals(1, taskList.size());

		taskList = Controller.getFollowingDayTaskList();
		assertEquals(4, taskList.size());

		taskList = Controller.getTodayTaskList();
		assertEquals(0, taskList.size());

		Controller.executeCMD("add deadlin123 by today 2359");
		taskList = Controller.getTodayTaskList();
		assertEquals(1, taskList.size());

		Controller.executeCMD("delete 1");
		taskList = Controller.getCombinedTaskList();
		assertEquals(5, taskList.size());

		Controller.executeCMD("delete 1,2");
		taskList = Controller.getCombinedTaskList();
		assertEquals(3, taskList.size());

		Controller.executeCMD("undo");
		taskList = Controller.getCombinedTaskList();
		assertEquals(5, taskList.size());

		Controller.executeCMD("undo");
		taskList = Controller.getCombinedTaskList();
		assertEquals(6, taskList.size());

		Controller.executeCMD("delete 1-3");
		taskList = Controller.getCombinedTaskList();
		assertEquals(3, taskList.size());

		Controller.executeCMD("delete all");
		taskList = Controller.getCombinedTaskList();
		assertEquals(0, taskList.size());

		Controller.executeCMD("undo");
		taskList = Controller.getCombinedTaskList();
		assertEquals(3, taskList.size());

		assertEquals("Float1", taskList.get(2).getTaskName());
		Controller.executeCMD("edit 3 taskName helloWord");
		taskList = Controller.getCombinedTaskList();
		assertEquals(3, taskList.size());
		assertEquals("helloWord", taskList.get(2).getTaskName());

		Controller.executeCMD("edit 3 hello123");
		taskList = Controller.getCombinedTaskList();
		assertEquals(3, taskList.size());
		assertEquals("hello123", taskList.get(2).getTaskName());
		Controller.executeCMD("undo");
		taskList = Controller.getCombinedTaskList();
		assertEquals(3, taskList.size());
		assertEquals("helloWord", taskList.get(2).getTaskName());
		Controller.executeCMD("edit 3 hello123");
		taskList = Controller.getCombinedTaskList();
		assertEquals(3, taskList.size());
		assertEquals("hello123", taskList.get(2).getTaskName());

		Controller.executeCMD("search hello123");
		taskList = Controller.getTaskList();
		assertEquals(1, taskList.size());

		Controller.executeCMD("display");
		taskList = Controller.getTaskList();
		assertEquals(3, taskList.size());

		Controller.executeCMD("search by 2015-12-14");
		taskList = Controller.getTaskList();
		assertEquals(0, taskList.size());

		Controller.executeCMD("display");
		taskList = Controller.getTaskList();
		assertEquals(3, taskList.size());

		Controller.executeCMD("search by 2015-12-16");
		taskList = Controller.getTaskList();
		int size = Controller.getSize();
		assertEquals(2, size);

		Controller.executeCMD("display");
		taskList = Controller.getTaskList();

		Controller.executeCMD("search on 2015-12-16");
		taskList = Controller.getTaskList();
		assertEquals(1, taskList.size());

		Controller.executeCMD("display");
		taskList = Controller.getTaskList();
		Controller.executeCMD("search from today to 2015-12-16");
		taskList = Controller.getTaskList();
		assertEquals(2, taskList.size());

		Controller.executeCMD("display");
		Controller.executeCMD("set path testing");
		String path = "testing" + "\\";
		assertEquals(path, Storage.getInstance().getPath());

		Controller.executeCMD("set filename newfile");
		String filename = "newfile.fxml";
		assertEquals(filename, Storage.getInstance().getfileName());

		Controller.executeCMD("archive 1");
		taskList = Controller.getCombinedTaskList();
		assertEquals(2, taskList.size());

		Controller.executeCMD("display");
		Controller.executeCMD("undo");
		Controller.executeCMD("archive 1-2");
		taskList = Controller.getCombinedTaskList();
		assertEquals(1, taskList.size());

		Controller.executeCMD("display");
		Controller.executeCMD("show archived");
		taskList = Controller.getArchivedList();
		assertEquals(2, taskList.size());

		Controller.executeCMD("unarchived 1,2");
		taskList = Controller.getCombinedTaskList();
		assertEquals(3, taskList.size());

		Controller.executeCMD("complete 1-3");
		taskList = Controller.getTaskList();
		assertEquals(0, taskList.size());

		Controller.executeCMD("show complete");
		taskList = Controller.getCompletedList();
		assertEquals(3, taskList.size());

		Controller.executeCMD("uncomplete 1-2");
		taskList = Controller.getTaskList();
		assertEquals(2, taskList.size());

		Controller.executeCMD("show by 2015-12-16");
		taskList = Controller.getCombinedTaskList();
		assertEquals(2, taskList.size());
		Controller.executeCMD("display");
		Controller.executeCMD("show on 2015-12-16");
		taskList = Controller.getCombinedTaskList();
		assertEquals(2, taskList.size());
		Controller.executeCMD("display");
		Controller.executeCMD("show from today to 2015-12-16");
		taskList = Controller.getCombinedTaskList();
		assertEquals(2, taskList.size());

		Controller.executeCMD("display");
		Controller.executeCMD("show floating");
		taskList = Controller.getFloatingTaskList();
		assertEquals(0, taskList.size());

		Controller.executeCMD("display");
		Controller.executeCMD("save");
		Controller.executeCMD("load");
		taskList = Controller.getCombinedTaskList();
		assertEquals(2, taskList.size());

		Controller.executeCMD("help");
		String help = "add <name>\nadd <name> from <time> to <time>\nadd <name> by   <deadline>\ndelete  <id>\nsearch  <id>\narchive <id>\nedit <id> <attribute> <info>\nset  path     <storage path>\nset  filename <filename>\nshow on <date>\nshow by <date>\nundo\n";
		assertEquals(help, Controller.getHelpString());

		Controller.executeCMD("clear");
		taskList = Controller.getCombinedTaskList();
		assertEquals(0, taskList.size());

		Controller.executeCMD("add abc by today");
		taskList = Controller.getCombinedTaskList();
		assertEquals(0, taskList.size());

		Controller.executeCMD("add eve from today 2359 to today 2359");
		taskList = Controller.getCombinedTaskList();
		assertEquals(1, taskList.size());

		assertEquals(3, TaskMemory.getInstance().getSize());

		Controller.executeCMD("exit");

	}

	@Test
	public void equalsTest() {
		Task a = new FloatingTask("a", null);
		Task b = new FloatingTask("a", null);
		Task c = a;
		assertEquals(true, a.equals(c));
		Task d = new DeadlineTask("abc", "2015-12-12", "0800", "null");
		Task e = new DeadlineTask("abc", "2015-12-12", "0800", "null");
		assertEquals(false, d.equals(e));
		Task f = new EventTask("event", "2015-12-12", "0800", "2015-12-14",
				"0800", "null");
		Task g = new EventTask("event", "2015-12-12", "0800", "2015-12-14",
				"0800", "null");
		Task h = g;
		assertEquals(false, f.equals(g));
		assertEquals(false, a.equals(g));
		assertEquals(false, e.equals(g));
		assertEquals(false, g.equals(a));
		assertEquals(true, h.equals(g));
	}
}
