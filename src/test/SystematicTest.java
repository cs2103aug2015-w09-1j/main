package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import controller.Controller;
import model.*;

public class SystematicTest {
	ArrayList<Task> taskList;
	
	@Test
	public void test() throws Exception{
		Controller.executeCMD("delete all");
		Controller.executeCMD("add floating");

		taskList=Controller.getTaskList();
		assertTrue((taskList.get(0))instanceof FloatingTask);
		assertTrue(taskList.get(0).getTaskName().equals("floating"));
		
		Controller.executeCMD("delete 1");
		Controller.executeCMD("add deadLine by 2015-11-20 0300");
		
		taskList=Controller.getTaskList();
		assertTrue((taskList.get(0))instanceof DeadlineTask);
		assertTrue(taskList.get(0).getTaskName().equals("deadLine"));
		
		Controller.executeCMD("delete 1");
		Controller.executeCMD("add event from 2015-11-15 0800 to 2015-11-16 1900");
		
		taskList=Controller.getTaskList();
		assertTrue((taskList.get(0))instanceof EventTask);
		assertTrue(taskList.get(0).getTaskName().equals("event"));
		
		Controller.executeCMD("add A");
		Controller.executeCMD("add D from 2014-11-20 1800 to 2014-12-01 0700");
		Controller.executeCMD("add C by 2015-11-01 1500");
		Controller.executeCMD("add B from 2015-12-10 0300 to 2015-12-12 0500");
		Controller.executeCMD("add ABC");		
		taskList=Controller.getTaskList();
		
		assertTrue((taskList.get(0))instanceof FloatingTask);
		assertTrue(taskList.get(0).getTaskName().equals("A"));
		assertTrue((taskList.get(1))instanceof FloatingTask);
		assertTrue(taskList.get(1).getTaskName().equals("ABC"));
		
		EventTask temp1=(EventTask) taskList.get(2);
		assertTrue((taskList.get(2))instanceof EventTask);
		assertTrue(temp1.getTaskName().equals("B"));
		assertTrue(temp1.getStartDate().equals("2015-12-10"));
		assertTrue(temp1.getStartTime().equals("03:00"));
		assertTrue(temp1.getEndDate().equals("2015-12-12"));
		assertTrue(temp1.getEndTime().equals("05:00"));
		
		
		DeadlineTask temp2=(DeadlineTask) taskList.get(3);
		assertTrue((taskList.get(3))instanceof DeadlineTask);
		assertTrue(temp2.getTaskName().equals("C"));
		assertTrue(temp2.getDeadlineDate().equals("2015-11-01"));
		assertTrue(temp2.getDeadlineTime().equals("15:00"));
		
		EventTask temp3=(EventTask) taskList.get(4);
		assertTrue((taskList.get(4))instanceof EventTask);
		assertTrue(temp3.getTaskName().equals("D"));
		assertTrue(temp3.getStartDate().equals("2014-11-20"));
		assertTrue(temp3.getStartTime().equals("18:00"));
		assertTrue(temp3.getEndDate().equals("2014-12-01"));
		assertTrue(temp3.getEndTime().equals("07:00"));
		
		Controller.executeCMD("delete 1");
		taskList=Controller.getTaskList();
		
		assertTrue((taskList.get(0))instanceof FloatingTask);
		assertTrue(taskList.get(0).getTaskName().equals("ABC"));
		
		assertTrue((taskList.get(1))instanceof EventTask);
		assertTrue(temp1.getTaskName().equals("B"));
		
		assertTrue((taskList.get(2))instanceof DeadlineTask);
		assertTrue(temp2.getTaskName().equals("C"));

		assertTrue((taskList.get(3))instanceof EventTask);
		assertTrue(temp3.getTaskName().equals("D"));

		Controller.executeCMD("undo");
		taskList=Controller.getTaskList();
		assertTrue((taskList.get(0))instanceof FloatingTask);
		assertTrue(taskList.get(0).getTaskName().equals("A"));
		
		Controller.executeCMD("search A");
		Controller.executeCMD("display all");
		Controller.executeCMD("delete 1-3");
		Controller.executeCMD("delete all");
		Controller.executeCMD("undo");
		Controller.executeCMD("undo");
		Controller.executeCMD("save");
		Controller.executeCMD("set path newfolder\\");
		Controller.executeCMD("save");
		assertTrue((new File("newfolder\\")).exists());
		assertTrue((new File("newfolder\\silentjarvis.fxml")).exists());
	}
}
