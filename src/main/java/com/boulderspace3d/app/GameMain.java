package com.boulderspace3d.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.boulderspace3d.input.InputController;
import com.boulderspace3d.player.PlayerShip;
import com.boulderspace3d.enemies.EnemyManager;
import com.boulderspace3d.ui.HUD;
import com.boulderspace3d.ui.Minimap;

/** Boucle principale du jeu. */
public class GameMain extends ApplicationAdapter {
    private SpriteBatch batch;
    private ScreenViewport viewport;
    private InputController input;
    private PlayerShip player;
    private EnemyManager enemyManager;
    private HUD hud;
    private Minimap minimap;

    @Override public void create() {
        batch        = new SpriteBatch();
        viewport     = new ScreenViewport();
        input        = new InputController(viewport);
        player       = Injection.providePlayerShip();
        enemyManager = Injection.provideEnemyManager();
        hud          = new HUD(player, enemyManager);
        minimap      = new Minimap(player,
                           Gdx.graphics.getWidth() - 120, 120, 100);
    }

    @Override public void render() {
        viewport.apply();
        float dt = Gdx.graphics.getDeltaTime();
        player.update(input, dt);
        enemyManager.updateAll(dt);
        hud.updateElo();

        Gdx.gl.glClearColor(0,0,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        player.render(batch);
        enemyManager.renderAll(batch);
        hud.render(batch);
        minimap.render(batch);
        batch.end();
    }

    @Override public void dispose() {
        batch.dispose();
        player.dispose();
        enemyManager.disposeAll();
        hud.dispose();
    }
}