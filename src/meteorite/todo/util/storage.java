package meteorite.todo.util;

import meteorite.todo.model.Task;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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

	public void save(Object f) {
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

	public Object load(){
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path+fileName)));
			Object o = decoder.readObject();
			decoder.close();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String args[]) throws Exception {
		Task a = new Task("xxxx", "yyyyy");
		Storage test=Storage.getInstance();
		
		test.reset();
		//storage.save(a);
		test.setPath("..\\");
		test.setfileName("test.fxml");
		test.save(a);
		test.setPath("doncare\\");
		test.setfileName("test.fxml");
		test.save(a);

		return;

	}
}
