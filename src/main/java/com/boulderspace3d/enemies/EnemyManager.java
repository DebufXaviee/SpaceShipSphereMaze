package com.boulderspace3d.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère la génération adaptative des ennemis.
 */
public class EnemyManager {
    private final List<EnemyAgent> enemies = new ArrayList<>();
    private float spawnRateBasic    = 0.1f;
    private float spawnRateMiniboss = 0.3f;
    private float spawnRateBoss     = 0.5f;

    public void updateEloFromParameters(float[] rates) {
        spawnRateBasic    = rates[0];
        spawnRateMiniboss = rates[1];
        spawnRateBoss     = rates[2];
    }

    public float[] getSpawnRates() {
        return new float[]{ spawnRateBasic, spawnRateMiniboss, spawnRateBoss };
    }

    public void spawnBasic()    { enemies.add(new EnemyAgent(spawnRateBasic)); }
    public void spawnMiniboss() { enemies.add(new EnemyAgent(spawnRateMiniboss)); }
    public void spawnBoss()     { enemies.add(new EnemyAgent(spawnRateBoss)); }

    public void updateAll(float dt) { /* IA multi-agent & A* ici */ }
    public void renderAll(SpriteBatch batch) {
        for (EnemyAgent e : enemies) e.render(batch);
    }
    public void disposeAll() {
        for (EnemyAgent e : enemies) e.dispose();
        enemies.clear();
    }
}