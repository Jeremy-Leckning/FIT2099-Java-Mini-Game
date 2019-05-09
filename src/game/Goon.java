package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Goon enemy
 * Does 10 damage.
 * Follows player and can shout insults at player
 */
public class Goon extends Actor {

    /**
     * Creates a goon actor
     * @param name name of goon
     * @param player the player the goon is targeting
     */
    public Goon(String name, Actor player) {
        super(name, '&', 5, 5);

        // Create Goon insults and add shouting behaviour
        ArrayList<String> insults = new ArrayList<String>(){{
            add("I'm gonna get ya!");
            add("Watch out for my goon slap!");
            add("You've already lost, give up!");
            add("The last guy was so much better than you!");
        }};
        addBehaviour(new ShoutBehaviour(insults));

        // Add Follow behaviour
        addBehaviour(new FollowBehaviour(player));

        // All enemies have a key
        this.addItemToInventory(new Key());

    }

    // List of behaviours
    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    /**
     * Processes Goons turn
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return Description of the goons turn
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {

        // Go through each behaviour, if no behaviours manifest, execute a standard Actor turn
        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            if(action != null)
                return action;
        }

        return super.playTurn(actions,  map,  display);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "goonslaps");
    }
}
