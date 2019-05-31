package game.RocketExpantion;

import edu.monash.fit2099.engine.*;
import game.GameSkills;
import game.IPlanetaryMap;

/**
 * Rocket pad where Rocket body and engine can be placed and where space travel happens
 */
public class RocketPad extends Ground {
    private boolean engine = false;
    private boolean body = false;
    private Location location;
	private IPlanetaryMap destinationMap;

    /**
     * Creates a Rocket Pad
     * @param location Location of pad
     * @param destination Destination map
     */
    public RocketPad(IPlanetaryMap destination, Location location) {
        super('~');
        this.destinationMap = destination;
        this.location = location;
    }

    /**
     * @return Location of pad
     */
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

            // Rocket already built -> fly
            if (this.allParts()){
                return this.getFlyAction(actor);
            }

            // Else -> placement
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

        // If player has spacesuit -> can fly
        if (actor.hasSkill(GameSkills.SPACETRAVELLER)){
            actions.add(new FlyAction(actor, this.destinationMap));
        }

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
    boolean allParts(){
        return this.engine && this.body;
    }



}
