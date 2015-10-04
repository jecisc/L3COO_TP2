/**
 * 
 */
package action.actionType;

import java.util.NoSuchElementException;

import poolOfResources.Resource;
import poolOfResources.ResourcePool;
import run.ResourcefulUser;
import action.Action;
import action.ActionFinishedException;

/**
 * TakeResourceAction is an Action which take a ResourcePool and will take
 * resources from that pool. There's a User passed in parameter who'll take the
 * resources.
 */
public class TakeResourceAction<R extends Resource> extends Action {

	protected final ResourcePool<R> poolOfResource;

	protected final ResourcefulUser<R> user;
	
	protected Boolean ready = true;

	public TakeResourceAction(ResourcePool<R> pool, ResourcefulUser<R> user) {
		this.poolOfResource = pool;
		this.user = user;
	}

	public boolean isReady() {
		return this.ready;
	}

	public boolean isFinished() {
		if (this.user.getResource() == null)
			return false;

		return true;
	}

	public boolean inProgress() {
		if (this.user.getResource() == null && !this.ready)
			return true;

		return false;
	}

	public void reallyDoStep() throws ActionFinishedException {
		if(this.isFinished())
			throw new ActionFinishedException();
		
		try {
			this.user.setResource(this.poolOfResource.provideResource());
			this.ready = false;
			System.out.println(" trying to take resource from "
					+ this.poolOfResource.toString() + "... success");
		} catch (NoSuchElementException e) {
			this.ready = false;
			System.out.println(" trying to take resource from "
					+ this.poolOfResource.toString() + "... failed");
		}
	}

}
