package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import action.Action;
import action.ActionFinishedException;
import action.actionType.ForeseeableAction;
import action.actionType.OneStepAction;
import action.scheduler.SequentialScheduler;

public class SequentialSchedulerTest {

	@Test(expected = ActionFinishedException.class)
	public void testNextAction() throws ActionFinishedException {
		SequentialScheduler sc = new SequentialScheduler();
		Action a = new OneStepAction();
		for(int i=0; i<10; i++){
			sc.add(a);
		}
		while(sc.doStep())
			sc.reallyDoStep();
		
		assertFalse(sc.doStep());
		sc.reallyDoStep();
	}

	@Test
	public void testIsReady() throws ActionFinishedException {
		SequentialScheduler sc = new SequentialScheduler();
		Action fA = new ForeseeableAction(2);
		assertFalse("Scheduler with null list can't be ready",sc.isReady());
		sc.add(fA);
		assertTrue("Scheduler with one foreseeableAction have to be ready",sc.isReady());
		sc.reallyDoStep();
		assertFalse("One step was done, scheduler can't be ready",sc.isReady());
		sc.reallyDoStep();
		assertTrue(sc.isFinished());
		assertFalse("The action is over, can't be ready",sc.isReady());
	}

	@Test
	public void testIsFinished() throws ActionFinishedException {
		SequentialScheduler sc = new SequentialScheduler();
		Action fA1 = new ForeseeableAction(2);
		Action fA2 = new ForeseeableAction(2);
		assertTrue("Empty scheduler is finished",sc.isFinished());
		sc.add(fA1);
		assertFalse("Scheduler with one action is not over",sc.isFinished());
		sc.add(fA2);
		assertFalse("Scheduler with two actions not started yet is not over",sc.isFinished());
		sc.reallyDoStep();
		assertFalse("Action 1/2 not over (one left)",sc.isFinished());
		sc.reallyDoStep();
		assertFalse("Action 1/2 over, Action 2/2 ready",sc.isFinished());
		sc.reallyDoStep();
		assertFalse("Action 1/2 over, Action 2/2 not over (one left)",sc.isFinished());
		sc.reallyDoStep();
		assertTrue("Actions are over",sc.isFinished());
	}
	
	@Test 
	public void testIsFinishedWithSchedulerInsideAScheduler() throws ActionFinishedException{
		SequentialScheduler sc_tmp= new SequentialScheduler();
		Action fA1 = new ForeseeableAction(2);
		Action fA2 = new ForeseeableAction(2);
		sc_tmp.add(fA1);
		sc_tmp.add(fA2);
		SequentialScheduler sc_with_sc = new SequentialScheduler(); 
		sc_with_sc.add(sc_tmp);
		for(int i=0; i<4; i++){
			assertFalse("Occurence "+i+" is not the final action",sc_with_sc.isFinished());
			sc_with_sc.reallyDoStep();
		}
		assertTrue("Scheduler finished",sc_with_sc.isFinished());	
	}

	@Test
	public void testInProgress() throws ActionFinishedException {
		SequentialScheduler sc= new SequentialScheduler();
		Action fA1 = new ForeseeableAction(2);
		Action fA2 = new ForeseeableAction(2);
		assertFalse("Empty list can't be in progress",sc.inProgress());
		sc.add(fA1);
		sc.add(fA2);
		assertFalse("Actions are not yet started",sc.inProgress());
		sc.reallyDoStep();
		for(int i=0; i<3; i++){
			assertTrue("Occurence "+i+" is the action in progress",sc.inProgress());
			sc.reallyDoStep();
		}
		assertFalse("Actions over, can't be in progress",sc.inProgress());
		
	}
	
	@Test
	public void testRemove() {
		Action a1 = new OneStepAction();
		Action a2 = new OneStepAction();
		Action a3 = new ForeseeableAction(2);
		LinkedList<Action> list = new LinkedList<Action>();
		list.add(a1);
		list.add(a2);
		list.add(a3);
		SequentialScheduler sc = new SequentialScheduler(list);
		assertTrue("There are 3 actions in the scheduler",sc.remove(a1));
		assertFalse("There are 2 actions in the scheduler",!sc.remove(a2));
		assertTrue("There are 1 action in the scheduler",sc.remove(a3));
		assertFalse("Action a3 is already removed",sc.remove(a3));
	}
	
}








