package game;

import edu.monash.fit2099.engine.*;

/**
 * Action to fly using rocket from map to map
 */
public class FlyAction extends Action {
	private Actor actor;
	private DisplayableMap departureMap;
	private DisplayableMap destinationMap;

	/**
	 * Creates a fly action
	 * @param actor Actor who will fly
	 * @param map Current map
	 * @param destination map to fly too
	 * Precondition: Actor must be player, maps must be different
	 * @throws IllegalArgumentException if actor is not player or if origin = destination
	 *
	 */
	public FlyAction(Actor actor,DisplayableMap map, DisplayableMap destination) throws IllegalArgumentException{
		// Precondition: Actor must be player
		if(!(actor instanceof Player)){
			throw new IllegalArgumentException("Flyer must be a player");
		}
		// Precondition: maps must be different
		if ( map == destination){
			throw new IllegalArgumentException("Origin and destination must be different");
		}

		this.actor = actor;
		this.departureMap = map;
		this.destinationMap = destination;
	}

	/**
	 * Executes the flying action
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return description of flight
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// Remove player from current map
		this.departureMap.removeActor(actor);

		// Get coordinates of pad on destination map
		int x = this.destinationMap.getPadCoord()[0];
		int y = this.destinationMap.getPadCoord()[1];

		// Add player to those coordinates
		this.destinationMap.addActor(actor,x,y);

		return this.actor + " flies to " + this.destinationMap;
	}
	
    @Override
	public String menuDescription(Actor actor) {
		return this.actor + " uses rocket to fly to " + this.destinationMap;
	}

    @Override
    public String hotKey() {
        return "";
    }

}
