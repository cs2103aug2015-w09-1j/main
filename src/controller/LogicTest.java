package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.*;

import org.junit.Test;

public class LogicTest {

	@Test
	public void BuildTaskTest() {
		Task task;
		Logic logic = new Logic();

		/* This is a boundary case for add nothing with empty task name */
		task = logic
				.buildTask(null, "2015-10-18", "2015-10-20", "0800", "1800");
		assertEquals(null, task);

		/* This is a boundary case for add nothing */
		task = logic.buildTask(null, null, null, null, null);
		assertEquals(null, task);

		/* This is a boundary case for add event task */
		task = logic.buildTask("Task1", "2015-10-18", "2015-10-20", "0800",
				"1800");
		if (task instanceof EventTask) {
			assertEquals("Task1", ((EventTask) task).getTaskName());
			assertEquals("2015-10-18", ((EventTask) task).getStartDate());
			assertEquals("2015-10-20", ((EventTask) task).getEndDate());
			assertEquals("0800", ((EventTask) task).getStartTime());
			assertEquals("1800", ((EventTask) task).getEndTime());
		}

		/* This is a boundary case for add deadline task */
		task = logic.buildTask("Task2", null, "2015-10-22", null, "1700");
		if (task instanceof DeadlineTask) {
			assertEquals("Task2", ((DeadlineTask) task).getTaskName());
			assertEquals("2015-10-22", ((DeadlineTask) task).getDeadlineDate());
			assertEquals("1700", ((DeadlineTask) task).getDeadlineTime());
		}

		/* This is a boundary case for add floating task */
		task = logic.buildTask("Task3", null, null, null, null);
		if (task instanceof FloatingTask) {
			assertEquals("Task3", ((FloatingTask) task).getTaskName());
		}

	}

	@Test
	public void DeleteTaskTest() {
		Task task;
		Logic logic = new Logic();
		ArrayList<Task> taskList = new ArrayList<Task>();
		Task taskA = new EventTask("Have Meeting With Kiwi 2103", "15-10-2015",
				"15-10-2015", "1200", "1500");
		Task taskB = new FloatingTask("Eating Dinner w Kenneth.");
		Task taskC = new DeadlineTask("Finish 2103 project.", "16-10-2015", "2359");
		Task taskD = new DeadlineTask("2103 project.", "16-10-2015", "2359");
		
		taskList.add(taskA);
		taskList.add(taskB);
		taskList.add(taskC);
		taskList.add(taskD);
		
		/* This is a boundary case for delete event task */
		task = logic.deleteTask(taskList, 1);
		if (task instanceof EventTask) {
			assertEquals("Have Meeting With Kiwi 2103", ((EventTask) task).getTaskName());
			assertEquals("15-10-2015", ((EventTask) task).getStartDate());
			assertEquals("15-10-2015", ((EventTask) task).getEndDate());
			assertEquals("1200", ((EventTask) task).getStartTime());
			assertEquals("1500", ((EventTask) task).getEndTime());	
		}
		
		/* This is a boundary case for delete floating task */
		task = logic.deleteTask(taskList, 2);
		if (task instanceof FloatingTask) {
			assertEquals("Eating Dinner w Kenneth.", ((FloatingTask) task).getTaskName());
		}
		
		/* This is a boundary case for delete deadline task */
		task = logic.deleteTask(taskList, 3);
		if (task instanceof DeadlineTask) {
			assertEquals("Finish 2103 project.", ((DeadlineTask) task).getTaskName());
			assertEquals("16-10-2015", ((DeadlineTask) task).getDeadlineDate());
			assertEquals("2359", ((DeadlineTask) task).getDeadlineTime());
		}
		
		/* This is a boundary case for delete nothing */
		task = logic.deleteTask(taskList, 5);
		assertEquals(null, task);
		
	}

}
