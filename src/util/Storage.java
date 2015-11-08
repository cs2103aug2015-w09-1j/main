package util;

import model.Task;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Storage {
	/**
	 * This is a class to deal with all operations relative to local date
	 *
	 * @author Liang Yuan
	 */
	private String path = "";
	private String fileName = "SilentJarvis.fxml";
	private static Storage theOne = null;
	private String tempStore;

	private Storage() {
		reset();
	}

	public void reset() {
		path = "";
		fileName = "SilentJarvis.fxml";
		return;
	}

	public boolean setPath(String path) {
		Boolean isSet = false;
		tempStore = this.path;
		this.path = path.trim().concat("\\");
		if (existFolder()) {
			isSet = true;
		} else {
			this.path = tempStore;
		}
		return isSet;
	}

	public boolean setfileName(String fileName) {
		Boolean isSet = false;
		tempStore = this.fileName;
		this.fileName = fileName.trim().split("\\.")[0].concat(".fxml");
		if (existFile()) {
			isSet = true;
		} else {
			this.fileName = tempStore;
		}
		return isSet;
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
		Boolean isSet = false;
		File pathToCheck = new File(path);
		if (path.compareTo("") == 0 || (pathToCheck.exists() && pathToCheck.isDirectory())) {
			isSet = true;
		} else {
			isSet = pathToCheck.mkdirs();
		}
		return isSet;
	}

	public boolean existFile() {
		Boolean isSet = false;
		if (existFolder()) {
			File fileToCheck = new File(path + fileName);
			if (fileToCheck.exists() && !fileToCheck.isDirectory()) {
				isSet = true;
			} else {
				try {
					save(new ArrayList<Task>());
					isSet = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return isSet;
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
		ArrayList<Task> listToload = null;
		try {
			if (existFile()) {
				XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path + fileName)));
				listToload = (ArrayList<Task>) decoder.readObject();
				decoder.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listToload;
	}
}