package game;

import edu.monash.fit2099.engine.*;

/**
 * Water pistol weapon
 */
public class WaterPistol extends WeaponItem {
    private boolean filled;
    private Actor player;
    private YugoMaxx ym;
    private DisplayableMap map;

    /**
     * Creates a water pistol
     * @param player player who will pick up pistol
     * @param ym Yugo Maxx boss who will be targeted
     * @param map map where ym and pistol are located
     */
    public WaterPistol(Actor player, YugoMaxx ym, DisplayableMap map) {
        super("Water pistol", 'r', 0, "squirt");
        this.filled = false;
        this.player = player;
        this.ym = ym;
        this.map = map;
    }

    /**
     * Fills water pistol with water
     */
    public void fill(){
        this.filled = true;
    }

    /**
     * Empties water pistol after firing
     */
    public void empty(){
        this.filled = false;
    }

    /**
     * Determines if pistol has water
     * @return
     */
    public boolean isFilled(){
        return this.filled;
    }

    /**
     * Determines actions player can take with pistol
     * @return
     */
    @Override
    public Actions getAllowableActions() {
        Actions actions = new Actions();

        // Has exoskeleton, need to shoot water pistol
        if (this.ym.hasSkeleton()) {

            // Check line of sight and pistol fullness -> actor can squirt
            if (this.lineOfSight()) {
                if (this.isFilled()) {
                    actions.add(new SquirtAction(this, ym));
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
                        return false;
                }
            }
            // Clear Line of sight -> return true
            return true;
        }
        return false;
    }

}
