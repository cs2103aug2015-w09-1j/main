package test;
/*
 * @@author Jason (A0127830J)
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import command.CreateTask;
import command.TaskMemory;
import model.FloatingTask;
import model.Task;

public class TestCreate {
	private CreateTask crTest;
	private ArrayList<Task> test = TaskMemory.getInstance().getTaskList();

	@Before
	public void initialize() {
		TaskMemory.getInstance().getTaskList().clear();
	}

	@Test
	/* Test task creation and undo
	 * @author Jason
	 */
	public void createTaskTest() {
		Task t = new FloatingTask("meeting with boss", null);
		crTest = new CreateTask(t);
		crTest.execute();
		assertSame(t, test.get(0));
		crTest.undo();
		assertEquals(test.size(), 0);
	}

}
