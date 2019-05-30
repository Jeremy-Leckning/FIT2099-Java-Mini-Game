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

/**
 * Moon Map
 */
class MoonMap extends GameMap implements IPlanetaryMap {
    private FlyAction flyAction;
    private final int[] padCoords = new int[]{2,1};

    /**
     * Creates the moon
     * @param groundFactory GF for moon
     * @param lines model for mooon
     */
    MoonMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    /**
     * Sets all Actors and Items on Map, including keys to enemies inventory
     * @param player player
     */
    void addActorsItems(Player player){
        // Enemies
        YugoMaxx ym = new YugoMaxx();
        this.addActor(ym,13,9);

        Goon goon = new Goon("Space Goon", player);
        goon.addItemToInventory(new Key());
        this.addActor(goon, 8, 9);

        Grunt grunt = new Grunt("Space Grunt", player);
        grunt.addItemToInventory(new Key());
        this.addActor(grunt, 1, 6);

        // Water pistol
        this.addItem(new WaterPistol(player,ym,this),9,9);
    }

    @Override
    public int[] getPadCoords() {
        return this.padCoords;
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
        while (all.hasNext()){
            this.removeActor(all.next());
        }
    }
}
