package test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import command.TaskMemory;
import model.DeadlineTask;
import model.EventTask;
import model.Task;
import util.Storage;

public class TaskMemmoryTest {
	private TaskMemory tm = TaskMemory.getInstance();
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/*
	@Test
	public void testTaskMemory() {
		assertEquals(Storage.getInstance().load(), tm.getTaskList());
	}
	*/
	
	
	
	@Test
	public void testGetNoArchivedList() {
		ArrayList<Task> temp = tm.getNoArchivedList();
		if (temp.size() > 0) {
			for(Task e:temp) {
				assertThat(e.getTaskType(), not(equalTo("Archived")));
			}
		}
		
	}
	
	@Test
	public void testGetTodayTaskList() {
		ArrayList<Task> today = tm.getTodayTaskList();
		String dateNow = LocalDate.now().toString();
		if (today.size() > 0) {
			for(Task e:today) {
				if (e instanceof DeadlineTask) {
					assertEquals(((DeadlineTask) e).getDeadlineDate(), dateNow);
				} else if (e instanceof EventTask) {
					assertTrue(((EventTask) e).getStartDate().compareTo(dateNow) <= 0);
					assertTrue(((EventTask) e).getEndDate().compareTo(dateNow) >= 0);
					
				}
			}
		}
	}
	
	/*
	@Test
	public void testGetTaskList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetFloatingTask() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetFollowingWeekTask() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetOtherTask() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetArchivedList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetTaskList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetSize() {
		fail("Not yet implemented"); // TODO
	}
	*/

}
