package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new LockedDoor(),new RocketPad());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....+...#....+....#....",
				"....#...#....#....#....",
				"....#####....######....",
				".......................",
				".......................",
				".......................",
				"...............~.......",
				".......................",
				".......................");
		
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);

		// Player
		Actor player = new StunnablePlayer("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 9, 10);

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
		
		// Add rocket plans inside a locked room.
		RocketPlans plans = new RocketPlans();
		gameMap.addItem(plans, 5, 2);



		world.run();
	}
}
