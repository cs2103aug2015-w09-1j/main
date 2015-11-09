package command;
/*
 * This is a abstract class implement the interface.
 * 
 * @@author Jason (A0127830J)
 */
public abstract class CreateCommand implements ICommand {
	public abstract void execute();

	public abstract void undo();

	public boolean undoable() {
		// return true, because CreateCommand is able to undo.
		return true;
	}
}
