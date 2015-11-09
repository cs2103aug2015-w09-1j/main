/*@@author Liang Yuan(A0133975W)*/
package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import controller.Controller;
import model.*;

public class SystematicTest {
	ArrayList<Task> taskList;
	Task task;
	DeadlineTask deadline;
	FloatingTask floating;
	EventTask event;
	int size;
	/**
	 * Systematic Test to test flow work
	 * 
	 */
	@Test
	public void initializeTest() throws Exception {
		Controller.executeCMD("set path systematicTest\\");
		Controller.executeCMD("set filename systematicTest");
		Controller.executeCMD("load");
		Controller.executeCMD("display");
		Controller.executeCMD("delete all");
		Controller.executeCMD("show complete");
		Controller.executeCMD("delete all");
		Controller.executeCMD("show archived");
		Controller.executeCMD("delete all");
		(new File("systematicTest\\systematicTest.fxml")).delete();
		(new File("systematicTest")).delete();
	}

	@Test
	public void testSet() throws Exception {
		Controller.executeCMD("set path testFolder\\");
		assertTrue((new File("testFolder")).exists());

		Controller.executeCMD("set filename testFile");
		assertTrue((new File("testFolder\\testFile.fxml")).exists());

		Controller.executeCMD("save");
		assertTrue((new File("testFolder\\testFile.fxml")).exists());
		
		(new File("testFolder\\testFile.fxml")).delete();
		(new File("testFolder")).delete();
		
		(new File("systematicTest\\systematicTest.fxml")).delete();
		
		Controller.executeCMD("set path systematicTest\\");
		Controller.executeCMD("set filename systematicTest");
		(new File("systematicTest\\systematicTest.fxml")).delete();
		(new File("systematicTest")).delete();
	}

	@Test
	public void testAddWithUndo() throws Exception {
		Controller.executeCMD("display");
		Controller.executeCMD("delete all");
		Controller.executeCMD("add deadLine by 2015-12-30 2359");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof DeadlineTask);
		deadline = (DeadlineTask) task;
		assertTrue(deadline.getTaskName().equals("deadLine"));
		assertTrue(deadline.getDeadlineDate().equals("2015-12-30"));
		assertTrue(deadline.getDeadlineTime().equals("23:59"));
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		assertEquals(taskList.size(),0);
		

