package com.game.visual.rendering.jobs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.entitiy.entities.NPCEntity;
import com.game.entitiy.entities.TowerEntity;
import com.game.visual.rendering.RenderJob;
import com.game.world.GameWorld;

/**
 * Deals with rendering world objects
 *
 * @author Youri Dudock
 */
public class WorldRenderingJob extends RenderJob {

    // the world that this job is set to
    private GameWorld world;

    @Override
    public void render() {
        if (world == null) {
            return;
        }

        //@TODO avoid duplicated code by having two separated loops

        // loop through world towers
        for (TowerEntity tower : world.getTowers()) {
            tower.update();

            // calculate drawing positions based on the size of the entity
            float drawingX = tower.getLocation().x - ((tower.getWidth() - 1) / 2);
            float drawingY = tower.getLocation().y - ((tower.getHeight() - 1) / 2);

            // set new sprite pos
            Sprite sprite = tower.getSprite();
            sprite.setPosition(drawingX, drawingY);

            // draw sprite
            tower.getSprite().draw(spriteBatch);

        }


        // loop through world npcs
        for (NPCEntity npc : world.getNpcs()) {
            // update the entity
            npc.update();

            // calculate drawing positions based on the size of the entity
            float drawingX = npc.getLocation().x - ((npc.getWidth() - 1) / 2);
            float drawingY = npc.getLocation().y - ((npc.getHeight() - 1) / 2);

            // set new sprite pos
            Sprite sprite = npc.getSprite();
            sprite.setPosition(drawingX, drawingY);

            // draw sprite
            npc.getSprite().draw(spriteBatch);

        }

    }

    public WorldRenderingJob(SpriteBatch batch, GameWorld world) {
        super(batch);
        this.world = world;
    }
}
