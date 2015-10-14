package meteorite.todo.util;



public class CommandParser {
	//Attributes
	private String userInput;
	private String command;
	private String taskType;
	private String args;
	private String[] argArray;
	private String[] inputArray;
	private String taskName;
	private String startTime;
	private String endTime;
	private String startDate;
	private String endDate;
	private int id;
	
	//Constructor
	public CommandParser(String userInput) {
		this.setUserInput(userInput);
		this.inputArray = userInput.split(" ", 3);
		this.command = inputArray[0];
		this.setTaskType(inputArray[1]);
		this.args = inputArray[2];
		this.argArray = args.split("from |to |by ");
		parse(userInput);
	}
	
	public CommandParser() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	public String getCommandType() {
		return command;
	}
	public int getId() {
		return id;
	}
	
	public String getTaskName(){
		return taskName;
	}
	
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	//event task
	public String getStartDate(){
		return startDate;
	}
	//event task
	public String getEndDate() {
		return endDate;
	}
	
	
	//event task
	public String getStartTime(){
		return startTime;
	}
	
	//event task
	public String getEndTime(){
		return endTime;	
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
	
	private void helper(int pos, String str) {
		switch(pos) {
		case 0:this.taskName = str;
		       break;
		case 1:this.startTime = str;
		       break;
		case 2:this.endTime = str;
			   break;
		case 3:this.startDate = str;
		       break;
		case 4:this.endDate = str;
		       break;
		}
	}
	
	private void parseAddCommand(String userInput) {
		int len = this.argArray.length;
		for (int i = 0;i < len;i++) {
			helper(i, this.argArray[i]);
		}
	}

	private void parseDeleteCommand(String userInput) {
		this.id = Integer.parseInt(this.argArray[0]);
	}
	
	private void parseDisplayCommand(String userInput){
		
	}
	
	//testing purpose
	public static void main(String[] args) {
		CommandParser cp = new CommandParser("add meeting with boss from 9 to 10 by Monday");
		System.out.println(cp.getStartTime());
	}

}
