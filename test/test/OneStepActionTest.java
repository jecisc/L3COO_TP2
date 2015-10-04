package test;

import static org.junit.Assert.*;

import org.junit.Test;

import action.Action;
import action.Scheduler;
import action.actionType.OneStepAction;
import action.scheduler.SequentialScheduler;

public class OneStepActionTest {

	@Test
	public void with1OneSteAction() throws Exception{
		Action action1 = new OneStepAction();
		Scheduler sch = new SequentialScheduler();
		assertTrue("Add don't work ?", sch.add(action1));
		assertTrue("Action is not Ready ?", action1.isReady());
		
		assertFalse("Scheduler finished ?", sch.isFinished());
		assertFalse("Action finished ?", action1.isFinished());
		
		sch.reallyDoStep();
		
		assertTrue("Scheduler not finished ?", sch.isFinished());
		assertTrue("Action not finished ?", action1.isFinished());
		

		assertFalse("Action in Progress ?", action1.inProgress());
		assertFalse("Action is Ready ?", action1.isReady());
	}

}

