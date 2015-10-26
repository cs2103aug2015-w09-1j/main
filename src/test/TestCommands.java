package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import command.*;
import model.*;

public class TestCommands {
	private CreateTask crTest;
	private DeleteTask delTest;
	private UpdateTask updTest;
	private CreateBulkTask crBTest;
	private DeleteBulkTask delBTest;
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
	
	@Test
	/* Test delete task and undo
	 * @author Jason
	 */
	public void deleteTaskTest() {
		Task t = new FloatingTask("Meeting with boss", null);
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
	
	@Test
	public void createBulkTaskTest() {
		ArrayList<Task> temp = new ArrayList<Task>();
		temp.add(new FloatingTask("meeting", null));
		temp.add(new FloatingTask("abcd", null));
		crBTest = new CreateBulkTask(temp);
		crBTest.execute();
		assertEquals(2, test.size());
		crBTest.execute();
		assertEquals(4, test.size());
	}
	
	@Test
	public void deleteBulkTaskTest() {
		ArrayList<Task> temp = new ArrayList<Task>();
		Task a = new FloatingTask("meeting", null);
		Task b = new FloatingTask("abcd", null);
		test.add(a);
		test.add(b);
		temp.add(a);
		temp.add(b);
		delBTest = new DeleteBulkTask(temp);
		delBTest.execute();
		assertEquals(0, test.size());
		delBTest.undo();
		assertEquals(2, test.size());
	}
	
	@Test
	public void compareDate() {
		assertEquals(true, "2016-1-25".compareTo("2015-12-25") > 0);
		assertEquals("2015-10-26", LocalDate.now().toString());
	}

}
