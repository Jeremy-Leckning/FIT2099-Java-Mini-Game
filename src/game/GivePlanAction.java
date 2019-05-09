package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Action to place Rocket body and engine on the Rocket Pad
 */
public class GivePlanAction extends Action {
	private Actor actor;
	private Q subject;
	private RocketPlans plans;

    /**
     * Creates a give plan action.
     * @param actor actor giving plans
     * @param subject Q receiving plans
     * @param plans the Rocket plans from players inventory
     * Precondition: actor has plans in inventory
     * @throws IllegalArgumentException if actor doen't have plans in inventory
     */
	public GivePlanAction(Actor actor, Q subject,RocketPlans plans) throws IllegalArgumentException {

	    // Precondition: actor has plans in inventory
        if (!actor.getInventory().contains(plans)){
            throw new IllegalArgumentException(actor + " doesn't have" + plans + " in it's inventory");
        }

		this.actor = actor;
		this.subject = subject;
		this.plans = plans;
	}

    /**
     * Executes the give plan action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Description of action performed
     */
	@Override
    public String execute(Actor actor, GameMap map) {

        // remove Rocket Plans from player's inventory and add Rocket Body
        actor.removeItemFromInventory(this.plans);
        actor.addItemToInventory(new RocketBody());

        // remove Q
        map.removeActor(this.subject);

        // Return description
        return this.actor + " gave Rocket plans to " + this.subject + ", and received Rocket Body in exchange. "
                + "Q disappeared with a cheery wave!!!";

    }
	
    @Override
	public String menuDescription(Actor actor) {
		return actor + " give plans to " + this.subject;
	}

    @Override
    public String hotKey() {
        return "";
    }

}
