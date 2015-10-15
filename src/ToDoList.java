import java.io.IOException;
import java.util.*;

import command.CreateTask;
import command.DeleteTask;
import command.TaskMemory;
import controller.*;
import model.*;
import controller.Controller;

public class ToDoList {

	public static void main(String[] args) throws IOException {
		Task task = Logic.buildTask("Going home to eat.", null, null, null,
				null);
		CreateTask create = new CreateTask(task);
		create.execute();

		ArrayList<Task> taskList = TaskMemory.getInstance().getTaskList();
		System.out.println("taskList : " + taskList.size());

		DeleteTask delete = new DeleteTask(taskList.get(1));
		delete.execute();

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
