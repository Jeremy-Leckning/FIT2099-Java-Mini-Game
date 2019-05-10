package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

/**
 * Rocket Body part
 * Q will give this to the player in return for the rocket plans
 */
public class RocketBody extends Item {

	/**
	 * Creates a Rocket Body
	 */
	public RocketBody() {
		super("RocketBody", 'B');

		// rocket body only ever created when placed inside players inventory: only drop item action
		this.allowableActions.clear();
		this.allowableActions.add(new DropItemAction(this));

	}
	
}