		Controller.executeCMD("add floating");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof FloatingTask);
		floating = (FloatingTask) task;
		assertTrue(floating.getTaskName().equals("floating"));

		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		assertEquals(taskList.size(),0);

		Controller.executeCMD("add event from 2015-12-25 0800 to 2015-12-30 2300");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof EventTask);
		event = (EventTask) task;
		assertTrue(event.getTaskName().equals("event"));
		assertTrue(event.getStartDate().equals("2015-12-25"));
		assertTrue(event.getStartTime().equals("08:00"));
		assertTrue(event.getEndDate().equals("2015-12-30"));
		assertTrue(event.getEndTime().equals("23:00"));
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		assertEquals(taskList.size(),0);
	}

	@Test
	public void testDeleteWithUndo() throws Exception {
		Controller.executeCMD("display");
		Controller.executeCMD("delete all");
		for (int i = 0; i < 30; i++) {
			Controller.executeCMD("add floating");
		}
		taskList=Controller.getTaskList();
		size=taskList.size();
		assertEquals(size,30);
		assertEquals(size,Controller.getSize());
		
		Controller.executeCMD("undo");
		taskList=Controller.getTaskList();
		size=taskList.size();
		assertEquals(size,29);
		
		Controller.executeCMD("delete 1");
		taskList=Controller.getTaskList();
		size=taskList.size();
		assertEquals(size,28);
		assertEquals(size,Controller.getSize());
		
		Controller.executeCMD("undo");
		taskList=Controller.getTaskList();
		size=taskList.size();
		assertEquals(size,29);
		
		Controller.executeCMD("clear");
		taskList=Controller.getTaskList();
		size=taskList.size();
		assertEquals(size,0);
		assertEquals(size,Controller.getSize());
		
		Controller.executeCMD("undo");
		taskList=Controller.getTaskList();
		size=taskList.size();
		assertEquals(size,29);
	}
	
	@Test
	public void testUpdate() throws Exception {
		Controller.executeCMD("display");
		Controller.executeCMD("delete all");
		Controller.executeCMD("add deadLine by 2015-12-30 2359");
		
		Controller.executeCMD("edit 1 taskName ddl");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		deadline = (DeadlineTask) task;
		assertEquals(deadline.getTaskName(),"ddl");
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		deadline = (DeadlineTask) task;
		assertEquals(deadline.getTaskName(),"deadLine");
		
		Controller.executeCMD("edit 1 endDate 2015-12-29");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		deadline = (DeadlineTask) task;
		assertEquals(deadline.getDeadlineDate(),"2015-12-29");
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		deadline = (DeadlineTask) task;
		assertEquals(deadline.getDeadlineDate(),"2015-12-30");
		
		Controller.executeCMD("edit 1 endTime 2300");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		deadline = (DeadlineTask) task;
		assertEquals(deadline.getDeadlineTime(),"23:00");
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		deadline = (DeadlineTask) task;
		assertEquals(deadline.getDeadlineTime(),"23:59");
		
		Controller.executeCMD("delete 1");		
		Controller.executeCMD("add event from 2015-12-25 0800 to 2015-12-30 2300");
		
		Controller.executeCMD("edit 1 taskName evt");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		event = (EventTask) task;
		assertEquals(event.getTaskName(),"evt");
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		event = (EventTask) task;
		assertEquals(event.getTaskName(),"event");
		
		Controller.executeCMD("edit 1 startDate 2015-12-24");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		event = (EventTask) task;
		assertEquals(event.getStartDate(),"2015-12-24");
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		event = (EventTask) task;
		assertEquals(event.getStartDate(),"2015-12-25");
		
		Controller.executeCMD("edit 1 startTime 0700");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		event = (EventTask) task;
		assertEquals(event.getStartTime(),"07:00");
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		event = (EventTask) task;
		assertEquals(event.getStartTime(),"08:00");
		
		Controller.executeCMD("edit 1 endDate 2015-12-29");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		event = (EventTask) task;
		assertEquals(event.getEndDate(),"2015-12-29");
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		event = (EventTask) task;
		assertEquals(event.getEndDate(),"2015-12-30");
		
		Controller.executeCMD("edit 1 endTime 2200");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		event = (EventTask) task;
		assertEquals(event.getEndTime(),"22:00");
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		event = (EventTask) task;
		assertEquals(event.getEndTime(),"23:00");
	
		Controller.executeCMD("delete 1");		
		Controller.executeCMD("add floating");
		
		Controller.executeCMD("edit 1 taskName flt");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		floating = (FloatingTask) task;
		assertEquals(floating.getTaskName(),"flt");
		
		Controller.executeCMD("undo");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		floating = (FloatingTask) task;
		assertEquals(floating.getTaskName(),"floating");
		Controller.executeCMD("delete 1");
	}

	@Test
	public void testSaveAndLoad() throws Exception {
		Controller.executeCMD("display");
		Controller.executeCMD("delete all");
		for (int i = 0; i < 30; i++) {
			Controller.executeCMD("add floating");
		}
		Controller.executeCMD("save");
		taskList = Controller.getTaskList();
		Controller.executeCMD("load");
		assertEquals(taskList.size(),Controller.getTaskList().size());
	}
	
	@Test
	public void testSearchAndShow() throws Exception {
		Controller.executeCMD("display");
		Controller.executeCMD("delete all");
		Controller.executeCMD("add deadLine by 2015-12-20 2359");
		Controller.executeCMD("add event from 2015-12-25 0800 to 2015-12-30 2300");
		Controller.executeCMD("add floating");
		
		Controller.executeCMD("show on 2015-12-20");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof DeadlineTask);
		
		Controller.executeCMD("display");
		Controller.executeCMD("show on 2015-12-30");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof EventTask);
		
		Controller.executeCMD("display");
		Controller.executeCMD("show by 2015-12-30");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof DeadlineTask);
		task = taskList.get(1);
		assertTrue(task instanceof EventTask);
		
		Controller.executeCMD("display");
		Controller.executeCMD("search on 2015-12-20");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof DeadlineTask);
		
		Controller.executeCMD("display");
		Controller.executeCMD("search on 2015-12-30");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof EventTask);
		
		Controller.executeCMD("display");
		Controller.executeCMD("search by 2015-12-30");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof DeadlineTask);
		task = taskList.get(1);
		assertTrue(task instanceof EventTask);
		
		Controller.executeCMD("display");
		Controller.executeCMD("search deadLine");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof DeadlineTask);
		
		Controller.executeCMD("display");
		Controller.executeCMD("search event");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof EventTask);
		
		Controller.executeCMD("display");
		Controller.executeCMD("search floating");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof FloatingTask);
		
		Controller.executeCMD("display");
		Controller.executeCMD("search floating deadLine event");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof FloatingTask);
		task = taskList.get(1);
		assertTrue(task instanceof DeadlineTask);
		task = taskList.get(2);
		assertTrue(task instanceof EventTask);
	}

	//@@author Jason (A0127830J)
	@Test
	public void testCompleteUncomplete() throws Exception {
		Controller.executeCMD("display");
		Controller.executeCMD("delete all");
		Controller.executeCMD("show complete");
		Controller.executeCMD("delete all");
		Controller.executeCMD("add deadLine by 2015-12-20 2359");
		Controller.executeCMD("add event from 2015-12-25 0800 to 2015-12-30 2300");
		Controller.executeCMD("add floating");

		Controller.executeCMD("complete 1");
		taskList = Controller.getCompletedList();
		task = taskList.get(0);
		assertTrue(task instanceof DeadlineTask);
		
		Controller.executeCMD("display");
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertFalse(task instanceof DeadlineTask);
		
		Controller.executeCMD("show complete");
		Controller.executeCMD("uncomplete 1");
		taskList = Controller.getCompletedList();
		assertTrue(taskList.size()==0);
		
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof DeadlineTask);
		
		Controller.executeCMD("complete 2");
		taskList = Controller.getCompletedList();
		task = taskList.get(0);
		assertTrue(task instanceof EventTask);
		
		taskList = Controller.getTaskList();
		task = taskList.get(1);
		assertFalse(task instanceof EventTask);
		
		Controller.executeCMD("show complete");
		Controller.executeCMD("uncomplete 1");
		taskList = Controller.getCompletedList();
		assertTrue(taskList.size()==0);
		
		taskList = Controller.getTaskList();
		task = taskList.get(1);
		assertTrue(task instanceof EventTask);
		
		Controller.executeCMD("complete 3");
		taskList = Controller.getCompletedList();
		task = taskList.get(0);
		assertTrue(task instanceof FloatingTask);
		
		Controller.executeCMD("show complete");
		Controller.executeCMD("uncomplete 1");
		taskList = Controller.getCompletedList();
		assertTrue(taskList.size()==0);
		
		taskList = Controller.getTaskList();
		task = taskList.get(2);
		assertTrue(task instanceof FloatingTask);
		
		Controller.executeCMD("complete 1-3");
		taskList = Controller.getTaskList();
		assertTrue(taskList.size()==0);
		
		taskList = Controller.getCompletedList();
		assertTrue(taskList.size()==3);
		
		Controller.executeCMD("show complete");
		Controller.executeCMD("uncomplete 1-3");
		taskList = Controller.getTaskList();
		assertTrue(taskList.size()==3);
		
		taskList = Controller.getCompletedList();
		assertTrue(taskList.size()==0);
	}
	
	//@@author Jason (A0127830J)
	@Test
	public void testArchiveUnarchive() throws Exception {
		Controller.executeCMD("display");
		Controller.executeCMD("delete all");
		Controller.executeCMD("add deadLine by 2015-12-20 2359");
		Controller.executeCMD("add event from 2015-12-25 0800 to 2015-12-30 2300");
		Controller.executeCMD("add floating");
		
		Controller.executeCMD("archive 1");
		taskList = Controller.getArchivedList();
		task = taskList.get(0);
		assertTrue(task instanceof DeadlineTask);
		
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertFalse(task instanceof DeadlineTask);
		
		Controller.executeCMD("show archived");
		Controller.executeCMD("unarchived 1");
		taskList = Controller.getArchivedList();
		assertTrue(taskList.size()==0);
		
		taskList = Controller.getTaskList();
		task = taskList.get(0);
		assertTrue(task instanceof DeadlineTask);
		
		Controller.executeCMD("archive 2");
		taskList = Controller.getArchivedList();
		task = taskList.get(0);
		assertTrue(task instanceof EventTask);
		
		taskList = Controller.getTaskList();
		task = taskList.get(1);
		assertFalse(task instanceof EventTask);
		
		Controller.executeCMD("show archived");
		Controller.executeCMD("unarchived 1");
		taskList = Controller.getArchivedList();
		assertTrue(taskList.size()==0);
		
		taskList = Controller.getTaskList();
		task = taskList.get(1);
		assertTrue(task instanceof EventTask);
		
		Controller.executeCMD("archive 3");
		taskList = Controller.getArchivedList();
		task = taskList.get(0);
		assertTrue(task instanceof FloatingTask);
		
		Controller.executeCMD("show archived");
		Controller.executeCMD("unarchived 1");
		taskList = Controller.getArchivedList();
		assertTrue(taskList.size()==0);
		
		taskList = Controller.getTaskList();
		task = taskList.get(2);
		assertTrue(task instanceof FloatingTask);
		
		Controller.executeCMD("archive 1-3");
		taskList = Controller.getTaskList();
		assertTrue(taskList.size()==0);
		
		taskList = Controller.getArchivedList();
		assertTrue(taskList.size()==3);
		
		Controller.executeCMD("show archived");
		Controller.executeCMD("unarchived 1-3");
		taskList = Controller.getTaskList();
		assertTrue(taskList.size()==3);
		
		taskList = Controller.getArchivedList();
		assertTrue(taskList.size()==0);
	}

	//@@author Jason (A0127830J)
	@Test
	public void testOtherShow() throws Exception {
		Controller.executeCMD("display");
		Controller.executeCMD("delete all");
		Controller.executeCMD("add deadLine by today 2359");
		Controller.executeCMD("add event from today 0800 to today 2300");
		Controller.executeCMD("add floating");
		
		Controller.executeCMD("show today");
		taskList = Controller.getTodayTaskList();
		assertTrue(taskList.size()==2);
		
		Controller.executeCMD("show floating");
		taskList = Controller.getFloatingTaskList();
		assertTrue(taskList.size()==1);
	}
	
	//@@author Liang Yuan(A0133975W)
	@Test
	public void testHelpThenExit() throws Exception {
		Controller.executeCMD("help");
		assertEquals(Controller.getHelpString(),"add <name>\nadd <name> from <time> to <time>\nadd <name> by   <deadline>\ndelete  <id>\nsearch  <id>\narchive <id>\nedit <id> <attribute> <info>\nset  path     <storage path>\nset  filename <filename>\nshow on <date>\nshow by <date>\nundo\n");
		
		Controller.executeCMD("exit");
		(new File("systematicTest\\systematicTest.fxml")).delete();
		(new File("systematicTest")).delete();
	}
}
