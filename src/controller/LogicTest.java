package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.*;

import org.junit.Test;

import command.TaskMemory;
import util.Storage;

public class LogicTest {
	ArrayList<Task> taskList = TaskMemory.getInstance().getCombinedTaskList();
	Logic logic = new Logic();
	@Test
	public void ClearTaskTest() {		
		logic.deleteAllTask(taskList);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		
		assertEquals(0, taskList.size());
	}
	
	@Test
	public void FailAddTaskTest(){
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask(null, null, null,
				null, null);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(0, taskList.size());
	}
	
	@Test
	public void AddEventTaskTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask("Event task", "2015-11-05", "2359",
				"2015-11-11", "1000");

		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(2, taskList.size());
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
		
		logic.executeCreateTask("Floating task", null, null, null, null);

		taskList = TaskMemory.getInstance()
				.getCombinedTaskList();
		assertEquals(1, taskList.size());
		assertEquals("Floating task", taskList.get(0).getTaskName());
		assertEquals("null", taskList.get(0).getTaskType());
	}

	

	@Test
	public void AddDeadlineTaskTest() {
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask("Deadline task", null, null, "2015-11-12",
				"1100");

		taskList = TaskMemory.getInstance()
				.getCombinedTaskList();
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
		
		logic.executeDeleteTask(taskList, 1);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(0, taskList.size());
	}
	
	@Test
	public void deleteMultipleTastTest(){
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask("Float", null, null, null, null);
		logic.executeCreateTask("Float2", null, null, null, null);
		
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(2, taskList.size());
		
		int[] index = {1,2};
		logic.deleteMultipleTask(taskList, index);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(0, taskList.size());
	}
	
	@Test
	public void searchByKeyWordTest(){
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
		
		
		taskList = logic.searchTaskByKeyword(taskList, "BOSS");
		assertEquals(2, taskList.size());
		assertEquals("BOSS", taskList.get(0).getTaskName());
		assertEquals("MEETING WITH BOSS", taskList.get(1).getTaskName());
		
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		taskList = logic.searchTaskByKeyword(taskList, "HoMe");
		assertEquals("Home", taskList.get(0).getTaskName());
		
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		taskList = logic.searchTaskByKeyword(taskList, "BOSS WITH");
		assertEquals("BOSS", taskList.get(0).getTaskName());
		assertEquals("MEETING WITH BOSS", taskList.get(1).getTaskName());
		
		}
	
	@Test
	public void searchByDateTest(){
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask("Event1", "2016-12-01", "0800", "2016-12-12", "0800");
		logic.executeCreateTask("Event2", "2015-12-12", "0800", "2015-12-13", "0800");
		logic.executeCreateTask("Deadline1", null, null, "2015-12-25", "0800");
		
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(3, taskList.size());
		
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
	public void searchOnDateTest(){
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask("Event1", "2016-12-01", "0800", "2016-12-12", "0800");
		logic.executeCreateTask("Event2", "2015-12-12", "0800", "2015-12-13", "0800");
		logic.executeCreateTask("Deadline1", null, null, "2015-12-25", "0800");
		
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(3, taskList.size());
		
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
	public void undoTest(){
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask("Event1", "2016-12-01", "0800", "2016-12-12", "0800");
		logic.executeCreateTask("Event2", "2015-12-12", "0800", "2015-12-13", "0800");
		logic.executeCreateTask("Deadline1", null, null, "2015-12-25", "0800");
		
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(3, taskList.size());
		
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
	public void searchBetweenDateTest(){
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask("Event1", "2016-12-01", "0800", "2016-12-12", "0800");
		logic.executeCreateTask("Event2", "2015-12-12", "0800", "2015-12-13", "0800");
		logic.executeCreateTask("Event3", "2015-12-13", "0800", "2015-12-14", "0800");
		logic.executeCreateTask("Event4", "2015-12-14", "0800", "2015-12-15", "0800");
		logic.executeCreateTask("Event5", "2015-12-15", "0800", "2015-12-16", "0800");
		logic.executeCreateTask("deadline", null, null, "2015-12-12", "0700");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(6, taskList.size());
		
		taskList = logic.searchTaskBetweenDate(taskList, "2015-12-12", "2015-12-14");
		assertEquals(3, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		
		taskList = logic.searchTaskBetweenDate(taskList, "2015-12-12", "2015-12-16");
		assertEquals(5, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		
		taskList = logic.searchTaskBetweenDate(taskList, "2015-12-12", "2016-12-16");
		assertEquals(6, taskList.size());
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		
	}
	
	@Test
	public void updateTaskTest(){
		
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask("Float1", null, null, null, null);
		logic.executeCreateTask("Deadline1", null,null, "2015-12-13", "0800");
		logic.executeCreateTask("Event3", "2015-12-13", "0800", "2015-12-14", "0800");
		logic.executeCreateTask("Event4", "2015-12-14", "0800", "2015-12-15", "0800");
		logic.executeCreateTask("Event5", "2015-12-15", "0800", "2015-12-16", "0800");
		
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline1", taskList.get(0).getTaskName());
		DeadlineTask deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-13", deadline.getDeadlineDate());
		assertEquals("0800", deadline.getDeadlineTime());
		
		logic.executeUpdateTask(taskList, "Deadline2", null, null, "2015-12-14", "0700", "null", 1);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline2", taskList.get(0).getTaskName());
		deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-14", deadline.getDeadlineDate());
		assertEquals("0700", deadline.getDeadlineTime());
		
		assertEquals("Float1", taskList.get(4).getTaskName());
		
		logic.executeUpdateTask(taskList, "Home sweet Home", null, null, null, null, "null", 5);
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Home sweet Home", taskList.get(4).getTaskName());	
	}
	
	@Test
	public void updateTaskByAttrTest(){
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask("Float1", null, null, null, null);
		logic.executeCreateTask("Deadline1", null,null, "2015-12-13", "0800");
		logic.executeCreateTask("Event3", "2015-12-13", "0800", "2015-12-14", "0800");
		logic.executeCreateTask("Event4", "2015-12-14", "0800", "2015-12-15", "0800");
		logic.executeCreateTask("Event5", "2015-12-15", "0800", "2015-12-16", "0800");
		
		
		
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline1", taskList.get(0).getTaskName());
		DeadlineTask deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-13", deadline.getDeadlineDate());
		assertEquals("0800", deadline.getDeadlineTime());
		assertEquals("null", deadline.getTaskType());
		
		int[] index = {1};
		logic.executeUpdateTaskByAttribute(taskList, index, "taskName", "Deadline4");
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
		
		logic.executeUpdateTaskByAttribute(taskList, index, "endDate", "2015-12-11");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline4", taskList.get(0).getTaskName());
		deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-11", deadline.getDeadlineDate());
		assertEquals("0700", deadline.getDeadlineTime());
		assertEquals("null", deadline.getTaskType());
		
		logic.executeUpdateTaskByAttribute(taskList, index, "taskType", "Completed");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline4", taskList.get(0).getTaskName());
		deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-11", deadline.getDeadlineDate());
		assertEquals("0700", deadline.getDeadlineTime());
		assertEquals("Completed", deadline.getTaskType());
		
		logic.executeUpdateTaskByAttribute(taskList, index, "taskType", "Archive");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Deadline4", taskList.get(0).getTaskName());
		deadline = (DeadlineTask) taskList.get(0);
		assertEquals("2015-12-11", deadline.getDeadlineDate());
		assertEquals("0700", deadline.getDeadlineTime());
		assertEquals("Completed", deadline.getTaskType());
		
		int[] index2 = {2};
		
		logic.executeUpdateTaskByAttribute(taskList, index2, "taskname", "Event100");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		EventTask event = (EventTask) taskList.get(1);
		assertEquals("2015-12-13", event.getStartDate());
		assertEquals("0800", event.getStartTime());
		assertEquals("2015-12-14", event.getEndDate());
		assertEquals("0800", event.getEndTime());
		
		logic.executeUpdateTaskByAttribute(taskList, index2, "startdate", "2015-12-11");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		event = (EventTask) taskList.get(1);
		assertEquals("2015-12-11", event.getStartDate());
		assertEquals("0800", event.getStartTime());
		assertEquals("2015-12-14", event.getEndDate());
		assertEquals("0800", event.getEndTime());
		
		logic.executeUpdateTaskByAttribute(taskList, index2, "starttime", "0900");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		event = (EventTask) taskList.get(1);
		assertEquals("2015-12-11", event.getStartDate());
		assertEquals("0900", event.getStartTime());
		assertEquals("2015-12-14", event.getEndDate());
		assertEquals("0800", event.getEndTime());
		
		logic.executeUpdateTaskByAttribute(taskList, index2, "enddate", "2015-12-13");
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
		
		logic.executeUpdateTaskByAttribute(taskList, index2, "taskType", "Completed");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		event = (EventTask) taskList.get(1);
		assertEquals("2015-12-11", event.getStartDate());
		assertEquals("0900", event.getStartTime());
		assertEquals("2015-12-13", event.getEndDate());
		assertEquals("0900", event.getEndTime());
		assertEquals("Completed", event.getTaskType());
		
		logic.executeUpdateTaskByAttribute(taskList, index2, "taskType", "Archived");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("Event100", taskList.get(1).getTaskName());
		event = (EventTask) taskList.get(1);
		assertEquals("2015-12-11", event.getStartDate());
		assertEquals("0900", event.getStartTime());
		assertEquals("2015-12-13", event.getEndDate());
		assertEquals("0900", event.getEndTime());
		assertEquals("Completed", event.getTaskType());
		
		int[] index3 = {5};
		logic.executeUpdateTaskByAttribute(taskList, index3, "taskname", "floatthere");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("floatthere", taskList.get(4).getTaskName());
		assertEquals("null", taskList.get(4).getTaskType());
		
		logic.executeUpdateTaskByAttribute(taskList, index3, "tasktype", "Completed");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("floatthere", taskList.get(4).getTaskName());
		assertEquals("Completed", taskList.get(4).getTaskType());
		
		logic.executeUpdateTaskByAttribute(taskList, index3, "taskType", "Archived");
		taskList = TaskMemory.getInstance().getCombinedTaskList();
		assertEquals(5, taskList.size());
		assertEquals("floatthere", taskList.get(4).getTaskName());
		assertEquals("Completed", taskList.get(4).getTaskType());
		
	}
	
	@Test
	public void saveTest(){
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		logic.executeCreateTask("Float1", null, null, null, null);
		logic.executeCreateTask("Deadline1", null,null, "2015-12-13", "0800");
		logic.executeCreateTask("Event3", "2015-12-13", "0800", "2015-12-14", "0800");
		logic.executeCreateTask("Event4", "2015-12-14", "0800", "2015-12-15", "0800");
		logic.executeCreateTask("Event5", "2015-12-15", "0800", "2015-12-16", "0800");
		
		logic.save();
	}
	
	@Test
	public void loadTest(){
		ClearTaskTest();
		assertEquals(0, taskList.size());
		
		taskList = Storage.getInstance().load();
		assertEquals(5, taskList.size());
	}

}
