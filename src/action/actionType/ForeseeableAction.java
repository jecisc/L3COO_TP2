package action.actionType;

import action.Action;
/**
 * Action with 1 or more steps to be finished
 *
 */
public class ForeseeableAction extends Action{
	protected final int TIME;
	protected int remaining;
	protected String typeOfAction;
	
	/**
	 * Constructor of ForeseeableAction
	 * @param time
	 * 		The value of TIME and remaining
	 */
	public ForeseeableAction(int time){
		if(time<=0) throw new IllegalArgumentException();
		else{
			this.remaining=time;
			this.TIME=time;
			this.typeOfAction = "undifined action";
		}
	}	
	
	/**
	 * Constructor of ForeseeableAction
	 * @param time
	 * 		The value of TIME and remaining
	 * @param type
	 * 		The type of action that ForeseeableAction execute
	 */
	public ForeseeableAction(int time, String type){
		if(time<=0) throw new IllegalArgumentException();
		else{
			this.remaining=time;
			this.TIME=time;
			this.typeOfAction = type;
		}
	}

	public boolean isReady() {
		return remaining==TIME;
	}

	public boolean isFinished() {
		return remaining<=0;
	}

	public boolean inProgress() {
		return (remaining<TIME && remaining>0);
	}

	public void reallyDoStep() {
		remaining --;
		System.out.println(" " + this.typeOfAction+" ("+ (this.TIME - this.remaining) + "/" + this.TIME + ")");
	}
	
}
