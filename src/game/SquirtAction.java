package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Player;

import java.util.Random;

/**
 * Action to fire water pistol
 */
public class SquirtAction extends Action {
    private WaterPistol pistol;
    private Random rand = new Random();
    private YugoMaxx target;

    /**
     * Creates action
     * @param pistol Water pistol
     * @param target YugoMaxx boss
     * Preconditions: pistol must be filled and YugoMaxx must still have his exoskeleton
     * @throws IllegalArgumentException if pistol is empty or YM doesn't have an exoskeleton
     */
    public SquirtAction(WaterPistol pistol,YugoMaxx target){
        //Precondition: Pistol must be full
        if (!pistol.isFilled()){
            throw new IllegalArgumentException("Pistol must be filled!");
        }
        // Precondition: YugoMaxx must still have an exo skeleton
        if (!target.hasSkeleton()){
            throw new IllegalArgumentException("Yugo Maxx must have an exe skeleton to shoot the water pistol at");
        }

        this.pistol = pistol;
        this.target = target;
    }

    /**
     * Attacks YM with water pistol
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of action
     * Precondition: Actor must be player and have water pistol in inventory
     * @throws IllegalArgumentException if actor is not player or does not have water pistol
     */
    @Override
    public String execute(Actor actor, GameMap map) throws IllegalArgumentException{
        // Precondition: actor must be player
        if(!(actor instanceof Player)){
            throw new IllegalArgumentException("actor must be player");
        }

        //Precondition: actor must have water pistol in inventory
        if (!actor.getInventory().contains(this.pistol)){
            throw new IllegalArgumentException("Player must have the waterpistol in inventory");
        }

        // Empty pistol and prepare move description
        pistol.empty();
        String moveDescription = actor + " shoots water pistol, emptying it and ";

        // Successful hit -> break skeleton
        if (rand.nextInt(10) > 3){
            this.target.breakSkeleton();
            moveDescription += "hits Yugo Maxx, destroying his exoskeleton!";
        }
        // Miss
        else{
            moveDescription += "misses Yugo Maxx!";
        }

        return moveDescription;

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " skirts water pistol";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
