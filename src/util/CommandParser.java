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
	/**********************************************************************
	 ************************   Attribute *****************************
	 ***********************************************************************/
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
	private String searchOnDate;
	private String searchByDate;
	private String searchStartDate;
	private String searchEndDate;
	private String displayMode;

	private String storagePath;
	private String storageFileName;
	
	private String editAttribute;
	private String editInfo;
	private LocalDateTime editDate;
	
	private String deleteMode;
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

	
	private static String help = "add <name>\nadd <name> from <time> to <time>\nadd <name> by   <deadline>\ndelete  <id>\nsearch  <id>\narchive <id>\nedit <id> <attribute> <info>\nset  path     <storage path>\nset  filename <filename>\nundo\n";
	
	CommandChecker cc;
	
	/**********************************************************************
	 ************************   Constructor  *****************************
	 ***********************************************************************/
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

	/**********************************************************************
	 ************************   Parser API *****************************
	 ***********************************************************************/
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
	
	/**********************************************************************
	 ***************Basic parsing methods for commands**************
	 ***********************************************************************/
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
	
	/**
	 * This method is used to parse an add command
	 */	
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
	
	/**
	 * This method is used to parse a show command
	 */		
	private void parseShowCommand() throws Exception{
		String args = getArgs();
		if(args.contains("archive")) {
			setShowOption("archived");
		} else if (args.contains("floating")) {
			setShowOption("floating");	
		} else if (args.contains("complete")) {
			setShowOption("complete");
		} else if (args.contains("by")){ //show tasks before a certain date
			String date = args.split(" ", 2)[1];
			List<Date> dates = new PrettyTimeParser().parse(date);
			assert(dates.size() == 1): "deadline cannot be reconginsed"; 
			date = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate().toString();
			setShowByDate(date);
		} else { //show tasks on a certain date or interval
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
				throw new Exception("time cannot be recongised correctly");
			}
		}
	}
	
	/**
	 * This method is used to parse an edit command
	 */	
	private void parseEditCommand() throws Exception{
		String args = getArgs();
		String[] argArray = args.split(" "); //will be used when editing one attribute
		String[] argArray2 = args.split(" ", 2); // will be used when updating all attributes
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
			} else {
				throw new Exception("attribute cannot be parsed correctly");
			}
		} else {
			//edit <id> <all info>
			//inner logic for updating all attributes and adding a new task is essentially the same
			//so parseAddCommand is called here.
			parseAddCommand(argArray2[1]);
		}

	}
	/**
	 * This method is used to parse a search command
	 */	
	private void parseSearchCommand() throws Exception {
		String args = getArgs();
		if(args.contains("on")) { //search tasks on a certain date
			Date date = new PrettyTimeParser().parse(args).get(0);
			LocalDateTime searchDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			setSearchOnDate(searchDate.toLocalDate().toString());
		} else if(args.contains("by")) { //search tasks before a certain date
			Date date = new PrettyTimeParser().parse(args).get(0);
			LocalDateTime searchDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			setSearchByDate(searchDate.toLocalDate().toString());
		} else if(args.contains("from")){ //search tasks in a time interval
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
			setSearchWord(removeExtraSpace(args));
		}
		
	}
	/**
	 * This method is used to parse a delete command
	 */	
	
	private void parseDeleteCommand() throws Exception{
		String args = getArgs();
		if(args.contains("all")) {
			setDeleteMode("all");
		} else {
			String[] argsArray = args.split(",");
			if (argsArray.length == 1 && !argsArray[0].contains("-")) {
				args = args.replaceAll("\\D+","");
				assert(args!=null):"user's input is not a digit";
				setDeleteTaskID(Integer.parseInt(args));

			} else {
				int[] idArr = parseMultipleIDs(argsArray);
				setDeleteIDs(idArr);
			}
		}
	}
	/**
	 * This method is used to parse a set command
	 */	
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
	/**
	 * This method is used to parse an archive command
	 */	
	private void parseArchiveCommand() throws Exception {
		String args = getArgs();
		String[] argsArray = args.split(",");
		int[] idArr = parseMultipleIDs(argsArray);
		setArchivedIDs(idArr);
		
	}
	/**
	 * This method is used to parse a complete command
	 */	
	private void parseCompleteCommand() throws Exception {
		String args = getArgs();
		String[] argsArray = args.split(",");
		int[] idArr = parseMultipleIDs(argsArray);
		setCompleteIDs(idArr);
		
	}
	/**
	 * This method is used to parse an unarchive command
	 */	
	private void parseUnarchivedCommand() throws Exception {
		String args = getArgs();
		String[] argsArray = args.split(",");
		int[] idArr = parseMultipleIDs(argsArray);
		setUnarchivedIDs(idArr);
	}
	/**
	 * This method is used to parse a complete command
	 */	
	private void parseUncompleteCommand() throws Exception {
		String args = getArgs();
		String[] argsArray = args.split(",");
		int[] idArr = parseMultipleIDs(argsArray);
		setUncompleteIDs(idArr);
	}
	/**
	 * This method is used to parse a display command
	 */	
	private void parseDisplayCommand() throws Exception{
		String args = getArgs();
		if(args == null || args.contains("all")){
			setDisplayMode("all");
		} else {
			throw new Exception("display command is invalid");
		}
	}
	/**
	 * This method is used to parse a clear command
	 */	
	private void parseClearCommand(){
		
	}
	/**
	 * This method is used to parse a save command
	 */	
	private void parseSaveCommand() { 
		
	}
	/**
	 * This method is used to parse a load command
	 */	
	private void parseLoadCommand() {
		
	}
	/**
	 * This method is used to parse a home command
	 */	
	private void parseHomeCommand() {
		
	}
	/**
	 * This method is used to parse an exit command
	 */	
	private void parseExitCommand() {
		
	}
	/**
	 * This method is used to parse a help command
	 */	
	private void parseHelpCommand(){
		
	}
	/**
	 * This method is used to parse an undo command
	 */	
	
	private void parseUndoCommand(){
		
	}
	/**
	 * This method is used to parse the user input 
	 * when user want to add an floating task
	 * @param user's input
	 */
	private void parseFloatTask(String args){
		setTaskName(args);
	}
	/**
	 * This method is used to parse the user input 
	 * when user want to add an event
	 * @param user's input
	 */
	private void parseEventTask(String args)  {
		String taskNameStr = args.substring(0, args.lastIndexOf("from"));
		String timeStr = args.substring(args.lastIndexOf("from"),args.length()-1);	
		setTaskName(taskNameStr);
		
		List<Date> dates = new PrettyTimeParser().parse(timeStr);
		assert(dates.size()==2): "event tasks' parsing result conatins more than two date";
		LocalDateTime startLdt = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime endLdt = dates.get(1).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			
		setStartDateTime(startLdt);
		setEndDateTime(endLdt);
		setStartDate(formatDateString(startLdt));
		setStartTime(formatTimeString(startLdt));
		setEndDate(formatDateString(endLdt));
		setEndTime(formatTimeString(endLdt));
	}

	/**
	 * This method is used to parse the user input 
	 * when user want to add an deadline task
	 * @param user's input
	 */
	private void parseDeadlineTask(String args) throws Exception {
		//add finish project manual by 2015-10-03 0900
		String[] argsArray = args.split("\\s+by\\s+");
		String endStr = argsArray[argsArray.length-1];		
		String startStr = args.substring(0,args.lastIndexOf("by"));
		setTaskName(startStr);
		
		List<Date> dates = new PrettyTimeParser().parse(endStr);
		assert(dates.size()==1): "deadline tasks' parsing result conatins more than one date";
		Date end = dates.get(0);
		LocalDateTime ldt = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		setEndDateTime(ldt);
		
		String endDate = ""+ldt.getYear()+"-"+formatTwoDigits(ldt.getMonthValue())+"-"+formatTwoDigits(ldt.getDayOfMonth());
		String endTime = ""+formatTwoDigits(ldt.getHour())+":"+formatTwoDigits(ldt.getMinute());
		setEndDate(endDate);
		setEndTime(endTime);

	}
	

	/**********************************************************************
	 ***************Miscellaneous setting attribute methods  *********
	 ***********************************************************************/
	
	private void setUserInput(String userInput){
		this.userInput = userInput;
	}
	private void setCommandType(){
		String[] inputArr = this.userInput.split(" ", 2);
		this.command = inputArr[0].trim();  
	}
	/**
	 * This method is used to set a global variable "args"
	 * which is the user's input without command keyword.
	 * This variable will be frequently used for further parsing.
	 */
	private void setArgs(){
		String[] inputArr = this.userInput.split(" ", 2);
		if(inputArr.length == 2) {
			this.args = removeExtraSpace(inputArr[1]);
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
	private void setDisplayMode(String args) {
		this.displayMode = args;
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
	
	/**********************************************************************
	 ***************Miscellaneous helper methods for parsing and formating*********
	 ***********************************************************************/
	/**
	 * This method is used to check whether a string can be parsed into an integer.
	 * 
	 * @param a string which by right should represent a index
	 */
	private boolean isInteger(String str) {
		str = str.replaceAll("\\D+","");
		return str != null;
	}
	/**
	 * This method is used to make a integer value be 2-digit
	 * 
	 * @param value that represents minute or second 
	 *            
	 * @return a string of length two. 
	 */
	static String formatTwoDigits(int value){
		String str = "" + value;
		if(str.split("").length == 1) {
			str = "0"+str;
		}
		return str;
	}
	/**
	 * This method is used to check if a string is a valid attribute 
	 * 
	 * @param a string that should be a attribute name 
	 */
	private boolean isAttribute(String str) {
		LinkedList<String> ls = new LinkedList<String>();
		ls.add("startdate");
		ls.add("starttime");
		ls.add("enddate");
		ls.add("endtime");
		ls.add("taskname");
		return ls.contains(str);
	}
	/**
	 * This method is used to remove extra space
	 * 
	 * @param a string typed by user 
	 *            
	 * @return a string with extra space removed
	 */
	private String removeExtraSpace(String args) {
		String[] argsArr = args.split("\\s+");
		String result = "";
		for(int i=0; i<argsArr.length; i++) {
			argsArr[i] = argsArr[i].trim();
			result += argsArr[i];
			result += " ";
		}
		return result.trim();
	}
	/**
	 * This method is used to covert a string into a list of index
	 * in integer type.
	 * 
	 * @param a string array that represents index. 
	 * Assume user use comma "," and minus "-"
	 * to separate different indexes.
	 *            
	 * @return a list of index in integer type
	 */
	private int[] parseMultipleIDs(String[] argsArray) throws Exception{
		for(int i=0; i<argsArray.length; i++){
			argsArray[i]= argsArray[i].trim(); 				//remove extra space
		}
		LinkedList<Integer> l = new LinkedList<Integer>();
		int[] idArr;
		for(int i=0; i<argsArray.length;i++){
			String[] indexArr = argsArray[i].split("-");
			if(indexArr.length == 1 && isInteger(indexArr[0])) { //it is only one integer
				l.add(Integer.parseInt(indexArr[0].replaceAll("\\D+", "")));
			} else {											//it is a series of integers
				String start = indexArr[0].replaceAll("\\D+", "");
				String end = indexArr[1].replaceAll("\\D+","");
				assert(start != null && end != null):"delete index cannot recongised";
				 
				int startIndex = Integer.parseInt(start);
				int endIndex = Integer.parseInt(end);
				if(startIndex>endIndex){
					throw new Exception("delete index invalid");
				}
				//add in parsed index into data structure
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
	/**
	 * This method is used to get a unified date format
	 * 
	 * @param an local date time object
	 *            
	 * @return a string that represents date in YYYY-MM-DD format
	 */
	private String formatDateString(LocalDateTime ldt) {
		String date = ""+ldt.getYear()+"-"+formatTwoDigits(ldt.getMonthValue())+"-"+formatTwoDigits(ldt.getDayOfMonth());
		return date;

	}
	/**
	 * This method is used to get a unified time format
	 * 
	 * @param an local date time object
	 *            
	 * @return a string that represents date in MM:SS format
	 */
	private String formatTimeString(LocalDateTime ldt) {
		String time = ""+formatTwoDigits(ldt.getHour())+":"+formatTwoDigits(ldt.getMinute());
		return time;

	}
	/**
	 * This method is used to detect a task type that will be added
	 * 
	 * @param user's input
	 *            
	 * @return a string that represents a task type
	 */
	private static String getTaskType(String input){
		if(input.contains("from")) {
			return "event";
		} else if (input.contains("by")) {
			return "deadline";
		} else {
			return "float";
		}	
	}
	

}

class CommandChecker {
	/*****************Attribute*****************/
	private boolean isValid;
	private String errorMessage;
	
	/*****************Constructor*****************/
	public CommandChecker(String userInput) {
		if(!isValidCommand(userInput)) {
			this.isValid = false;
		}
		this.isValid = true;
	}
	/*****************Public API*****************/
	public boolean isValid(){
		return this.isValid;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	/*****************Private helper methods*****************/
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