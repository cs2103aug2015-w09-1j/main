package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import command.CreateTask;
import command.TaskMemory;
import command.UpdateTask;
import model.FloatingTask;
import model.Task;

public class TestUpdate {
	private CreateTask crTest;
	private UpdateTask updTest;
	private ArrayList<Task> test = TaskMemory.getInstance().getTaskList();

	@Test
	/* Test update task and undo
	 * @author Jason
	 */
	public void updateTaskTest() {
		test.clear();
		Task t = new FloatingTask("Meeting with boss", null);
		crTest = new CreateTask(t);
		crTest.execute();
		Task new_t = new FloatingTask("Change subject", null);
		updTest = new UpdateTask(t, new_t);
		updTest.execute();
		assertSame(test.get(0), new_t);
		updTest.undo();
		assertSame(test.get(0), t);
		
	}

}
