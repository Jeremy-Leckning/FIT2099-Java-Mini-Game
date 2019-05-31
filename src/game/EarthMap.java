package game;

import edu.monash.fit2099.engine.*;
import game.GoonGruntExtention.Goon;
import game.GoonGruntExtention.Grunt;
import game.MapEnhancementExtention.Key;
import game.MoonExpantion.SpaceSuit;
import game.NinjaExpantion.Ninja;
import game.RocketExpantion.*;

import java.util.Iterator;
import java.util.List;

/**
 * Earth Map
 */
class EarthMap extends GameMap implements IPlanetaryMap {
    private FlyAction flyAction;
    private final int[] padCoords = new int[] {15,8};

    /**
     * Creates the Earth
     * @param groundFactory GF for earth
     * @param lines model
     */
    EarthMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    /**
     * Sets all Actors and Items on Map, including keys to enemies inventory
     * @param player player
     */
    void addActorsItems(Player player){
        // NPCs
        Q q = new Q(100);
        this.addActor(q, 6, 6);

        // Enemies
        Ninja ninja1 = new Ninja("Ninja",player);
        ninja1.addItemToInventory(new Key());
        this.addActor(ninja1,0,1);

        Goon goon1 = new Goon("Senior Goon",player);
        goon1.addItemToInventory(new Key());
        this.addActor(goon1, 0, 0);

        Goon goon2 = new Goon("Junior Goon",player);
        goon2.addItemToInventory(new Key());
        this.addActor(goon2, 10, 10);

        Grunt grunt1 = new Grunt("Grunt",player);
        grunt1.addItemToInventory(new Key());
        this.addActor(grunt1,12,9);

        DoctorMaybe DrMaybe = new DoctorMaybe(player);
        this.addActor(DrMaybe,15,3);

        // Add rocket plans inside a locked room.
        RocketPlans plans = new RocketPlans();
        this.addItem(plans, 5, 2);

        // Add space suit
        SpaceSuit spaceSuit = new SpaceSuit();
        this.addItem(spaceSuit, 21, 5);




    }

    /**
     * Kills all actors on map to end game
     */
    public void killAll(){
        Iterator<Actor> all = this.actorLocations.iterator();
        while (all.hasNext()){
            this.removeActor(all.next());
        }
    }

    @Override
    public int[] getPadCoords() {
        return this.padCoords;
    }

    @Override
    public String getName() {
        return "Earth";
    }

    @Override
    public Action getFlyAction() {
        return this.flyAction;
    }

    @Override
    public void setFlyAction(game.RocketExpantion.FlyAction flyAction) {
        this.flyAction = flyAction;
    }
}
