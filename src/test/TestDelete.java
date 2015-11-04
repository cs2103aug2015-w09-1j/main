package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import command.CreateTask;
import command.DeleteTask;
import command.TaskMemory;
import model.FloatingTask;
import model.Task;

public class TestDelete {
	private CreateTask crTest;
	private DeleteTask delTest;
	private ArrayList<Task> test = TaskMemory.getInstance().getTaskList();

	@Test
	/* Test delete task and undo
	 * @author Jason
	 */
	public void deleteTaskTest() {
		test.clear();
		Task t = new FloatingTask("Meeting with boss", null);
		crTest = new CreateTask(t);
		crTest.execute();
		delTest = new DeleteTask(t);
		delTest.execute();
		assertEquals(test.size(),0);
	}

}
