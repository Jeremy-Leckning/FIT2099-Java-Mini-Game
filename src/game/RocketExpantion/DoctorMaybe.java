package game.RocketExpantion;

import edu.monash.fit2099.engine.*;

/**
 * Doctor Maybe Miniboss.
 * Has 25 hitpoints and does 3 damage
 * Does not move
 * Drops Rocket Engine upon death
 */
public class DoctorMaybe extends Actor {
    private Actor target;
    /**
     * Creates a Doctor Maybe object and gives him a rocket engine
     * @param target Player dr maybe should target
     */
    public DoctorMaybe(Actor target) {
        super("Doctor Maybe", 'D', 5, 25);
        this.target = target;

        // Add rocket engine
        this.addItemToInventory(new RocketEngine());
    }

    /**
     * Plays dr Maybe's turn
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return an attack action, dr maybe doesn't move but is very aggressive
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        // If player 1 block away -> return attack action
        if (this.playerInReach(map)){
            return new AttackAction(this, this.target);
        }

        // Else -> return skip turn action
        return new SkipTurnAction();
    }

    /**
     * Checks if targeted player is in adjacent block
     * @param map game map
     * @return true/false
     */
    private boolean playerInReach(GameMap map){
        Location here = map.locationOf(this);

        // Loop through adjacent blocks
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // If player is in adjacent block, return true
            if (map.actorAt(destination) == this.target) {
                return true;
            }
        }

        // Otherwise, player isn't 1 block away -> return false
        return false;
    }

    /**
     * Dr maybe does 3 hitpoints
     * @return Dr Maybes intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(3, "tickles");
    }
}
