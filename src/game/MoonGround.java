package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class MoonGround extends Ground {
	public MoonGround() {
		super('^');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasSkill(GameSkills.SPACETRAVELLER);
	}
}
