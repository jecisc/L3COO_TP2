package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import poolOfResources.BasketPool;
import poolOfResources.resources.Basket;
import run.ResourcefulUser;
import action.ActionFinishedException;
import action.actionType.TakeResourceAction;

public class TakeResourceActionTest {

	@Test(expected = ActionFinishedException.class)
	public void testStates() throws ActionFinishedException {
		BasketPool baskets = new BasketPool(1);
		ResourcefulUser<Basket> user = new ResourcefulUser<Basket>();
		ResourcefulUser<Basket> user2 = new ResourcefulUser<Basket>();
		TakeResourceAction<Basket> tAct = new TakeResourceAction<Basket>(baskets, user);
		TakeResourceAction<Basket> tAct2 = new TakeResourceAction<Basket>(baskets, user2);
		tAct2.reallyDoStep();
		assertTrue("The action is not ready ?!", tAct.isReady());
		assertFalse("The action is in Progress ?!", tAct.inProgress());
		assertFalse("The action is done ?!", tAct.isFinished());
		assertFalse("The action is ready ?!", tAct2.isReady());
		assertFalse("The action is in Progress (2)?!", tAct2.inProgress());
		assertTrue("The action is not done ?!", tAct2.isFinished());
		tAct.reallyDoStep();
		assertTrue("The action is not in Progress ?!", tAct.inProgress());
		tAct2.reallyDoStep();
	}
	
}
