package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import poolOfResources.BasketPool;
import poolOfResources.resources.Basket;
import run.ResourcefulUser;
import action.ActionFinishedException;
import action.actionType.FreeResourceAction;

public class FreeResourceActionTest {

	@Test(expected = ActionFinishedException.class)
	public void testStates() throws ActionFinishedException {
		BasketPool baskets = new BasketPool(1);
		ResourcefulUser<Basket> user = new ResourcefulUser<Basket>();
		user.setResource(baskets.provideResource());
		FreeResourceAction<Basket> fAct = new FreeResourceAction<Basket>(baskets, user);
		assertTrue("The action is not ready ?!", fAct.isReady());
		assertFalse("The action is in Progress ?!", fAct.inProgress());
		assertFalse("The action is done ?!", fAct.isFinished());
		fAct.reallyDoStep();
		assertFalse("The action is ready ?!", fAct.isReady());
		assertFalse("The action is in Progress (2)?!", fAct.inProgress());
		assertTrue("The action is not done ?!", fAct.isFinished());
		fAct.reallyDoStep();		
	}

	@Test
	public void testWithResourceAlreadyFree() throws ActionFinishedException {
		BasketPool baskets = new BasketPool(1);
		ResourcefulUser<Basket> user = new ResourcefulUser<Basket>();
		Basket bas = new Basket();
		user.setResource(bas);
		FreeResourceAction<Basket> fAct = new FreeResourceAction<Basket>(baskets, user);
		assertFalse("The action is in Progress ?!", fAct.inProgress());
		fAct.reallyDoStep();
		assertFalse("The action is ready ?!", fAct.isReady());
		assertFalse("The action is done ?!", fAct.isFinished());
		assertTrue("The action is not in Progress ?!", fAct.inProgress());
	}
}
