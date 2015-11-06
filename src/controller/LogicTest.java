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
		
	}

}
