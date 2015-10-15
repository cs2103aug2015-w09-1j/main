package command;

public abstract class RetrieveCommand implements ICommand {

	public abstract void execute();

	public abstract void undo();

	public boolean undoable() {
		// return false because retrieve is not able to undo.
		return false;
	}

}
