package meteorite.todo.util;



public class CommandParser {
	//Attributes
	private String userInput;
	private String[] inputArray;
	private String taskName;
	private String description;
	private String taskId;
	private String startTime;
	private String endTime;
	private String dueDate;
	
	//Constructor
	public CommandParser(String userInput) {
		this.userInput = userInput;
		this.inputArray = userInput.split(" ");
		parse(userInput);
	}
	
	//Parser API
	public String getCommandType() {
		return getFirstWord(userInput);
	}
	public String getTaskID(){
		//use a randomised generator to assign an ID to each task
		return taskId;		
	} 
	
	public String getTaskName(){
		return taskName;
	}
	
	public String getDescription(){
		return description;
	}
	
	//event task
	public String getDueDate(){
		return dueDate;
	}
	
	//event task
	public String getStartTime(){
		return startTime;
	}
	
	//event task
	public String getEndTime(){
		return endTime;	
	}
	
	
	
	/* special API for delete cmd */
	public String getIndex(){
		return taskId;	
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
		this.taskName = inputArray[1];
		this.description = inputArray[2];
	}

	private void parseDeleteCommand(String userInput){
		this.taskId = inputArray[1];
	}
	
	private void parseDisplayCommand(String userInput){
		
	}
	
	private String getFirstWord(String commandTypeString){
		String[] strArr = commandTypeString.split(" ");
		String commandType = strArr[0];
		return commandType;
	
	}

}
