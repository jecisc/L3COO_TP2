package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import action.Action;
import action.ActionFinishedException;
import action.Scheduler;
import action.actionType.ForeseeableAction;
import action.scheduler.FairScheduler;

public class FairShedulerTest {

	@Test(expected = ActionFinishedException.class)
	public void testExecutionOfAFairScheduler() throws ActionFinishedException{
		Action fAct1 = new ForeseeableAction(1);
		Action fAct2 = new ForeseeableAction(2);
		LinkedList<Action> list = new LinkedList<Action>();
		list.add(fAct2);
		list.add(fAct1);
		Scheduler sch = new FairScheduler(list);

		assertFalse("Scheduler finished without step ?", sch.isFinished());
		assertFalse("First Action finished without step ?", fAct2.isFinished());
		assertFalse("Second Action finished without step ?", fAct1.isFinished());

		sch.reallyDoStep();
		
		assertFalse("Scheduler finished with one step ?", sch.isFinished());
		assertFalse("First Action finished with one step?", fAct2.isFinished());
		assertFalse("Second Action finished with one step ?", fAct1.isFinished());

		sch.reallyDoStep();
		
		assertFalse("Scheduler finished with two step ?", sch.isFinished());
		assertFalse("First Action finished with two step?", fAct2.isFinished());
		assertTrue("Second Action not finished with two step ?", fAct1.isFinished());

		sch.reallyDoStep();
		
		assertTrue("Scheduler not finished with three step ?", sch.isFinished());
		assertTrue("First Action not finished with three step?", fAct2.isFinished());
		
		sch.reallyDoStep();
	}
	
	@Test(expected = ActionFinishedException.class)
	public void testCaseOfANullScheduler() throws ActionFinishedException{
		Scheduler sch = new FairScheduler();
		sch.reallyDoStep();
	}
	
	@Test(expected = ActionFinishedException.class)
	public void testASchedulerInsideAScheduler() throws ActionFinishedException{
		Scheduler daddySch = new FairScheduler();
		Scheduler son1Sch = new FairScheduler();
		Scheduler son2Sch = new FairScheduler();
		Action fAct1 = new ForeseeableAction(1);
		Action fAct2 = new ForeseeableAction(1);
		son1Sch.add(fAct2);
		son2Sch.add(fAct1);
		daddySch.add(son1Sch);
		daddySch.add(son2Sch);

		assertFalse("Daddy Scheduler finished without step ?", daddySch.isFinished());
		assertFalse("First Son Scheduler finished without step ?", son1Sch.isFinished());
		assertFalse("Second SonScheduler finished without step ?", son2Sch.isFinished());

		daddySch.reallyDoStep();
		
		assertFalse("Scheduler finished with one step ?", daddySch.isFinished());
		assertTrue("First son Scheduler not finished with one step?", son1Sch.isFinished());
		assertFalse("Second son Scheduler finished with one step ?", son2Sch.isFinished());

		daddySch.reallyDoStep();
		
		assertTrue("Scheduler not finished with two step ?", daddySch.isFinished());
		assertTrue("Second Action not finished with two step ?", son2Sch.isFinished());
		
		daddySch.reallyDoStep();
	}
}
