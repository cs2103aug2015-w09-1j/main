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
			System.out.println(taskList.get(i).getTaskName());
		}

	}

}
