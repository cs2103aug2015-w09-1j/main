package test;
/*
 * @@author Jason (A0127830J)
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import command.CreateBulkTask;
import command.TaskMemory;
import model.FloatingTask;
import model.Task;

public class TestCreateBulk {
	private CreateBulkTask crBTest;
	private ArrayList<Task> test = TaskMemory.getInstance().getTaskList();

	@Test
	public void createBulkTaskTest() {
		test.clear();
		ArrayList<Task> temp = new ArrayList<Task>();
		temp.add(new FloatingTask("meeting", null));
		temp.add(new FloatingTask("abcd", null));
		crBTest = new CreateBulkTask(temp);
		crBTest.execute();
		assertEquals(2, test.size());
		crBTest.execute();
		assertEquals(4, test.size());
	}

}
