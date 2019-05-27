package game;

import edu.monash.fit2099.engine.*;

/**
 * Rocket pad where Rocket body and engine can be placed
 */
public class RocketPad extends Ground {
    private boolean engine = false;
    private boolean body = false;
    private Location location;
    private DisplayableMap departureMap;
	private DisplayableMap destinationMap;

    /**
     * Creates a Rocket Pad
     */
    public RocketPad(DisplayableMap departure, DisplayableMap destination, Location location) {
        super('~');
        this.departureMap = departure;
        this.destinationMap = destination;
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
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

        // Ensure actor is player
        if (actor instanceof Player) {

            // Rocket already built
            if (this.allParts()){
                return this.getFlyAction(actor);
            }

            // Else return placement actions or empty
            return this.getPlacementAction(actor);
        }

        return new Actions();
    }

    /**
     * Generates the flying actions of the pad
     * @param actor Player flying
     * @return flying action
     */
    private Actions getFlyAction(Actor actor){
        Actions actions = new Actions();
        actions.add(new FlyAction(actor, this.departureMap, this.destinationMap));
        return actions;
    }

    /**
     * Generates rocket parts placement actions
     * @param actor Player
     * @return Allowable actions
     */
    private Actions getPlacementAction(Actor actor){
        Actions actions = new Actions();

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
