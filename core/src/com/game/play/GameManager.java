package com.game.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.game.data.GameRepository;
import com.game.entitiy.entities.NPCEntity;
import com.game.entitiy.life.EntityFactory;
import com.game.entitiy.entities.TowerEntity;
import com.game.entitiy.order.orders.TowerAttackAnyNPCOrder;
import com.game.event.EventAction;
import com.game.play.wave.WaveManager;
import com.game.player.Player;
import com.game.visual.projectile.ProjectileSpawner;
import com.game.world.GameWorld;

/**
 * This manager manages the overall active game
 *
 * @author Youri Dudock
 */
public class GameManager implements InputProcessor {

    // the screen that the game is on
    private PlayScreen screen;

    // the human that plays this game
    private Player player;

    // instance of the world
    private GameWorld world;

    // instance of the wave manager
    private WaveManager waveManager;

    // instance of the entity factory
    private EntityFactory factory;

    // instance of a projectile spawner
    private ProjectileSpawner projectileSpawner;

    public GameManager(PlayScreen screen) {
        this.screen = screen;
        factory = new EntityFactory();
        player = new Player();
    }

    public void start() {
        // @TODO input processor handler in a new class
        // set input processor
        Gdx.input.setInputProcessor(this);

        // sets up the game world
        setupWorld();

        // setup projectile
        setupProjectiles();

        // sets up the camera
        setupCamera();

        // creates a wave handler
        waveManager = new WaveManager(this);

        // start running NPC waves
        waveManager.loadWaves();

        // start a wave
        waveManager.startWave();
    }

    /**
     * Gets called on every frame
     */
    public void update() {

    }

    /**
     * Sets up the world
     */
    private void setupWorld() {
        // load the world and map
        world = new GameWorld();
        world.setMap(GameRepository.get().getMaps().get(0));

        // signals rendering engine to start map and world rendering
        screen.getRenderEngine().startMapRendering(world);
        screen.getRenderEngine().startWorldRendering(world);

    }

    /**
     * Sets up the camera
     */
    private void setupCamera() {
        screen.getCamera().fullscreen();
    }

    /**
     * Sets up projectile
     */
    private void setupProjectiles() {
        // setup a new projectile manager
        projectileSpawner = new ProjectileSpawner();

        // start rendering projectile
        screen.getRenderEngine().startProjectileRendering(projectileSpawner);
    }

    public Player getPlayer() {
        return player;
    }

    public GameWorld getWorld() {
        return world;
    }

    public PlayScreen getScreen() {
        return screen;
    }

    /**
     *
     * @param npc the npc that has died
     *
     * @return the action event of when a npc has died
     */
    public EventAction getNPCDeathActionEvent(NPCEntity npc) {
        return new EventAction() {
            @Override
            public void trigger() {
                // add points for the player
                player.addPoints(npc.getKillValue());

                System.out.println("player points: " + player.getPoints());
            }

        };
    }

    /**
     *
     * @param npc npc that reached the end
     *
     * @return the action event when a npc has reached the end of the map
     */
    public EventAction getNPCReachedEndActionEvent(NPCEntity npc) {
        return new EventAction() {
            @Override
            public void trigger() {
                // kill the npc
                npc.kill();

                // remove hitpoints from the player
                player.hit(npc.getDamage());
            }

        };
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // @TODO put this in a tower placement class, this is just test func for now

        Vector3 worldCoordinates = new Vector3(screenX, screenY,0);
        screen.getGame().getCamera().get().unproject(worldCoordinates);

        double floorx = Math.floor(worldCoordinates.x);
        double floory = Math.floor(worldCoordinates.y);

        Vector2 mouseLoc = new Vector2((float) floorx, (float) floory);

        TowerEntity entity = factory.createTower(0);
        entity.setLocation(mouseLoc);
        getWorld().addEntity(entity);

        entity.setOrder(new TowerAttackAnyNPCOrder(getWorld(), projectileSpawner));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }




}
