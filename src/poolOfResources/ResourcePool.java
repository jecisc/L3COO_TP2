package poolOfResources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A ResourcePool is a administrator of resource which dispose of a certain
 * initial amount of resource.
 */
public abstract class ResourcePool<R extends Resource> {

	/**
	 * The list of the resources in use.
	 */
	protected List<R> resourcesInUse;

	/**
	 * The list of the available resources we're able to use.
	 */
	protected List<R> availableResource;

	/**
	 * Constructor of the ResourcePool. Create a list of
	 * <code>nbOfResource</code> resources.
	 * 
	 * @param nbOfResource
	 *            the number of resource on the Pool.
	 */
	public ResourcePool(int nbOfResource) {

		// First we create a new List of resource.
		this.availableResource = new ArrayList<R>();
		for (int indexResource = 0; indexResource < nbOfResource; indexResource++)
			this.availableResource.add(createResource()); // Create resource is
															// an abstract
															// method which must
															// be implement.

		// Then we create an empty list of resource in use.
		this.resourcesInUse = new ArrayList<R>();
	}

	/**
	 * If a resource is available, return the first available resource and add
	 * it to the resources in use.
	 * 
	 * @return the first resource available.
	 * @throws NoSuchElementException
	 *             if the list of the available resource is empty.
	 */
	public R provideResource() throws NoSuchElementException {

		// If a resource, at least, is available we provide the first resource
		// available.
		if (this.availableResource.size() > 0) {
			R res = this.availableResource.get(0);
			this.resourcesInUse.add(res);
			this.availableResource.remove(0);
			return res;
		}

		// Else we return an exception.
		throw new NoSuchElementException();
	}

	/**
	 * If the resource in parameter is in use on the pool, free the resource.
	 * Else return an IllegalArgumentException.
	 * 
	 * @param busyResource
	 *            the resource that have to be free.
	 * @return <code>true</code> if the function succeed to free the resource.
	 * @throws IllegalArgumentException
	 *             is the resource in parameter is not use on the pool or not busy.
	 */
	public boolean freeResource(R busyResource) throws IllegalArgumentException {
			if(this.resourcesInUse.isEmpty())
				throw new IllegalArgumentException();
			else{
				this.availableResource.add(busyResource);
				return this.resourcesInUse.remove(busyResource);
			}
	}

	/**
	 * Create a new resource of the type of the pool.
	 * 
	 * @return a new resource for the pool.
	 */
	public abstract R createResource();

	public String toString() {
		return "pool of resource";
	}

}
