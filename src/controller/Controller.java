package controller;

import java.util.*;
import java.io.IOException;

import parser.*;
import model.*;

/**
 * 
 * Method to parse user commands, and pass parameters to UI, Storage and Logic
 * 
 */
public class Controller {
	
	public Controller(){
		
	}
	private static Scanner scanner = new Scanner(System.in);

	private static final String MESSAGE_ERROR_COMMAND = "Unrecongnised command entered";

	private static ArrayList<String> content = new ArrayList<String>();

	private static void executeCMD(String cmdType) throws IOException {
		switch (cmdType) {
			case "add": {
				Logic logic = new Logic();
				//logic.buildTask(task_id, task_name, description, date, start_time, end_time)
				//ICommand cmd = new Create();
				//cmd.execute();
			}
			case "delete": {
				
			}
		}

	}

	public static void inputCommand() throws IOException {
		System.out.print("command: ");
		String command = scanner.nextLine();
		parser.Parser parse = new Parser(command);
		String cmdType = parse.getCommandType();
		executeCMD(cmdType);
	}

}
