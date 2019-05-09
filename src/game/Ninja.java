package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Ninja Enemy.
 * Don't move unless player is 5 blocks away
 * Throws stun powder at enemies which stuns them for 2 turns
 */
public class Ninja extends Actor {
    private Actor target;

    /**
     * Creates a Ninja Actor
     * @param name name of ninja
     * @param player player the ninja targets
     */
    public Ninja(String name, Actor player) {
        super(name, 'n', 5, 5);
        this.target = player;

        // All enemies have a key
        this.addItemToInventory(new Key());

    }

    /**
     * Processes Ninjas turn
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return Action the ninja takes
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {

        // if player is close enough -> ninja does a throw turn
        if (player5BlocksAway(map)){
            return new ThrowAction(target);
        }
        // Otherwise Ninja does nothing
        return new SkipTurnAction();
    }

    /**
     * Determines if target is at least 5 blocks away
     * @param map game map
     * @return true/false
     */
    private boolean player5BlocksAway(GameMap map) {

        // Get location of Actor and Target
        Location here = map.locationOf(this);
        Location there = map.locationOf(target);

        // Check for line of sight
        if (here.x() == there.x() || here.y() == there.y()) {

            // Calculate absolute distance in x and y plane
            int absx = Math.abs(here.x() - there.x());
            int absy = Math.abs(here.y() - there.y());

            // Ensure Ninja <= 5 blocks away
            if (absx <= 5 || absy <= 5) {

                // Ensure path to player is free of obstacles
                Range xs = new Range(Math.min(here.x(), there.x()), absx + 1);
                Range ys = new Range(Math.min(here.y(), there.y()), absy + 1);

                for (int x : xs) {
                    for (int y : ys) {
                        if (map.at(x, y).getGround().blocksThrownObjects())
                            return false;
                    }
                }
            }

            // All criteria are met -> return true
            return true;
        }

        return false;
    }

}
