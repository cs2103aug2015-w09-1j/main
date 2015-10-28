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
//		 Controller.executeCMD("add abc");
		
		Controller.executeCMD("add efg");
		Controller.executeCMD("delete 2");
		Controller.executeCMD("edit 1 taskName aaa");
		Controller.executeCMD("show archived");
		Controller.executeCMD("unarchived 1");
		
//		 Controller.executeCMD("archive 1");
//		Controller.executeCMD("complete 1");
//	 Controller.executeCMD("save");
		//Controller.executeCMD("set C:/Users/calvin/Desktop/products/");
		//System.out.println(Controller.getSize());
		printout(Controller.getNoArchivedList());
		 System.out.println("---------------------------------------");
		printout(Controller.getTaskList());
		 System.out.println("---------------------------------------");
//		printout(Controller.getFloatingTaskList());
//		System.out.println("----------------------------------------");
//		printout(Controller.getFollowingWeekTaskList());
//		System.out.println("----------------------------------------");
//		printout(Controller.getOtherTaskList());
		System.out.println("----------------------------------------");
		printout(Controller.getArchivedList());
		
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
