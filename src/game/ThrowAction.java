package game;

import edu.monash.fit2099.engine.*;

import java.util.List;
import java.util.Random;

public class ThrowAction extends Action {
    private Actor target;
    private Random rand = new Random();

    public ThrowAction(Actor target){
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String moveDescription = "";

        moveDescription += throwStunPowder(actor);
        moveDescription += " and then ";
        moveDescription += moveNinja(actor,map);

        return moveDescription;

    }

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

    public String throwStunPowder(Actor actor){

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
