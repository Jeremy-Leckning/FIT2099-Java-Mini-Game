package game.MoonExpantion;

import edu.monash.fit2099.engine.*;

/**
 * Oxygen dispensing station
 */
public class OxygenDispenser extends Ground {
    private boolean isDispensing = false;
    private boolean tankAvailable = false;
    /**
     * Creates an oxygen dispenser.
     */
    public OxygenDispenser() {
        super('i');
    }

    /**
     * Determines actions actors can perform.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return allowable Actions
     */
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

    /**
     * Triggers oxygen production
     */
    void produceOxygen(){
        this.isDispensing = true;
    }

    /**
     * Determines if dispenser is empty and not producing oxygen
     * @return true/false
     */
    boolean isIdle(){
        return (!this.isDispensing || !this.tankAvailable);
    }
}
