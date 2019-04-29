package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.Random;

public class ShoutBehaviour extends Action implements ActionFactory {
    private ArrayList<String> insults;
    private Random rand = new Random();


    public ShoutBehaviour( ArrayList<String> insults){
        this.insults = insults;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // 10% chance -> shout
        if (rand.nextInt(9) == 1){
            return this;
        }

        // 90% chance -> don't shout
        return null;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Chose a random insult
        int index = rand.nextInt(insults.size());
        String insult = insults.get(index);

        // Return the shouting text
        return actor + "shouts: '" + insult + "'";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "Shouts";
    }

    @Override
    public String hotKey() {
        return null;
    }
}
