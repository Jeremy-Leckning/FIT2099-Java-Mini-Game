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

                // Player must have:
                if (item instanceof IStorageTank){  // A storage tank

                    if(!((IStorageTank) item).isFilled()){  // That isn't full

                        if (((IStorageTank) item).holds() == this.element){     // That holds water

                            // All conditions met -> Actor can fill up
                            actions.add(new FillTankAction((IStorageTank) item));
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
