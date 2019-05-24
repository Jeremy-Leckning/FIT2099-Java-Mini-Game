package game;

import edu.monash.fit2099.engine.*;

public class FlyAction extends Action {
	private Actor actor;
	private DisplayableMap departureMap;
	private DisplayableMap destinationMap;
	
	public FlyAction(Actor actor,DisplayableMap map, DisplayableMap destination) {
		this.actor = actor;
		this.departureMap = map;
		this.destinationMap = destination;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		this.departureMap.removeActor(actor);
		if (this.destinationMap.isMoon()) {
			this.destinationMap.addActor(actor, 2, 1);
		}
		else if (this.destinationMap.isEarth()) {
			this.destinationMap.addActor(actor, 14, 8);
		}
		
		return this.actor + " flies to " + this.destinationMap;
	}
	
    @Override
	public String menuDescription(Actor actor) {
		return this.actor + " uses rocket to fly to " + this.destinationMap;
	}

    @Override
    public String hotKey() {
        return "";
    }

}
