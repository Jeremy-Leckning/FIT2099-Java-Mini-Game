package game.MoonExpantion;

import edu.monash.fit2099.engine.*;

/**
 * Evil millionaire industrialist. has exoskeleton
 */
public class YugoMaxx extends Actor {
    private boolean exoSkeleton;

    /**
     * Creates the boss
     */
    public YugoMaxx() {
        super("Yugo Maxx", 'Y', 4, 1);
        this.exoSkeleton = true;
    }

    /**
     * Records exoskeleton destruction
     */
    void breakSkeleton(){
        this.exoSkeleton = false;
    }

    /**
     * Determines if boss still has exoskeleton
     * @return true/false
     */
    boolean hasSkeleton(){
        return this.exoSkeleton;
    }

    /**
     * Determines actions other actors can perform on YM
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return allowable actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {

        // Actor must be a player
        if (otherActor instanceof Player){

            // If YM has skeleton, he can't be attacked regularly
            if (this.hasSkeleton()) {
                return new Actions();
            }

            // Otherwise he can be killed
            return new Actions( new KillYMAction(this));
        }

        // Other actors do nothing
        return new Actions();
    }



}
