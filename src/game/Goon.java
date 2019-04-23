package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Goon extends Actor {

    // Goons have 50 hitpoints and are always represented with a &
    public Goon(String name, Actor player) {
        super(name, '&', 5, 5);
        // Goon insults
        ArrayList<String> insults = new ArrayList<String>(){{
            add("insult 1");
            add("insult 2");
            add("insult 3");
            add("insult 4");
        }};
        this.addItemToInventory(new Key());

        addBehaviour(new ShoutBehaviour(insults));
        addBehaviour(new FollowBehaviour(player));
    }
    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            if(action != null)
                return action;
        }

        return super.playTurn(actions,  map,  display);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "goonslaps");
    }
}
