package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class PressButtonAction extends Action {
	
	public PressButtonAction() {
	}
	
	@Override
    public String execute(Actor actor, GameMap map) {
        for (Item item : actor.getInventory()){
            if (item instanceof OxygenDispenser){
            	
        		Location here = map.locationOf(actor);
        		
        		// The button does nothing while there is an oxygen tank in the current location
        		for (Item item2 : here.getItems()) {
        			if (item2 instanceof OxygenTank) {
        				return "Cannot produce another Oxygen Tank as there is already one at the current location";
        			}
        		}
        		here.addItem(new OxygenTank());
        		return actor + " has pressed the button on the Oxygen Dispenser and has produced an Oxygen Tank";
            }
        }
        
        throw new IllegalArgumentException("Actor Must have an Oxygen Dispenser to press its button");
    }
	
    @Override
	public String menuDescription(Actor actor) {
		return actor + " presses the button on the oxygen dispenser";
	}
    
    @Override
    public String hotKey() {
        return "";
    }

}
