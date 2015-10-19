package main;

import java.io.IOException;
import java.util.*;

import command.CreateTask;
import command.DeleteTask;
import command.TaskMemory;
import controller.*;
import model.*;

public class ToDoList {

	public static void main(String[] args) throws IOException {
		Controller.executeCMD("search");
		Controller.executeCMD("showall");
//		Controller.executeCMD("delete");
//		Controller.executeCMD("undo");
//		Controller.executeCMD("add");
		//Controller.getInstance();
		printout(Controller.getInstance().getTaskList());
//		Logic logic = new Logic();
//		
//		Task task = logic.buildTask("Going home to eat.", null, null, null, null);
//		CreateTask create = new CreateTask(task);
//		create.execute();
//		Task task2 = logic.buildTask("Going home to eat.", null, "10-10-2015", null, "1800");
//		CreateTask create2 = new CreateTask(task2);
//		create2.execute();
//		logic.pushToProcessStack(create);
//		logic.pushToProcessStack(create2);
//		
//		ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
//		System.out.println("taskList : " + taskList.size());
//		Task thisTask = taskList.get(4);
//		
//		if(task.equals(thisTask)){
//			System.out.println("True");
//		}else{
//			System.out.println("False");
//		}
//		
//		ArrayList<Task> searchedList = logic.searchTask(taskList, "2103");
//		printout(searchedList);
		
//		Task taskToDelete = logic.deleteTask(1);
//		DeleteTask delete = new DeleteTask(taskToDelete);
//		delete.execute();
//		
//		logic.pushToProcessStack(delete);
//		logic.undo();
//		logic.undo();

		//printout(taskList);
		
		

	}

	private static void printout(ArrayList<Task> taskList) {
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i) instanceof EventTask) {
				EventTask et = (EventTask) taskList.get(i);
				System.out.println(et.getTaskName() + " " + et.getStartTime() + " " + et.getEndTime() + " " + et.getStartDate() + " "+ et.getEndDate());
			}else if(taskList.get(i) instanceof DeadlineTask){
				DeadlineTask dl = (DeadlineTask) taskList.get(i);
				System.out.println(dl.getTaskName() + " " + dl.getDeadlineTime() + " " + dl.getDeadlineDate());
			}else if(taskList.get(i) instanceof FloatingTask){
				FloatingTask fl = (FloatingTask) taskList.get(i);
				System.out.println(fl.getTaskName());
			}
		}
	}

}
