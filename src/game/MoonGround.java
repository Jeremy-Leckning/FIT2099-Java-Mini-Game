package game;

import edu.monash.fit2099.engine.*;

public class MoonGround extends Ground {
	public MoonGround() {
		super('^');
	}


	@Override
	public MoveActorAction getMoveAction(Actor actor, Location location, String direction, String hotKey) {

		// if actor is player -> moonwalk
		if (actor instanceof Player) {
			if (actor.hasSkill(GameSkills.SPACETRAVELLER) && actor.hasSkill(GameSkills.SPACEBREATHER)){
				return new MoonWalkAction(location, direction, hotKey,actor);
			}
			return null;
		}
		// if enemy -> normal walk
		return new MoveActorAction(location, direction, hotKey);

	}
}
