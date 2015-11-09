//@@author ZhouYou(A0133976U)
	/**
	 * This class is used to test Parser component API
	 */
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
	
	/**
	 * test getCommandType()
	 * which should return a command type corresponding
	 * to the user's input in a String
	 */
	@Test
	public void testGetCommandType() throws Exception {
		//add an event task 
		CommandParser cp1 = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("add", cp1.getCommandType());
		
		//add a deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals("add", cp2.getCommandType());
		
		//add a float task
		CommandParser cp3 = new CommandParser("add visit uncle Lee");
		assertEquals("add", cp3.getCommandType());
		
		//delete a task
		CommandParser cp4 = new CommandParser("delete 2");	
		assertEquals("delete", cp4.getCommandType());
		
		//display
		CommandParser cp5 = new CommandParser("display all");	
		assertEquals("display", cp5.getCommandType());
		
		//search a task based on keyword
		CommandParser cp6 = new CommandParser("search meeting");
		assertEquals("search", cp6.getCommandType());
		
		//search a task based on date
		CommandParser cp7 = new CommandParser("search on 2015-10-03");
		assertEquals("search", cp7.getCommandType());
		
		//undo command
		CommandParser cp8 = new CommandParser("undo");
		assertEquals("undo", cp8.getCommandType());
		
		//help command
		CommandParser cp9 = new CommandParser("help");
		assertEquals("help", cp9.getCommandType());
		
		//complete command
		CommandParser cp10 = new CommandParser("complete 10");
		assertEquals("complete", cp10.getCommandType());
		
		//archive command
		CommandParser cp11 = new CommandParser("archive 11");
		assertEquals("archive", cp11.getCommandType());
		
		//set the data file's path
		CommandParser cp12 = new CommandParser("set path C:/Program/SilentJarvis/Data");
		assertEquals("set", cp12.getCommandType());
		
		//edit command
		CommandParser cp14 = new CommandParser("edit 2 meeting with boss by 2015-03-04 1259");
		assertEquals("edit", cp14.getCommandType());
		
		//save command
		CommandParser cp15 = new CommandParser("save");
		assertEquals("save", cp15.getCommandType());
		
		//load command
		CommandParser cp16 = new CommandParser("load");
		assertEquals("load", cp16.getCommandType());
		
		//home command
		CommandParser cp17 = new CommandParser("home");
		assertEquals("home", cp17.getCommandType());
		
		//show tasks on a given interval
		CommandParser cp18 = new CommandParser("show this week");
		assertEquals("show", cp18.getCommandType());
		
		//exit command
		CommandParser cp19 = new CommandParser("exit");
		assertEquals("exit", cp19.getCommandType());
		
		//display command
		CommandParser cp20 = new CommandParser("display");
		assertEquals("display", cp20.getCommandType());
		
		//clear command
		CommandParser cp21 = new CommandParser("clear");
		assertEquals("clear", cp21.getCommandType());
		
		//mark "incomplete" command
		CommandParser cp22 = new CommandParser("uncomplete 2");
		assertEquals("uncomplete", cp22.getCommandType());
		
		//mark "unarchived" command
		CommandParser cp23 = new CommandParser("unarchived 3");
		assertEquals("unarchived", cp23.getCommandType());
		
		//show command
		CommandParser cp24 = new CommandParser("show archived");
		assertEquals("show", cp24.getCommandType());
		
	}
	/**
	 * test getStoragePath()
	 * which should return an the data file storage path 
	 * specified by the user
	 */
	@Test
	public void testGetStoragePath() throws Exception{
		CommandParser cp1 = new CommandParser("set path C:/Program/SilentJarvis/Data");
		assertEquals("C:/Program/SilentJarvis/Data", cp1.getStoragePath());
	}
	/**
	 * test getStoragePath()
	 * which should return an the data file storage path 
	 * specified by the user
	 */
	@Test
	public void testGetStorageFileName() throws Exception{
		CommandParser cp1 = new CommandParser("set filename mytext.txt");
		assertEquals("mytext.txt", cp1.getStorageFileName());
	}
	/**
	 * test getTaskName()
	 * which should return the name of a task in a string 
	 */
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
		
		//updating all information of a task
		CommandParser cp4 = new CommandParser("edit 2 meeting with boss by 2015-03-04 1259");
		assertEquals("meeting with boss", cp4.getTaskName());
		
		CommandParser cp5 = new CommandParser("add kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk by 2015-10-03");
		assertEquals("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk", cp5.getTaskName());

	}
	/**
	 * test getDisplayMode()
	 * which should return display option in a String
	 */	
	@Test
	public void testGetDisplayMode() throws Exception{
		//currently there's only one display mode 
		//no matter what user types, as long as it is
		//a display command, the mode should be "all".  
		CommandParser cp1 = new CommandParser("display");
		assertEquals("all", cp1.getDisplayMode());
		
		//all
		CommandParser cp2 = new CommandParser("display all");
		assertEquals("all", cp2.getDisplayMode());
	}
	/**
	 * test getSearchWord()
	 * which should return the keyword to be searched
	 * specified by the user
	 */
	@Test
	public void testGetSearchWord() throws Exception{
		//keyword should be returned after extra spaces being ignored
		CommandParser cp1 = new CommandParser("search meeting  ss   tt");
		assertEquals("meeting ss tt", cp1.getSearchWord());

	}
	/**
	 * test getID()
	 * which should return a index in Integer type
	 */
	@Test
	public void testGetId() throws Exception{
		int id = 2;
		//delete a task
		CommandParser cp1 = new CommandParser("delete 2");
		assertEquals(id, cp1.getId());
		
	}
	/**
	 * test invalid command type handling
	 * if user type an invalid command, parse should throw an exception.
	 */
	@Test
	public void testInvalidCommandTypeException() throws Exception {
		try{
			CommandParser cp1 = new CommandParser("random type");
		} catch(Exception e){
			assertEquals("command cannot be recongnised", e.getMessage());
		}
	}
	/**
	 * test getStartDate()
	 * which should return the starting date of a task in "YYYY-MM-DD" format
	 */
	@Test
	public void testGetStartDate() throws Exception{
		//add an event task
		CommandParser cp1 = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("2015-10-03", cp1.getStartDate());
		
		//add a deadline task
		CommandParser cp3 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals(null, cp3.getStartDate());
	}
	/**
	 * test getEndDate()
	 * which should return the ending date of a task in "YYYY-MM-DD" format
	 */
	@Test
	public void testGetEndDate() throws Exception {
		//add an event task
		CommandParser cp1 = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("2015-10-04", cp1.getEndDate());
		
		//add a deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by oct 9 9am");
		assertEquals("2015-10-09", cp2.getEndDate());
		
		//edit a task
		CommandParser cp3 = new CommandParser("edit 2 meeting with boss by 2015-03-04 1259");
		assertEquals("2015-03-04", cp3.getEndDate());
	}
	/**
	 * test getStartTime()
	 * which should return the starting time of a task in "MM-SS" format
	 */
	@Test
	public void testGetStartTime() throws Exception {
		//add an event task
		CommandParser cp = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("09:00", cp.getStartTime());
		
		//add a deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals(null, cp2.getStartDate());
	}
	/**
	 * test getEndTime()
	 * which should return the ending time of a task in "MM-SS" format
	 */
	@Test
	public void testGetEndTime() throws Exception {
		//add an event task
		CommandParser cp = new CommandParser("add meeting from today 2pm to today 3pm");
		assertEquals("15:00", cp.getEndTime());
		
		//add a deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals("09:00", cp2.getEndTime());
		
		//edit a task
		CommandParser cp3 = new CommandParser("edit 2 meeting with boss by 2015-03-04 1259");
		assertEquals("12:59", cp3.getEndTime());
	}
	/**
	 * test getDeleteMode()
	 * which should return the mode of deletion in a String
	 */
	@Test
	public void testGetDeleteMode() throws Exception{
		//delete all tasks
		CommandParser cp1 = new CommandParser("delete all");
		assertEquals("all", cp1.getDeleteMode());
		
		//delete a specific task
		CommandParser cp2 = new CommandParser("delete 2");
		assertEquals(null, cp2.getDeleteMode());
	}
	/**
	 * test getEditAttribute()
	 * which should return attribute name to be edited in a String (Case ignored)
	 */
	@Test
	public void testGetEditAttribute() throws Exception{ 
		//edit the starting date of a task
		CommandParser cp1 = new CommandParser("edit 1 startDate 2015-10-23");
		assertEquals("startdate", cp1.getEditAttribute());
		
		
		//editing name of a task
		CommandParser cp2 = new CommandParser("edit 1 taskname event");
		assertEquals("taskname", cp2.getEditAttribute());
	}
	/**
	 * test getStartEditInfo()
	 * which should return the attribute information to be updated in a String
	 */
	@Test
	public void testGetEditInfo() throws Exception{
		//editing staring date of a task
		CommandParser cp1 = new CommandParser("edit 1 startDate 2015-10-23");
		assertEquals("2015-10-23", cp1.getEditInfo());
		
		//updating the name of a task
		CommandParser cp2 = new CommandParser("edit 1 taskname event");
		assertEquals("event", cp2.getEditInfo());
	}

	/**
	 * test getDeleteIDs()
	 * which should return a index in Integer type
	 */
	@Test
	public void testGetDeleteIDs() throws Exception{
		CommandParser cp1 = new CommandParser("delete 1, 2,3, 10-12");
		int[] target = {1, 2, 3, 10, 11, 12};
		int[] actual = cp1.getDeleteIDs();
		assertArrayEquals(target, actual);
		
		CommandParser cp2 = new CommandParser("delete 10-12");
		int[] target2 = {10, 11, 12};
		int[] actual2 = cp2.getDeleteIDs();
		assertArrayEquals(target2, actual2);

	}
	/**
	 * test getStart()
	 * which should return the starting date and time in LocalDateTime type
	 */
	@Test
	public void testGetStart() throws Exception{
		CommandParser cp1 = new CommandParser("add attend tutorial from 2015-10-03 to 2015-10-23");
		assertEquals("2015-10-03", cp1.getStartDateTime().toLocalDate().toString());
	}
	/**
	 * test getEnd()
	 * which should return the end date in LocalDateTime type
	 */
	@Test
	public void testGetEnd() throws Exception{
		CommandParser cp1 = new CommandParser("add finish report by 2015-10-23");
		assertEquals("2015-10-23", cp1.getEndDateTime().toLocalDate().toString());
	}
	/**
	 * test getSearchOnDate()
	 * which should return a date in a String when user wants to 
	 * search tasks on that date
	 */
	@Test
	public void testSearchOnDate() throws Exception{
		CommandParser cp1 = new CommandParser("search on December 29");
		assertEquals("2015-12-29", cp1.getSearchOnDate());
		assertEquals(null, cp1.getSearchByDate());
	}
	/**
	 * test getSearchByDate()
	 * which should return a date in a String when user wants to 
	 * search tasks before that date
	 */
	@Test
	public void testSearchByDate() throws Exception {
		CommandParser cp1 = new CommandParser("search by December 9");
		assertEquals(null, cp1.getSearchOnDate());
		assertEquals("2015-12-09", cp1.getSearchByDate());
	}
	/**
	 * test getShowOption()
	 * which should return the show option in a String type
	 */
	@Test
	public void testGetShowOption() throws Exception{
		//show all archived tasks
		CommandParser cp1 = new CommandParser("show archived");
		assertEquals("archived", cp1.getShowOption());
		
		//show all floating tasks
		CommandParser cp2 = new CommandParser("show floating");
		assertEquals("floating", cp2.getShowOption());
		
		//show all complete tasks
		CommandParser cp3 = new CommandParser("show complete");
		assertEquals("complete", cp3.getShowOption());
	}
	/**
	 * test getShowByDate()
	 * which should return a date in a String when user wants to 
	 * show tasks before that date
	 */
	@Test
	public void testGetShowByDate() throws Exception{
		CommandParser cp1 = new CommandParser("show by December 12");
		assertEquals("2015-12-12", cp1.getShowByDate());
	}
	/**
	 * test getShowDate()
	 * which should return a date in a String when user wants to 
	 * show tasks on that date
	 */
	@Test
	public void testGetShowDate() throws Exception{
		CommandParser cp1 = new CommandParser("show December 12");
		assertEquals("2015-12-12", cp1.getShowDate());
	}
	/**
	 * test getShowStartDate()
	 * which should return the starting date of an interval in a String when user wants to 
	 * show tasks within a certain period
	 */
	@Test
	public void testGetShowStartDate() throws Exception {
		CommandParser cp1 = new CommandParser("show from December 12 to December 20");
		assertEquals("2015-12-12", cp1.getShowStartDate());
	}
	/**
	 * test getShowEndDate()
	 * which should return the ending date of an interval in a String when user wants to 
	 * show tasks within a certain period
	 */
	@Test
	public void testGetShowEndDate() throws Exception {
		CommandParser cp1 = new CommandParser("show from December 12 to December 20 ");
		assertEquals("2015-12-20", cp1.getShowEndDate());
	}
	/**
	 * test getUnarchivedIDs()
	 * which should return a list of indexes in Integer type
	 * when user wants to unarchive a group of tasks
	 */
	@Test
	public void testGetUnarchivedID() throws Exception{
		int[] id1 = {1};
		CommandParser cp1 = new CommandParser("unarchived 1");
		assertArrayEquals(id1, cp1.getUnarchivedIDs());
		
		int[] id2 = {1,2,4};
		CommandParser cp2 = new CommandParser("unarchived 1,2,4");
		assertArrayEquals(id2, cp2.getUnarchivedIDs());
		
		int[] id3 = {1,2,3,5,6};
		CommandParser cp3 = new CommandParser("unarchived 1-3, 5-6");
		assertArrayEquals(id3, cp3.getUnarchivedIDs());
	}
	/**
	 * test getUncompleteIDs()
	 * which should return a list of indexes in Integer type
	 * when user wants to label tasks as "incomplete"
	 */
	@Test
	public void testGetUncompleteID() throws Exception{
		int[] id1 = {1};
		CommandParser cp1 = new CommandParser("uncomplete 1");
		assertArrayEquals(id1, cp1.getUncompleteIDs());
		
		int[] id2 = {1,2,3,67};
		CommandParser cp2 = new CommandParser("uncomplete 1-3, 6   7");
		assertArrayEquals(id2, cp2.getUncompleteIDs());
		
		int[] id3 = {1,2,3,5,6};
		CommandParser cp3 = new CommandParser("uncomplete 1-3, 5-6");
		assertArrayEquals(id3, cp3.getUncompleteIDs());
	}
	/**
	 * test getUnArchivedIDs()
	 * which should return a list of indexes in Integer type
	 * when user wants to archive a group of tasks
	 */
	@Test
	public void testGetArchivedIDs() throws Exception {
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
	/**
	 * test getCompleteIDs()
	 * which should return a list of indexes in Integer type
	 * when user wants to label tasks as "complete"
	 */
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
	/**
	 * test getHelpString()
	 * which should return a String that represents a command list
	 * as reference for users
	 */
	@Test
	public void TestGetHelpString() throws Exception {
		CommandParser cp1 = new CommandParser("help");
		String help = "add <name>\nadd <name> from <time> to <time>\nadd <name> by   <deadline>\ndelete  <id>\nsearch  <id>\narchive <id>\nedit <id> <attribute> <info>\nset  path     <storage path>\nset  filename <filename>\nshow on <date>\nshow by <date>\nundo\n";
		assertEquals(help, cp1.getHelpString());
	}
	/**
	 * test getSearchStartDate()
	 * which should return a String that represents the starting date
	 * when user want to search tasks within certain period
	 */
	@Test
	public void TestGetSearchStartDate() throws Exception {
		CommandParser cp1 = new CommandParser("search from 2015-10-31 to 2015-11-3");
		assertEquals("2015-10-31", cp1.getSearchStartDate());
	}
	/**
	 * test getSearchStartDate()
	 * which should return a String that represents the ending date
	 * when user want to search tasks within certain period
	 */
	@Test
	public void TestGetSearchEndDate() throws Exception{
		CommandParser cp1 = new CommandParser("search from 2015-10-31 to 2015-11-3");
		assertEquals("2015-11-03", cp1.getSearchEndDate());
	}
	

	
	
}
