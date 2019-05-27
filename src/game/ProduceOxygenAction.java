package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ProduceOxygenAction extends Action {
    private OxygenDispenser dispenser;

    public ProduceOxygenAction(OxygenDispenser dispenser){
        this.dispenser = dispenser;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        dispenser.produceOxygen();
        return actor + "'s oxygen will be ready next turn";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " Orders oxygen.";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
