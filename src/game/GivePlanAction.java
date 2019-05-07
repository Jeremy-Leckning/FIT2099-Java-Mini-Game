package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class GivePlanAction extends Action {
	private Actor actor;
	private Actor subject;
	
	public GivePlanAction(Actor actor, Actor subject) {
		this.actor = actor;
		this.subject = subject;
	}
	
	@Override
    public String execute(Actor actor, GameMap map) {
		for (Item item : actor.getInventory()){
			if (item instanceof RocketPlans){
				actor.removeItemFromInventory(item);
				this.subject.addItemToInventory(item);
				map.removeActor(this.subject);
				return this.actor + " gave Rocket plans to " + this.subject + ", and received Rocket Engine in exchange. "
						+ "Q disappeared with a cheery wave!!!";
			}
		}
		return "You don't have the plans fool";
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
