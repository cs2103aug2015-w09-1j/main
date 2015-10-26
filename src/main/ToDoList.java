package main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import command.CreateTask;
import command.DeleteTask;
import command.TaskMemory;
import controller.*;
import model.*;

public class ToDoList {

	public static void main(String[] args) throws IOException {
		
//		 Controller.executeCMD("add I am at starbucks now doing 2103T from 2015-10-20 1600 to 2015-10-30 1800");
		 Controller.executeCMD("add lecture from 2015-10-20 1600 to 2015-10-30 1800");
//		Controller.executeCMD("add floatingtask1");
//		// //event task
//		 Controller.executeCMD("add going shopping by 2015-11-02 2200");
//		Controller.executeCMD("add going home by 2015-11-03 2200");
//		Controller.executeCMD("search by 2015-10-30");
		Controller.executeCMD("add going home by 2015-10-30 2200");
//		// // dealine task
//		// Controller.executeCMD("edit 4 "); // floating
//		 Controller.executeCMD("edit 1 u are a dog from 2015-10-20 1530 to 2015-10-20 1400");
//		 Controller.executeCMD("edit 1 startDate tomorrow");
		// Controller.executeCMD("add abcf");
		 //Controller.executeCMD("delete 2"); //delete
		// Controller.executeCMD("undo");
		//Controller.executeCMD("add abch");
		 //Controller.executeCMD("archive 1");
		 Controller.executeCMD("show by 2015-10-31");
		 Controller.executeCMD("archive 3");
		 Controller.executeCMD("archive 1");
		 Controller.executeCMD("show archived");
		 //Controller.executeCMD("show from 2015-10-29 to 2015-10-31");
		 Controller.executeCMD("unarchived 2");
		
		// Controller.executeCMD("search abc"); //search
		//Controller.executeCMD("set C:/Users/calvin/Desktop/products/");
		//Controller.executeCMD("save");
		// Controller.executeCMD("delete all");
		// Controller.executeCMD("search abc");
		// //search
		// Controller.executeCMD("search h");
		// Controller.executeCMD("delete 4");
		// Controller.executeCMD("display all"); //display all : not filtered

		// Controller.executeCMD("load");
		// Controller.executeCMD("delete 10     ");
		
		// Controller.executeCMD("search 2103");
		// Controller.executeCMD("delete 1");
		// Controller.executeCMD("edit 4 drink coffee from 2015-10-20 1500 to 2015-10-20 1700");
		// Controller.executeCMD("showall");
		// Controller.executeCMD("delete");
		 //Controller.executeCMD("undo");
		// Controller.executeCMD("add");
		// Controller.getInstance();
//		// System.out.println(Controller.getSize());
		 printout(Controller.getTaskList());
		 System.out.println("---------------------------------------");
		printout(Controller.getFloatingTaskList());
		System.out.println("----------------------------------------");
		printout(Controller.getFollowingWeekTaskList());
		System.out.println("----------------------------------------");
		printout(Controller.getOtherTaskList());
		System.out.println("----------------------------------------");
		printout(Controller.getArchivedList());
		//		// Logic logic = new Logic();
		//
		// Task task = logic.buildTask("Going home to eat.", null, null, null,
		// null);
		// CreateTask create = new CreateTask(task);
		// create.execute();
		// Task task2 = logic.buildTask("Going home to eat.", null,
		// "10-10-2015", null, "1800");
		// CreateTask create2 = new CreateTask(task2);
		// create2.execute();
		// logic.pushToProcessStack(create);
		// logic.pushToProcessStack(create2);
		//
		// ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
		// System.out.println("taskList : " + taskList.size());
		// Task thisTask = taskList.get(4);
		//
		// if(task.equals(thisTask)){
		// System.out.println("True");
		// }else{
		// System.out.println("False");
		// }
		//
		// ArrayList<Task> searchedList = logic.searchTask(taskList, "2103");
		// printout(searchedList);

		// Task taskToDelete = logic.deleteTask(1);
		// DeleteTask delete = new DeleteTask(taskToDelete);
		// delete.execute();
		//
		// logic.pushToProcessStack(delete);
		// logic.undo();
		// logic.undo();

		// printout(taskList);

	}

	private static void printout(ArrayList<Task> taskList) {
		try{
			for (int i = 0; i < taskList.size(); i++) {
				if (taskList.get(i) instanceof EventTask) {
					EventTask et = (EventTask) taskList.get(i);
					System.out.println(et.getTaskName() + " from "
							+ et.getStartTime() + " " + et.getStartDate() + " to "
							+ et.getEndTime() + " " + et.getEndDate());
				} else if (taskList.get(i) instanceof DeadlineTask) {
					DeadlineTask dl = (DeadlineTask) taskList.get(i);
					System.out.println(dl.getTaskName() + " by "
							+ dl.getDeadlineTime() + " " + dl.getDeadlineDate());
				} else if (taskList.get(i) instanceof FloatingTask) {
					FloatingTask fl = (FloatingTask) taskList.get(i);
					System.out.println(fl.getTaskName());
				}
			}
		}catch(Exception e){
			
		}
	}

}
