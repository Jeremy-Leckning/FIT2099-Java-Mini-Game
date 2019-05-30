package game;

import edu.monash.fit2099.engine.Action;
import game.RocketExpantion.FlyAction;

/**
 * Interface for maps supporting Moon and Rocket expantion packs
 */
public interface IPlanetaryMap {
     /**
      * Returns coordinates of pad location
      * @return int[] [x,y] coords
      */
     int[] getPadCoords();

     /**
      * @return Planet Name
      */
     String getName();

     /**
      * @return Fly action from this planets pad
      */
     Action getFlyAction();

     /**
      * Set the fly action from this map
      * @param flyAction Action from this map to next
      */
     void setFlyAction(FlyAction flyAction);

     /**
      * Kills all actor on map to end game
      */
     void killAll();
}
