package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import action.actionType.ForeseeableAction;

public class ForseeableActionTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void testWrongTimeParameterOnConstructor(){
		@SuppressWarnings("unused")
		ForeseeableAction fAct = new ForeseeableAction(-1);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWrongTimeParameterOnConstructorForSecondConstructor(){
		@SuppressWarnings("unused")
		ForeseeableAction fAct = new ForeseeableAction(-1, "testing");		
	}
	
	@Test
	public void testIsReady() {
		ForeseeableAction fAct = new ForeseeableAction(2);
		assertTrue("The action is not ready ?!", fAct.isReady());
		fAct.reallyDoStep();
		assertFalse("The action is ready ?!", fAct.isReady());
	}

	@Test
	public void testIsFinished() {
		ForeseeableAction fAct = new ForeseeableAction(2);
		assertFalse("The action is Finished ?!", fAct.isFinished());
		fAct.reallyDoStep();
		assertFalse("The action is Finished (2) ?!", fAct.isFinished());
		fAct.reallyDoStep();
		assertTrue("The action is not Finished ?!", fAct.isFinished());
	}

	@Test
	public void testInProgress() {
		ForeseeableAction fAct = new ForeseeableAction(2);
		assertFalse("The action is in Progress ?!", fAct.inProgress());
		fAct.reallyDoStep();
		assertTrue("The action is not in Progress (2) ?!", fAct.inProgress());
		fAct.reallyDoStep();
		assertFalse("The action is in Progress ?!", fAct.inProgress());
	}

	@Test
	public void testForeseeableAction() {
		ForeseeableAction fAct = new ForeseeableAction(2, "dress");
		assertTrue("We can't do a step ?!", fAct.doStep());
		fAct.reallyDoStep();
		assertTrue("We can't do a step (2) ?!", fAct.doStep());
		fAct.reallyDoStep();
		assertFalse("We can do a step ?!", fAct.doStep());
	}

}
