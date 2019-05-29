package game.MoonExpantion;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

/**
 * Tank containing oxygen
 */
class OxygenTank extends Item implements IStorageTank{
    private int pressure;
    private final Element element = Element.OXYGEN;

    /**
     * Creates a new Oxygen tank with 10 oxygen, gives player SPACEBREATER skill
     */
    OxygenTank() {
        super("Oxygen Tank",'x');

        this.pressure = 10;

        // Oxygen tanks will be picked up straight from the oxygen dispenser, so will start with drop item
        this.allowableActions.clear();
        this.allowableActions.add(new DropItemAction(this));
    }

    @Override
    public void increment() throws Exception {
        //Precondition: tank cant be full
        if (this.isFilled()){
            throw new Exception("Cant fill a full tank!");
        }

        this.pressure += 1;
    }

    @Override
    public void decrement() throws Exception {
        //Precondition: tank cant be full
        if (this.isEmpty()){
            throw new Exception("Cant fill an empty tank!");
        }

        this.pressure -= 1;

    }

    @Override
    public boolean isFilled() {
        return this.pressure == 10;
    }

    @Override
    public boolean isEmpty() {
        return this.pressure == 0;
    }

    @Override
    public Element holds() {
        return this.element;
    }
}
