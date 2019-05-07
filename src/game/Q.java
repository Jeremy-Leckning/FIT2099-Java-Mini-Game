package game;


import java.util.Random;

import edu.monash.fit2099.engine.*;

public class Q extends Actor {
	private Random rand = new Random();
	
	public Q(int hitPoints) {
		super("Q", 'Q', 2, hitPoints);
		this.addItemToInventory(new RocketBody());
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new TalkAction(otherActor, this));
	}
	
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
    	// Q wanders around the map
        Location here = map.locationOf(this);
        Actions possible_moves = new Actions();

        // Loop through adjacent squares
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // If No actor is at destination, add a move actor action to the list to that destination
            if (!map.isAnActorAt(destination)) {
                Ground adjacentGround = map.groundAt(destination);
                possible_moves.add(adjacentGround.getMoveAction(this, destination, exit.getName(), exit.getHotKey()));
            }
        }
        possible_moves.add(new SkipTurnAction());
        
        // Choose random move
        Action chosenMove = possible_moves.get(rand.nextInt(possible_moves.size()));

        return chosenMove;
    }

}
