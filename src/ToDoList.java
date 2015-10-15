import java.io.IOException;
import java.util.*;

import command.CreateTask;

import controller.*;
import model.*;
import controller.Controller;
public class ToDoList {

	public static void main(String[] args) throws IOException {
		
		CreateTask create = new CreateTask();
		create.execute();
		
		
		
	}

}
