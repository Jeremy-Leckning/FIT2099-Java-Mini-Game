package game;

import edu.monash.fit2099.engine.*;
import game.GoonGruntExtention.Goon;
import game.GoonGruntExtention.Grunt;
import game.MapEnhancementExtention.Key;
import game.MoonExpantion.WaterPistol;
import game.MoonExpantion.YugoMaxx;
import game.RocketExpantion.FlyAction;

import java.util.Iterator;
import java.util.List;

public class MoonMap extends GameMap implements IPlanetaryMap {
    private FlyAction flyAction;

    public MoonMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    public void addActorsItems(Player player){
        // Enemies
        YugoMaxx ym = new YugoMaxx();
        this.addActor(ym,1,1);

        Goon goon3 = new Goon("Space Goon", player);
        goon3.addItemToInventory(new Key());
        this.addActor(goon3, 8, 9);

        Grunt grunt2 = new Grunt("Space Grunt", player);
        grunt2.addItemToInventory(new Key());
        this.addActor(grunt2, 1, 6);



        // Water pistol
        this.addItem(new WaterPistol(player,ym,this),4,6);
    }

    @Override
    public int[] getPadCoords() {
        return new int[]{2,1};
    }

    @Override
    public String getName() {
        return "Moon";
    }

    @Override
    public Action getFlyAction() {
        return this.flyAction;
    }

    @Override
    public void setFlyAction(game.RocketExpantion.FlyAction flyAction) {
        this.flyAction = flyAction;
    }

    @Override
    public void killAll() {
        Iterator<Actor> all = this.actorLocations.iterator();
        for (Iterator<Actor> it = all; it.hasNext(); ) {
            this.removeActor(it.next());
        }
    }
}
