package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoutAction extends Action {
    private Actor actor;
    private Random rand = new Random();
    private ArrayList<String> insults;

    public ShoutAction(Actor actor,ArrayList<String> insults){
        this.actor = actor;
        this.insults = insults;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int index = rand.nextInt(insults.size());
        String insult = insults.get(index);
        return actor + ": " + insult;
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
