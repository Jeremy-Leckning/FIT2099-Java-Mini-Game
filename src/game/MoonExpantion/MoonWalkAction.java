package game.MoonExpantion;

import edu.monash.fit2099.engine.*;
import game.IPlanetaryMap;

/**
 * MoveActorAction for players on the moon
 */
class MoonWalkAction extends MoveActorAction {
    private final Element element = Element.OXYGEN;
    /**
     * Creates a moon walk action
     * @param moveToLocation Destination
     * @param direction direction of travel
     * @param hotKey hotkey for move
     */
    MoonWalkAction(Location moveToLocation, String direction, String hotKey) {
        super(moveToLocation, direction, hotKey);
    }

    /**
     * Execute a moonwalk, will use up player's oxygen, if player is out of oxygen, it will instead perform an emergency exit to earth
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of walk
     * PreCondition: Actor must be Player
     * @throws IllegalArgumentException if actor is not player
     */
    @Override
    public String execute(Actor actor, GameMap map) throws IllegalArgumentException {
        // Precondition: Actor must be player
        if(!(actor instanceof Player)){
            throw new IllegalArgumentException("Moon Walker must be a player");
        }

        // Find oxygen tank
        for (Item item : actor.getInventory()){
            if (item instanceof IStorageTank){
                if(((IStorageTank) item).holds() ==  this.element){

                    try {   // Tank has oxygen -> use up 1 and perform move
                        ((IStorageTank) item).decrement();
                        return super.execute(actor,map);
                    }
                    catch (Exception e){    // Tank is out of oxygen -> remove and continue
                        actor.removeItemFromInventory(item);
                    }
                }
            }
        }
        // No oxygen found -> emergency exit
        return this.emergencyExit(actor,map);

    }

    /**
     * Creates and executes an emergency exit back to earth
     * @param actor player
     * @param map current map
     * @return description of the move
     * precondition: map must implement IPlanetaryMap
     * @throws ClassCastException if map does not implement IPlanetaryMap
     */
    private String emergencyExit(Actor actor, GameMap map) throws ClassCastException{
        String moveDescription = actor + " is out of oxygen! ";

        // Get current map's pad to generate a fly action and execute
        Action emergency = ((IPlanetaryMap) map).getFlyAction();
        moveDescription += emergency.execute(actor,map);

        moveDescription += " in an intense emergency exit!";
        return moveDescription;

    }

}
