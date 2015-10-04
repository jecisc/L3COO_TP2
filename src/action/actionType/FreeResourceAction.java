package action.actionType;

import poolOfResources.Resource;
import poolOfResources.ResourcePool;
import run.ResourcefulUser;
import action.Action;
import action.ActionFinishedException;
/**
 * FreeResourceAction is an Action which take a ResourcePool and will release
 * resources from that pool.
 *
 * */
public class FreeResourceAction<R extends Resource> extends Action {

	protected final ResourcePool<R> poolOfResource;
	
	protected final ResourcefulUser<R> user;
	
	protected Boolean ready = true;

	public FreeResourceAction(ResourcePool<R> pool, ResourcefulUser<R> user) {
		this.poolOfResource = pool;
		this.user = user;
	}

	public boolean isReady() {
		return this.ready;
	}

	public boolean isFinished() {
		if (this.user.getResource()==null)
			return true;
		
		return false;
	}

	public boolean inProgress() {
		if (this.user.getResource()==null || this.ready)
			return false;
		
		return true;
	}

	public void reallyDoStep() throws ActionFinishedException {
		if(this.isFinished())
			throw new ActionFinishedException();
		else{
			try{
				this.poolOfResource.freeResource(this.user.getResource());
				this.user.resetResource();
				this.ready = false;
				System.out.println(" freeing resource from "+ this.poolOfResource.toString());
				}
			catch(IllegalArgumentException e){
				this.ready = false;
				System.out.println("ERROR. Someone try to free something that doesn't exist or is already free.");
			}
		}	
	}

}
