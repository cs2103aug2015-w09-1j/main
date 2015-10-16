package command;

public abstract class SearchCommand implements ICommand {
	public abstract void execute();

	public abstract void undo();

	public boolean undoable() {
		// return false, because SearchCommand is not able to undo.
		return false;
	}
}
