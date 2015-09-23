import java.util.*;
import CommandType;

/**
 * @author Zhou You
 */

public class Parser {
	/* Attribute */
	private userInput;
	private String[] inputArr;
	
	
	/* Constructor */
	public Parser(String userInput){
		this.userInput = userInput;
		this.inputArr = userInput.split(" ");
	}
	
	/* Parser API */
	protected String getTaskID(){
		//use a randomised generator to assign an ID to each task		
	} 
	
	protected String getTaskName(){
		
	}
	
	protected String getDescription(){
		
	}
	
	protected String getDate(){
		
	}
	
	protected String getStartTime(){
		
	}
	
	protected String getEndTime(){
		
	}
	
	
	/* Helper method */
	private String getFirstWord(){
		CommandType commandType = determineCommandType(commandTypeString);
		
		switch (commandType) {
			case ADD:
				return add(userCommand);
			case DISPLAY:
				return display(userCommand);
			case CLEAR:
				return clear(userCommand);
			case DELETE:
				return delete(userCommand);
			case INVALID:
				return String.format(MESSAGE_INVALID_FORMAT, userCommand);
			case EXIT:
				System.exit(0);
			case SORT:
				return sort(userCommand);
			case SEARCH:
				return search(userCommand);
			default:
			  throw new Error("Unrecognized command type");
		}
	}
	
	private static CommandType determineCommandType(String commandTypeString) {
		if(commandTypeString == null){
			throw new Error("Command type string cannot be null!");
		}
		
		if(commandTypeString.equalsIgnoreCase("add")) {
			return CommandType.ADD;
		} else if (commandTypeString.equalsIgnoreCase("DISPLAY")) {
			return CommandType.DISPLAY;
		} else if (commandTypeString.equalsIgnoreCase("CLEAR")) {
			return CommandType.CLEAR;
		} else if (commandTypeString.equalsIgnoreCase("EXIT")) {
			return CommandType.EXIT;
		} else if(commandTypeString.equalsIgnoreCase("DELETE")) {
			return CommandType.DELETE;
		} else if(commandTypeString.equalsIgnoreCase("SORT")) { 
			return CommandType.SORT;
		} else if(commandTypeString.equalsIgnoreCase("SEARCH")) {
			return CommandType.SEARCH;	
		} else {
			return CommandType.INVALID;
		}
	}
	
	
}
