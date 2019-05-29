package game.MoonExpantion;

import edu.monash.fit2099.engine.Item;

/**
 * Space suit for space travel
 */
public class SpaceSuit extends Item {

	/**
	 * Creates a space suit -  adds SPACETRAVELLER skill.
	 */
	public SpaceSuit() {
		super("Space Suit", 'S');
		this.addSkill(GameSkills.SPACETRAVELLER);
	}
	
}
