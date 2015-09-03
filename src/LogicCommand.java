import java.util.*;

public class LogicCommand {

	public LogicCommand(){
		
	}
	
	public Task addTask(String task_id, String task_name, String description, String date, String start_time, String end_time){
		
		if(task_name == null){
			return null; //fail to add task.
		}	else{
			if(description == null){
				description = "";
			}
			
			Task task = null;
			if(date != null && start_time != null && end_time != null){
				//event task
				task = new EventTask(task_id, task_name, description, date, start_time, end_time);
			}else{
				if(date == null && start_time==null && end_time == null){
					//floating task
					task = new FloatingTask(task_id, task_name, description);
				}	else{
					//deadline task
					task = new DeadlineTask(task_id, task_name, description, date, end_time);
				}
			}
			return task;
		}
	}
}
