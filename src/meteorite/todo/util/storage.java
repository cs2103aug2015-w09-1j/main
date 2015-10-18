package meteorite.todo.util;

import model.Task;
import model.DeadlineTask;
import model.EventTask;
import model.FloatingTask;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Storage {

	private String path="";
	private String fileName=null;
	private static Storage theOne=null;
	
	public Storage(){
		reset();
	}
	
	public void reset(){
		path="";
		fileName=null;
		return;
	}

	public void setPath(String path) {
		this.path = path;
		return;
	}

	public void setfileName(String fileName) {
		this.fileName = fileName;
		return;
	}
	
	public static Storage getInstance(){
		if(theOne==null)
			theOne= new Storage();
		return theOne;
	}
	
	public String getPath() {
		return path;
	}

	public String getfileName() {
		return fileName;
	}

	public void save(ArrayList<Task> f) {
		//assert fileName==null;;
		try {
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path+fileName)));
			encoder.writeObject(f);
			encoder.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Task> load(){
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path+fileName)));
			ArrayList<Task> o = (ArrayList<Task>) decoder.readObject();
			decoder.close();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String args[]) throws Exception {
		Storage test=Storage.getInstance();

		ArrayList<Task> listToSave = new ArrayList<Task>();

		FloatingTask A = new FloatingTask();
		A.setTaskName("name");
		listToSave.add(A);

		DeadlineTask B = new DeadlineTask();
		B.setDeadlineDate("ddl date");
		listToSave.add(B);

		EventTask C = new EventTask();
		C.setEndTime("end time");
		listToSave.add(C);

		test.reset();
		test.setPath("..\\");
		test.setfileName("test.fxml");
		test.save(listToSave);
		test.setPath("doncare\\");
		test.setfileName("test.fxml");
		test.save(listToSave);
		ArrayList<Task> loadedList = test.load();

		return;

	}
}
