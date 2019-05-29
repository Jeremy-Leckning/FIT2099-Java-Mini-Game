package game.RocketExpantion;
import edu.monash.fit2099.engine.*;

/**
 * Talk action between player and Q
 */
class TalkAction extends Action {
	private Actor subject;

	/**
	 * Creates a new Talk action
	 * @param subject Q that is being talked to
	 * @throws IllegalArgumentException if subject is not Q
	 */
    TalkAction(Actor subject) throws IllegalArgumentException {
		// Precondition: subject is Q
		if (!(subject instanceof Q)){
			throw new IllegalArgumentException("Player can only talk to Q");
		}

		this.subject = subject;
	}

	/**
	 * Executes a talk action
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return Description of action performed
	 */
	@Override
    public String execute(Actor actor, GameMap map) {
		// Check if player has plans
		for (Item item : actor.getInventory()){

			// Player has plans -> ask 'politely'
			if (item instanceof RocketPlans){
				return this.subject + " said 'Hand them over! I don't have all day'!";
			}
		}
		// Player doesn't have plans -> tell player
		return this.subject + "'I can give you something that will help, but I'm going to need the plans.'";
    }
	
    @Override
	public String menuDescription(Actor actor) {
		return actor + " talks to " + this.subject;
	}

    @Override
    public String hotKey() {
        return "";
    }
}
