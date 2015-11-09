//@@author ZhouYou(A0133976U)
package model;
import java.util.*;

/*
 * This class models a floating Task
 * It inherits from a task model and contains no extra information 
 */
public class FloatingTask extends Task{
	/***************Constructor********************/
	public FloatingTask(){
		super();
	}
	public FloatingTask(String task_name, String task_type){
		super(task_name, task_type);	
	}

}
