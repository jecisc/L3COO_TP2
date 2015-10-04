package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import poolOfResources.BasketPool;
import poolOfResources.CubiclePool;
import run.Swimmer;
import action.ActionFinishedException;


public class SwimmerTest {

	protected Swimmer alphonse;

	@Before
	public void initObjects() {
		BasketPool bPool = new BasketPool(2);
		CubiclePool cPool = new CubiclePool(1);
		alphonse = new Swimmer("Alphonse", bPool, cPool, 1, 1, 10);
	}
	
	@Test
	public void testIsReady() throws ActionFinishedException {
		assertTrue("Swimmer not ready ?", alphonse.isReady());
		alphonse.reallyDoStep();
		assertFalse("Swimmer ready ?", alphonse.isReady());
		
	}

	@Test(expected = ActionFinishedException.class)
	public void testIsFinished() throws ActionFinishedException {
		while (alphonse.doStep())
			alphonse.reallyDoStep();

		assertTrue("Alphonse didn't finish all its actions", alphonse.isFinished());
		alphonse.reallyDoStep();
	}

	@Test
	public void testInProgress() throws ActionFinishedException {
		assertFalse("Alphonse began its actions ?",alphonse.inProgress());
		alphonse.reallyDoStep();
		assertTrue("Alphonse began its actions but has not yet completed",alphonse.inProgress());
	}
}
