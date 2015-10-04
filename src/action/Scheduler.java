package action;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 *	Scheduler is a kind of action that contains a set of actions and make these actions progress.
 */
public abstract class Scheduler extends Action {
	
	protected LinkedList<Action> listAct;
	
	/**
	 * Constructor of a Scheduler. This constructor create a new list of action null by default. 
	 */
	public Scheduler(){
		this.listAct = new LinkedList<Action>();		
	}
	
	/**
	 *  Constructor of a Scheduler. This constructor create a list of action based on a list in parameter.
	 *  @param a list of action.
	 */
	public Scheduler(LinkedList<Action> list){
		this.listAct = list;
	}
	
	/**
	 * Allow to add an action to the Scheduler.
	 * 
	 * @param a the action we need to add to our Scheduler.
	 */
	public boolean add(Action a){
		return this.listAct.add(a);
	}
		
	/**
	 * isReady allow to know if the Scheduler is in his initial state. 
	 * @return true if and only if all the actions of the scheduler are in an initial state. Else return false.
	 */
	public boolean isReady(){
		if(this.listAct.isEmpty())
			return false; 
		Iterator<Action> it = this.listAct.iterator();
		while(it.hasNext()){
			if (! it.next().isReady())
				return false;
		}
		return true;
	}
	
	/**
	 * isInProgress allow to know if the Scheduler is doing the actions. 
	 * @return true if and only if all the action are not on the initial or final state. Else return false.
	 */
	public boolean inProgress(){
		boolean SchBegin = false;
		Iterator<Action> it = this.listAct.iterator();
		while(it.hasNext()){
			Action currentAct = it.next();
			if (currentAct.inProgress())
				return true;
			
			if(SchBegin && currentAct.isReady())
				return true;
			
			if(!SchBegin && currentAct.isFinished())
				SchBegin = true;				
		}
		return false;
	}
	
	/**
	 * isFinished allow to know if the Scheduler is in his final state.
	 * @return true if and only if all the actions on the Scheduler are finished.
	 */
	public boolean isFinished(){
		Iterator<Action> it = this.listAct.iterator();
		while(it.hasNext()){
			if (!it.next().isFinished())
				return false;				
		}
		return true;
	}
	
	/**
	 * doStep allow to know if our Scheduler is able to do a step. 
	 * @return true if and only if we can do a step with our Scheduler. Else return false. 
	 */
	public boolean doStep(){
		if(!this.isFinished())
			return true;
		
		return false;
	}
	
	/**
	 * nextAction allow to know the next action we'll use on our Scheduler.
	 * @return the next action.
	 * @exception ActionFinishedException if and only if the all the Action of the Scheduler are finished.
	 */
	public abstract Action nextAction() throws ActionFinishedException;
	
	/**
	 * remove allow to remove an action from the scheduler if this action is on the scheduler. 
	 * @param a the action we want to remove from the Scheduler.
	 * @return true if and only if the action is removed from the Scheduler. False if the action doesn't exist on the Scheduler.
	 */
	public boolean remove(Action a){
		return this.listAct.remove(a);
	}
	
	/**
	 * getAction allow to have a list of all the action of the action on the Scheduler. 
	 * @return a LinkedList of all the action on the Scheduler.
	 */
	public List<Action> getAction(){
		return this.listAct;
	}
	
	/**
	 * reallyDoStep execute one action.
	 * @throws ActionFinishedException if the action is finished.
	 */
	public abstract void reallyDoStep() throws ActionFinishedException;
	

}
