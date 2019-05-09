package game;

import edu.monash.fit2099.engine.Item;

/**
 * Stun powder thrown by ninja
 * Stuns player for 2 rounds
 */
public class StunPowder extends Item {
    private int count = 0;

    /**
     * Creates new stun powder
     */
    public StunPowder() {
        super("Stun Powder", 's');
    }

    /**
     * Determines if stun powder has expired or not
     * @return true/false
     */
    public boolean expired() {
        return count == 2;
    }

    /**
     * Registers a turn where stun powder is consumed
     */
    public void use(){
        count += 1;
    }
}
