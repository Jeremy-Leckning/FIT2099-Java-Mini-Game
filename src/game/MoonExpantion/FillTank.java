package game.MoonExpantion;

import edu.monash.fit2099.engine.*;

/**
 * Action to fill a tank
 */
class FillTank extends Action {
    private IStorageTank tank;

    /**
     * Creates a fill action
     * @param tank an item with a tank
     * precondition: tank cant be full
     * @throws IllegalArgumentException if tank is full
     */
    FillTank(IStorageTank tank) throws IllegalArgumentException{
        // Precondition: tank cant be full
        if(tank.isFilled()){
            throw new IllegalArgumentException("Tank can't be full to fill up");
        }

        this.tank = tank;
    }

    /**
     * Refills the Tank
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of event
     * precondition: actor must be a player and must have the tank item in inventory
     * @throws IllegalArgumentException if actor doesn't have the tank or isn't a player
     */
    @Override
    public String execute(Actor actor, GameMap map) throws IllegalArgumentException{
        // Precondition: actor must be a player
        if(!(actor instanceof Player)){
            throw new IllegalArgumentException("Actor must be player");
        }
        // Precondition: tank must be in player's inventory
        if (!actor.getInventory().contains((Item) this.tank)){
            throw new IllegalArgumentException("Player must have pistol in inventory!");
        }

        // refill tank
        while (!this.tank.isFilled()){
            try{
                this.tank.increment();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        return actor + "fills up " + this.tank;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fills up " + this.tank;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
