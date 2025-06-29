package com.boulderspace3d.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** Système de tir en rayons laser. */
public class ShootingSystem {
    private final Texture crosshair;

    public ShootingSystem(String path) {
        crosshair = new Texture(path);
    }

    public void shoot(float x, float y) {
        // Logique d’instanciation du beam
    }

    public void render(SpriteBatch batch) {
        // Dessin du crosshair ou beam
    }

    public void dispose() {
        crosshair.dispose();
    }
}