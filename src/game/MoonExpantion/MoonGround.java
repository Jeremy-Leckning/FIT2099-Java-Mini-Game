package game.MoonExpantion;

import edu.monash.fit2099.engine.*;
import game.GameSkills;

/**
 * Ground found on the moon, cant be walked on by players without a space suit and oxygen
 */
public class MoonGround extends Ground {
	/**
	 * Creates Moon Ground
	 */
	public MoonGround() {
		super('^');
	}

	/**
	 * Determines if actors can step on moon ground
	 * @param actor the Actor moving
	 * @param location the destination
	 * @param direction the direction of the destination from actor
	 * @param hotKey the character that will trigger the movement command
	 * @return MoveActorAction
	 */
	@Override
	public MoveActorAction getMoveAction(Actor actor, Location location, String direction, String hotKey) {

		// if actor is player -> needs suit
		if (actor instanceof Player) {

			// If player has suit -> return a moon walk
			if (actor.hasSkill(GameSkills.SPACETRAVELLER)){
				return new MoonWalkAction(location, direction, hotKey);
			}

			// Else player can't walk
			return null;
		}

		// if enemy -> normal walk
		return new MoveActorAction(location, direction, hotKey);

	}
}
