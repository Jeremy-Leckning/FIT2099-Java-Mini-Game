package game.MoonExpantion;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.GameSkills;

/**
 * Action to end Yugo Maxx's terror reign once his exoskeleton is destroyed.
 */
public class KillYMAction extends Action {
    private YugoMaxx ym;

    /**
     * Creates the kill action.
     * Precondition: Yugo Maxx's exo skeleton must be destroyed
     * @param ym YugoMaxx Boss
     * @throws IllegalArgumentException if YM's exoskeleton is not broken
     */
    KillYMAction(YugoMaxx ym) throws IllegalArgumentException{
        // Precondition: YM's skeleton must be destroyed
        if (ym.hasSkeleton()){
            throw new IllegalArgumentException(ym + "'s exoskeleton must be destroyed to kill him.");
        }

        this.ym = ym;
    }

    /**
     * Executes the action. Gives player GAMEWINNER skill.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The description of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Record end of game
        actor.addSkill(GameSkills.GAMEWINNER);

        // Remove YM
        map.removeActor(this.ym);

        return actor + " kills " + this.ym + "! " + actor + "picks up his sleeping body and can return it to earth to win!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " kills " + this.ym;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
