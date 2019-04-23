package game;

import edu.monash.fit2099.demo.Floor;
import edu.monash.fit2099.engine.*;

import java.util.Random;

public class UnlockDoorAction extends Action {
    private Location doorLocation;

    public UnlockDoorAction(Location doorLocation) {
        this.doorLocation = doorLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.add(new UnlockedDoor(), doorLocation);
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
