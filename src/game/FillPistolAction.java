package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Player;

/**
 * Action to fill water pistol when near a pool of water
 */
public class FillPistolAction extends Action {
    private WaterPistol pistol;

    /**
     * Creatres an action
     * @param pistol Water pistol
     * precondition: Pistol must be empty
     * @throws IllegalArgumentException if pistol is full
     */
    public FillPistolAction(WaterPistol pistol) throws IllegalArgumentException{
        // Precondition: pistol must be empty
        if(pistol.isFilled()){
            throw new IllegalArgumentException("Pistol must be empty to fill up");
        }

        this.pistol = pistol;
    }

    /**
     * Refills the pistol
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of event
     * precondition: actor must be a player and must have pistol in inventory
     * @throws IllegalArgumentException if actor doesnt have pistol or isnt a player
     */
    @Override
    public String execute(Actor actor, GameMap map) throws IllegalArgumentException{
        // Precondition: actor must be a player
        if(!(actor instanceof Player)){
            throw new IllegalArgumentException("Actor must be player");
        }
        // Precondition: pistol must be in player's inventory
        if (!actor.getInventory().contains(this.pistol)){
            throw new IllegalArgumentException("Player must have pistol in inventory!");
        }

        // refill pistol
        this.pistol.fill();

        return actor + "fills up water pistol";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fills up water pistol";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
