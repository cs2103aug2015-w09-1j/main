package command;

public abstract class DeleteBulkCommand implements ICommand {
	public abstract void execute();

	public abstract void undo();

	public boolean undoable() {
		// return true, because DeleteBulkCommand is able to undo.
		return true;
	}
}