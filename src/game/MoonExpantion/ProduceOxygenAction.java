package game.MoonExpantion;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Player;

/**
 * Action to commence oxygen production
 */
class ProduceOxygenAction extends Action {
    private OxygenDispenser dispenser;

    /**
     * Creates the action
     * @param dispenser Oxygen dispenser that will produce oxygen
     * pre condition: Dispenser must not be creating oxygen or have an available tank
     * @throws IllegalArgumentException if dispenser is not idle
     */
    ProduceOxygenAction(OxygenDispenser dispenser) throws IllegalArgumentException{
        // Precondition: dispenser must be idle
        if(!dispenser.isIdle()){
            throw new IllegalArgumentException("Dispenser is not ready to dispense!");
        }

        this.dispenser = dispenser;
    }

    /**
     * Executes the production of oxygen. Will take 1 turn
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of event
     * Precondition: Actor must be a player
     * @throws IllegalArgumentException if actor is not player
     */
    @Override
    public String execute(Actor actor, GameMap map) throws IllegalArgumentException {
        // Precondition: actor must be a player
        if(!(actor instanceof Player)){
            throw new IllegalArgumentException("Only a player can order Oxygen!");
        }

        // Record oxygen production begun
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
