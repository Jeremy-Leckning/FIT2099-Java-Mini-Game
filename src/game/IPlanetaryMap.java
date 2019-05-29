package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import game.RocketExpantion.FlyAction;

public interface IPlanetaryMap {
     int[] getPadCoords();
     String getName();
     Action getFlyAction();
     void setFlyAction(FlyAction flyAction);
     void killAll();
}
