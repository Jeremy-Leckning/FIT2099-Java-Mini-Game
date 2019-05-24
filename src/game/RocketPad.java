package game;

import edu.monash.fit2099.engine.*;

/**
 * Rocket pad where Rocket body and engine can be placed
 */
public class RocketPad extends Ground {
    private boolean engine = false;
    private boolean body = false;
    private DisplayableMap departureMap;
	private DisplayableMap destinationMap;

    /**
     * Creates a Rocket Pad
     */
    public RocketPad(DisplayableMap departure, DisplayableMap destination) {
        super('~');
        this.departureMap = departure;
        this.destinationMap = destination;
    }

	/**
     * Determines what actions players can make when next to pad
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Actions the player can perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();

        // Ensure actor is player
        if (actor instanceof Player) {

            // check player's inventory
            for (Item item : actor.getInventory()) {

                // Player has Engine
                if (item instanceof RocketEngine) {
                    actions.add(new PlaceOnPadAction(item, this));
                }

                // Player has Body
                if (item instanceof RocketBody) {
                    actions.add(new PlaceOnPadAction(item, this));
                }
            }
            
            if (this.allParts() || this.destinationMap.isEarth()) {
            	actions.add(new FlyAction(actor, this.departureMap, this.destinationMap));
            }
        }

        // If actor not player or neither items were found empty actions is returned
        return actions;
    }

    /**
     * Records player placing Rocket engine on pad
     */
    public void placeEngine(){
        this.engine = true;
    }

    /**
     * Records player placing Rocket body on pad
     */
    public void placeBody(){
        this.body = true;
    }

    /**
     * Determines if both Rocket engine and body have been placed on pad
     * @return true/false
     */
    public boolean allParts(){
        return this.engine && this.body;
    }

}
