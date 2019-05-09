package game;

import edu.monash.fit2099.engine.*;

/**
 * Unlocked door
 */
public class UnlockedDoor extends Ground {

    /**
     * Creates an unlocked door
     */
    public UnlockedDoor() {
        super('-');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }


    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        return new Actions();

    }

    @Override
    public boolean blocksThrownObjects() {
        return false;
    }
}
