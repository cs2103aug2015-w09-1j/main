//@@author Zhou You(A0133976U)

package test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.*;
import org.ocpsoft.prettytime.*;
import util.CommandParser;

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
	public void testGetCommandType() throws Exception {
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
		CommandParser cp5 = new CommandParser("display all");	
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
		CommandParser cp12 = new CommandParser("set path C:/Program/SilentJarvis/Data");
		assertEquals("set", cp12.getCommandType());
		
		//edit
		CommandParser cp14 = new CommandParser("edit 2 meeting with boss by 2015-03-04 1259");
		assertEquals("edit", cp14.getCommandType());
		
		//save
		CommandParser cp15 = new CommandParser("save");
		assertEquals("save", cp15.getCommandType());
		
		//load
		CommandParser cp16 = new CommandParser("load");
		assertEquals("load", cp16.getCommandType());
		
		//home
		CommandParser cp17 = new CommandParser("home");
		assertEquals("home", cp17.getCommandType());
		
		//show
		CommandParser cp18 = new CommandParser("show this week");
		assertEquals("show", cp18.getCommandType());
		
		//exit
		CommandParser cp19 = new CommandParser("exit");
		assertEquals("exit", cp19.getCommandType());
		
		//display
		CommandParser cp20 = new CommandParser("display");
		assertEquals("display", cp20.getCommandType());
		
		//clear
		CommandParser cp21 = new CommandParser("clear");
		assertEquals("clear", cp21.getCommandType());
		
	}
	
	@Test
	public void testGetStoragePath() throws Exception{
		CommandParser cp1 = new CommandParser("set path C:/Program/SilentJarvis/Data");
		assertEquals("C:/Program/SilentJarvis/Data", cp1.getStoragePath());
	}

	@Test
	public void testGetStorageFileName() throws Exception{
		CommandParser cp1 = new CommandParser("set filename mytext.txt");
		assertEquals("mytext.txt", cp1.getStorageFileName());
	}
	
	@Test
	public void testGetTaskName() throws Exception{
		//event task
		CommandParser cp1 = new CommandParser("add meeting from home from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("meeting from home", cp1.getTaskName());
		
		//deadline task
		CommandParser cp2 = new CommandParser("add finish project    by manual by 2015-10-03 0900");
		assertEquals("finish project by manual", cp2.getTaskName());
		
		//float task
		CommandParser cp3 = new CommandParser("add visit uncle Lee");
		assertEquals("visit uncle Lee", cp3.getTaskName());
		
		//edit
		CommandParser cp4 = new CommandParser("edit 2 meeting with boss by 2015-03-04 1259");
		assertEquals("meeting with boss", cp4.getTaskName());

	}
	
	@Test
	public void testGetDisplayMode() throws Exception{
		//all
		CommandParser cp1 = new CommandParser("display");
		assertEquals("all", cp1.getDisplayMode());
		
		//all
		CommandParser cp2 = new CommandParser("display all");
		assertEquals("all", cp2.getDisplayMode());
	}
	
	
	@Test
	public void testGetSearchWord() throws Exception{
		CommandParser cp1 = new CommandParser("search meeting  ss   tt");
		assertEquals("meeting ss tt", cp1.getSearchWord());

	}
	
	
	@Test
	public void testGetId() throws Exception{
		int id = 2;
		//delete
		CommandParser cp1 = new CommandParser("delete 2");
		assertEquals(id, cp1.getId());
		
	}

	@Test
	public void testGetErrorMessage() {

	}

	@Test
	public void testGetStartDate() throws Exception{
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
	public void testGetEndDate() throws Exception {
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
	public void testGetStartTime() throws Exception {
		//event task
		CommandParser cp = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("09:00", cp.getStartTime());
		
		//deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals(null, cp2.getStartDate());
	}

	@Test
	public void testGetEndTime() throws Exception {
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
	public void testGetDeleteMode() throws Exception{
		CommandParser cp1 = new CommandParser("delete all");
		assertEquals("all", cp1.getDeleteMode());
	}
	
	@Test
	public void testGetEditAttribute() throws Exception{ 
		CommandParser cp1 = new CommandParser("edit 1 startDate 2015-10-23");
		assertEquals("startdate", cp1.getEditAttribute());
		
		CommandParser cp2 = new CommandParser("edit 1 taskname event");
		assertEquals("taskname", cp2.getEditAttribute());
	}
	
	@Test
	public void testGetEditInfo() throws Exception{
		CommandParser cp1 = new CommandParser("edit 1 startDate 2015-10-23");
		assertEquals("2015-10-23", cp1.getEditInfo());
		
		CommandParser cp2 = new CommandParser("edit 1 taskname event");
		assertEquals("event", cp2.getEditInfo());
	}

	
	@Test
	public void TestGetDeleteIDs() throws Exception{
		CommandParser cp1 = new CommandParser("delete 1, 2,3, 10-12");
		int[] target = {1, 2, 3, 10, 11, 12};
		int[] actual = cp1.getDeleteIDs();
		assertArrayEquals(target, actual);
		
		CommandParser cp2 = new CommandParser("delete 10-12");
		int[] target2 = {10, 11, 12};
		int[] actual2 = cp2.getDeleteIDs();
		assertArrayEquals(target2, actual2);

	}
	@Test
	public void TestGetStart() throws Exception{
		CommandParser cp1 = new CommandParser("add attend tutorial from 2015-10-03 to 2015-10-23");
		assertEquals("2015-10-03", cp1.getStartDateTime().toLocalDate().toString());
	}
	
	@Test
	public void TestGetEnd() throws Exception{
		CommandParser cp1 = new CommandParser("add finish report by 2015-10-23");
		assertEquals("2015-10-23", cp1.getEndDateTime().toLocalDate().toString());
	}
	
	@Test
	public void TestSearchOnDate() throws Exception{
		CommandParser cp1 = new CommandParser("search on December 29");
		assertEquals("2015-12-29", cp1.getSearchOnDate());
		assertEquals(null, cp1.getSearchByDate());
	}
	
	@Test
	public void TestSearchByDate() throws Exception {
		CommandParser cp1 = new CommandParser("search by December 9");
		assertEquals(null, cp1.getSearchOnDate());
		assertEquals("2015-12-09", cp1.getSearchByDate());
	}
	
	@Test
	public void TestGetShowOption() throws Exception{
		CommandParser cp1 = new CommandParser("show archive");
		assertEquals("archived", cp1.getShowOption());
		
		CommandParser cp2 = new CommandParser("show floating");
		assertEquals("floating", cp2.getShowOption());
		
		CommandParser cp3 = new CommandParser("show complete");
		assertEquals("complete", cp3.getShowOption());
	}
	
	@Test
	public void TestGetShowByDate() throws Exception{
		CommandParser cp1 = new CommandParser("show by December 12");
		assertEquals("2015-12-12", cp1.getShowByDate());
	}
	
	@Test
	public void TestGetShowDate() throws Exception{
		CommandParser cp1 = new CommandParser("show December 12");
		assertEquals("2015-12-12", cp1.getShowDate());
	}
	
	@Test
	public void TestGetShowStartDate() throws Exception {
		CommandParser cp1 = new CommandParser("show from December 12 to December 20");
		assertEquals("2015-12-12", cp1.getShowStartDate());
	}
	
	@Test
	public void TestGetShowEndDate() throws Exception {
		CommandParser cp1 = new CommandParser("show from December 12 to December 20 ");
		assertEquals("2015-12-20", cp1.getShowEndDate());
	}
	@Test
	public void TestGetUnarchivedID() throws Exception{
		int[] id = {1};
		CommandParser cp1 = new CommandParser("unarchived 1");
		assertArrayEquals(id, cp1.getUnarchivedIDs());
	}
	@Test
	public void TestGetUncompleteID() throws Exception{
		int[] id1 = {1};
		CommandParser cp1 = new CommandParser("uncomplete 1");
		assertArrayEquals(id1, cp1.getUncompleteIDs());
	}
	
	@Test
	public void TestGetArchivedIDs() throws Exception {
		int[] id1 = {1};
		CommandParser cp1 = new CommandParser("archive 1");
		assertArrayEquals(id1, cp1.getArchivedIDs());
		
		int[] id2 = {1,2,3,67};
		CommandParser cp2 = new CommandParser("archive 1-3, 6   7");
		assertArrayEquals(id2, cp2.getArchivedIDs());
		
		int[] id3 = {1,2,3,5,6};
		CommandParser cp3 = new CommandParser("archive 1-3, 5-6");
		assertArrayEquals(id3, cp3.getArchivedIDs());
	}
	
	@Test
	public void TestGetCompleteIDs() throws Exception {
		int[] id1 = {1};
		CommandParser cp1 = new CommandParser("complete 1");
		assertArrayEquals(id1, cp1.getCompleteIDs());
		
		int[] id2 = {1,2,3};
		CommandParser cp2 = new CommandParser("complete 1-3");
		assertArrayEquals(id2, cp2.getCompleteIDs());
		
		int[] id3 = {1,2,3,5,6};
		CommandParser cp3 = new CommandParser("complete 1-3, 5-6");
		assertArrayEquals(id3, cp3.getCompleteIDs());
	}
	
	@Test
	public void TestGetHelpString() throws Exception {
		CommandParser cp1 = new CommandParser("help");
		String help = "add <name>\nadd <name> from <time> to <time>\nadd <name> by   <deadline>\ndelete  <id>\nsearch  <id>\narchive <id>\nedit <id> <attribute> <info>\nset  path     <storage path>\nset  filename <filename>\nundo\n";
		assertEquals(help, cp1.getHelpString());
	}
	
	@Test
	public void TestGetSearchStartDate() throws Exception {
		CommandParser cp1 = new CommandParser("search from 2015-10-31 to 2015-11-3");
		assertEquals("2015-10-31", cp1.getSearchStartDate());
	}
	
	@Test
	public void TestGetSearchEndDate() throws Exception{
		CommandParser cp1 = new CommandParser("search from 2015-10-31 to 2015-11-3");
		assertEquals("2015-11-03", cp1.getSearchEndDate());
	}
	

	
	
}
