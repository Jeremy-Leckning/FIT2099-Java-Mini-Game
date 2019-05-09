package game;


import java.util.Random;
import edu.monash.fit2099.engine.*;

/**
 * Friendly NPC that accepts plans and gives Rocket body
 */
public class Q extends Actor {
	private Random rand = new Random();

    /**
     * Create a Q Actor
     * @param hitPoints
     */
	public Q(int hitPoints) {
		super("Q", 'Q', 2, hitPoints);
	}

    /**
     * Determines actions player can take when next to Q
     * @param actor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return allowable Actions
     */
	@Override
	public Actions getAllowableActions(Actor actor, String direction, GameMap map) {
	    // For players
	    if (actor instanceof Player) {

            // Player can always talk to Q
            Actions actionsList = new Actions(new TalkAction(this));

            // Check if player has plans
            for (Item item : actor.getInventory()) {
                if (item instanceof RocketPlans) {
                    // If so, add a new give plan action
                    actionsList.add(new GivePlanAction(actor, this, (RocketPlans) item));
                }
            }
            return actionsList;
        }

	    // For enemies -> can't do anything
        return new Actions();
	}

    /**
     * Processes Q's turn
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return Action Q takes
     */
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
        // add a skip turn option
        possible_moves.add(new SkipTurnAction());
        
        // Choose random move
        Action chosenMove = possible_moves.get(rand.nextInt(possible_moves.size()));
        return chosenMove;
    }
}
