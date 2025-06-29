package com.boulderspace3d.ai;

import com.boulderspace3d.data.GameRecord;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * Spécialisation fitness & voisinage pour l’ELO adaptatif.
 */
public class EloOptimizer {
    private static final Random RNG = new Random();

    public static Function<float[], Double> fitnessFunction(
        List<GameRecord> history,
        int currentLevel,
        double targetTime
    ) {
        return chrom -> {
            double score = 0.0;
            for (GameRecord rec : history) {
                if (rec.getLevel() != currentLevel) continue;
                double gapTime  = Math.abs(rec.getSurvivalTime() - targetTime);
                double normTime = Math.max(0, 1.0 - gapTime / targetTime);
                double dodge    = rec.getDodgeRatio();
                double colorHit = rec.getColorTargetScore();
                score += 0.4 * normTime + 0.3 * dodge + 0.3 * colorHit;
            }
            return score;
        };
    }

    public static Function<float[], float[]> neighborFunction() {
        return chrom -> {
            float[] c = chrom.clone();
            int idx    = RNG.nextInt(c.length);
            float delta= (float)(RNG.nextGaussian() * 0.05);
            c[idx]     = Math.max(0f, c[idx] + delta);
            return c;
        };
    }
}