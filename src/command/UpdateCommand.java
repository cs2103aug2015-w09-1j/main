package command;

/*
 * @@author A0111947E
 */

public abstract class UpdateCommand implements ICommand {
	public abstract void execute();

	public abstract void undo();

	public boolean undoable() {
		// return true, because CreateCommand is able to undo.
		return true;
	}
}
