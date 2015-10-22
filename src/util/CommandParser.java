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
//		a. delete single task
//			delete <id>
//		b. delete all tasks
//			delete all
//		c. delete multiple tasks
//			delete <index>,<index>,<startIndex-EndIndex>
//	
//	3) search
//		a. search tasks on a certain date
//			search on <date>
//		b. search by task name
//			search <keyword>
//	
//	4) display / showall
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
//		c. edit a specific attribute
//			edit <id> <attribute> <info>
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
//	
//	11) load
//		load
//		
//	12) save
//		save


package util;

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
	private String deleteMode;
	private String storagePath;
	private String editAttribute;
	private String editInfo;
	
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
	public String getStoragePath(){
		return this.storagePath;
	}
	public String getDeleteMode(){
		return this.deleteMode;
	}
	public String getEditAttribute(){
		return this.editAttribute;
	}
	public String getEditInfo(){
		return this.editInfo;
	}
	
	
	//private methods
	private void parse(){
		String cmdType = getCommandType();
		switch (cmdType) {
			case "add":
				parseAddCommand(getArgs());
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
			case "undo":
				parseUndoCommand();
				break;
			case "help":
				parseHelpCommand();
				break;
			case "complete":
				parseCompleteCommand();
				break;
			case "archive":
				parserArchiveCommand();
				break;
			case "set":
				parseSetCommand();
				break;
			case "edit":
				parseEditCommand();
				break;
			case "save":
				parseSaveCommand();
				break;
			case "load":
				parseLoadCommand();
				break;
			default:
				throw new Error("command not recognised: "+cmdType);
		}
	}
	
	private void parseSaveCommand(){
		
	}
	
	private void parseLoadCommand(){
		
	}
	
	private void parseEditCommand(){
		String args = getArgs();
		String[] argArray = args.split(" ", 2);
		String[] argArray2 = args.split(" ");
		if(argArray.length == 1) {
			setTaskID(Integer.parseInt(args));
		} else if(argArray2.length == 3){
			setTaskID(Integer.parseInt(argArray2[0]));
			setEditAttribute(argArray2[1]);
			setEditInfo(argArray2[2]);
		} else {
			String id = argArray[0];
			setTaskID(Integer.parseInt(id));
			parseAddCommand(argArray[1]);
			
		}
	}
	
	private void parseSetCommand(){
		String args = getArgs();
		setStoragePath(args);
	}
	
	private void parserArchiveCommand() {
		String args = getArgs();
		setTaskID(Integer.parseInt(args));
		
	}
	private void parseCompleteCommand() {
		String args = getArgs();
		setTaskID(Integer.parseInt(args));
		
	}
	private void parseHelpCommand(){
		
	}
	
	private void parseUndoCommand(){
		
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
	private void parseAddCommand(String args){
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
		if(args.equals("all")) {
			setDeleteMode("all");
		} else {
			setTaskID(Integer.parseInt(args));
		}
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
		if(!this.command.equals("undo") && !this.command.equals("help")
				&& !this.command.equals("load") && !this.command.equals("save")){
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
	private void setTaskID(int id) {
		this.taskID = id;
	}
	private void setSearchDate(String date) {
		this.searchDate = date;
	}	
	private void setSearchWord(String word) {
		this.searchWord = word;
	}
	private void setStoragePath(String path) {
		this.storagePath = path;
	}
	private void setEditAttribute(String attribute) {
		this.editAttribute = attribute;
	}
	private void setEditInfo(String info) {
		this.editInfo = info;
	}
	
	private void setDeleteMode(String mode){
		this.deleteMode = mode;
	}
	
	static void print(String[] str){
		for(int i=0;i<str.length;i++){
			System.out.println("Index "+i+" : "+ str[i]);
		}
	}

	public static void main(String[] args) {
//		CommandParser cp2 = new CommandParser("edit 2 startDate 2015-10-23");
		String str = "1, 2, 3, 4-6";
		String[] strArr = str.split(",");
		for(int i=0; i<strArr.length; i++){
			strArr[i] = strArr[i].trim();
		}
		print(strArr);
//		System.out.println(cp2.getEditAttribute());
		
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