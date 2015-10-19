package command;

public class RetrieveTask extends RetrieveCommand {

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void undo() {
		// false, unable to do a undo for retrieve
		if (this.undoable()) {

		}

	}

}
