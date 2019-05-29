package game.MoonExpantion;

import edu.monash.fit2099.engine.*;

/**
 * Pool of water, cant be entered
 */
public class WaterPool extends Ground {
    private final Element element = Element.WATER;

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

        // If actor is a player
        if (actor instanceof Player){
            for (Item item : actor.getInventory()){

                // Actor has a not filled water tank
                if (item instanceof IStorageTank){
                    if(!((IStorageTank) item).isFilled()){
                        if (((IStorageTank) item).holds() == this.element){

                            // Actor can fill up
                            actions.add(new FillTank((IStorageTank) item));
                        }
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
