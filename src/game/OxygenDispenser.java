package game;

import edu.monash.fit2099.engine.*;

public class OxygenDispenser extends Ground {
    private boolean isDispensing = false;
    private boolean tankAvailable = false;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public OxygenDispenser() {
        super('O');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();

        // Ensure actor is player
        if (actor instanceof Player) {

            // If dispensing, generator now has oxygen, return no actions
            if (this.isDispensing) {
                this.isDispensing = false;
                this.tankAvailable = true;
                return actions;
            }

            // not dispensing and no tank, player can order oxygen
            if (!this.tankAvailable){
                actions.add(new ProduceOxygenAction(this));
                return actions;
            }

            // Tank is ready, player can pick up
            actions.add(new PickUpItemAction(new OxygenTank()));
            this.tankAvailable = false;
            return actions;

        }
        return actions;
    }

    public void produceOxygen(){
        this.isDispensing = true;
    }
}
