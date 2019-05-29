package game.MapEnhancementExtention;

import edu.monash.fit2099.engine.*;

/**
 * Action to unlock a locked door
 */
class UnlockDoorAction extends Action {
    private Location doorLocation;

    /**
     * Creates a new unlock door action
     * @param doorLocation location of the door being unlocked
     */
    UnlockDoorAction(Location doorLocation) {
        this.doorLocation = doorLocation;
    }

    /**
     * Executes the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of the action performed
     * @throws IllegalArgumentException if actor doesn't have a key
     */
    @Override
    public String execute(Actor actor, GameMap map) throws IllegalArgumentException {
        // Precondition: actor must have a key
        boolean hasKey = false;
        for (Item item : actor.getInventory()){
            if (item instanceof Key){
                hasKey = true;
            }
        }
        if (!hasKey){
            throw new IllegalArgumentException("Actor Must have a key to unlock door");
        }

        // Replace the LockedDoor with an UnlockedDoor at the location
        map.add(new UnlockedDoor(), doorLocation);

        // Return the action description
        return actor + " unlocks the door";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " Unlock Door";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
