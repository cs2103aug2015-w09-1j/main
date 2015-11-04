package command;

public abstract class CreateBulkCommand implements ICommand {
	public abstract void execute();

	public abstract void undo();

	public boolean undoable() {
		// return true, because CreateBulkCommand is able to undo.
		return true;
	}
}