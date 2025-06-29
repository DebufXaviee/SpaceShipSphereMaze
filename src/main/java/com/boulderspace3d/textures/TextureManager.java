package com.boulderspace3d.textures;

/**
 * Gère les textures et fournit des couleurs aléatoires.
 */
public class TextureManager {
    public float[] getRandomColorForEntity(String type) {
        return new float[]{
            (float)Math.random(),
            (float)Math.random(),
            (float)Math.random(),
            1.0f
        };
    }

    public void dispose() {
        // libérer les ressources si besoin
    }
}