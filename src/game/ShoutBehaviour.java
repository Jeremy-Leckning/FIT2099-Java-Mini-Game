package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.Random;

public class ShoutBehaviour implements ActionFactory {
    private ArrayList<String> insults;
    private Random rand = new Random();


    public ShoutBehaviour( ArrayList<String> insults){
        this.insults = insults;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // 10% chance -> shout
        if (rand.nextInt(9) == 1){
            return new ShoutAction(actor,insults);
        }

        // 90% chance -> don't shout
        return null;
    }
}
