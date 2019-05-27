package game;

import edu.monash.fit2099.engine.Item;
import game.GameSkills;

public class OxygenTank extends Item {
    private int pressure;

    public OxygenTank() {
        super("Oxygen Tank",'x');
        this.pressure = 2;
        this.addSkill(GameSkills.SPACEBREATHER);
    }

    public void useOxygen(){
        this.pressure -= 1;
        if (this.pressure == 0){
            this.removeSkill(GameSkills.SPACEBREATHER);
        }
    }

}
