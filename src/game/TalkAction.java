package game;
import java.util.Random;

import edu.monash.fit2099.engine.*;

public class TalkAction extends Action {
	private Actor actor;
	private Actor subject;

	public TalkAction(Actor actor, Actor subject) {
		this.actor = actor;
		this.subject = subject;
	}
	
	@Override
    public String execute(Actor actor, GameMap map) {
		for (Item item : actor.getInventory()){
			if (item instanceof RocketPlans){
				return this.subject + " said 'Hand them over! I don't have all day'!";
			}
		}
		return this.subject + "'I can give you something that will help, but I’m going to need the plans.'";
    }
	
    @Override
	public String menuDescription(Actor actor) {
		return actor + " talks to" + subject;
	}
    
    @Override
    public String hotKey() {
        return "";
    }
}
