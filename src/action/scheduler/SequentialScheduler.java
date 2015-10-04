package action.scheduler;

import java.util.Iterator;
import java.util.LinkedList;

import action.Action;
import action.Scheduler;
import action.ActionFinishedException;

/**
 * A SequentialScheduler is a Scheduler which execute a step of the first action non finished. 
 */
public class SequentialScheduler extends Scheduler {

	public SequentialScheduler(LinkedList<Action> list){
		super(list);
	}
	
	public SequentialScheduler() {
		super();
	}

	/**
	 * nextAction return the first action not finished on the Scheduler. 
	 * @return the first action not finished on the Scheduler.
	 * @exception ActionFinishedException if all the Actions are finished.
	 */
	public Action nextAction() throws ActionFinishedException{
		Iterator<Action> it = this.getAction().iterator();
		while(it.hasNext()){
			Action a = it.next();
			if(!a.isFinished())
				return a;
		}
		throw new ActionFinishedException();
	}

	/**
	 * Execute a step on the first action not finished on the Scheduler.
	 * @exception ActionFinishedException if the action is already finished.
	 */
	public void reallyDoStep() throws ActionFinishedException {
		this.nextAction().reallyDoStep();
	}
}
