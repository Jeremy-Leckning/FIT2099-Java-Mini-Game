package game.MapEnhancementExtention;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

/**
 * Key to unlock Locked doors
 * Enemies drop keys when defeated
 */
public class Key extends Item {

    /**
     * Creates a Key item
     */
    public Key() {
        super("Key", 'K');

        // Keys will always be in Enemies inventory by default, so they should start with dropitemactions
        this.allowableActions.clear();
        this.allowableActions.add(new DropItemAction(this));
        
    }
}
