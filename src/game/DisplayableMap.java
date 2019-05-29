package game;

import java.util.List;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import game.RocketExpantion.RocketPad;

/**
 * Extension to game map that has a name and stores rocket pad
 */
public class DisplayableMap extends GameMap {
	private String name;
	private RocketPad pad;
	private int[] padCoord;

	/**
	 * Create a Displayable map
	 * @param groundFactory Factory to create Ground objects
	 * @param lines List of Strings representing rows of the map
	 * @param name Name of the planet/map
	 */
	public DisplayableMap(GroundFactory groundFactory, List<String> lines, String name) {
		super(groundFactory, lines);
		this.name = name;
	}

	/**
	 * Returns x and y coordinates of the rocketpad location
	 * @return int[x,y]
	 */
	public int[] getPadCoord(){
		return this.padCoord;
	}

	/**
	 * Registers the map's rocket pad and initialises coordinates
	 * @param pad Rocket pad of the map
	 */
	public void setPad(RocketPad pad){
		this.padCoord = new int[] {pad.getLocation().x(),pad.getLocation().y()};
		this.pad = pad;
	}

	/**
	 * Returns the rocket pad of the map
	 * @return
	 */
	public RocketPad getPad(){
		return this.pad;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
