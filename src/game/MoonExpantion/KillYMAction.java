package game.MoonExpantion;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class KillYMAction extends Action {
    private YugoMaxx ym;

    KillYMAction(YugoMaxx ym){
        this.ym = ym;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addSkill(GameSkills.GAMEWINNER);
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
