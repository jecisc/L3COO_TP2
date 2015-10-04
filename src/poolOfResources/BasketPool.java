package poolOfResources;

import poolOfResources.resources.Basket;

/**
 * Class of a Pool of Resource 'Basket'
 * 
 */
public class BasketPool extends ResourcePool<Basket>{

	public BasketPool(int nbOfResource) {
		super(nbOfResource);
	}

	public Basket createResource() {
		return new Basket();
	}

	public String toString() {
		return "pool basket";
	}

}
