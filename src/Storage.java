import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {

	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Task> loadList(String path) {
		try {
			ObjectInputStream fileToLoad = new ObjectInputStream(new FileInputStream(path));
			ArrayList<Task> loadedList = (ArrayList<Task>) fileToLoad.readObject();
			return loadedList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("resource")
	public static void saveList(ArrayList<Task> listToSave, String path) {
		try {
			ObjectOutputStream fileToSave = new ObjectOutputStream(new FileOutputStream(path));
			fileToSave.writeObject(listToSave);
			
			fileToSave.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// For test
	public static void main(String args[]) {
		ArrayList<Task> listToSave = new ArrayList<Task>();

		FloatingTask A = new FloatingTask();
		A.setTaskName("task name");
		listToSave.add(A);

		DeadlineTask B = new DeadlineTask();
		B.setDeadlineDate("ddl date");
		listToSave.add(B);

		EventTask C = new EventTask();
		C.setEndTime("end time");
		listToSave.add(C);

		saveList(listToSave, "test.txt");
		ArrayList<Task> loadedList = loadList("test.txt");

		System.out.println(loadedList.get(0));
		System.out.println(loadedList.get(1));
		System.out.println(loadedList.get(2));

		return;

	}
}
