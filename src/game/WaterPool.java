package game;

import edu.monash.fit2099.engine.*;

/**
 * Pool of water, cant be entered
 */
public class WaterPool extends Ground {

    /**
     * Creates a water pool
     */
    public WaterPool() {
        super('w');
    }

    /**
     * Determines actions that can be performed
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Allowable Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();

        // If actor is a player and has an empty pistol -> can fill up
        if (actor instanceof Player){
            for (Item item : actor.getInventory()){
                if (item instanceof WaterPistol){
                    if(!((WaterPistol) item).isFilled()){
                        actions.add(new FillPistolAction((WaterPistol) item));
                    }
                }
            }
        }

        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
