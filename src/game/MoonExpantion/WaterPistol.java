package game.MoonExpantion;

import edu.monash.fit2099.engine.*;
import game.GameSkills;

/**
 * Water pistol weapon
 */
public class WaterPistol extends Item implements IStorageTank {
    private boolean filled;
    private Actor player;
    private YugoMaxx ym;
    private GameMap map;

    /**
     * Creates a water pistol
     * @param player player who will pick up pistol
     * @param ym Yugo Maxx boss who will be targeted
     * @param map map where ym and pistol are located
     */
    public WaterPistol(Actor player, YugoMaxx ym, GameMap map) {
        super("Water pistol", 'r');
        this.filled = false;
        this.player = player;
        this.ym = ym;
        this.map = map;
    }


    /**
     * Determines actions player can take with pistol
     * @return allowable Actions
     */
    @Override
    public Actions getAllowableActions() {
        Actions actions = new Actions();

        // hasn't been picked up -> player can pick up
        if(!this.player.getInventory().contains(this)){
            actions.add(new PickUpItemAction(this));
            return actions;
        }

        // Is picked up -> player can shoot if:

        // YM still alive
        if (!this.player.hasSkill(GameSkills.GAMEWINNER)) {

            // Player is on same map as YM
            if (this.map.locationOf(this.player).map() == this.map.locationOf(this.ym).map()) {

                // YM still has exoskeleton
                if (this.ym.hasSkeleton()) {

                    // Player to YM line of sight
                    if (this.lineOfSight()) {

                        // Pistol is filled
                        if (this.isFilled()) {

                            // All conditions met -> actor can squirt
                            actions.add(new SquirtAction(this, ym));
                        }
                    }
                }
            }
        }
    return actions;
    }

    /**
     * Determines if Yugo Maxx is in a clear line of sight of player
     * @return true/false
     */
    private boolean lineOfSight(){
        // Get location of Actor and Target
        Location here = this.map.locationOf(this.ym);
        Location there = this.map.locationOf(this.player);

        // Check for line of sight
        if (here.x() == there.x() || here.y() == there.y()) {

            // Ensure path to player is free of obstacles
            Range xs = new Range(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
            Range ys = new Range(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

            for (int x : xs) {
                for (int y : ys) {
                    if (this.map.at(x, y).getGround().blocksThrownObjects())
                        // Path is blocked -> false
                        return false;
                }
            }
            // Clear Line of sight -> return true
            return true;
        }
        return false;
    }

    @Override
    public void increment() throws Exception {
        //Precondition: tank cant be full
        if (this.isFilled()){
            throw new Exception("Cant fill a full tank!");
        }
        this.filled = true;
    }

    @Override
    public void decrement() throws Exception {
        //Precondition: tank cant be full
        if (this.isEmpty()){
            throw new Exception("Cant empty an empty tank!");
        }
        this.filled = false;
    }

    @Override
    public boolean isFilled() {
        return this.filled;
    }

    @Override
    public boolean isEmpty() {
        return !this.filled;
    }

    @Override
    public Element holds() {
        return Element.WATER;
    }
}
