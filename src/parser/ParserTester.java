package parser;
import java.util.*;

public class ParserTester {
	

	public static void main(String[] args) {
		//Result of add cmd:
		System.out.println("Add: ");
		System.out.println("    floatingTask: "+floatingTaskTest());
		System.out.println("       eventTask: "+eventTaskTest());
		System.out.println("    deadlineTask: "+deadlineTaskTest());
		
		//Result of display cmd:
		System.out.println("\nDisplay: "+displayTest());
		
		//Result of delete cmd:
		System.out.println("\nDelete: "+deleteTest());
	}
	
	/* ================ Add ================*/
	private static String floatingTaskTest(){
		String input = "add -f having_diner with_Lucy";
		Parser parseResult = new Parser(input);
		if(!parseResult.getCommandType().equals("add")) {
			return "getCommandType() method is wrong";
		}
		if(!parseResult.getTaskName().equals("having_diner")) {
			return "getTaskName() method is wrong"; 
		}
		if(!parseResult.getDescription().equals("with_Lucy")) {
			return "getDescription() method is wrong";
		}
		if(parseResult.getDate() != null){
			return "getDate() method is wrong";
		}
		if(parseResult.getStartTime() != null){
			return "getStartTime() method is wrong";
		}
		if(parseResult.getEndTime() != null){
			return "getEndTime() method is wrong";
		}
		return "pass";
	}
	
	private static String eventTaskTest(){
		String input = "add -e attend_CS2103T_lecture topic_is_about_refactor 2015-10-02 1400 1600";
		Parser parseResult = new Parser(input);
		if(!parseResult.getCommandType().equals("add")) {
			return "getCommandType() method is wrong";
		}
		if(!parseResult.getTaskName().equals("attend_CS2103T_lecture")) {
			return "getTaskName() method is wrong"; 
		}
		if(!parseResult.getDescription().equals("topic_is_about_refactor")) {
			return "getDescription() method is wrong";
		}
		if(!parseResult.getDate().equals("2015-10-02")){
			return "getDate() method is wrong";
		}
		if(!parseResult.getStartTime().equals("1400")){
			return "getStartTime() method is wrong";
		}
		if(!parseResult.getEndTime().equals("1600")){
			return "getEndTime() method is wrong";
		}
		return "pass";
	}
	
	private static String deadlineTaskTest(){
		String input = "add -d submit_V0.1 add_delete_display_functions 2015-10-07 0900";
		Parser parseResult = new Parser(input);
		if(!parseResult.getCommandType().equals("add")) {
			return "getCommandType() method is wrong";
		}
		if(!parseResult.getTaskName().equals("submit_V0.1")) {
			return "getTaskName() method is wrong"; 
		}
		if(!parseResult.getDescription().equals("add_delete_display_functions")) {
			return "getDescription() method is wrong";
		}
		if(!parseResult.getDate().equals("2015-10-07")){
			return "getDate() method is wrong";
		}
		if(parseResult.getStartTime() != null){
			return "getStartTime() method is wrong";
		}
		if(!parseResult.getDeadlineTime().equals("0900")){
			return "getDeadlineTime() method is wrong";
		}
		return "pass";
	}

	/* ================ delete ================ */
	private static String deleteTest(){
		String input = "delete mock_id12345";
		Parser parseResult = new Parser(input);
		if(!parseResult.getCommandType().equals("delete")){
			return "getCommandType() method is wrong"; 
		}
		if(!parseResult.getIndex().equals("mock_id12345")){
			return "getIndex() method is wrong";
		}
		return "pass";
	}
	/* ================ delete ================ */	
	private static String displayTest(){
		String input = "display";
		Parser parseResult = new Parser(input);
		if(!parseResult.getCommandType().equals("display")){
			return "getCommandType() method is wrong";
		}
		return "pass";
	}
		

}
