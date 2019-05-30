package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action to end game
 */
public class EndGameAction extends Action {
    private String message;

    /**
     * Creates action
     * @param message End game message to display
     */
    public EndGameAction(String message){
        this.message = message;
    }

    /**
     * Ends the game
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return End game message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ((IPlanetaryMap) map).killAll();
        return this.message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " quits";
    }

    @Override
    public String hotKey() {
        return "q";
    }
}
