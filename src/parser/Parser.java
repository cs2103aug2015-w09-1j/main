package parser;
import java.util.*;
/**
 * @author Zhou You
 */
 
 /**
 * Parser API demo:
 * Construct:
 * Parser parseResult = new Parser(inputString);
 *
 * Method:
 * getCommandType(): return commandType in String
 * 
 * for add cmd:
 * getTaskType():floatingTask, eventTask, deadlineTask
 * getTaskID()
 * getTaskName()
 * getDescription()
 * getDate()
 * getStartTime()
 * getEndTime()
 *
 * for delete cmd:
 * getIndex()
 * 
 * for display cmd:
 */

public class Parser {
	/* =========== Attribute ===========*/
	private String userInput;
	private String[] inputArr;
	private String task_id;
	private String task_name;
	private String description;
	private String date;
	private String start_time;
	private String end_time;
	
	
	/* =========== Constructor ===========*/
	public Parser(String userInput){
		this.userInput = userInput;
		this.inputArr = userInput.split(" ");
		parse(userInput);
	}
	
	/* =========== Parser API ===========*/
	public String getCommandType(){
		return getFirstWord(userInput);
	}
	
	/* special method for add cmd */

	//general
	protected String getTaskType(){
		String type = inputArr[1];
		switch (type) {
			case "-f":
				return "floatingTask";
			case "-e":
				return "eventTask";
			case "-d":
				return "deadlineTask";
			default:
				throw new Error("Unrecognized task type");
		}
	}
	
	protected String getTaskID(){
		//use a randomised generator to assign an ID to each task
		return task_id;		
	} 
	
	protected String getTaskName(){
		return task_name;
	}
	
	protected String getDescription(){
		return description;
	}
	
	//event task
	protected String getDate(){
		return date;
	}
	
	//event task
	protected String getStartTime(){
		return start_time;
	}
	
	//event task
	protected String getEndTime(){
		return end_time;	
	}
	
	//deadline task
	protected String getDeadlineTime(){
		return end_time;
	}
	
	
	
	/* special API for delete cmd */
	protected String getIndex(){
		return task_id;	
	}
	
	
	/* Helper method */
	private void parse(String userInput){
		String cmdType = getCommandType();
		switch (cmdType) {
			case "add":
				parseAddCommand(userInput);
				break;
			case "delete":
				parseDeleteCommand(userInput);
				break;
			case "display":
				parseDisplayCommand(userInput);
				break;
			default:
			  throw new Error("Unrecognized command type");
		}
	}
	
	private void parseAddCommand(String userInput){
		this.task_id = getTaskID();
		this.task_name = inputArr[2];
		this.description = inputArr[3];
		
		String type = inputArr[1];
		if(type.equals("-f")) {
			parseFloatingTask();
		}
		if(type.equals("-e")) {
			parseEventTask();
		} 
		if(type.equals("-d")){
			parseDeadlineTask();
		}
	}
	
	private void parseDeleteCommand(String userInput){
		this.task_id = inputArr[1];
	}
	
	private void parseDisplayCommand(String userInput){
		
	}
	
	private void parseFloatingTask(){

	}
	
	private void parseEventTask(){
		this.date = inputArr[4];
		this.start_time = inputArr[5];
		this.end_time = inputArr[6];
	}
	
	private void parseDeadlineTask(){
		this.date = inputArr[4];
		this.end_time = inputArr[5];
	}
	
	private String getFirstWord(String commandTypeString){
		String[] strArr = commandTypeString.split(" ");
		String commandType = strArr[0];
		return commandType;
	
	}

	
	
}
