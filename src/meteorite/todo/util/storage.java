package meteorite.todo.util;

import meteorite.todo.model.Task;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class storage {

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

	}

	public static void main(String args[]) throws Exception {
		Task a = new Task("xxxx", "yyyyy");

		storage.setPath("..\\");
		storage.setfileName("test.fxml");
		storage.save(a);
		storage.setPath("doncare\\");
		storage.setfileName("test.fxml");
		storage.save(a);
		return;

	}
}