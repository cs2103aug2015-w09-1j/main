package test;

import static org.junit.Assert.*;

import org.junit.Test;

import command.TaskMemory;
import util.Storage;

public class TaskMemmoryTest {
	private TaskMemory tm = new TaskMemory();

	@Test
	public void testTaskMemory() {
		assertEquals(Storage.getInstance().load(), tm.getTaskList());
	}

	@Test
	public void testGetNoArchivedList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetTodayTaskList() {
		fail("Not yet implemented"); // TODO
	}

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

}
