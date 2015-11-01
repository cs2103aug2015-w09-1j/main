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
//	Controller.executeCMD("delete all");
//		 Controller.executeCMD("add I am at starbucks now doing 2103T from 2015-10-20 1600 to 2015-10-30 1800");
//		 Controller.executeCMD("add lecture from 2015-10-20 1600 to 2015-10-30 1800");
//		 Controller.executeCMD("add abc by today");
//		 Controller.executeCMD("add abc by today 5.11pm");
//		 Controller.executeCMD("add abc by today 5.09pm");
//		Controller.executeCMD("add calvinsim by 2015-10-29 2159");
//		Controller.executeCMD("add calvinsim by 2015-10-29 2201");
//		Controller.executeCMD("add hijjjj by 2015-11-10");
//		Controller.executeCMD("add 2103T HELLO by 2015-11-11");
		
//		Controller.executeCMD("edit 1 taskName aaa");
//		Controller.executeCMD("show archived");
//		Controller.executeCMD("edit 2 taskName aaa");
//		Controller.executeCMD("undo");
//		
//		 Controller.executeCMD("archive 1");
//		Controller.executeCMD("complete 1");
		//Controller.executeCMD("save");
		//Controller.executeCMD("set C:/Users/calvin/Desktop/products/");
		//System.out.println(Controller.getSize());
		printout(Controller.getTaskList());
		 System.out.println("---------------------------------------");
		printout(Controller.getFloatingTaskList());
		System.out.println("----------------------------------------");
		printout(Controller.getFollowingWeekTaskList());
		System.out.println("----------------------------------------");
		printout(Controller.getOtherTaskList());
		System.out.println("----------------------------------------");
		printout(Controller.getArchivedList());
		System.out.println("----------------------------------------");
		printout(Controller.getTodayTaskList());
		
	}

	private static void printout(ArrayList<Task> taskList) {
		try{
			for (int i = 0; i < taskList.size(); i++) {
				if (taskList.get(i) instanceof EventTask) {
					EventTask et = (EventTask) taskList.get(i);
					System.out.println(et.getTaskName() + " from "
							+ et.getStartTime() + " " + et.getStartDate() + " to "
							+ et.getEndTime() + " " + et.getEndDate() + " " + et.getTaskType());
				} else if (taskList.get(i) instanceof DeadlineTask) {
					DeadlineTask dl = (DeadlineTask) taskList.get(i);
					System.out.println(dl.getTaskName() + " by "
							+ dl.getDeadlineTime() + " " + dl.getDeadlineDate() + " " + dl.getTaskType());
				} else if (taskList.get(i) instanceof FloatingTask) {
					FloatingTask fl = (FloatingTask) taskList.get(i);
					System.out.println(fl.getTaskName() + " " + fl.getTaskType());
				}
			}
		}catch(Exception e){
			
		}
	}

}
