package meteorite.todo.util;

import meteorite.todo.model.Task;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class storage {

<<<<<<< HEAD
	private static String path="";
	private static String fileName=null;

	public static void setPath(String path) {
		storage.path = path;
		return;
	}

	public static void setfileName(String fileName) {
		storage.fileName = fileName;
		return;
	}

	public static String getPath() {
		return path;
	}

	public static String getfileName() {
		return fileName;
	}

	public static void save(Object f) {
		try {
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path+fileName)));
			encoder.writeObject(f);
			encoder.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public static Object load() throws Exception {
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path+fileName)));
			Object o = decoder.readObject();
			decoder.close();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

=======
	/*
	 * @SuppressWarnings({ "unchecked", "resource" }) public static
	 * ArrayList<Task> loadList(String path) { try { ObjectInputStream
	 * fileToLoad = new ObjectInputStream(new FileInputStream(path));
	 * ArrayList<Task> loadedList = (ArrayList<Task>) fileToLoad.readObject();
	 * return loadedList; } catch (Exception e) { e.printStackTrace(); } return
	 * null; }
	 * 
	 * 
	 * @SuppressWarnings("resource") public static void saveList(ArrayList<Task>
	 * listToSave, String path) { try { ObjectOutputStream fileToSave = new
	 * ObjectOutputStream(new FileOutputStream(path));
	 * fileToSave.writeObject(listToSave); fileToSave.flush();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	// For test

	public static void save(Object f, String filename) throws Exception {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
		encoder.writeObject(f);
		encoder.close();
	}

	public static Object load(String filename) throws Exception {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		Object o = decoder.readObject();
		decoder.close();
		return o;
>>>>>>> origin/storage
	}

	public static void main(String args[]) throws Exception {
		Task a = new Task("xxxx", "yyyyy");

<<<<<<< HEAD
		storage.setPath("..\\");
		storage.setfileName("test.fxml");
		storage.save(a);
		storage.setPath("doncare\\");
		storage.setfileName("test.fxml");
		storage.save(a);
=======
		storage.save(a, "foo.fxml");
>>>>>>> origin/storage
		return;

	}
}
