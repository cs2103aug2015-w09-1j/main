package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import command.*;
import model.*;

public class TestCommands {
	private CreateTask crTest;
	private DeleteTask delTest;
	private UpdateTask updTest;
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
		Task t = new FloatingTask("meeting with boss");
		crTest = new CreateTask(t);
		crTest.execute();
		assertSame(t, test.get(0));
		crTest.undo();
		assertEquals(test.size(), 0);
	}
	
	@Test
	/* Test delete task and undo
	 * @author Jason
	 */
	public void deleteTaskTest() {
		Task t = new FloatingTask("Meeting with boss");
		crTest = new CreateTask(t);
		crTest.execute();
		delTest = new DeleteTask(t);
		delTest.execute();
		assertEquals(test.size(),0);
	}
	
	@Test
	/* Test update task and undo
	 * @author Jason
	 */
	public void updateTaskTest() {
		Task t = new FloatingTask("Meeting with boss");
		crTest = new CreateTask(t);
		crTest.execute();
		Task new_t = new FloatingTask("Change subject");
		updTest = new UpdateTask(t, new_t);
		updTest.execute();
		assertSame(test.get(0), new_t);
		updTest.undo();
		assertSame(test.get(0), t);
		
	}

}
