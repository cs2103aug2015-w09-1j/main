import java.util.*;

public class Command {

	public Command(){
		
	}
	
	public Task addTask(String task_name, String description, String date, String start_time, String end_time){
		
		if(task_name == null){
			return null; //fail to add task.
		}	else{
			if(description == null){
				description = "";
			}
			Task task = null;
			if(date != null && start_time != null && end_time != null){
				task = new Event(task_name, description, date, start_time, end_time);
			}
			
			if(date == null){
				//floating task
			}	else{
				//deadline task
			}
			
			return task;
		}
	}
}
