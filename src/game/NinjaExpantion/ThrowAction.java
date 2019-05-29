package game.NinjaExpantion;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * Throw Action for Ninja
 */
class ThrowAction extends Action {
    private Actor target;
    private Random rand = new Random();

    /**
     * Creates a new throw Action
     * @param target target of the action
     */
    ThrowAction(Actor target){
        this.target = target;
    }

    /**
     * Performs the throw action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of action performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String moveDescription = "";

        // execute the throwing segment and append description to final description
        moveDescription += throwStunPowder(actor);

        // Add punctuation to description
        moveDescription += " and then ";

        // execute the moving segment and append description to final description
        moveDescription += moveNinja(actor,map);

        return moveDescription;

    }

    /**
     * Handles the Ninjas moving
     * @param actor Ninja
     * @param map Game map
     * @return Description of move
     */
    private String moveNinja(Actor actor, GameMap map){
        Location here = map.locationOf(actor);
        Actions moves = new Actions();

        // Loop through adjacent squares
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // If No actor is in square add a move actor action to the list
            if (!map.isAnActorAt(destination)) {
                Ground adjacentGround = map.groundAt(destination);
                moves.add(adjacentGround.getMoveAction(actor, destination, exit.getName(), exit.getHotKey()));
            }
        }

        // Chose random move
        Action chosenMove = moves.get(rand.nextInt(moves.size()));

        // Execute and return description
        return chosenMove.execute(actor,map);
    }

    /**
     * Handles ninjas throwing
     * @param actor ninja
     * @return description of throw
     */
    private String throwStunPowder(Actor actor){

        // 50% chance of hitting
        if (rand.nextBoolean()){

            // Check player isn't already stunned
            for (Item item : target.getInventory()){
                if (item instanceof StunPowder){
                    return actor + " Throws stun powder and hits player, but player is already stunned!";        // Ninja hits but player already stunned
                }
            }

            // If not, add stun powder to inventory and add appropriate description
            target.addItemToInventory(new StunPowder());
            return actor + " Throws stun powder and hits player, stunning them for 2 rounds!";       // Ninja hits and player not stunned
        }

        else{
            return actor + " Throws stun powder and misses player!";         // Ninja Misses
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    @Override
    public String hotKey() {
        return null;
    }
}
