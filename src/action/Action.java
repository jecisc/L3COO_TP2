package action;

/**
 * Class of Action
 * 
 */
public abstract class Action {

	/**
	 * Return true if the action is ready i.e. the action did not start yet Else
	 * return false
	 * 
	 * @return boolean
	 */
	public abstract boolean isReady();

	/**
	 * Return true if the action is finished i.e. the action can't do another
	 * step Else return false
	 * 
	 * @return boolean
	 */
	public abstract boolean isFinished();

	/**
	 * Return true if the action is in progress i.e. the action is already
	 * started but not finished Else return false
	 * 
	 * @return boolean
	 */
	public abstract boolean inProgress();

	/**
	 * This method do a step of the action
	 * 
	 * @throws ActionFinishedException
	 *             if the Action can't do a step.
	 */
	public abstract void reallyDoStep() throws ActionFinishedException;

	/**
	 * Return true if reallyDoStep can be use, else false
	 * 
	 * @return boolean
	 */
	public boolean doStep() {
		return (!isFinished());
	}
}
