import java.util.*;

public class Logic {

	public Logic(){
		
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
	
	public ArrayList<Task> deleteTask(ArrayList<Task> listOfTask, String index){
		if(!listOfTask.isEmpty()){
			for(int i = 0; i < listOfTask.size(); i++){
				if(listOfTask.get(i).getTaskId().equals(index)){
					listOfTask.remove(i);
				}
			}
		}
		return listOfTask; 
	}
	
	public String displayTask(ArrayList<Task> listOfTask){
		String output = "";
		if(!listOfTask.isEmpty()){
			for(int i = 0; i < listOfTask.size(); i++){
				output += listOfTask.get(i).getTaskId() + ". "
						+ listOfTask.get(i).getTaskName() + "\r\n";
				if(listOfTask.get(i).getDescription() != ""){
					output += "Description: "+listOfTask.get(i).getDescription()+"\r\n"; 
				}			
				if(listOfTask.get(i) instanceof EventTask){
					EventTask et = (EventTask) listOfTask.get(i);
					output += "Date/Time: "+et.getDate()+", "+et.getStartTime()+" - "+et.getEndTime()+"\r\n";
				}else if(listOfTask.get(i) instanceof DeadlineTask){
					DeadlineTask dt = (DeadlineTask) listOfTask.get(i);
					output += "Deadline: "+dt.getDeadlineDate()+", "+dt.getDeadlineTime()+"\r\n";
				}else if(listOfTask.get(i) instanceof FloatingTask){
					
				}
				output += "\r\n";
			}
		}else{
			output = "Task list is empty.";
		}
		return output;
	}
}
