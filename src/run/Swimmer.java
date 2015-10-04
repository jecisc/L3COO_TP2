package run;

import poolOfResources.BasketPool;
import poolOfResources.CubiclePool;
import poolOfResources.resources.Basket;
import poolOfResources.resources.Cubicle;
import action.Action;
import action.ActionFinishedException;
import action.actionType.ForeseeableAction;
import action.actionType.FreeResourceAction;
import action.actionType.TakeResourceAction;
import action.scheduler.SequentialScheduler;

/**
 * This class represents a Swimmer
 */
public class Swimmer extends Action{
	protected String name;
	protected BasketPool bPool;
	protected CubiclePool cPool;
	protected SequentialScheduler schAction;
	
	/**
	 * Constructor of a Swimmer
	 * @param name his name
	 * @param bPool his BasketPool where he can takes a Basket.
	 * @param cPool his CubiclePool where he can takes a Cubicle.
	 * @param undressed represent the time that the swimmer need to get undress.
	 * @param swim represent the time that the swimmer will be on the pool.
	 * @param dressed represent the time that the swimmer need to get dress.
	 */
	public Swimmer(String name, BasketPool bPool, CubiclePool cPool, int undressed, int swim, int dressed){
		this.name=name;
		this.bPool=bPool;
		this.cPool=cPool;
		this.schAction = new SequentialScheduler();
		schAction.add(new TakeResourceAction<Basket>(bPool, new ResourcefulUser<Basket>())); // this action is the action to take a basket.
		schAction.add(new TakeResourceAction<Cubicle>( cPool, new ResourcefulUser<Cubicle>())); // this action is the action to take a cubicle to undress.
		schAction.add(new ForeseeableAction(undressed, "undressing"));
		ResourcefulUser<Cubicle> userCubicle = new ResourcefulUser<Cubicle>(); //creation of a new user who use a Cubicle.
		userCubicle.setResource(new Cubicle());
		schAction.add(new FreeResourceAction<Cubicle>(cPool, userCubicle)); // this action is the action to free a cubicle.
		schAction.add(new ForeseeableAction(swim, "swimming"));
		schAction.add(new TakeResourceAction<Cubicle>( cPool, new ResourcefulUser<Cubicle>())); // this action is the action to take a cubicle to dress.
		schAction.add(new ForeseeableAction(dressed, "dressing"));
		ResourcefulUser<Cubicle> userCubicle2 = new ResourcefulUser<Cubicle>(); //creation of a new user who use a Cubicle.
		userCubicle2.setResource(new Cubicle());
		schAction.add(new FreeResourceAction<Cubicle>(cPool, userCubicle2)); // this action is the action to free a cubicle.
		ResourcefulUser<Basket> userBasket = new ResourcefulUser<Basket>(); //creation of a new user who use a Basket.
		userBasket.setResource(new Basket());
		schAction.add(new FreeResourceAction<Basket>(bPool,userBasket)); // this action is the action to free a basket.
	}

	public boolean isReady() {
		return this.schAction.isReady();
	}

	public boolean isFinished() {
		return this.schAction.isFinished();
	}

	public boolean inProgress() {
		return this.schAction.inProgress();
	}

	public void reallyDoStep() throws ActionFinishedException {
		System.out.println(this.name + "'s turn");
		System.out.print("	"+this.name);
		this.schAction.reallyDoStep();		
	}
	
	
}
