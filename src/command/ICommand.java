package command;

/*
 * This is the Interface class with execute, undo and undoable method.
 * Uundoable is to check that is this command is able to be undo.
 * 
 * @@author A0111947E
 */
public interface ICommand {
	public void execute();

	public void undo();

	public boolean undoable();
}
