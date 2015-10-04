package run;

import poolOfResources.Resource;

/**
 * User who will use or free resources.
 */
public class ResourcefulUser<R extends Resource> {

	protected R resource;
	
	public R getResource(){
		return resource;
	}
	
	public void setResource(R resource){
		this.resource = resource;
	}

	public void resetResource(){
		this.resource = null;
	}
}
