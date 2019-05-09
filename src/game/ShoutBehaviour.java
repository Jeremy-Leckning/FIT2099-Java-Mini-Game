package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Shout Behaviour that also acts as shout action for Goon
 */
public class ShoutBehaviour extends Action implements ActionFactory {
    private ArrayList<String> insults;
    private Random rand = new Random();

    /**
     * Creates a new shout behaviour action factory
     * @param insults list of strings of possible insults
     */
    public ShoutBehaviour( ArrayList<String> insults){
        this.insults = insults;
    }

    /**
     * Determines if behaviour will manifest itself into action
     * @param actor Actor who's turn it is
     * @param map game map
     * @return A shout action or null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // 10% chance -> shout
        if (rand.nextInt(9) == 1){
            return this;
        }

        // 90% chance -> don't shout
        return null;
    }

    /**
     * Executes a shouting action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Description of action performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Chose a random insult
        int index = rand.nextInt(insults.size());
        String insult = insults.get(index);

        // Return the shouting text
        return actor + "shouts: '" + insult + "'";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "Shouts";
    }

    @Override
    public String hotKey() {
        return null;
    }
}
