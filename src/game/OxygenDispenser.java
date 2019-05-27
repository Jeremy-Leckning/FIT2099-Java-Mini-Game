package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

public class OxygenDispenser extends Item {
	
	public OxygenDispenser(Actor actor) {
		super("Oxygen Dispenser", 'O');
		allowableActions.add(new PressButtonAction());
	}
}
