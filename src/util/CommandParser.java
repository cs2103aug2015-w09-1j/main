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
//		a. search tasks based on its name
//			search <name>
//		b. search tasks on a certain date
//			search on <date>
//		c. search by task name
//			search by <keyword>
//	
//	4) display 
//		display (all) -- display the default view
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
//	7) (un)complete
//		complete <id> -- mark the task as complete
//		
//	8) (un)archive
//		archive <id>
//	
//	9) set storage path
//		set path <storage path>
//		set name <file name>
//	
//	10) help
//		help
//	
//	11) load
//		load
//		
//	12) save
//		save
//
//	14) show
//		a. show all floating tasks
//			show floating  
//
//		b. show a certain date
//			show on <date>
// 
//		c. show everything before a date
//			show by <date>
//
//		d. show a period
//			show from <date> to <date>
//
//		e. show archived
//			show archived

//		f. show complete
//			show complete


//@@author A0133976U

package util;

import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

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
	private String storageFileName;
	private String editAttribute;
	private String editInfo;
	private LocalDateTime editDate;
	private String searchOnDate;
	private String searchByDate;
	private int[] deleteIDs;
	private int[] archivedIDs;
	
	private String showOption;
	private String showByDate;
	private String showDate;
	private String showStartDate;
	private String showEndDate;
	
	private int[] unarchivedIDs;
	private int[] uncompleteIDs;
	private int[] completeIDs;
	
	private String searchStartDate;
	private String searchEndDate;
	
	private static String help = "add <name>\nadd <name> from <time> to <time>\nadd <name> by   <deadline>\ndelete  <id>\nsearch  <id>\narchive <id>\nedit <id> <attribute> <info>\nset  path     <storage path>\nset  filename <filename>\nundo\n";
	
	CommandChecker cc;
	
	//Constructor
	public CommandParser(String userInput) throws Exception {
		this.setUserInput(userInput);
		cc = new CommandChecker(userInput);
		if (!cc.isValid()) {
			throw new Exception(cc.getErrorMessage());
		} else {
			 this.setCommandType();
			 this.setArgs();
			 parse();
		} 			
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
	public String getShowOption() {
		return this.showOption;
	}
	public String getShowByDate() {
		return this.showByDate;
	}
	public String getShowDate() {
		return this.showDate;
	}
	public String getShowStartDate() {
		return this.showStartDate;
	}
	public String getShowEndDate() {
		return this.showEndDate;
	}
	public int[] getUncompleteIDs() {
		return this.uncompleteIDs;
	}
	public int[] getUnarchivedIDs() {
		return this.unarchivedIDs;
	}
	public int[] getArchivedIDs() {
		return this.archivedIDs;
	}
	public int[] getCompleteIDs() {
		return this.completeIDs;
	}
	public String getStorageFileName() {
		return this.storageFileName;
	}
	public String getHelpString() {
		return help;
	}
	public String getSearchStartDate() {
		return this.searchStartDate;
	}
	public String getSearchEndDate() {
		return this.searchEndDate;
	}
	//private methods
	private void parse() throws Exception{
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
				parseArchiveCommand();
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
			case "show":
				parseShowCommand();
				break;
			case "home":
				parseHomeCommand();
				break;
			case "unarchived":
				parseUnarchivedCommand();
				break;
			case "uncomplete":
				parseUncompleteCommand();
				break;
			case "exit":
				parseExitCommand();
				break;
			case "clear":
				parseClearCommand();
				break;
			default:
				throw new Exception("command cannot be recongnised");
		}
	}
	
	private void parseClearCommand(){
		
	}
	
	private void parseSaveCommand() { 
		
	}
	
	private void parseLoadCommand() {
		
	}
	
	private void parseHomeCommand() {
		
	}
	
	private void parseExitCommand() {
		
	}
	private void parseUnarchivedCommand() throws Exception {
		String args = getArgs();
		String[] argsArray = args.split(",");
		for(int i=0; i<argsArray.length; i++){
			argsArray[i] = argsArray[i].trim();
		}
		int[] idArr = parseMultipleIDs(argsArray);
		setUnarchivedIDs(idArr);
	}
	
	private void parseUncompleteCommand() throws Exception {
		String args = getArgs();
		String[] argsArray = args.split(",");
		for(int i=0; i<argsArray.length; i++){
			argsArray[i] = argsArray[i].trim();
		}
		int[] idArr = parseMultipleIDs(argsArray);
		setUncompleteIDs(idArr);
	}
	
	private void parseShowCommand() throws Exception{
		String args = getArgs();
		if(args.contains("archive")) {
			setShowOption("archived");
		} else if (args.contains("floating")) {
			setShowOption("floating");	
		} else if (args.contains("complete")) {
			setShowOption("complete");
		} else if (args.contains("by")){
			String date = args.split(" ", 2)[1];
			List<Date> dates = new PrettyTimeParser().parse(date);
			if(dates.size() != 1) {
				throw new Exception("time cannot be reconginsed");
			}
			date = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate().toString();
			setShowByDate(date);
		} else {
			List<Date> dates = new PrettyTimeParser().parse(args);
			if(dates.size() == 1) {
				String date = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate().toString();
				setShowDate(date);
			} else if(dates.size() == 2){
				String startDate = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate().toString();
				setShowStartDate(startDate);
				String endDate = dates.get(1).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate().toString();
				setShowEndDate(endDate);
			} else {
				throw new Exception("time cannot be recongised");
			}
		}
	}
	
	private void parseEditCommand() throws Exception{
		String args = getArgs();
		String[] argArray = args.split(" ");
		String[] argArray2 = args.split(" ", 2);
		if(!isInteger(argArray[0])){
			throw new Exception("Task index cannot be parsed");
		} 
		
		setTaskID(Integer.parseInt(argArray[0]));
		if(isAttribute(argArray[1].toLowerCase())) {
			//edit <id> <attribute> <info>
			setEditAttribute(argArray[1].toLowerCase());
			List<Date> dates = new PrettyTimeParser().parse(argArray2[1]);
			if (dates.size() == 0) {
				setEditInfo(argArray[2].trim());
			} else if(argArray[1].toLowerCase().contains("time")) {
				String time = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalTime().toString();
				setEditInfo(time);
			} else if(argArray[1].toLowerCase().contains("date")) {
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
		ls.add("startdate");
		ls.add("starttime");
		ls.add("enddate");
		ls.add("endtime");
		ls.add("taskname");
		return ls.contains(str);
	}
	
	private void parseSetCommand() throws Exception{
		String[] args = getArgs().split(" ", 2);
		String type = args[0].toLowerCase();
		if(type.equals("filename")) {
			setStorageFileName(args[1]);
		} else if(type.equals("path")) {
			setStoragePath(args[1]);
		} else {
			throw new Exception("set attribute cannot be recongised");
		}
	}
	
	private void parseArchiveCommand() throws Exception {
		String args = getArgs();
		String[] argsArray = args.split(",");
		int[] idArr = parseMultipleIDs(argsArray);
		setArchivedIDs(idArr);
		
	}
	private void parseCompleteCommand() throws Exception {
		String args = getArgs();
		String[] argsArray = args.split(",");
		int[] idArr = parseMultipleIDs(argsArray);
		setCompleteIDs(idArr);
		
	}
	private void parseHelpCommand(){
		
	}
	
	private void parseUndoCommand(){
		
	}
	
	private void parseSearchCommand() throws Exception {
		String args = getArgs();
		if(args.contains("on")) {
			Date date = new PrettyTimeParser().parse(args).get(0);
			LocalDateTime searchDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			setSearchOnDate(searchDate.toLocalDate().toString());
		} else if(args.contains("by")) {
			Date date = new PrettyTimeParser().parse(args).get(0);
			LocalDateTime searchDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			setSearchByDate(searchDate.toLocalDate().toString());
		} else if(args.contains("from")){
			List<Date> dates = new PrettyTimeParser().parse(args);
			if(dates.size() == 2) {
				String startDate = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate().toString();
				setSearchStartDate(startDate);
				String endDate = dates.get(1).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate().toString();
				setSearchEndDate(endDate);
			} else {
				throw new Exception("dates cannot be recongised");
			}
		} else {
			setSearchWord(standarlizeWord(args));
		}
		
	}
	
	private String standarlizeWord(String args) {
		String[] argsArr = args.split("\\s+");
		String result = "";
		for(int i=0; i<argsArr.length; i++) {
			argsArr[i] = argsArr[i].trim();
			result += argsArr[i];
			result += " ";
		}
		return result.trim();
	}
	private void parseAddCommand(String args) throws Exception{
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
				throw new Exception("task type not recognised");		
		}

	}
	
	private void parseDeleteCommand() throws Exception{
		String args = getArgs();
		if(args.contains("all")) {
			setDeleteMode("all");
		} else {
			String[] argsArray = args.split(",");
			if (argsArray.length == 1 && !argsArray[0].contains("-")) {
				args = args.replaceAll("\\D+","");
				if(args != null) {
					setDeleteTaskID(Integer.parseInt(args));
				} else {
					throw new Exception("deleted index invalid");
				}
			} else {
				int[] idArr = parseMultipleIDs(argsArray);
				setDeleteIDs(idArr);
			}
		}
	}
	
	private int[] parseMultipleIDs(String[] argsArray) throws Exception{
		for(int i=0; i<argsArray.length; i++){
			argsArray[i]= argsArray[i].trim();
		}
		LinkedList<Integer> l = new LinkedList<Integer>();
		int[] idArr;
		for(int i=0; i<argsArray.length;i++){
			String[] indexArr = argsArray[i].split("-");
			if(indexArr.length == 1 && isInteger(indexArr[0])) {
				l.add(Integer.parseInt(indexArr[0].replaceAll("\\D+", "")));
			} else {
				String start = indexArr[0].replaceAll("\\D+", "");
				String end = indexArr[1].replaceAll("\\D+","");
				if(start == null || end == null) {
					throw new Exception("delete index cannot recongised");
				}
				int startIndex = Integer.parseInt(start);
				int endIndex = Integer.parseInt(end);
				
				if(startIndex>endIndex){
					throw new Exception("delete index invalid");
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
		return idArr;
	}
	
	private void parseDisplayCommand() throws Exception{
		String args = getArgs();
		if(args == null || args.contains("all")){
			setDisplayMode("all");
		} else {
			throw new Exception("display cmd invalid");
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
	
	private void parseEventTask(String args) throws Exception {
		String taskNameStr = args.substring(0, args.lastIndexOf("from"));
		String timeStr = args.substring(args.lastIndexOf("from"),args.length()-1);
		
		setTaskName(taskNameStr);
		
		List<Date> dates = new PrettyTimeParser().parse(timeStr);
		if(dates.size()==2) {
			LocalDateTime startLdt = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			LocalDateTime endLdt = dates.get(1).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			setStartDateTime(startLdt);
			setEndDateTime(endLdt);
			setStartDate(getDateString(startLdt));
			setStartTime(getTimeString(startLdt));
			setEndDate(getDateString(endLdt));
			setEndTime(getTimeString(endLdt));
		} else {
			throw new Exception("time cannot be recongized");
		}

	}
	private String getDateString(LocalDateTime ldt) {
		String date = ""+ldt.getYear()+"-"+format(ldt.getMonthValue())+"-"+format(ldt.getDayOfMonth());
		return date;

	}
	private String getTimeString(LocalDateTime ldt) {
		String time = ""+format(ldt.getHour())+":"+format(ldt.getMinute());
		return time;

	}
	
	private void parseDeadlineTask(String args) throws Exception {
		//add finish project manual by 2015-10-03 0900
		String[] argsArray = args.split("\\s+by\\s+");
		String endStr = argsArray[argsArray.length-1];
		
		String startStr = args.substring(0,args.lastIndexOf("by"));
		
		setTaskName(startStr);
		
		
		List<Date> dates = new PrettyTimeParser().parse(endStr);
		
		if(dates.size() == 1){
			Date end = dates.get(0);
			LocalDateTime ldt = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			setEndDateTime(ldt);
		
			String endDate = ""+ldt.getYear()+"-"+format(ldt.getMonthValue())+"-"+format(ldt.getDayOfMonth());
			String endTime = ""+format(ldt.getHour())+":"+format(ldt.getMinute());
			setEndDate(endDate);
			setEndTime(endTime);
		} else {
			throw new Exception("deadline cannot be recongized correctly");
		}


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
				&& !this.command.equals("load") && !this.command.equals("save") && !this.command.equals("home")
				&& !this.command.equals("exit")){
			String[] inputArr = this.userInput.split(" ", 2);
			if(inputArr.length == 2) {
				this.args = trim(inputArr[1]);
			}
		}
	}
	private String trim(String str){
		String[] strArr = str.split("\\s+");
		String result = "";
		for(int i=0; i<strArr.length; i++) {
			result += strArr[i];
			result += " ";
		}
		return result.trim();
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
		this.editAttribute = attribute.toLowerCase();
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
	private void setShowOption(String option) {
		this.showOption = option;
	}
	private void setShowByDate(String date) {
		this.showByDate = date;
	}
	private void setShowDate(String date) {
		this.showDate = date;
	}
	private void setShowStartDate(String date) {
		this.showStartDate = date;
	}
	private void setShowEndDate(String date) {
		this.showEndDate = date;
	}
	private void setUncompleteIDs(int[] IDs) {
		this.uncompleteIDs = IDs;
	}
	private void setUnarchivedIDs(int[] IDs) {
		this.unarchivedIDs = IDs;
	}
	private void setArchivedIDs(int[] IDs) {
		this.archivedIDs = IDs;
	}
	private void setStorageFileName(String filename) {
		this.storageFileName = filename;
	}
	private void setCompleteIDs(int[] IDs) {
		this.completeIDs = IDs;
	}
	private void setSearchStartDate(String date) {
		this.searchStartDate = date;
	}
	private void setSearchEndDate(String date) {
		this.searchEndDate = date;
	}
	private void setDeleteTaskID(int index) {
		this.taskID = index;
	}
	private boolean isInteger(String str) {
		str = str.replaceAll("\\D+","");
		return str != null;
	}
	
	static String format(int value){
		String str = "" + value;
		if(str.split("").length == 1) {
			str = "0"+str;
		}
		return str;
	}


	public static void main(String[] args) {
//		String help = getHelpString();
//		System.out.print(getHelpString());
//		CommandParser cp2 = new CommandParser("delete 1,    3-5");
//		print(cp2.getDeleteIDs());
//		String str = "add drop tomorrow mother from tonight 2pm to 3pm";
//		System.out.println(str.substring(str.lastIndexOf("from"), str.length()-1));
//		String str ="";
//		str = str.replaceAll("\\D+","");
//		System.out.println(Integer.parseInt(str));
		
//		System.out.print(cp2.getSearchWord());
//		print(cp2.getDeleteIDs());
//		System.out.print(cp2.getUncompleteID());
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
		

	}
	

}

class CommandChecker {
	//Attribute
	private boolean isValid;
	private String errorMessage;
	
	//Constructor
	public CommandChecker(String userInput) {
		if(!isValidCommand(userInput)) {
			this.isValid = false;
		}
		this.isValid = true;
	}
	
	public boolean isValid(){
		return this.isValid;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	private boolean isValidCommand(String userInput) {
		String firstWord = getFirstWord(userInput);
		return isCommand(firstWord);
	}
	
	private void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		
	}
	
	private boolean isCommand(String word) {
		LinkedList<String> ls = new LinkedList<String>();
		ls.add("add");
		ls.add("edit");
		ls.add("display");
		ls.add("set");
		ls.add("show");
		ls.add("delete");
		ls.add("help");
		ls.add("save");
		ls.add("load");
		ls.add("search");
		ls.add("undo");
		ls.add("archive");
		ls.add("unarchive");
		ls.add("complete");
		ls.add("uncomplete");
		if(!ls.contains(word)){
			setErrorMessage(word + " is not a valid command!");
			return false;
		} else {
			return true;
		}
		
	}

	private String getFirstWord(String userInput) {
		return userInput.split("\\s+")[0].toLowerCase();
	}
}