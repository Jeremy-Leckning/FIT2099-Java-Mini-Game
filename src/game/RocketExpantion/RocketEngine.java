package game.RocketExpantion;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

/**
 * Rocket Engine Item
 */
class RocketEngine extends Item {

	/**
	 * Creates a Rocket Engine
	 */
    RocketEngine() {
		super("Rocket Engine", 'E');
		
		// Dropped by DoctorMaybe when defeated
	    this.allowableActions.clear();
	    this.allowableActions.add(new DropItemAction(this));
	}
	
		
}
