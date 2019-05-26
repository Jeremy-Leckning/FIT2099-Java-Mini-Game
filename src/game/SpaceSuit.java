package game;

import edu.monash.fit2099.engine.Item;

public class SpaceSuit extends Item {

	/**
	 * @param name
	 * @param displayChar
	 */
	public SpaceSuit() {
		super("Space Suit", 'S');
		this.addSkill(GameSkills.SPACETRAVELLER);
	}
	
}
