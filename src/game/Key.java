package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

public class Key extends Item {

    public Key() {
        super("Key", 'K');
        this.allowableActions.clear();
        this.allowableActions.add(new DropItemAction(this));


    }
}
