package com.boulderspace3d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.boulderspace3d.player.PlayerShip;

/**
 * Minimap avec fog of war (70% caché, 30% révélé).
 */
public class Minimap {
    private final ShapeRenderer shape;
    private final float mapRadius;
    private final float revealRadius;
    private final float centerX, centerY;
    private final PlayerShip player;

    public Minimap(PlayerShip player, float centerX, float centerY, float mapRadius) {
        this.player       = player;
        this.centerX      = centerX;
        this.centerY      = centerY;
        this.mapRadius    = mapRadius;
        this.revealRadius = (float)Math.sqrt(0.3) * mapRadius;
        this.shape        = new ShapeRenderer();
    }

    public void render(SpriteBatch batch) {
        shape.setProjectionMatrix(batch.getProjectionMatrix());
        shape.begin(ShapeRenderer.ShapeType.Filled);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        shape.setColor(0,0,0,0.7f);
        shape.circle(centerX, centerY, mapRadius);

        Gdx.gl.glBlendFunc(GL20.GL_ZERO, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shape.setColor(0,0,0,0);
        Vector3 pos = player.getPosition();
        float vx = centerX + (pos.x / 100f) * mapRadius;
        float vy = centerY + (pos.z / 100f) * mapRadius;
        shape.circle(vx, vy, revealRadius);

        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glDisable(GL20.GL_BLEND);
        shape.end();
    }

    public void dispose() {
        shape.dispose();
    }
}