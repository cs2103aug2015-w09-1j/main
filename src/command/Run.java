package command;

import java.util.ArrayList;

import controller.Controller;
import model.*;

public class Run {

	public static void main(String[] args) {
		Controller.executeCMD("archive 5");
		
        printout(Controller.getTaskList());
        System.out.println("F---------------------------------------");
       printout(Controller.getFloatingTaskList());
       System.out.println("FW----------------------------------------");
       printout(Controller.getFollowingDayTaskList());
       System.out.println("O----------------------------------------");
       //printout(Controller.getOtherTaskList());
       System.out.println("A----------------------------------------");
       printout(Controller.getArchivedList());
       System.out.println("T----------------------------------------");
       printout(Controller.getTodayTaskList());
       System.out.println("COMB----------------------------------------");
       printout(Controller.getCombinedTaskList());

	}
	
    private static void printout(ArrayList<Task> taskList) {
        try{
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i) instanceof EventTask) {
                    EventTask et = (EventTask) taskList.get(i);
                    System.out.println(i +" "+ et.getTaskName() + " from "
                            + et.getStartTime() + " " + et.getStartDate() + " to "
                            + et.getEndTime() + " " + et.getEndDate() + " " + et.getTaskType());
                } else if (taskList.get(i) instanceof DeadlineTask) {
                    DeadlineTask dl = (DeadlineTask) taskList.get(i);
                    System.out.println(i +" "+ dl.getTaskName() + " by "
                            + dl.getDeadlineTime() + " " + dl.getDeadlineDate() + " " + dl.getTaskType());
                } else if (taskList.get(i) instanceof FloatingTask) {
                    FloatingTask fl = (FloatingTask) taskList.get(i);
                    System.out.println(i +" "+ fl.getTaskName() + " " + fl.getTaskType());
                }
            }
        }catch(Exception e){
            
        }
    }


}
