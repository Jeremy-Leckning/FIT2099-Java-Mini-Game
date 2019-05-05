package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;

public class Q extends Actor {
	private Random rand = new Random();
	
	public Q(int hitPoints, Actor player) {
		super("Q", 'Q', 2, hitPoints);
		this.addItemToInventory(new RocketBody());
	}
	
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
    	// Q wanders around the map
        Location here = map.locationOf(this);
        Actions moves = new Actions();

        // Loop through adjacent squares
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // If No actor is in square add a move actor action to the list
            if (!map.isAnActorAt(destination)) {
                Ground adjacentGround = map.groundAt(destination);
                moves.add(adjacentGround.getMoveAction(this, destination, exit.getName(), exit.getHotKey()));
            }
        }
        moves.add(new SkipTurnAction());
        
        // Choose random move
        Action chosenMove = moves.get(rand.nextInt(moves.size()));

        return chosenMove;
    }

}
