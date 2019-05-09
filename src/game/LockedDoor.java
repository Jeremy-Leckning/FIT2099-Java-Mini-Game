package game;

import edu.monash.fit2099.demo.WindowSmashAction;
import edu.monash.fit2099.engine.*;

/**
 * Door that can't be entered without being unlocked with key first
 */
public class LockedDoor extends Ground {

	/**
	 * Creates a Locked Door.
	 */
	public LockedDoor() {
		super('+');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Get actions players can perform on locked door
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return allowable actions
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){

		// If actor has a key -> return an UnlockDoorAction
		for (Item item : actor.getInventory()){
			if (item instanceof Key){
				return new Actions(new UnlockDoorAction(location));
			}
		}
		// else return empty Action
		return new Actions();

	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
