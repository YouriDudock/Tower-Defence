package com.game.visual.scene2d.hud;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.event.EventAction;
import com.game.play.GameManager;

/**
 * The HUD visible to the player
 *
 * @author Youri Dudock
 */
public class GameHud {

    private GameManager manager;

    public GameHud(GameManager manager) {
        this.manager = manager;
    }

    /**
     * Creates the HUD
     */
    public void create() {
        Table table = new Table();
        table.setWidth(manager.getScreen().getRenderEngine().getUIStage().getWidth());
        table.padBottom(150);

        Label cash = new Label("Cash: " + manager.getPlayer().getPoints(),
                manager.getScreen().getRenderEngine().getUISkin()
        );

        manager.getPlayer().getPointsChangedEvent().register(this, new EventAction() {
            @Override
            public void trigger() {
                cash.setText("Cash: " + manager.getPlayer().getPoints());
            }
        });

        Label hitpoints = new Label("Hitpoints: " + manager.getPlayer().getHitpoints(),
                manager.getScreen().getRenderEngine().getUISkin()
        );

        manager.getPlayer().getHitpointsChangedEvent().register(this, new EventAction() {
            @Override
            public void trigger() {
                hitpoints.setText("Hitpoints: " + manager.getPlayer().getHitpoints());
            }
        });


        table.add(cash).pad(30);
        table.add(hitpoints);

        manager.getScreen().getRenderEngine().getUIStage().addActor(table);

    }

}
