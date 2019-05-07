package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new LockedDoor());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....+...#....#....#....",
				"....#...#....#....#....",
				"....#####....##.###....",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);


		Actor player = new Player("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 2, 2);

		Ninja ninja1 = new Ninja("ninja1",player);
		gameMap.addActor(ninja1,0,1);

		Goon goon1 = new Goon("goon1",player);
		gameMap.addActor(goon1, 0, 0);
		Goon goon2 = new Goon("goon2",player);
		gameMap.addActor(goon2, 10, 10);
		
		Q q = new Q(100);
		gameMap.addActor(q, 6, 6);
		
		// Adding the rocket plans inside a locked room. 
		RocketPlans plans = new RocketPlans("Plans", 'P');
		gameMap.addItem(plans, 5, 2);		
		world.run();
	}
}
