package game;

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
}
