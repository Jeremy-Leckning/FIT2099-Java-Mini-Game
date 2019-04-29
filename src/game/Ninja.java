package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ninja extends Actor {

    private Actor target;
    private Random rand = new Random();


    public Ninja(String name, Actor player) {
        super(name, 'n', 5, 5);
        this.target = player;

        // All enemies have a key
        this.addItemToInventory(new Key());

    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {

        if (player5BlocksAway(map)){
            return new ThrowAction(target);
        }

        return new SkipTurnAction();
    }

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
