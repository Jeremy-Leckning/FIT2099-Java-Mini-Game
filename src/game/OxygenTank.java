package game;

import edu.monash.fit2099.engine.Item;
import game.GameSkills;

/**
 * Tank containing oxygen
 */
public class OxygenTank extends Item {
    private int pressure;

    /**
     * Creates a new Oxygen tank with 10 oxygen, gives player SPACEBREATER skill
     */
    public OxygenTank() {
        super("Oxygen Tank",'x');
        this.pressure = 10;
        this.addSkill(GameSkills.SPACEBREATHER);
    }

    /**
     * Use up 1 oxygen, if oxygen runs out, SPACEBREATER skill will be removed
     */
    public void useOxygen(){
        this.pressure -= 1;

        // If out of oxygen remove skill
        if (this.pressure == 0){
            this.removeSkill(GameSkills.SPACEBREATHER);
        }
    }

    /**
     * Determines if tank is empty
     * @return true/false
     */
    public boolean outOfOxygen(){
        return this.pressure == 0;
    }

}
