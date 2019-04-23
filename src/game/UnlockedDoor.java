package game;

import edu.monash.fit2099.engine.*;

public class UnlockedDoor extends Ground {

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
