package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Action to place Rocket engine and body on a pad
 */
public class PlaceOnPadAction extends Action {
    private Item item;
    private RocketPad pad;

    /**
     * Creates a new place on pad action
     * @param item RocketBody or engine to be placed on pad
     * @param pad The pad that items will be placed on
     * @throws IllegalArgumentException if item not rocket body or engine
     */
    public PlaceOnPadAction(Item item, RocketPad pad){

        // Precondition: Item must be rocket body or engine
        if (!(item instanceof RocketBody)&&!(item instanceof RocketEngine)){
            throw new IllegalArgumentException("Only rocket engines or body can be placed on pad");
        }

        this.item = item;
        this.pad = pad;
    }

    /**
     * Processes the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String description = "";

        // Determine which item is being placed and update pad and description
        if (item instanceof RocketBody){
            pad.placeBody();
            description += actor + " places rocket body on pad.";
        }
        if (item instanceof RocketEngine){
            pad.placeEngine();
            description += actor + " places rocket engine on pad.";
        }

        // If both engine and body engine are now on pad -> notify player and create rocket
        if (pad.allParts()){
            description += " And successfully builds the rocket!";
        }

        // Remove the item from player's inventory
        actor.removeItemFromInventory(item);

        return description;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" places "+this.item + " on pad.";
    }

    @Override
    public String hotKey() {
        return"";
    }
}
