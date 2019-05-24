package game;

import java.util.List;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;

public class DisplayableMap extends GameMap {
	private String name;
	
	public DisplayableMap(GroundFactory groundFactory, List<String> lines, String name) {
		super(groundFactory, lines);
		this.name = name;
	}
	
	public boolean isMoon() {
		if (this.name == "Moon") {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isEarth() {
		if (this.name == "Earth") {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return name;
	}

}
