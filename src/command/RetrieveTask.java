package command;

public class RetrieveTask extends RetrieveCommand {

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void undo() {
		// false
		if (this.undoable()) {

		}

	}

}
