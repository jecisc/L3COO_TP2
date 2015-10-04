package action.actionType;

import action.Action;
/**
 * Action with 1 and only 1 step to be finished
 *
 */
public class OneStepAction extends Action {
	protected boolean end = false;

	public boolean isReady() {
		return !end;
	}

	public boolean isFinished() {
		return end;
	}

	public boolean inProgress() {
		return false;
	}

	public void reallyDoStep() {
		end = true;
	}

}
