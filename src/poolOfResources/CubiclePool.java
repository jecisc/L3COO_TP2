package poolOfResources;

import poolOfResources.resources.Cubicle;

/**
 * Class of a Pool of Resource 'Cubicle'
 * 
 */
public class CubiclePool extends ResourcePool<Cubicle> {

	public CubiclePool(int nbOfResource) {
		super(nbOfResource);
	}

	public Cubicle createResource() {
		return new Cubicle();
	}

	public String toString() {
		return "pool cubicle";
	}

}
