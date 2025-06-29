package com.boulderspace3d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boulderspace3d.data.DatabaseHelper;
import com.boulderspace3d.data.GameRecord;
import com.boulderspace3d.enemies.EnemyManager;
import com.boulderspace3d.player.PlayerShip;
import com.boulderspace3d.ai.GeneticAlgorithm;
import com.boulderspace3d.ai.SimulatedAnnealingOptimizer;
import com.boulderspace3d.ai.EloOptimizer;

import java.util.List;

/**
 * Affiche l’ELO adaptatif et les barres de vie.
 */
public class HUD {
    private final PlayerShip player;
    private final EnemyManager manager;
    private final ShapeRenderer shape;

    public HUD(PlayerShip p, EnemyManager m) {
        player  = p;
        manager = m;
        shape   = new ShapeRenderer();
    }

    public void updateElo() {
        List<GameRecord> history = DatabaseHelper.fetchAll();
        float[] oldRates          = manager.getSpawnRates();

        GeneticAlgorithm<float[]> ga = new GeneticAlgorithm<>(
            () -> List.of(oldRates),
            EloOptimizer.fitnessFunction(history, player.getLevel(), player.getLevel()*10),
            (a,b) -> a,
            EloOptimizer.neighborFunction(),
            20
        );
        float[] gaResult = ga.evolve(30);

        SimulatedAnnealingOptimizer<float[]> sa = new SimulatedAnnealingOptimizer<>(
            EloOptimizer.fitnessFunction(history, player.getLevel(), player.getLevel()*10),
            EloOptimizer.neighborFunction(),
            1.0, 0.03
        );
        float[] saResult = sa.optimize(gaResult, 50);

        double avg    = history.stream()
                        .filter(r->r.getLevel()==player.getLevel())
                        .mapToDouble(GameRecord::getSurvivalTime)
                        .average().orElse(0);
        double target = player.getLevel()*10;
        float[] finalRates = new float[3];
        for (int i=0;i<3;i++) {
            finalRates[i] = (float)(avg>target ? saResult[i]*1.1 : saResult[i]*0.8);
        }
        manager.updateEloFromParameters(finalRates);
    }

    public void render(SpriteBatch batch) {
        float x = 10, y = Gdx.graphics.getHeight() - 20;
        float w = 200 * player.getHealth();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(1,0,0,1);
        shape.rect(x, y, w, 10);
        shape.end();
    }

    public void dispose() {
        shape.dispose();
    }
}