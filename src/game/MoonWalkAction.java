package game;

import edu.monash.fit2099.engine.*;

/**
 * MoveActorAction for players on the moon
 */
public class MoonWalkAction extends MoveActorAction {

    /**
     * Creates a moon walk action
     * @param moveToLocation Destination
     * @param direction direction of travel
     * @param hotKey hotkey for move
     */
    public MoonWalkAction(Location moveToLocation, String direction, String hotKey) {
        super(moveToLocation, direction, hotKey);
    }

    /**
     * Execute a moonwalk, will use up players oxygen, if player runs out of oxygen, will also perform an emergency exit to earth
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of walk
     * PreCondition: Actor must be Player with oxygen tank
     * @throws IllegalArgumentException if actor is not player or if player has no oxygen tank
     */
    @Override
    public String execute(Actor actor, GameMap map) throws IllegalArgumentException {
        // Precondition: Actor must be player
        if(!(actor instanceof Player)){
            throw new IllegalArgumentException("Flyer must be a player");
        }

        // Find oxygen tank
        for (Item item : actor.getInventory()){
            if (item instanceof OxygenTank){

                // Use up 1 oxygen and execute the move
                ((OxygenTank) item).useOxygen();
                 String moveDescription = super.execute(actor,map);

                 // If player is out of oxygen, also perform emergency exit.
                 if (((OxygenTank) item).outOfOxygen()){
                     moveDescription += this.emergencyExit(actor,map);
                 }

                 return moveDescription;
            }
        }
        // Tank not found: throw exception
        throw new IllegalArgumentException("Actor has no oxygen Remaining!");

    }

    /**
     * Creates and executes an emergency exit back to earth
     * @param actor player
     * @param map current map
     * @return description of the move
     */
    private String emergencyExit(Actor actor, GameMap map){
        String moveDescription = " and because player is out of oxygen, ";

        // Get current map's pad to generate a fly action and execute
        Actions emergency = ((DisplayableMap) map).getPad().allowableActions(actor, new Location(map,3,1),"");
        moveDescription += emergency.get(0).execute(actor,map);

        moveDescription += " in an intense emergency exit!";
        return moveDescription;

    }

}
