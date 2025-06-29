package com.boulderspace3d.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.boulderspace3d.input.InputController;
import com.boulderspace3d.di.Injection;

/**
 * Vaisseau du joueur avec autorégénération de santé.
 */
public class PlayerShip {
    private final Texture texture;
    private Vector3 position;
    private float yaw, pitch, roll;
    private final float regenRate    = 0.2f;
    private final float regenCooldown= 5f;
    private float health = 1f, timeSinceHit = 0f;

    public PlayerShip() {
        texture  = new Texture("assets/playerShip/hull_panels_albedo.png");
        position = new Vector3(0,0,0);
    }

    public void update(InputController input, float dt) {
        yaw   += input.getYawInput()   * dt * 100f;
        pitch += input.getPitchInput() * dt * 100f;
        roll  += input.getRollInput()  * dt * 100f;

        position.x += input.getStrafeInput()* dt * MathUtils.cosDeg(yaw);
        position.z += input.getForwardInput()* dt * MathUtils.sinDeg(yaw);

        if (timeSinceHit >= regenCooldown) health = Math.min(1f, health + regenRate);
        else                                timeSinceHit += dt;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void fireLeft()  { Injection.provideShootingSystem().shoot(position.x, position.y); }
    public void fireRight() { Injection.provideShootingSystem().shoot(position.x, position.y); }

    public void takeDamage(float dmg) { health = Math.max(0f, health - dmg); timeSinceHit = 0f; }
    public float getHealth()          { return health; }
    public int getLevel()             { return 1; }
    public Vector3 getPosition()      { return position; }
    public void dispose()             { texture.dispose(); }
}