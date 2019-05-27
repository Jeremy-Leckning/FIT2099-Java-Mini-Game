package game;

import java.util.ArrayList;
import java.util.HashMap;

import edu.monash.fit2099.engine.*;

/**
 * Player Class that implements the stunned functionality.
 * When player is stunned, they skip their turn twice.
 */
public class StunnablePlayer extends Player {
    /**
     * Creates Stun-able Player
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param priority    How early in the turn the player can act
     * @param hitPoints   Player's starting number of hitpoints
     */
    public StunnablePlayer(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display){

        // If player is stunned, return a SkipTurn action
        for (Item item : inventory){
            if (item instanceof StunPowder){
                // increase use count of stun powder by 1
                ((StunPowder) item).use();

                // If player stunned for 2 rounds, remove stun powder
                if (((StunPowder) item).expired()){
                    inventory.remove(item);
                }

                return new SkipTurnAction();
            }
        }

        // Else play a normal players turn
        return super.playTurn(actions,map,display);
    }
    
	/**
	 * Display a menu to the user and have them select an option.
	 *
	 * @param actions the Actions that the user can choose from
	 * @param display the I/O object that will display the map
	 * @return the Action selected by the user
	 */
    @Override
	protected Action showMenu(Actions actions, Display display) {
		ArrayList<Character> freeChars = new ArrayList<Character>();
		HashMap<Character, Action> keyToActionMap = new HashMap<Character, Action>();

		for (char i = 'a'; i <= 'z'; i++)
			freeChars.add(i);
		
		/*
		 * Removing PressButtonAction from list of possible actions if the player is at the location of dispenser on map but does not have
		 * the dispenser in his/her inventory.
		 */
		boolean found = false;
		for (Item item : this.getInventory()) {
			if (item instanceof OxygenDispenser) {
				found = true;
			}
		}
		if (!found) {
			for(Action action : actions) {
				if(action instanceof PressButtonAction) {
					actions.remove(action);
				}
			}
		}
			
		for (Action action : actions) {
			String hotKey = action.hotKey();
			if (hotKey != "") {
				if (freeChars.isEmpty())
					break;
				char c = hotKey.charAt(0);
				freeChars.remove(Character.valueOf(c));
				keyToActionMap.put(c, action);
				display.println(hotKey + ": " + action.menuDescription(this));
			}
		}

		for (Action action : actions) {
			if (action.hotKey() == "") {
				if (freeChars.isEmpty())
					break;
				char c = freeChars.get(0);
				freeChars.remove(0);
				keyToActionMap.put(c, action);
				display.println(c + ": " + action.menuDescription(this));
			}
		}

		char key;
		do {
			key = display.readChar();
		} while (!keyToActionMap.containsKey(key));
		
		return keyToActionMap.get(key);
	}
}
