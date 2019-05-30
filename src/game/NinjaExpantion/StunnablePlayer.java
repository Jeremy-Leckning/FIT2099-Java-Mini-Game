package game.NinjaExpantion;

import edu.monash.fit2099.engine.*;
import game.EndGameAction;

/**
 * Player Class that implements the stunned functionality.
 * When player is stunned, they skip their turn twice.
 * Player also has option to quit game
 */
public class StunnablePlayer extends Player {

	/**
	 * Creates Stun-able Player
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param priority    How early in the turn the player can act
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public StunnablePlayer(String name, char displayChar, int priority, int hitPoints) {
		super(name, displayChar, priority, hitPoints);
	}

	/**
	 * Processors players turn
	 * @param actions the actions to display
	 * @param map the map to display
	 * @param display the object that performs the console I/O
	 * @return Action player will perform
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {

		// If player is unconscious -> game over
		if( !this.isConscious()){
			new EndGameAction(this + " is dead!").execute(this,map);
		}

		// If player is stunned, return a SkipTurn action
		for (Item item : inventory) {
			if (item instanceof StunPowder) {
				// increase use count of stun powder by 1
				((StunPowder) item).use();

				// If player stunned for 2 rounds, remove stun powder
				if (((StunPowder) item).expired()) {
					inventory.remove(item);
				}

				return new SkipTurnAction();
			}
		}

		// Else play a normal players turn
		actions.add(new EndGameAction(this + " quits."));
		return super.playTurn(actions, map, display);
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10,"punches");
	}


}
    
