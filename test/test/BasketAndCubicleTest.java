package test;

import static org.junit.Assert.*;

import org.junit.Test;

import poolOfResources.resources.Basket;
import poolOfResources.resources.Cubicle;

public class BasketAndCubicleTest {

	@Test
	public void testDescription() {
		Basket b = new Basket();
		Cubicle c = new Cubicle();
		String theBasketDescription = "A basket for put clothing inside.";
		String theCubicleDescription = "A cubicle for getting undressed/dressed";
		assertFalse("The descriptions of a cubicle and a basket are different",c.description()==b.description());
		assertTrue("Description of a Basket is 'A basket for put clothing inside.'",theBasketDescription==b.description());
		assertTrue("Description of a Cubicle is 'A cubicle for getting undressed/dressed'", theCubicleDescription==c.description());
	}

}
