package util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.*;
import org.ocpsoft.prettytime.*;

public class CommandParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCommandParserString() {
	}

	@Test
	public void testCommandParser() {
	}

	@Test
	public void testGetCommandType() {
		//event task
		CommandParser cp1 = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("add", cp1.getCommandType());
		
		//deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals("add", cp2.getCommandType());
		
		//float task
		CommandParser cp3 = new CommandParser("add visit uncle Lee");
		assertEquals("add", cp3.getCommandType());
		
		//delete
		CommandParser cp4 = new CommandParser("delete 2");	
		assertEquals("delete", cp4.getCommandType());
		
		
		//display
		CommandParser cp5 = new CommandParser("display archived");	
		assertEquals("display", cp5.getCommandType());
		
		//search
		CommandParser cp6 = new CommandParser("search meeting");
		assertEquals("search", cp6.getCommandType());
		
		CommandParser cp7 = new CommandParser("search on 2015-10-03");
		assertEquals("search", cp7.getCommandType());
		
		//undo
		CommandParser cp8 = new CommandParser("undo");
		assertEquals("undo", cp8.getCommandType());
		
		//help
		CommandParser cp9 = new CommandParser("help");
		assertEquals("help", cp9.getCommandType());
		
		//complete
		CommandParser cp10 = new CommandParser("complete 10");
		assertEquals("complete", cp10.getCommandType());
		
		//archive 
		CommandParser cp11 = new CommandParser("archive 11");
		assertEquals("archive", cp11.getCommandType());
		
		//set
		CommandParser cp12 = new CommandParser("set C:/Program/SilentJarvis/Data");
		assertEquals("set", cp12.getCommandType());
		
		//edit
		CommandParser cp13 = new CommandParser("edit 2");
		assertEquals("edit", cp13.getCommandType());
		
		CommandParser cp14 = new CommandParser("edit 2 meeting with boss by 2015-03-04 1259");
		assertEquals("edit", cp14.getCommandType());
		
		//save
		CommandParser cp15 = new CommandParser("save");
		assertEquals("save", cp15.getCommandType());
		
		//load
		CommandParser cp16 = new CommandParser("load");
		assertEquals("load", cp16.getCommandType());
		
	}
	
	@Test
	public void testGetStoragePath(){
		CommandParser cp1 = new CommandParser("set C:/Program/SilentJarvis/Data");
		assertEquals("C:/Program/SilentJarvis/Data", cp1.getStoragePath());
	}

	@Test
	public void testGetTaskName() {
		//event task
		CommandParser cp1 = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("meeting", cp1.getTaskName());
		
		//deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals("finish project manual", cp2.getTaskName());
		
		//float task
		CommandParser cp3 = new CommandParser("add visit uncle Lee");
		assertEquals("visit uncle Lee", cp3.getTaskName());
		
		//edit
		CommandParser cp4 = new CommandParser("edit 2 meeting with boss by 2015-03-04 1259");
		assertEquals("meeting with boss", cp4.getTaskName());

	}
	
	@Test
	public void testGetDisplayMode(){
		//archive
		CommandParser cp1 = new CommandParser("display archived");
		assertEquals("archived", cp1.getDisplayMode());
		
		//all
		CommandParser cp2 = new CommandParser("display all");
		assertEquals("all", cp2.getDisplayMode());
	}
	
	
	@Test
	public void testGetSearchWord() {
		CommandParser cp1 = new CommandParser("search meeting");
		assertEquals("meeting", cp1.getSearchWord());

	}
	
	@Test
	public void testGetSearchDate() {
		CommandParser cp1 = new CommandParser("search on 2015-10-03");
		assertEquals("2015-10-03", cp1.getSearchDate());

	}
	
	
	@Test
	public void testGetId() {
		int id = 2;
		//delete
		CommandParser cp1 = new CommandParser("delete 2");
		assertEquals(id, cp1.getId());
		
		//complete
		CommandParser cp2 = new CommandParser("complete 2");
		assertEquals(id, cp2.getId());
		
		//archive
		CommandParser cp3 = new CommandParser("archive 2");
		assertEquals(id, cp3.getId());
		
		//edit
		CommandParser cp4 = new CommandParser("edit 2");
		assertEquals(id, cp4.getId());

	}

	@Test
	public void testGetErrorMessage() {

	}

	@Test
	public void testGetStartDate() {
		//event task
		CommandParser cp1 = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("2015-10-03", cp1.getStartDate());
		
//		
//		CommandParser cp2 = new CommandParser("add meeting with boss from  to 10 on Monday");
//		assertEquals("Monday", cp2.getStartDate());
		
		//deadline task
		CommandParser cp3 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals(null, cp3.getStartDate());
	}

	@Test
	public void testGetEndDate() {
		//event task
		CommandParser cp1 = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("2015-10-04", cp1.getEndDate());
		
		//deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by oct 9 9am");
		assertEquals("2015-10-09", cp2.getEndDate());
		
		//edit
		CommandParser cp3 = new CommandParser("edit 2 meeting with boss by 2015-03-04 1259");
		assertEquals("2015-03-04", cp3.getEndDate());
	}

	@Test
	public void testGetStartTime() {
		//event task
		CommandParser cp = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("09:00", cp.getStartTime());
		
		//deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals(null, cp2.getStartDate());
	}

	@Test
	public void testGetEndTime() {
		CommandParser cp = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("09:00", cp.getEndTime());
		
		//deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals("09:00", cp2.getEndTime());
		
		//edit
		CommandParser cp3 = new CommandParser("edit 2 meeting with boss by 2015-03-04 1259");
		assertEquals("12:59", cp3.getEndTime());
	}
	
	@Test
	public void testGetDeleteMode(){
		CommandParser cp1 = new CommandParser("delete all");
		assertEquals("all", cp1.getDeleteMode());
	}
	
	@Test
	public void testGetEditAttribute(){
		CommandParser cp1 = new CommandParser("edit 1 startDate 2015-10-23");
		assertEquals("startDate", cp1.getEditAttribute());
	}
	
	@Test
	public void testGetEditInfo(){
		CommandParser cp1 = new CommandParser("edit 1 startDate 2015-10-23");
		assertEquals("2015-10-23", cp1.getEditInfo());
	}
	@Test
	public void testGetEditDate(){
		CommandParser cp1 = new CommandParser("edit 1 start 2015-10-03");
		assertEquals("2015-10-03", cp1.getEditDate().toLocalDate().toString());
	}
	
	@Test
	public void TestGetDeleteIDs(){
		CommandParser cp1 = new CommandParser("delete 1, 2,3, 10-12");
		int[] target = {1, 2, 3, 10, 11, 12};
		int[] actual = cp1.getDeleteIDs();
		assertArrayEquals(target, actual);
		

	}
	@Test
	public void TestGetStart() {
		CommandParser cp1 = new CommandParser("add attend tutorial from 2015-10-03 to 2015-10-23");
		assertEquals("2015-10-03", cp1.getStartDateTime().toLocalDate().toString());
	}
	
	@Test
	public void TestGetEnd() {
		CommandParser cp1 = new CommandParser("add finish report by 2015-10-23");
		assertEquals("2015-10-23", cp1.getEndDateTime().toLocalDate().toString());
	}
	

}
