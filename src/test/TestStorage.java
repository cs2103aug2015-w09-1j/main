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

	@Test
	public void test_getInstance() {
		// test for default setting
		assertEquals(test.getfileName(), "SilentJarvis.fxml");
	}

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
