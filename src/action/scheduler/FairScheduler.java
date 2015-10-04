package action.scheduler;

import java.util.LinkedList;

import action.Action;
import action.ActionFinishedException;
import action.Scheduler;

/**
 * A FairScheduler is a scheduler which execute a step on the next --not finished -- action of the current action.
 */
public class FairScheduler extends Scheduler {

	public FairScheduler(LinkedList<Action> list){
		super(list);
	}

	public FairScheduler() {
		super();
	}

	protected Action currentAction = null;
	
	/**
	 * nextAction return the first action not finished after the current action on the Scheduler. 
	 * @return the first action not finished on the Scheduler.
	 * @exception ActionFinishedException if all the Actions are finished.
	 */
	public Action nextAction() throws ActionFinishedException {
		int ActionTest = 0;
		while(ActionTest != this.getAction().size()){
				nextActionPossible(); //Sub-function define just under this function.		
				if(! this.currentAction.isFinished())
					return currentAction;
				else
					ActionTest++;
			}

		throw new ActionFinishedException();
	}


	/**
	 * Same than nextAction but the select action can be finished.
	 * @exception ActionFinishedException if all the action are finished.
	 */
	private void nextActionPossible() throws ActionFinishedException {
		if(this.currentAction == null){
				this.currentAction = this.getAction().get(0);
		} 
		else {
			int nextAct = this.getAction().indexOf(this.currentAction)+1;
			if (nextAct == this.getAction().size())
				this.currentAction = this.getAction().get(0);
			else
				this.currentAction = this.getAction().get(nextAct); 
		}
	}
	
	/**
	 * Execute a step on the first action not finished after the current action on the Scheduler. 
	 * @exception ActionFinishedException if the action is already finished.
	 */
	public void reallyDoStep() throws ActionFinishedException {
		this.nextAction().reallyDoStep();
	}

}
