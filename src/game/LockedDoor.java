package game;

import edu.monash.fit2099.demo.WindowSmashAction;
import edu.monash.fit2099.engine.*;


public class LockedDoor extends Ground {

	public LockedDoor() {
		super('+');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		// If actor has a key
		for (Item item : actor.getInventory()){
			if (item instanceof Key){
				return new Actions(new UnlockDoorAction(location));
			}
		}
		// else
		return new Actions();

	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
