package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new LockedDoor());
		FancyGroundFactory moonGroundFactory = new FancyGroundFactory(new MoonGround());
		DisplayableMap gameMap;

		List<String> map = Arrays.asList(
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
				".......................");
		
        List<String> moonMap = Arrays.asList(
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
        
        DisplayableMap moon = new DisplayableMap(moonGroundFactory, moonMap, "Moon");
		gameMap = new DisplayableMap(groundFactory, map, "Earth");
        
		gameMap.add(new RocketPad(gameMap, moon, gameMap.at(15,8)), gameMap.at(15, 8));
		moon.add(new Rocket(moon, gameMap), moon.at(2, 1));

		// delete
		((RocketPad) gameMap.groundAt(gameMap.at(15, 8))).rocketBuilt(gameMap);
		gameMap.add(new OxygenDispenser(),gameMap.at(9,9));

		
		world.addMap(gameMap);
		world.addMap(moon);
		
		// Player
		Actor player = new StunnablePlayer("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 9, 10);

		// Delete
		player.addItemToInventory(new SpaceSuit());


		// NPCs
		Q q = new Q(100);
		gameMap.addActor(q, 6, 6);

		// Enemies
		Ninja ninja1 = new Ninja("Ninja",player);
		gameMap.addActor(ninja1,0,1);

		Goon goon1 = new Goon("Senior Goon",player);
		gameMap.addActor(goon1, 0, 0);

		Goon goon2 = new Goon("Junior Goon",player);
		gameMap.addActor(goon2, 10, 10);

		Grunt grunt1 = new Grunt("Grunt",player);
		gameMap.addActor(grunt1,12,9);

		DoctorMaybe DrMaybe = new DoctorMaybe(player);
		gameMap.addActor(DrMaybe,15,3);
		
		Goon goon3 = new Goon("Space Goon", player);
		moon.addActor(goon3, 8, 9);
			
		Grunt grunt2 = new Grunt("Space Grunt", player);
		moon.addActor(grunt2, 1, 6);
		
		// Add rocket plans inside a locked room.
		RocketPlans plans = new RocketPlans();
		gameMap.addItem(plans, 5, 2);
		
		// Add space suit and Oxygen Dispenser to Earth map
		SpaceSuit spaceSuit = new SpaceSuit();
		gameMap.addItem(spaceSuit, 21, 5);
		
		OxygenDispenser oxygenDispenser = new OxygenDispenser(player);
		gameMap.addItem(oxygenDispenser, 16, 10);

		world.run();
	}
}
