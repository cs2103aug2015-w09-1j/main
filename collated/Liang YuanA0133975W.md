# Liang YuanA0133975W
###### src\test\SystematicTest.java
``` java
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
	 * @author Liang Yuan
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

	@Test
	public void testCompleteUncomplete() throws Exception {
		Controller.executeCMD("display");
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
	
	@Test
	public void testHelpThenExit() throws Exception {
		Controller.executeCMD("help");
		assertEquals(Controller.getHelpString(),"add <name>\nadd <name> from <time> to <time>\nadd <name> by   <deadline>\ndelete  <id>\nsearch  <id>\narchive <id>\nedit <id> <attribute> <info>\nset  path     <storage path>\nset  filename <filename>\nshow on <date>\nshow by <date>\nundo\n");
		
		Controller.executeCMD("exit");
		(new File("systematicTest\\systematicTest.fxml")).delete();
		(new File("systematicTest")).delete();
	}
}
```
###### src\test\TestStorage.java
``` java
package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.DeadlineTask;
import model.EventTask;
import model.FloatingTask;
import model.Task;
import util.Storage;

public class TestStorage {
	Storage test = Storage.getInstance();
/*
	@Test
	public void test_getInstance() {
		// test for default setting
		assertEquals(test.getfileName(), "SilentJarvis.fxml");
	}
	*/

	/**
	 * setPath(String): 1.should be able to ignore the spaces out of valid
	 * content
	 * 
	 * applied partition
	 */
	@Test
	public void test_setPath() {
		test.reset();
		
		// test for case without space
		test.setPath("path");
		assertEquals(test.getPath(), "path\\");

		// test for case with space in head
		test.setPath("        path");
		assertEquals(test.getPath(), "path\\");

		// test for case with space in tail
		test.setPath("path        ");
		assertEquals(test.getPath(), "path\\");
		
		new File(test.getPath()).delete();
	}

	/**
	 * setFilename(String): 1.should be able to ignore the spaces out of valid
	 * content; 2.should always set filename end with .fxml
	 * 
	 * applied partition
	 */
	@Test
	public void test_setFilename() {
		test.reset();
		
		// test for case without space
		test.setfileName("filename");
		assertEquals(test.getfileName(), "filename.fxml");

		// test for case with space in head
		test.setfileName("        filename");
		assertEquals(test.getfileName(), "filename.fxml");

		// test for case with space in tail
		test.setfileName("filename        ");
		assertEquals(test.getfileName(), "filename.fxml");

		// test for case with tail that can cause file type mess
		test.setfileName("filename.txt");
		assertEquals(test.getfileName(), "filename.fxml");
		
		new File(test.getfileName()).delete();
	}

	/**
	 * existFolder(): 1. return true if folder exists; 2. return true if folder
	 * does not exist but have been created successfully;
	 * 
	 * applied partition
	 */
	@Test
	public void test_existFolder() {
		// test for default setting
		test.reset();
		assertTrue(test.existFolder());

		// test for parent folder
		test.setPath("..\\");
		assertTrue(test.existFolder());

		// test for creating new folder
		File canNotExist = new File("newFolder\\");
		if ((canNotExist.exists() && canNotExist.isDirectory())) {
			canNotExist.delete();
		}
		test.setPath("newFolder\\");
		assertTrue(test.existFolder());
		new File(test.getPath()).delete();
	}

	/**
	 * existFile(): 1. return true if file exists; 2. return true if file does
	 * not exist but have been created successfully;
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_existFile() throws IOException {
		// test for default setting
		test.reset();
		assertTrue(test.existFile());

		// test for creating new file
		test.setfileName("newFile");
		assertTrue(test.existFile());
		new File(test.getfileName()).delete();

		// test for checking existed file
		File canNotExist = new File("newFile.fxml");
		if (canNotExist.exists()) {
			canNotExist.delete();
		}
		test.setfileName("newFile.fxml");
		assertTrue(test.existFile());
		new File(test.getfileName()).delete();
	}

	/**
	 * save(ArrayList<Task>): 1. save an ArrayList in FXML file
	 * 
	 * load(): 1. return an ArrayList the same as what been saved
	 * 
	 * Due to whether save and load are successful depends on the whether the
	 * two ArrayLists are the same, these two methods can only be tested
	 * together.
	 */
	@Test
	public void test_saveAndLoad() {
		test.reset();
		test.setfileName("test");
		ArrayList<Task> listToSave = new ArrayList<Task>();
		ArrayList<Task> listToLoad = new ArrayList<Task>();

		test.save(listToSave);
		listToLoad = test.load();

		// test for default setting
		assertEquals(listToSave, listToLoad);

		FloatingTask A = new FloatingTask();
		A.setTaskName("name");
		listToSave.add(A);

		test.save(listToSave);
		listToLoad = test.load();
		FloatingTask Ax = (FloatingTask) listToLoad.get(0);

		// test for case after one task has been added
		assertEquals(A.getTaskName(), Ax.getTaskName());

		DeadlineTask B = new DeadlineTask();
		B.setDeadlineDate("ddl date");
		listToSave.add(B);

		EventTask C = new EventTask();
		C.setEndTime("end time");
		listToSave.add(C);

		test.save(listToSave);
		listToLoad = test.load();
		DeadlineTask Bx = (DeadlineTask) listToLoad.get(1);
		EventTask Cx = (EventTask) listToLoad.get(2);

		// test for case after different tasks have been added
		assertEquals(A.getTaskName(), Ax.getTaskName());
		assertEquals(B.getDeadlineDate(), Bx.getDeadlineDate());
		assertEquals(C.getEndTime(), Cx.getEndTime());
		new File(test.getfileName()).delete();
	}
}
```
###### src\util\Storage.java
``` java

package util;

import model.Task;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Storage {
	/**
	 * This is a class to deal with all operations relative to local date
	 *
	 * @author Liang Yuan
	 */
	private String path = "";
	private String fileName = "SilentJarvis.fxml";
	private static Storage theOne = null;
	private String tempStore;

	private Storage() {
		reset();
	}

	public void reset() {
		path = "";
		fileName = "SilentJarvis.fxml";
		return;
	}

	public boolean setPath(String path) {
		Boolean isSet = false;
		tempStore = this.path;
		this.path = path.trim().concat("\\");
		if (existFolder()) {
			isSet = true;
		} else {
			this.path = tempStore;
		}
		return isSet;
	}

	public boolean setfileName(String fileName) {
		Boolean isSet = false;
		tempStore = this.fileName;
		this.fileName = fileName.trim().split("\\.")[0].concat(".fxml");
		if (existFile()) {
			isSet = true;
		} else {
			this.fileName = tempStore;
		}
		return isSet;
	}

	public static Storage getInstance() {
		if (theOne == null)
			theOne = new Storage();
		return theOne;
	}

	public String getPath() {
		return path;
	}

	public String getfileName() {
		return fileName;
	}

	public boolean existFolder() {
		Boolean isSet = false;
		File pathToCheck = new File(path);
		if (path.compareTo("") == 0 || (pathToCheck.exists() && pathToCheck.isDirectory())) {
			isSet = true;
		} else {
			isSet = pathToCheck.mkdirs();
		}
		return isSet;
	}

	public boolean existFile() {
		Boolean isSet = false;
		if (existFolder()) {
			File fileToCheck = new File(path + fileName);
			if (fileToCheck.exists() && !fileToCheck.isDirectory()) {
				isSet = true;
			} else {
				try {
					save(new ArrayList<Task>());
					isSet = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return isSet;
	}

	public void save(ArrayList<Task> listToSave) {
		try {
			if (existFolder()) {
				XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path + fileName)));
				encoder.writeObject(listToSave);
				encoder.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Task> load() {
		ArrayList<Task> listToload = null;
		try {
			if (existFile()) {
				XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path + fileName)));
				listToload = (ArrayList<Task>) decoder.readObject();
				decoder.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listToload;
	}
}
```
###### src\view\GUIView.java
``` java
package view;

import java.util.ArrayList;
import controller.Controller;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DeadlineTask;
import model.EventTask;
import model.FloatingTask;
import model.Task;
import util.Storage;

/**
 * This is a class to build GUI directly
 * */
public class GUIView{
	private int taskCount;
	private int gridRowCount;
	private Image deadlineImage;
	private Image eventImage;
	private Image floatingImage;
	private Image completeImage;
	private Image archivedImage;
	private Image helpImage;
	private Image floatingTitle;
	private Image todayTitle;
	private Image followingTitle;
	private Image seeMoreTitle;
	private Image backgroundImage;
	private Image iconImage;
	private static GUIView theOne;
	private GridPane TaskDisplayGrid;
	protected TextField userCommandBlock;
	protected ScrollPane TaskDisplayBlock;
	private Label message;
	private Label signal;	
	final private Font HELP_TITLE_FONT = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 17);
	final private Font HELP_CONTENT_FONT = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 14);
	final private Font TASK_NAME_FONT = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 17);
	final private Font TASK_INFO_FONT = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 17);
	final private Font MESSAGE_FONT = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 22);
	final private Font SIGNAL_FONT = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14);
	final private Color FLOATING_COLOR = Color.web("#039ed3");
	final private Color EVENT_COLOR = Color.web("#17a42a");
	final private Color DEADLINE_COLOR = Color.web("#b9ac1d");
	final private Color COMPLETED_COLOR = Color.web("#898989");
	final private Color ARCHIVED_COLOR = Color.web("#ae31f6");
	final private Color COMMON_COLOR = Color.web("#039ed3");
	final private Color WARNING_COLOR = Color.web("#ff0000");
	final private Color SAFE_COLOR = Color.web("#17a42a");

	private GUIView(){
	}
	
	protected static GUIView getInstance() {
		if (theOne == null) {
			theOne = new GUIView();
		}
		return theOne;
	}
	
	protected void buildGUI(Stage primaryStage) throws Exception {		
		loadImage();

		initialStage(primaryStage);

		primaryStage.show();

		buildView(primaryStage);

		showPartitionList(1);
	}

	private void loadImage() {
		backgroundImage = new Image(getClass().getResourceAsStream("/resource/back3.png"));
		iconImage = new Image(getClass().getResourceAsStream("/resource/icon.png"));
		deadlineImage = new Image(getClass().getResourceAsStream("/resource/deadlineTask.png"));
		eventImage = new Image(getClass().getResourceAsStream("/resource/eventTask.png"));
		floatingImage = new Image(getClass().getResourceAsStream("/resource/floatingTask.png"));
		completeImage = new Image(getClass().getResourceAsStream("/resource/completeTask.png"));
		archivedImage = new Image(getClass().getResourceAsStream("/resource/archivedTask.png"));
		floatingTitle = new Image(getClass().getResourceAsStream("/resource/floating.png"));
		todayTitle = new Image(getClass().getResourceAsStream("/resource/today.png"));
		followingTitle = new Image(getClass().getResourceAsStream("/resource/following.png"));
		seeMoreTitle = new Image(getClass().getResourceAsStream("/resource/seeMore.png"));
		helpImage =new Image(getClass().getResourceAsStream("/resource/help.png"));
	}

	private void initialStage(Stage primaryStage) {
		primaryStage.setTitle("Silent Jarvis");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(iconImage);
	}

	private void buildView(Stage primaryStage) {
		GridPane mainGrid = new GridPane();
		mainGrid.setHgap(5);
		mainGrid.setVgap(20);
		mainGrid.setPadding(new Insets(10, 5, 5, 25));

		ImageView mainBack = new ImageView(backgroundImage);
		Group holdBack = new Group();
		holdBack.getChildren().addAll(mainBack, mainGrid);

		StackPane SystemMessageBlock = new StackPane();
		buildSysMsgBlk(SystemMessageBlock);
		mainGrid.add(SystemMessageBlock, 0, 1);

		TaskDisplayBlock = new ScrollPane();
		buildTskDisBlk();
		mainGrid.add(TaskDisplayBlock, 0, 2);

		userCommandBlock = new TextField();
		userCommandBlock.setId("text-field");
		userCommandBlock.requestFocus();
		mainGrid.add(userCommandBlock, 0, 3);

		Scene mainScene = new Scene(holdBack, 820, 660);
		primaryStage.setScene(mainScene);

		GUIController.dragStage(mainGrid, primaryStage);
	}

	private void buildSysMsgBlk(StackPane SystemMessageBlock) {
		SystemMessageBlock.setPrefSize(770, 40);

		message = new Label();
		message.setId("msg");
		message.setFont(MESSAGE_FONT);

		signal = new Label();
		signal.setId("sig");
		signal.setFont(SIGNAL_FONT);
		signal.setTextFill(SAFE_COLOR);

		SystemMessageBlock.getChildren().add(message);
		StackPane.setAlignment(message, Pos.TOP_LEFT);

		SystemMessageBlock.getChildren().add(signal);
		StackPane.setAlignment(signal, Pos.BOTTOM_RIGHT);

		showWelcome();
	}

	private void buildTskDisBlk() {
		TaskDisplayBlock.setPrefSize(770, 500);
		TaskDisplayBlock.setOpacity(0.9);
		TaskDisplayBlock.setHbarPolicy(ScrollBarPolicy.NEVER);

		TaskDisplayGrid = new GridPane();
		TaskDisplayGrid.setId("task-grid");
		TaskDisplayGrid.setHgap(3);
		TaskDisplayGrid.setVgap(3);
		TaskDisplayGrid.setPadding(new Insets(4, 4, 4, 4));

		TaskDisplayBlock.setContent(TaskDisplayGrid);
	}

	protected void showWelcome() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Welcome to SilentJarvis! Recent tasks are listed below");

		signal.setText("");
	}

	protected void showError() {
		message.setTextFill(WARNING_COLOR);
		message.setText("Error! Invalid or wrong format of command.");

		signal.setText("");
	}

	protected void showToday() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Today's tasks are listed below");

		signal.setText("");
	}

	protected void showSetFilename() {
		message.setTextFill(COMMON_COLOR);
		message.setText("New filename: " + Storage.getInstance().getfileName());

		signal.setText("Set successfully!");
	}

	protected void showSetPath() {
		message.setTextFill(COMMON_COLOR);
		message.setText("New path: " + Storage.getInstance().getPath());

		signal.setText("Set successfully!");
	}

	protected void showAll() {
		message.setTextFill(COMMON_COLOR);
		message.setText("All tasks are listed below");

		signal.setText("");
	}

	protected void showArchived() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Archived tasks are listed below");

		signal.setText("");
	}

	protected void showCompleted() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Completed tasks are listed below");

		signal.setText("");
	}

	protected void showSearch() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Search results");

		signal.setText("");
	}

	protected void showFloating() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Floating tasks are listed below");

		signal.setText("");
	}

	protected void showBy() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Tasks before selected date are listed below");

		signal.setText("");
	}

	protected void showOn() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Tasks on selected date are listed below");

		signal.setText("");
	}
	
	protected void showSave() {
		signal.setText("Saved to " + Storage.getInstance().getPath() + Storage.getInstance().getfileName());
	}

	protected void showAdd() {
		signal.setText("New task added!");
	}

	protected void showDelete() {
		signal.setText("Task Deleted!");
	}

	protected void showUpdate() {
		signal.setText("Task Edited!");
	}

	protected void showUndo() {
		signal.setText("Undo successfully!");
	}

	protected void showLoad() {
		signal.setText("Loaded from " + Storage.getInstance().getPath() + Storage.getInstance().getfileName());
	}

	protected void showComplete() {
		signal.setText("Task complete!");
	}

	protected void showUnComOrArc() {
		signal.setText("Task recovered!");
	}

	protected void showArchive() {
		signal.setText("Task archived!");
	}

	protected void showClear() {
		signal.setText("All tasks selected have been cleared.");
	}
```
###### view\GUIController.java
``` java

package view;

import java.io.IOException;
import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUIController extends Application {
	private static double xOffset = 0;
	private static double yOffset = 0;
	private static GUIView GUI_VIEW = GUIView.getInstance();
	
	public GUIView getView() {
		return GUI_VIEW;
	}
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage Stage) throws Exception {
		GUI_VIEW.buildGUI(Stage);
		getCommand();
	}
	
	private void getCommand() {
		GUI_VIEW.userCommandBlock.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					String command = GUI_VIEW.userCommandBlock.getText();

					if (!command.equals("")) {
						try {
							execute(command);
						} catch (Exception e) {
							GUI_VIEW.showError();
						}
					}
				}

				if (event.getCode().equals(KeyCode.UP)) {
					GUI_VIEW.TaskDisplayBlock.setVvalue(GUI_VIEW.TaskDisplayBlock.getVvalue() - 0.1f);
				}
				
				if (event.getCode().equals(KeyCode.DOWN)) {
					GUI_VIEW.TaskDisplayBlock.setVvalue(GUI_VIEW.TaskDisplayBlock.getVvalue() + 0.1f);
				}
			}
		});
	}
	
	protected static void dragStage(GridPane grid,final Stage stage) {
		grid.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});

		grid.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
			}
		});
	}
	
	protected static void escClose(Scene scene,final Stage stage) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent t) {
			if (t.getCode() == KeyCode.ESCAPE) {
				stage.close();
			}
		}
	});
	}
	
	private String getCommandType(String command, int i) {
		return command.trim().split("\\s+")[i];
	}

	private void execute(String command) throws Exception {
		String commandType = getCommandType(command, 0);
		switch (commandType) {
		case "add":
			executeAdd(command);
			break;
		case "delete":
			executeDelete(command);
			break;
		case "search":
			executeSearch(command);
			break;
		case "load":
			executeLoad(command);
			break;
		case "save":
			executeSave(command);
			break;
		case "display":
			executeAll(command);
			break;
		case "edit":
			executeUpdate(command);
			break;
		case "complete":
			executeComplete(command);
			break;
		case "archive":
			executeArchive(command);
			break;
		case "unarchived":
			executeUnComOrArc(command);
			break;
		case "uncomplete":
			executeUnComOrArc(command);
			break;
		case "set":
			executeSet(command);
			break;
		case "show":
			executeShow(command);
			break;
		case "undo":
			executeUndo(command);
			break;
		case "exit":
			executeExit(command);
			break;
		case "help":
			executeHelp(command);
			break;
		case "clear":
			executeClear(command);
			break;
		default:
			GUI_VIEW.showError();
			break;
		}
	}

	private void executeClear(String command) {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showClear();
	}

	private void executeHelp(String command) {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showHelp(Controller.getHelpString());
	}

	private void executeExit(String command) {
		Controller.executeCMD(command);
	}

	private void executeUndo(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showUndo();
	}

	private void executeUnComOrArc(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showUnComOrArc();
	}

	private void executeArchive(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showArchive();
	}

	private void executeComplete(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showComplete();
	}

	private void executeUpdate(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showUpdate();
	}

	private void executeAll(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
	}

	private void executeSave(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showSave();
	}

	private void executeLoad(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showLoad();
	}

	private void executeSearch(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showGettedList(Controller.getTaskList());
		GUI_VIEW.showSearch();
	}

	private void executeDelete(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showDelete();
	}

	private void executeShow(String command) throws IOException {
		Controller.executeCMD(command);
		switch (getCommandType(command, 1)) {
		case "archived":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getArchivedList());
			GUI_VIEW.showArchived();
			break;
		case "complete":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getCompletedList());
			GUI_VIEW.showCompleted();
			break;
		case "floating":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getFloatingTaskList());
			GUI_VIEW.showFloating();
			break;
		case "by":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getTaskList());
			GUI_VIEW.showBy();
			break;
		case "on":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getTaskList());
			GUI_VIEW.showOn();
			break;
		case "today":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showGettedList(Controller.getTodayTaskList());
			GUI_VIEW.showToday();
			break;
		default:
			GUI_VIEW.showError();
			break;
		}
	}

	private void executeSet(String command) throws IOException {
		Controller.executeCMD(command);
		switch (getCommandType(command, 1)) {
		case "path":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showSetPath();
			break;
		case "filename":
			GUI_VIEW.userCommandBlock.clear();
			GUI_VIEW.showSetFilename();
			break;
		default:
			GUI_VIEW.showError();
			break;
		}
	}

	private void executeAdd(String command) throws IOException {
		Controller.executeCMD(command);
		GUI_VIEW.userCommandBlock.clear();
		GUI_VIEW.showPartitionList(0);
		GUI_VIEW.showAll();
		GUI_VIEW.showAdd();
	}
}
```
###### view\GUIView.java
``` java
package view;

import java.util.ArrayList;
import controller.Controller;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DeadlineTask;
import model.EventTask;
import model.FloatingTask;
import model.Task;
import util.Storage;

public class GUIView{
	private int taskCount;
	private int gridRowCount;
	private Image deadlineImage;
	private Image eventImage;
	private Image floatingImage;
	private Image completeImage;
	private Image archivedImage;
	private Image helpImage;
	private Image floatingTitle;
	private Image todayTitle;
	private Image followingTitle;
	private Image seeMoreTitle;
	private Image backgroundImage;
	private Image iconImage;
	private static GUIView theOne;
	private GridPane TaskDisplayGrid;
	protected TextField userCommandBlock;
	protected ScrollPane TaskDisplayBlock;
	private Label message;
	private Label signal;	
	final private Font HELP_TITLE_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 17);
	final private Font HELP_CONTENT_FONT = Font.font("Stencil Std", FontWeight.NORMAL, FontPosture.REGULAR, 14);
	final private Font TASK_NAME_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 17);
	final private Font TASK_INFO_FONT = Font.font("Stencil Std", FontWeight.NORMAL, FontPosture.REGULAR, 17);
	final private Font MESSAGE_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 22);
	final private Font SIGNAL_FONT = Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 14);
	final private Color FLOATING_COLOR = Color.web("#039ed3");
	final private Color EVENT_COLOR = Color.web("#17a42a");
	final private Color DEADLINE_COLOR = Color.web("#b9ac1d");
	final private Color COMPLETED_COLOR = Color.web("#898989");
	final private Color ARCHIVED_COLOR = Color.web("#ae31f6");
	final private Color COMMON_COLOR = Color.web("#039ed3");
	final private Color WARNING_COLOR = Color.web("#ff0000");
	final private Color SAFE_COLOR = Color.web("#17a42a");

	private GUIView(){
	}
	
	protected static GUIView getInstance() {
		if (theOne == null) {
			theOne = new GUIView();
		}
		return theOne;
	}
	
	protected void buildGUI(Stage primaryStage) throws Exception {		
		loadImage();

		initialStage(primaryStage);

		primaryStage.show();

		buildView(primaryStage);

		showPartitionList(1);
	}

	private void loadImage() {
		backgroundImage = new Image(getClass().getResourceAsStream("back3.png"));
		iconImage = new Image(getClass().getResourceAsStream("icon.png"));
		deadlineImage = new Image(getClass().getResourceAsStream("deadlineTask.png"));
		eventImage = new Image(getClass().getResourceAsStream("eventTask.png"));
		floatingImage = new Image(getClass().getResourceAsStream("floatingTask.png"));
		completeImage = new Image(getClass().getResourceAsStream("completeTask.png"));
		archivedImage = new Image(getClass().getResourceAsStream("archivedTask.png"));
		floatingTitle = new Image(getClass().getResourceAsStream("floating.png"));
		todayTitle = new Image(getClass().getResourceAsStream("today.png"));
		followingTitle = new Image(getClass().getResourceAsStream("following.png"));
		seeMoreTitle = new Image(getClass().getResourceAsStream("seeMore.png"));
		helpImage =new Image(getClass().getResourceAsStream("help.png"));
	}

	private void initialStage(Stage primaryStage) {
		primaryStage.setTitle("Silent Jarvis");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(iconImage);
	}

	private void buildView(Stage primaryStage) {
		GridPane mainGrid = new GridPane();
		mainGrid.setHgap(5);
		mainGrid.setVgap(20);
		mainGrid.setPadding(new Insets(10, 5, 5, 25));

		ImageView mainBack = new ImageView(backgroundImage);
		Group holdBack = new Group();
		holdBack.getChildren().addAll(mainBack, mainGrid);

		StackPane SystemMessageBlock = new StackPane();
		buildSysMsgBlk(SystemMessageBlock);
		mainGrid.add(SystemMessageBlock, 0, 1);

		TaskDisplayBlock = new ScrollPane();
		buildTskDisBlk();
		mainGrid.add(TaskDisplayBlock, 0, 2);

		userCommandBlock = new TextField();
		userCommandBlock.setId("text-field");
		userCommandBlock.requestFocus();
		mainGrid.add(userCommandBlock, 0, 3);

		Scene mainScene = new Scene(holdBack, 820, 660);
		primaryStage.setScene(mainScene);

		GUIController.dragStage(mainGrid, primaryStage);
	}

	private void buildSysMsgBlk(StackPane SystemMessageBlock) {
		SystemMessageBlock.setPrefSize(770, 40);

		message = new Label();
		message.setId("msg");
		message.setFont(MESSAGE_FONT);

		signal = new Label();
		signal.setId("sig");
		signal.setFont(SIGNAL_FONT);
		signal.setTextFill(SAFE_COLOR);

		SystemMessageBlock.getChildren().add(message);
		StackPane.setAlignment(message, Pos.TOP_LEFT);

		SystemMessageBlock.getChildren().add(signal);
		StackPane.setAlignment(signal, Pos.BOTTOM_RIGHT);

		showWelcome();
	}

	private void buildTskDisBlk() {
		TaskDisplayBlock.setPrefSize(770, 500);
		TaskDisplayBlock.setOpacity(0.9);
		TaskDisplayBlock.setHbarPolicy(ScrollBarPolicy.NEVER);

		TaskDisplayGrid = new GridPane();
		TaskDisplayGrid.setId("task-grid");
		TaskDisplayGrid.setHgap(3);
		TaskDisplayGrid.setVgap(3);
		TaskDisplayGrid.setPadding(new Insets(4, 4, 4, 4));

		TaskDisplayBlock.setContent(TaskDisplayGrid);
	}

	protected void showWelcome() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Welcome to SilentJarvis! Recent tasks are listed below");

		signal.setText("");
	}

	protected void showError() {
		message.setTextFill(WARNING_COLOR);
		message.setText("Error! Invalid or wrong format of command.");

		signal.setText("");
	}

	protected void showToday() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Today's tasks are listed below");

		signal.setText("");
	}

	protected void showSetFilename() {
		message.setTextFill(COMMON_COLOR);
		message.setText("New filename: " + Storage.getInstance().getfileName());

		signal.setText("Set successfully!");
	}

	protected void showSetPath() {
		message.setTextFill(COMMON_COLOR);
		message.setText("New path: " + Storage.getInstance().getPath());

		signal.setText("Set successfully!");
	}

	protected void showAll() {
		message.setTextFill(COMMON_COLOR);
		message.setText("All tasks are listed below");

		signal.setText("");
	}

	protected void showArchived() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Archived tasks are listed below");

		signal.setText("");
	}

	protected void showCompleted() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Completed tasks are listed below");

		signal.setText("");
	}

	protected void showSearch() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Search results");

		signal.setText("");
	}

	protected void showFloating() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Floating tasks are listed below");

		signal.setText("");
	}

	protected void showBy() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Tasks before selected date are listed below");

		signal.setText("");
	}

	protected void showOn() {
		message.setTextFill(COMMON_COLOR);
		message.setText("Tasks on selected date are listed below");

		signal.setText("");
	}
	
	protected void showSave() {
		signal.setText("Saved to " + Storage.getInstance().getPath() + Storage.getInstance().getfileName());
	}

	protected void showAdd() {
		signal.setText("New task added!");
	}

	protected void showDelete() {
		signal.setText("Task Deleted!");
	}

	protected void showUpdate() {
		signal.setText("Task Edited!");
	}

	protected void showUndo() {
		signal.setText("Undo successfully!");
	}

	protected void showLoad() {
		signal.setText("Loaded from " + Storage.getInstance().getPath() + Storage.getInstance().getfileName());
	}

	protected void showComplete() {
		signal.setText("Task complete!");
	}

	protected void showUnComOrArc() {
		signal.setText("Task recovered!");
	}

	protected void showArchive() {
		signal.setText("Task archived!");
	}

	protected void showClear() {
		signal.setText("All tasks selected have been cleared.");
	}

	protected void showHelp(String string) {
		GridPane popUpGrid = new GridPane();
		popUpGrid.setVgap(15);
		popUpGrid.setPadding(new Insets(30, 30, 30, 30));

		ImageView helpBack = new ImageView(helpImage);
		Group holdBack = new Group();
		holdBack.getChildren().addAll(helpBack, popUpGrid);

		Text helpTitle = new Text("Sample format:");
		helpTitle.setId("help-title");
		helpTitle.setFont(HELP_TITLE_FONT);
		helpTitle.setFill(FLOATING_COLOR);
		popUpGrid.add(helpTitle, 0, 0);
		
		Text helpString = new Text(string);
		helpString.setId("help-string");
		helpString.setFont(HELP_CONTENT_FONT);
		helpString.setFill(FLOATING_COLOR);
		popUpGrid.add(helpString, 0, 1);

		Text helpEnd = new Text("Press <ESC> to close.");
		helpEnd.setId("help-end");
		helpEnd.setFont(HELP_CONTENT_FONT);
		helpEnd.setFill(FLOATING_COLOR);
		popUpGrid.add(helpEnd, 0, 2);
		
		Stage secondStage = new Stage();
		secondStage.initStyle(StageStyle.UNDECORATED);
		Scene secondScene = new Scene(holdBack, 340, 340);
		secondStage.setScene(secondScene);
		secondStage.show();
		
		secondScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if (t.getCode() == KeyCode.ESCAPE) {
					secondStage.close();
				}
			}
		});
		
		GUIController.escClose(secondScene,secondStage) ;
		
		GUIController.dragStage(popUpGrid, secondStage);
	}
	
	protected void showPartitionList(int type) {
		gridRowCount = 0;
		taskCount=0;
		
		TaskDisplayGrid.getChildren().clear();		
		
		displayTodayTitle();
		showList(Controller.getTodayTaskList(),type);

		displayFollowingTitle();
		showList(Controller.getFollowingDayTaskList(),type);
		
		displayFloatingTitle();
		showList(Controller.getFloatingTaskList(),type);
	}

	protected void showGettedList(ArrayList<Task> taskList) {
		gridRowCount=0;
		taskCount=0;
		
		TaskDisplayGrid.getChildren().clear();

		showList(taskList,0);
	}
	
	private void showList(ArrayList<Task> taskList,int type){
		int taskSize=taskList.size();
		
		for (int i = 0; i < taskSize; i++) {
			Task task = taskList.get(i);
			
			taskCount++;
			
			if (task instanceof DeadlineTask) {
				displayADeadlineTask((DeadlineTask) task);
			} else if (task instanceof FloatingTask) {
				displayAFloatingTask((FloatingTask) task);
			} else if (task instanceof EventTask) {
				displayAEventTask((EventTask) task);
			}
			
			if ((taskSize>3)&&(type==1)&&(i == 2)) {
				displaySeeMore();
				taskCount+=taskSize-i-1;
				break;
			}
		}
	}
	
	private void displayTodayTitle(){
		GridPane todayPane = new GridPane();
		todayPane.setPrefSize(760, 30);
		Group todayGroup = new Group();
		todayGroup.setId("today-group");
		ImageView todayBack = new ImageView(todayTitle);
		todayGroup.getChildren().addAll(todayBack, todayPane);
		TaskDisplayGrid.add(todayGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private void displayFollowingTitle(){
		GridPane FollowingPane = new GridPane();
		FollowingPane.setPrefSize(760, 30);
		Group FollowingGroup = new Group();
		FollowingGroup.setId("following-group");
		ImageView FollowingBack = new ImageView(followingTitle);
		FollowingGroup.getChildren().addAll(FollowingBack, FollowingPane);
		TaskDisplayGrid.add(FollowingGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private void displayFloatingTitle(){
		GridPane FloatingPane = new GridPane();
		FloatingPane.setPrefSize(760, 30);
		Group FloatingGroup = new Group();
		FloatingGroup.setId("floating-group");
		ImageView FloatingBack = new ImageView(floatingTitle);
		FloatingGroup.getChildren().addAll(FloatingBack, FloatingPane);
		TaskDisplayGrid.add(FloatingGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private void displaySeeMore(){
		GridPane seeMorePane = new GridPane();
		seeMorePane.setPrefSize(760, 30);
		Group seeMoreGroup = new Group();
		seeMoreGroup.setId("see-group");
		ImageView seeMoreBack = new ImageView(seeMoreTitle);
		seeMoreGroup.getChildren().addAll(seeMoreBack, seeMorePane);
		TaskDisplayGrid.add(seeMoreGroup, 0, gridRowCount);
		gridRowCount++;
	}
	
	private void displayAEventTask(EventTask task) {
		GridPane event = new GridPane();
		event.setId("event");
		event.setPrefSize(760, 30);
		event.setHgap(1);
		event.setVgap(1);
		event.setPadding(new Insets(4, 1, 1, 1));

		Group holdBack = new Group();
		ImageView eventBack;
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			eventBack = new ImageView(completeImage);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			eventBack = new ImageView(archivedImage);
		} else {
			eventBack = new ImageView(eventImage);
		}
		holdBack.getChildren().addAll(eventBack, event);

		GridPane nameGrid = new GridPane();
		nameGrid.setPrefSize(375, 25);	
		Text name = new Text();
		name.setId("event-name");
		name.setText(" " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName());
		name.setFont(TASK_NAME_FONT);
		name.setFill(EVENT_COLOR);
		nameGrid.add(name, 0, 0);
		event.add(nameGrid, 0, 0);

		Text info = new Text();
		info.setId("event-info");
		info.setText("  S:  " + task.getStartDate() + " " + task.getStartTime() + "    E:  " + task.getEndDate() + " "
				+ task.getEndTime());
		info.setFont(TASK_INFO_FONT);
		info.setFill(EVENT_COLOR);
		event.add(info, 1, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(COMPLETED_COLOR);
			info.setFill(COMPLETED_COLOR);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(ARCHIVED_COLOR);
			info.setFill(ARCHIVED_COLOR);
		}

		TaskDisplayGrid.add(holdBack, 0, gridRowCount);
		gridRowCount++;
	}

	private void displayAFloatingTask(FloatingTask task) {
		GridPane floating = new GridPane();
		floating.setId("floating");
		floating.setPrefSize(760, 30);
		floating.setHgap(1);
		floating.setVgap(1);
		floating.setPadding(new Insets(4, 1, 1, 1));

		Group holdBack = new Group();
		ImageView floatingBack;
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			floatingBack = new ImageView(completeImage);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			floatingBack = new ImageView(archivedImage);
		} else {
			floatingBack = new ImageView(floatingImage);
		}
		holdBack.getChildren().addAll(floatingBack, floating);

		Text name = new Text();
		name.setId("floating-name");
		name.setText(" " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName());
		name.setFont(TASK_NAME_FONT);
		name.setFill(FLOATING_COLOR);
		floating.add(name, 0, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(COMPLETED_COLOR);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(ARCHIVED_COLOR);
		}

		TaskDisplayGrid.add(holdBack, 0, gridRowCount);
		gridRowCount++;
	}

	private void displayADeadlineTask(DeadlineTask task) {
		GridPane deadline = new GridPane();
		deadline.setPrefSize(760, 30);
		deadline.setHgap(1);
		deadline.setVgap(1);
		deadline.setPadding(new Insets(4, 1, 1, 1));

		Group back = new Group();
		ImageView deadlineBack;
		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			deadlineBack = new ImageView(completeImage);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			deadlineBack = new ImageView(archivedImage);
		} else {
			deadlineBack = new ImageView(deadlineImage);
		}
		back.getChildren().addAll(deadlineBack, deadline);

		GridPane nameGrid = new GridPane();
		nameGrid.setPrefSize(550, 25);
		Text name = new Text();
		name.setId("deadline-name");
		name.setText(" " + Integer.valueOf(taskCount).toString() + ". " + task.getTaskName());
		name.setFont(TASK_NAME_FONT);
		name.setFill(DEADLINE_COLOR);
		nameGrid.add(name, 0, 0);
		deadline.add(nameGrid, 0, 0);

		Text info = new Text();
		info.setId("deadline-info");
		info.setText("    By: " + task.getDeadlineDate() + " " + task.getDeadlineTime());
		info.setFont(TASK_INFO_FONT);
		info.setFill(DEADLINE_COLOR);
		deadline.add(info, 1, 0);

		if (task.getTaskType().equals("Completed") || task.getTaskType() == "Completed") {
			name.setFill(COMPLETED_COLOR);
			info.setFill(COMPLETED_COLOR);
		} else if (task.getTaskType().equals("Archived") || task.getTaskType() == "Archived") {
			name.setFill(ARCHIVED_COLOR);
			info.setFill(ARCHIVED_COLOR);
		}

		TaskDisplayGrid.add(back, 0, gridRowCount);
		gridRowCount++;
	}
}
```
