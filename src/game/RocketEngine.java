package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

/**
 * Rocket Engine Item
 */
public class RocketEngine extends Item {

	/**
	 * Creates a Rocket Engine
	 */
	public RocketEngine() {
		super("Rocket Engine", 'E');
		
		// Dropped by DoctorMaybe when defeated
	    this.allowableActions.clear();
	    this.allowableActions.add(new DropItemAction(this));
	}
	
		
}
