package test;
/*
 * @@author Jason (A0127830J)
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import command.DeleteBulkTask;
import command.TaskMemory;
import model.FloatingTask;
import model.Task;

public class TestDeleteBulk {
	private DeleteBulkTask delBTest;
	private ArrayList<Task> test = TaskMemory.getInstance().getTaskList();

	@Test
	public void deleteBulkTaskTest() {
		test.clear();
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

}
