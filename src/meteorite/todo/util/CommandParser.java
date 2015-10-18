//the follow is the list of command:
//	1) add
//		a.with deadline
//			add <task name> by <date> <time>
//		b.event
//			add <task name> from <date><time> to <date><time>
//		c.floating
//			add <task name>
//	
//	2) delete
//		delete <id>
//	
//	3) search
//		a. search tasks on a certain date
//			search on <date>
//		b. search by task name
//			search <keyword>
//	
//	4) display
//		a. display archived tasks
//			display archived
//		b. display all incomplete tasks
//			display all
//	
//	5) edit
//		a. reset a task. all attribute will be set as null
//			edit <id>
//	    b. edit the whole task
//	    	edit <id> <all information>
//	
//	6) undo
//		undo
//		
//	7) complete
//		complete <id> -- mark the task as complete
//		
//	8) archive
//		archive <id>
//	
//	9) set storage path
//		set <storage path>
//	
//	10) help
//		help


package meteorite.todo.util;

public class CommandParser {
	//Attribute
	private String userInput;
	private String command;
	private String args;
	
	private String taskName;
	private String startTime;
	private String endTime;
	private String startDate;
	private String endDate;
	private int taskID;
	private String searchWord;
	private String searchDate;
	private String displayMode;
	
	CommandChecker cc;
	
	//Constructor
	public CommandParser(String userInput) {
		this.setUserInput(userInput);
		cc = new CommandChecker(userInput);
		if (!cc.isValid()) {
			throw new Error(cc.getErrorMessage());
		} else {
			 this.setCommandType();
			 this.setArgs();
			 parse();
		} 			
	}
	public CommandParser() {
		
	}
	//public methods
	public String getCommandType() {
		return this.command;
	}
	public String getTaskName(){
		return this.taskName;
	}
	public int getId(){
		return this.taskID;
	}
	public String getErrorMessage(){
		return cc.getErrorMessage();
	}
	public String getStartDate(){
		return this.startDate;
	}
	public String getEndDate(){
		return this.endDate;
	}
	public String getStartTime(){
		return this.startTime;
	}
	public String getEndTime(){
		return this.endTime;
	}
	public String getSearchDate(){
		return this.searchDate;
	}
	public String getSearchWord(){
		return this.searchWord;
	}
	public String getDisplayMode(){
		return this.displayMode;
	}
	
	
	
	//private methods
	private void parse(){
		String cmdType = getCommandType();
		switch (cmdType) {
			case "add":
				parseAddCommand();
				break;
			case "delete":
				parseDeleteCommand();
				break;
			case "display":
				parseDisplayCommand();
				break;
			case "search":
				parseSearchCommand();
				break;
			default:
				throw new Error("command not recognised: "+cmdType);
		}
	}
	
	private void parseSearchCommand() {
		String args = getArgs();
		if(args.contains("on")) {
			String date = args.substring(3);
			setSearchDate(date);
		} else {
			setSearchWord(args);
		}
		
	}
	private void parseAddCommand(){
		String args = getArgs();
		String taskType = getTaskType(args);
		switch (taskType){
			case "float":
				parseFloatTask(args);
				break;
			case "deadline":
				parseDeadlineTask(args);
				break;
			case "event":
				parseEventTask(args);
				break;
			default:
				throw new Error("task type not recognised");		
		}

	}
	
	private void parseDeleteCommand(){
		String args = getArgs();
		setTaskID(args);
	}
	
	private void parseDisplayCommand(){
		String args = getArgs();
		if(args.equals("archived")) {
			setDisplayMode(args);
		} else if(args.equals("all")) {
			setDisplayMode(args);
		} else {
			throw new Error("display cmd invalid");
		}
	}
	
	private void setDisplayMode(String args) {
		this.displayMode = args;
	}
	private static String getTaskType(String input){
		if(input.contains("from")) {
			return "event";
		} else if (input.contains("by")) {
			return "deadline";
		} else {
			return "float";
		}	
	}
	
	private void parseFloatTask(String args){
		setTaskName(args);
	}
	
	private void parseEventTask(String args) {
		//add meeting from 2015-10-03 0900 to 2015-10-04 0900
		String[] argsArray = args.split("from |to");
		String[] startArr = argsArray[1].split(" ");
		String[] endArr = argsArray[2].trim().split(" ");
		
		setTaskName(argsArray[0]);
		setStartDate(startArr[0]);
		setStartTime(startArr[1]);
		setEndDate(endArr[0]);
		setEndTime(endArr[1]);

	}
	
	private void parseDeadlineTask(String args) {
		//add finish project manual by 2015-10-03 0900
		String[] argsArray = args.split(" by ");
		String[] endArr = argsArray[1].split(" ");
		setTaskName(argsArray[0]);
		setEndDate(endArr[0]);
		setEndTime(endArr[1]);
	}
	
	private void setUserInput(String userInput){
		this.userInput = userInput;
	}
	private void setCommandType(){
		String[] inputArr = this.userInput.split(" ", 2);
		this.command = inputArr[0].trim();  
	}
	private void setArgs(){
		if(!this.command.equals("undo") ){
			String[] inputArr = this.userInput.split(" ", 2);
			this.args = inputArr[1];
		}
	}
	private String getArgs() {
		return this.args;
	}
	private void setTaskName(String name) {
		this.taskName = name.trim();
	}
	private void setStartTime(String time) {
		this.startTime = time.trim();
	}
	private void setEndTime(String time) {
		this.endTime = time.trim();
	}
	private void setStartDate(String date) {
		this.startDate = date.trim();
	}
	private void setEndDate(String date) {
		this.endDate = date.trim();
	}
	private void setTaskID(String id) {
		int taskID = Integer.parseInt(id);
		this.taskID = taskID;
	}
	private void setSearchDate(String date) {
		this.searchDate = date;
	}	
	private void setSearchWord(String word) {
		this.searchWord = word;
	}
	
	static void print(String[] str){
		for(int i=0;i<str.length;i++){
			System.out.println("Index "+i+" : "+ str[i]);
		}
	}
	public static void main(String[] args) {
		CommandParser cp2 = new CommandParser("display archived");
		String str = "display archived";
		System.out.println(cp2.getCommandType());
		
	}
	

}

class CommandChecker {
	//Attribute
	private boolean isValid;
	private String errorMessage;
	
	//Constructor
	public CommandChecker(String userInput) {
		this.isValid = true;
	}
	
	public boolean isValid(){
		return this.isValid;
	}
	
	public String getErrorMessage(){
		return this.errorMessage;
	}
}