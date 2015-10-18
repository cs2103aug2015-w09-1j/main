package meteorite.todo.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		CommandParser cp = new CommandParser("delete 2");
		assertEquals(id, cp.getId());

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
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals("2015-10-03", cp2.getEndDate());
	}

	@Test
	public void testGetStartTime() {
		//event task
		CommandParser cp = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("0900", cp.getStartTime());
		
		//deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals(null, cp2.getStartDate());
	}

	@Test
	public void testGetEndTime() {
		CommandParser cp = new CommandParser("add meeting from 2015-10-03 0900 to 2015-10-04 0900");
		assertEquals("0900", cp.getEndTime());
		
		//deadline task
		CommandParser cp2 = new CommandParser("add finish project manual by 2015-10-03 0900");
		assertEquals("0900", cp2.getEndTime());
	}

}
