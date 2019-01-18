package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.ants.something.a.AllData;
import com.mygdx.game.ants.something.animated.event.a.AnimatedEventSomething;
import com.mygdx.game.ants.something.animated.event.npc.a.MyPath;
import com.mygdx.game.ants.something.animated.event.npc.a.Npc;
import com.mygdx.game.enums.entity.EntityAnimation;
import com.mygdx.game.enums.entity.EntityTex;
import com.mygdx.game.world.Clock;
import com.mygdx.game.world.CoreTileData;
import com.mygdx.game.world.DeadPool;
import com.mygdx.game.world.Factory;
import com.mygdx.game.world.Job;
import com.mygdx.game.world.MyPathFinder;
import com.mygdx.game.world.WorldManager;
import com.mygdx.game.world.WorldPositionManager;
import com.mygdx.game.world.WorldResAnimManager;
import com.mygdx.game.world.WorldResTexRegManager;

import java.util.List;


public class Spawner{
//    private boolean isBuilding;
//    private boolean isDestroying;
    private boolean canSpawn;
    private boolean canDespawn;

    private final Vector2 nullVector = new Vector2(-1000,-1000);

    private List<Npc> npcsOnDuty;

    private Job job;
    private Vector1 destination;
    private Vector2[] vvv;
    private Npc[] pumps;
    public void setJob(Clock clock){
        job = new Job();
        job.hrs = 1;
        job.task = this.getClass().getName() + ": Job Done";
        job.vvv = this.vvv;
        job.npcs = this.pumps;
        job.endless = true;
        clock.shedule(job);
    }

    private WorldPositionManager posManager;
    private Group worldGroup;
    private WorldResTexRegManager texRegManager;
    private WorldResAnimManager animManager;
    private AllData allData;
    public DeadPool deadPool;
    private Collider collider;
    private CoreTileData coreTileData;

    // for debug only, posManager sets, Gdx.app.debug gets
    private int x;
    private int y;
    public Spawner(Group worldGroup,
                   WorldResTexRegManager texRegManager,
                   WorldResAnimManager animManager,
                   AllData allData,
                   DeadPool deadPool,
                   Collider collider,
                   CoreTileData coreTileData,
                   int tileSize,
                   int mapWidth,
                   int mapHeight) {
        this.worldGroup = worldGroup;
        this.texRegManager = texRegManager;
        this.animManager = animManager;
        this.allData = allData;
        this.deadPool = deadPool;
        this.collider = collider;
    }
    private void spawn(EntityAnimation entityAnim){
        if (true){
            if (canSpawn){
                // worldGroup.swapActor()
                // todo make ghost always on foreground
                // todo add x y, id >> convert entity
                Gdx.app.log("Factory", "Spawning NPC at "+x+"/"+y+" with EID:"+entityAnim.name());
                Gdx.app.debug("Debug: Factory", "build() method *START* ------------"+
                        " ------------ ------------ ------------ ------------ ------------");
                Gdx.app.debug("Debug: Factory", "arg entityID="+entityAnim.getID());
                Gdx.app.debug("Debug: Factory", "arg entity="+entityAnim);
                Gdx.app.debug("Debug: Factory", "x/y="+x+"/"+y);
                Gdx.app.debug("Debug: Factory", "OnDuty.size()="+npcsOnDuty.size());
                Gdx.app.debug("Debug: Factory", "");
                // tear off DeadPool , and store in ... from ...onDuty
                //todo BOOOO its not a tavern
                Npc pumpkin = deadPool.createNpc(entityAnim);
                npcsOnDuty.add(pumpkin);
                pumpkin.setMyIndex(npcsOnDuty.size()-1);
                // Object property init
                pumpkin.set01EntityTex(EntityTex.Temp);
                pumpkin.set02EntityAnim(entityAnim);
                pumpkin.set11TexReg(texRegManager);
                pumpkin.set21Bounds();
                pumpkin.set22Borders();
                pumpkin.set23Range();
                pumpkin.set31World(worldGroup);
                pumpkin.setVisible(true);
                pumpkin.setPosition(posManager.getPosX(), posManager.getPosY());
                pumpkin.setName("Tavern: "+entityAnim.name());
                pumpkin.set09Data(allData.getSomethingDataByID(pumpkin.getEntityID()));
                // inform coreTileData about data update
//                coreTileData.buildingHere(posManager.getTileX(), posManager.getTileY());
                // add collider
//                if (tavern.getData().isCollider)
//                    collider.add(tavern.getBorder());
            }
        }
    }
    private void despawn(){

    }
    public void spawnOnEvent(EntityAnimation entity, Vector2 pos){
        this.canSpawn = true;
        posManager.update(pos);
        x = posManager.getPosX();
        y = posManager.getPosY();
        this.spawn(entity);
        this.canSpawn = false;

    }
    public void despawnOnEvent(){
//        this.canSpawn = true;
//        posManager.update(pos);
//        this.canSpawn = false;
    }


    public void set33Destination(int x, int y){
        destination = new Vector1(x/32,y/32);

    }
    public void set34Path(MyPathFinder pathFinder,Npc temp){
        pathFinder.calculate(temp, new Vector1(2,10)); //todo this>>npc
        Vector2[] vvv = pathFinder.getPath5();
        this.vvv = vvv;
        pumps = new Npc[30];
        for (int i = 0; i < pumps.length; i++) {
            pumps[i] = new Npc();
            pumps[i].set01EntityTex(EntityTex.Temp);
            pumps[i].set02EntityAnim(EntityAnimation.PUMPKIN);
            pumps[i].set11TexReg(texRegManager);
            pumps[i].set12Anim(animManager);
            pumps[i].set21Bounds();
            pumps[i].set22Borders(0,0);
            pumps[i].set23Range();
            pumps[i].set31World(worldGroup);
            pumps[i].set32Position(2*32,2*32);
        }

    }
}
