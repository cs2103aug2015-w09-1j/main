import java.io.IOException;
import java.util.*;

import controller.*;
import model.*;
import controller.Controller;
public class ToDoList {

	public static void main(String[] args) {
		
		try {
			Controller.inputCommand();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
