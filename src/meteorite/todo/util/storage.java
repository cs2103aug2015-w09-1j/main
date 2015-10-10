package meteorite.todo.util;

import meteorite.todo.model.Task;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class storage {

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

	public static void write(Object f, String filename) throws Exception {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
		encoder.writeObject(f);
		encoder.close();
	}

	public static Object read(String filename) throws Exception {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		Object o = decoder.readObject();
		decoder.close();
		return o;
	}

	public static void main(String args[]) throws Exception {
		ArrayList<Task> listToSave = new ArrayList<Task>();
		Task a = new Task("xxxx", "yyyyy");

		storage.write(a, "foo.fxml");
		return;

	}
}