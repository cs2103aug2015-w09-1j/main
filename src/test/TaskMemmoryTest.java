package test;

import static org.junit.Assert.*;

import org.junit.Test;

import command.TaskMemory;
import util.Storage;

public class TaskMemmoryTest {

	@Test
	public void testTaskMemory() {
		TaskMemory tm = new TaskMemory();
		assertEquals(Storage.getInstance().load(), tm.getTaskList());
	}

	@Test
	public void testGetInstance() {
		fail("Not yet implemented"); // TODO
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
