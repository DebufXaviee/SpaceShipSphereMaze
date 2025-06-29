package com.boulderspace3d.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** Représente un vaisseau ennemi. */
public class EnemyAgent {
    private final Texture texture;
    private float x, y, health;

    public EnemyAgent(float h) {
        texture = new Texture("assets/enemyShip/dark_alloy_albedo.png");
        this.health = h;
        this.x = 100; this.y = 100;
    }

    public void render(SpriteBatch batch) { batch.draw(texture, x, y); }
    public void takeDamage(float d)        { health = Math.max(0f, health - d); }
    public float getHealth()               { return health; }
    public void dispose()                  { texture.dispose(); }
}