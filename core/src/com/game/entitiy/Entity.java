package com.game.entitiy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.game.data.GameRepository;
import com.game.event.events.entity.EntityDeathEvent;
import com.game.event.events.entity.EntityMovementEvent;
import com.game.entitiy.order.EntityOrder;
import com.game.world.map.tiles.MapTile;

import static com.game.util.VectorUtil.degree;

/**
 * An Entity is an active game controlled participant that has specific behavior
 * This abstract class provides the main expected behavior of every Entity, but sub types may implement their own
 *
 * @author Youri Dudock
 */
public abstract class Entity {

    // path to the entity folder
    private final String ENTITY_FOLDER = "entities/";

    // the ID of the entity, as defined in the data storage
    private int ID;

    // the name of the entity
    private String name;

    // the current location of the entity
    private Vector2 location;

    // the sprite get of the entity for rendering
    private Sprite sprite;

    // the name of the sprite file in assets
    private String spriteName;

    // the event for when an entity has moved
    private EntityMovementEvent entityMovementEvent;

    // the event for when the entity has died
    private EntityDeathEvent deathEvent;

    // the speed of the entity
    private float speed;

    // the order that the entity must perform
    private EntityOrder order;

    // width and height of the entity
    private float width, height;

    // if the entity is dead
    private boolean isDead = false;

    // the damage that this entity
    private int damage;

    public Entity(Sprite sprite) {
        entityMovementEvent = new EntityMovementEvent(this);
        this.sprite = sprite;
    }

    public Entity() {
    }

    /**
     * Updates the entity
     */
    public void update() {
        if (order != null) {
            order.execute();

            if (order.hasFinished()) {
                order = null;
            }
        }
    }

    /**
     * Kills this entity
     */
    public void kill() {
        isDead = true;

        if (deathEvent != null) {
            deathEvent.trigger();

            // clear the event because all event subscribers have been notified
            deathEvent.clear();
        }
    }


    /**
     * Rotates the entity by rotating its inner sprite
     *
     * @param degrees the amount of rotation in degrees
     */
    public void rotate(float degrees) {
        if (sprite != null) {
            sprite.setRotation(degrees);
        }
    }

    /**
     * Rotates the entity by rotating its inner sprite
     *
     * @param target entity to rotate to
     */
    public void rotateTowards(Entity target) {
        rotate(degree(getLocation(), target.getLocation()));
    }

    /**
     * Rotates the entity by rotating its inner sprite
     *
     * @param tile the map tile to rotate the entity towards
     */
    public void rotateTowards(MapTile tile) {
        rotate(degree(getLocation(), tile.getLocation()));
    }

    /**
     *
     * @return the folder name of the child type in assets
     */
    public abstract String getSpriteFolderName();

    /**
     * Sets a new order for this entity
     *
     * @param order the new order to follow
     */
    public void setOrder(EntityOrder order) {
        order.setEntity(this);
        this.order = order;
    }


    /**
     * Moves an entity to a new location
     *
     * @param location the location as 2d vector
     */
    public void setLocation(Vector2 location)
    {
        this.location = location;

        // trigger movement event
        if (entityMovementEvent != null)
            entityMovementEvent.trigger();
    }

    /**
     * Moves an entity to a map tile
     *
     * @param location the location as Map Tile
     */
    public void setLocation(MapTile location)
    {
        this.setLocation(location.getLocation().cpy());
    }

    /**
     *
     * @return the current location of the entity
     */
    public Vector2 getLocation() {
        return location;
    }


    /**
     * Gives an get of the entity sprite
     * Also creates and sets the sprite get if it wasn't set before and sprite name is known
     *
     * @return get of the entity sprite
     */
    public Sprite getSprite() {
        if (sprite == null) {
            loadSpritesConfig();
        }

        return sprite;
    }

    /**
     * Sets a sprite
     *
     * @param sprite sprite to set
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public EntityMovementEvent getEntityMovementEvent() {
        return entityMovementEvent;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    /**
     * Sets the size of the entity sprite
     *
     * @param width sprite width in units
     * @param height sprite height in units
     */
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }


    public void setSpriteName(String spriteName) {
        this.spriteName = spriteName;
    }

    public String getSpriteName() {
        return spriteName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Loads the sprite and its config
     */
    private void loadSpritesConfig() {
        if (spriteName != null) {
            sprite = new Sprite(new Texture(GameRepository.SPRITES_PATH + ENTITY_FOLDER + getSpriteFolderName() + "/" + spriteName));
        }

        sprite.setSize(width, height);
        sprite.setOriginCenter();
    }

    public boolean isDead() {
        return isDead;
    }


    public EntityDeathEvent getDeathEvent() {
        if (deathEvent == null) {
            deathEvent = new EntityDeathEvent();
        }

        return deathEvent;
    }

    public void setDeathEvent(EntityDeathEvent deathEvent) {
        this.deathEvent = deathEvent;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
