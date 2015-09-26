package model;
import controller.*;
public class Create implements ICommand {
	
	public Create(){
		
	}
	public void execute(){
		System.out.println("create");
		//find type
		
	}
	public void undo(){
		System.out.println("undo");
	}
}
