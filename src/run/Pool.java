package run;

import poolOfResources.BasketPool;
import poolOfResources.CubiclePool;
import action.ActionFinishedException;
import action.scheduler.FairScheduler;

/**
 * This class represents the pool simulation (problem of pool)
 * 
 */
public class Pool {

	/**
	 * Let the simulation begin, print the details of the actions and print the
	 * number of steps made to finish the simulation
	 * 
	 * @param args
	 * @throws ActionFinishedException
	 */
	public static void main(String[] args) throws ActionFinishedException {
		BasketPool baskets = new BasketPool(6);
		CubiclePool cubicles = new CubiclePool(3);
		FairScheduler s = new FairScheduler();
		s.add(new Swimmer("Camille", baskets, cubicles, 6, 4, 8));
		s.add(new Swimmer("Lois", baskets, cubicles, 2, 10, 4));
		s.add(new Swimmer("Mae", baskets, cubicles, 10, 18, 10));
		s.add(new Swimmer("Ange", baskets, cubicles, 3, 7, 5));
		s.add(new Swimmer("Louison", baskets, cubicles, 18, 3, 3));
		s.add(new Swimmer("Charlie", baskets, cubicles, 3, 6, 10));
		s.add(new Swimmer("Alexis", baskets, cubicles, 6, 5, 7));
		int nbSteps = 0;
		while (!s.isFinished()) {
			nbSteps++;
			s.reallyDoStep();
		}
		System.out.println("Finished in " + nbSteps + " steps");
	}
}
