package game.MoonExpantion;

import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * Evil millionaire industrialist. has exoskeleton
 */
public class YugoMaxx extends Actor {
    private boolean exoSkeleton;
	private Random rand = new Random();

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
    
    
    /**
     * Processes YugoMaxx turn
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return Description of the goons turn
     */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		// Making sure YugoMaxx does not pick up items on map(such as water pistol)
        for (Action action : actions) {
        	if (action instanceof PickUpItemAction) {
        		actions.remove(action);
        	}
        }
        return super.playTurn(actions, map, display);
    }


}
