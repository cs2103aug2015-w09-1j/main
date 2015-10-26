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
//	    a. edit the whole task
//	    	edit <id> <all information>
//		b. edit a specific attribute
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

import java.util.LinkedList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.time.*;

import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;
import org.ocpsoft.prettytime.nlp.parse.DateGroup;
import org.ocpsoft.prettytime.shade.org.antlr.runtime.debug.Profiler.DecisionDescriptor;

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
	
	private LocalDateTime start;
	private LocalDateTime end;
	
	private int taskID;
	private String searchWord;
	private String searchDate;
	private String displayMode;
	private String deleteMode;
	private String storagePath;
	private String editAttribute;
	private String editInfo;
	private LocalDateTime editDate;
	private String searchOnDate;
	private String searchByDate;
	private int[] deleteIDs;
	
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
	public LocalDateTime getEditDate() {
		return this.editDate;
	}
	public int[] getDeleteIDs(){
		return this.deleteIDs;
	}
	public LocalDateTime getEndDateTime(){
		return this.end;
	}
	public LocalDateTime getStartDateTime(){
		return this.start;
	}
	public String getSearchOnDate() {
		return this.searchOnDate;
	}
	public String getSearchByDate() {
		return this.searchByDate;
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
		String[] argArray = args.split(" ");
		String[] argArray2 = args.split(" ", 2);
		setTaskID(Integer.parseInt(argArray[0]));
		if(isAttribute(argArray[1])) {
			//edit <id> <attribute> <info>
			setEditAttribute(argArray[1]);
			List<Date> dates = new PrettyTimeParser().parse(argArray2[1]);
			if (dates.size() == 0) {
				setEditInfo(argArray[2]);
			} else if(argArray[1].contains("Time")) {
				String time = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalTime().toString();
				setEditInfo(time);
			} else if(argArray[1].contains("Date")) {
				String date = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate().toString();
				setEditInfo(date);
			}
		} else {
			//edit <id> <all info>
			parseAddCommand(argArray2[1]);
		}

	}
	
	private boolean isAttribute(String str) {
		LinkedList<String> ls = new LinkedList<String>();
		ls.add("startDate");
		ls.add("startTime");
		ls.add("endDate");
		ls.add("endTime");
		ls.add("taskName");
		return ls.contains(str);
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
			Date date = new PrettyTimeParser().parse(args).get(0);
			LocalDateTime searchDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			setSearchOnDate(searchDate.toLocalDate().toString());
		} else if(args.contains("by")) {
			Date date = new PrettyTimeParser().parse(args).get(0);
			LocalDateTime searchDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			setSearchByDate(searchDate.toLocalDate().toString());
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
			String[] argsArray = args.split(",");
			for(int i=0; i<argsArray.length; i++){
				argsArray[i] = argsArray[i].trim();
			}
			if (argsArray.length == 1 && !argsArray[0].contains("-")) {
				setTaskID(Integer.parseInt(args));
			} else {
				parseMultipleIDs(argsArray);
			}
		}
	}
	
	private void parseMultipleIDs(String[] argsArray){
		LinkedList<Integer> l = new LinkedList<Integer>();
		int[] idArr;
		for(int i=0; i<argsArray.length;i++){
			String[] indexArr = argsArray[i].split("-");
			if(indexArr.length == 1) {
				l.add(Integer.parseInt(indexArr[0]));
			} else {
				int startIndex = Integer.parseInt(indexArr[0]);
				int endIndex = Integer.parseInt(indexArr[1]);
				
				if(startIndex>endIndex){
					throw new Error("delete index invalid");
				}
				
				while(startIndex <= endIndex) {
					if(!l.contains(startIndex)) {
						l.add(startIndex);
					}
					startIndex++;
				}
				
			}
		}
		idArr = new int[l.size()];
		for(int k=0; k<l.size(); k++){
			idArr[k] = l.get(k);
		}
		setDeleteIDs(idArr);
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
		String[] argsArray = args.split("from | to ");
		String startStr = argsArray[1];
		String endStr = argsArray[2];
		
		setTaskName(argsArray[0]);
		Date start = new PrettyTimeParser().parse(startStr).get(0);		
		Date end = new PrettyTimeParser().parse(endStr).get(0);
		LocalDateTime startLdt = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime endLdt = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		setStartDateTime(startLdt);
		setEndDateTime(endLdt);
		setStartDate(getDateString(startLdt));
		setStartTime(getTimeString(startLdt));
		setEndDate(getDateString(endLdt));
		setEndTime(getTimeString(endLdt));


	}
	private String getDateString(LocalDateTime ldt) {
		String date = ""+ldt.getYear()+"-"+format(ldt.getMonthValue())+"-"+format(ldt.getDayOfMonth());
		return date;

	}
	private String getTimeString(LocalDateTime ldt) {
		String time = ""+format(ldt.getHour())+":"+format(ldt.getMinute());
		return time;

	}
	
	private void parseDeadlineTask(String args) {
		//add finish project manual by 2015-10-03 0900
		String[] argsArray = args.split(" by ");
		String endStr = argsArray[1];
		
		setTaskName(argsArray[0]);
		
		Date end = new PrettyTimeParser().parse(endStr).get(0);
		LocalDateTime ldt = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		setEndDateTime(ldt);
		
		String endDate = ""+ldt.getYear()+"-"+format(ldt.getMonthValue())+"-"+format(ldt.getDayOfMonth());
		String endTime = ""+format(ldt.getHour())+":"+format(ldt.getMinute());
		setEndDate(endDate);
		setEndTime(endTime);


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
	private void setDeleteIDs(int[] ids){
		this.deleteIDs = ids;
	}
	private void setEndDateTime(LocalDateTime end) {
		this.end = end;
	}
	private void setStartDateTime(LocalDateTime start) {
		this.start = start;
	}
	private void setEditDate(LocalDateTime date) {
		this.editDate = date;
	}
	private void setSearchOnDate(String date) {
		this.searchOnDate = date;
	}
	private void setSearchByDate(String date) {
		this.searchByDate = date;
	}
	
	static void print(String[] str){
		for(int i=0;i<str.length;i++){
			System.out.println("Index "+i+" : "+ str[i]);
		}
	}
	
	static void print(int[] str){
		for(int i=0;i<str.length;i++){
			System.out.println("Index "+i+" : "+ str[i]);
		}
	}
	
	static String format(int value){
		String str = "" + value;
		if(str.split("").length == 1) {
			str = "0"+str;
		}
		return str;
	}


	public static void main(String[] args) {
		CommandParser cp2 = new CommandParser("delete 2-5");
		print(cp2.getDeleteIDs());
//		String arg = "edit 2 startDate sad";
//		String[] argsArray = arg.split("from | to ");
		
//		String[] argsArray = arg.split(" ");
//		String startStr = argsArray[1];
//		String endStr = argsArray[2];
//		System.out.print(startStr);
//		System.out.print(endStr);
//		print(argsArray);
		
//		String str = "1-10";
//		String[] strArr = str.split("-");
//		for(int i=0; i<strArr.length; i++){
//			strArr[i] = strArr[i].trim();
//		}
//		print(strArr);
//		System.out.println(cp2.getDeleteIDs());
//		System.out.print(cp2.getDeleteIDs().toString());
		

//		PrettyTime p = new PrettyTime();
//		System.out.println(p.format(new Date()));
		//prints: “moments from now”

//		System.out.println(p.format(new Date(System.currentTimeMillis() + 1000*60*10)));
		//prints: “10 minutes from now”
		
//	     List<Date> dates = new PrettyTimeParser().parse("buy october tenth");
//	     System.out.println(dates);
	     
//	     List<Date> dates = new PrettyTimeParser().parse("add finish report by tonight");
//	     System.out.println(dates.get(0));
//	     System.out.println(dates.get(0).getDay());
	     
//	     List<DateGroup> parse = new PrettyTimeParser().parseSyntax("I eat fish every three days");
//	     System.out.println(parse.get(0).getDates().get(0));

		
//		String endStr = "tomorrow night";
//		Date end = new PrettyTimeParser().parse(endStr).get(0);
//		LocalDateTime ldt = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//		System.out.println(ldt.getDayOfMonth());
//		System.out.println(ldt.getDayOfWeek());
//		System.out.println(ldt.getDayOfYear());
//		System.out.println(ldt.getYear());
//		
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