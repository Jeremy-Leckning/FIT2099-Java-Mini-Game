package game;

import edu.monash.fit2099.engine.*;

public class MoonWalkAction extends MoveActorAction {
    private Actor player;

    public MoonWalkAction(Location moveToLocation, String direction, String hotKey,Actor player) {
        super(moveToLocation, direction, hotKey);
        this.player = player;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item item : actor.getInventory()){
            if (item instanceof OxygenTank){
                ((OxygenTank) item).useOxygen();
            }
        }
        return super.execute(actor,map);
    }
}
