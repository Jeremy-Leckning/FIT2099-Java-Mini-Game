package game;

import edu.monash.fit2099.engine.*;

public class Rocket extends Ground {

    private DisplayableMap departure;
    private DisplayableMap destination;

    public Rocket(DisplayableMap departure, DisplayableMap destination) {
        super('R');
        this.departure = departure;
        this.destination = destination;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();

        // Ensure actor is player
        if (actor instanceof Player) {
            actions.add(new FlyAction(actor, this.departure, this.destination));
        }

        // If actor not player or neither items were found empty actions is returned
        return actions;
    }
}
