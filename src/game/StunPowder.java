package game;

import edu.monash.fit2099.engine.Item;

public class StunPowder extends Item {
    private int count = 0;

    public StunPowder() {
        super("Stun Powder", 's');
    }

    public boolean expired() {
        return count == 2;
    }

    public void use(){
        count += 1;
    }
}
