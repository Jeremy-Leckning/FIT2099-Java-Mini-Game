package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

public class RocketEngine extends Item {

	/**
	 * @param name
	 * @param displayChar
	 */
	public RocketEngine() {
		super("Engine", 'E');
		
		// Dropped by DoctorMaybe when defeated
	    this.allowableActions.clear();
	    this.allowableActions.add(new DropItemAction(this));
	}
	
		
}
