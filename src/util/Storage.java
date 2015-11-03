package util;

import model.Task;
import model.DeadlineTask;
import model.EventTask;
import model.FloatingTask;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Storage {

	private String path = "";
	private String fileName = "SilentJarvis.fxml";
	private static Storage theOne = null;
	private String tempStore;

	public Storage() {
		reset();
	}

	public void reset() {
		path = "";
		fileName = "SilentJarvis.fxml";
		return;
	}

	public boolean setPath(String path) {
		tempStore=this.path;
		this.path = path.trim();
		if(existFolder()){
			return true;
		}
		this.path=tempStore;
		return false;
	}

	public boolean setfileName(String fileName) {
		tempStore=this.fileName;
		this.fileName = fileName.trim().split("\\.")[0].concat(".fxml");
		if(existFile()){
			return true;
		}
		this.fileName=tempStore;
		return false;
	}

	public static Storage getInstance() {
		if (theOne == null)
			theOne = new Storage();
		return theOne;
	}

	public String getPath() {
		return path;
	}

	public String getfileName() {
		return fileName;
	}

	public boolean existFolder() {
		File pathToCheck = new File(path);
		if (path.compareTo("")==0||(pathToCheck.exists() && pathToCheck.isDirectory())) {
			return true;
		}
		boolean success = pathToCheck.mkdirs();
		return success;
	}

	public boolean existFile() {
		if (existFolder()) {
			File fileToCheck = new File(path + fileName);
			if (fileToCheck.exists() && !fileToCheck.isDirectory()) {
				return true;
			}

			try {
				save(new ArrayList<Task>());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void save(ArrayList<Task> listToSave) {
		try {
			if (existFolder()) {
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path + fileName)));
			encoder.writeObject(listToSave);
			encoder.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Task> load() {
		try {
			if (existFile()) {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path + fileName)));
			ArrayList<Task> o = (ArrayList<Task>) decoder.readObject();
			decoder.close();
			return o;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}