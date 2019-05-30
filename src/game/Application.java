package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.MapEnhancementExtention.Floor;
import game.MapEnhancementExtention.LockedDoor;
import game.MapEnhancementExtention.Wall;
import game.MoonExpantion.*;
import game.NinjaExpantion.StunnablePlayer;
import game.RocketExpantion.*;

public class Application {

	public static void main(String[] args) {
		// Create both maps
		EarthMap earthMap = buildEarthMap();
		MoonMap moonMap = buildMoonMap();

		// Add rocket pads to both maps
		Location earthPadLocation = earthMap.at(earthMap.getPadCoords()[0],earthMap.getPadCoords()[1]);
		RocketPad earthPad = new RocketPad(moonMap, earthPadLocation);
		earthMap.add(earthPad,earthPadLocation);

		Location moonPadLocation = moonMap.at(moonMap.getPadCoords()[0],moonMap.getPadCoords()[1]);
		RocketPad moonPad = new RocketPad(earthMap, moonPadLocation);
		// Moon pad already has rocket built
		moonPad.placeBody();
		moonPad.placeEngine();
		moonMap.add(moonPad,moonPadLocation);

		// Create world and add maps
		World world = new World(new Display());
		world.addMap(earthMap);
		world.addMap(moonMap);

		// Create Player and add to world
		StunnablePlayer player = new StunnablePlayer("Player", '@', 1, 100);
		world.addPlayer(player, earthMap, 10, 4);

		// Set the fly action for each map
		moonMap.setFlyAction(new FlyAction(player,earthMap));
		earthMap.setFlyAction(new FlyAction(player,moonMap));

		// Populate maps with enemies and items
		earthMap.addActorsItems(player);
		moonMap.addActorsItems(player);

		// StartGame
		world.run();
	}

	private static MoonMap buildMoonMap(){
		FancyGroundFactory moonGroundFactory = new FancyGroundFactory(new MoonGround());
		List<String> moonString = Arrays.asList(
				"^^^^^^^^^^^^^^^",
				"^^^^^^^^^^^^^^^",
				"^^^^^^^^^^^^^^^",
				"^^^^^^^^^^^^^^^",
				"^^^^^^^^^^^^^^^",
				"^^^^^^^^^^^^^^^",
				"^^^^^^^^^^^^^^^",
				"^^^^^^^^^^^^^^^",
				"^^^^^^^^^^^^^^^",
				"^^^^^^^^^^^^^^^");
		return new MoonMap(moonGroundFactory,moonString);
	}

	private static EarthMap buildEarthMap(){
		FancyGroundFactory earthGroundFactory = new FancyGroundFactory(new Floor(), new Wall(),new LockedDoor(), new WaterPool(), new OxygenDispenser());

		List<String> earhString = Arrays.asList(
				".......................",
				"....#####....######....",
				"....+...#....+....#....",
				"....#...#....#....#....",
				"....#####....######....",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				"....i........w.........");

		return new EarthMap(earthGroundFactory,earhString);
	}

}
