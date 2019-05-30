package game.RocketExpantion;

import edu.monash.fit2099.engine.*;
import game.IPlanetaryMap;
import game.GameSkills;
import game.EndGameAction;

/**
 * Action to fly using rocket from map to map
 */
public class FlyAction extends Action {
	private Actor actor;
	private IPlanetaryMap destinationMap;


	/**
	 * Creates a fly action
	 * @param actor Actor who will fly
	 * @param destination map to fly too
	 * Precondition: Actor must be player, maps must be different
	 * @throws IllegalArgumentException if actor is not player
	 *
	 */
	public FlyAction(Actor actor, IPlanetaryMap destination) throws IllegalArgumentException{
		// Precondition: Actor must be player
		if(!(actor instanceof Player)){
			throw new IllegalArgumentException("Flyer must be a player");
		}

		this.actor = actor;
		this.destinationMap = destination;
	}

	/**
	 * Executes the flying action
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return description of flight
	 * Precondition: Destination map must be different to current map
	 * @throws IllegalArgumentException If dest map == origin map
	 */
	@Override
	public String execute(Actor actor, GameMap map) throws IllegalArgumentException {
		// Precondition: origin != destination
		if (this.destinationMap == map){
			throw new IllegalArgumentException("Cant fly to the same map...");
		}

		// Remove player from current map
		map.removeActor(actor);

		// If player has won -> must be flying back to earth -> Game over
		if (this.gameOver(actor)){
			return new EndGameAction(actor + " flies back to earth with Yugo Maxx's body and wins the game!").execute(actor,map);
		}

		else{
			// Get coordinates of pad on destination map
			int[] coords = this.destinationMap.getPadCoords();

			// Add player to those coordinates
			((GameMap) this.destinationMap).addActor(actor,coords[0],coords[1]);

			return this.actor + " flies to " + this.destinationMap.getName();
		}
	}

	/**
	 * Detects if player has GAMEWINNER skills representing them carrying YM's body
	 * @param actor Player
	 * @return true if actor has defeated Yugo Maxx
	 */
	private boolean gameOver(Actor actor){
		return actor.hasSkill(GameSkills.GAMEWINNER);
	}

    @Override
	public String menuDescription(Actor actor) {
		return this.actor + " uses rocket to fly to " + this.destinationMap.getName();
	}

    @Override
    public String hotKey() {
        return "";
    }

}
