package com.boulderspace3d.ai;

import java.util.Random;
import java.util.function.Function;

/**
 * Recuit simulé générique.
 */
public class SimulatedAnnealingOptimizer<T> {
    private final Function<T, Double> fitness;
    private final Function<T, T> neighborFunction;
    private final double initTemp;
    private final double coolingRate;
    private final Random rng = new Random();

    public SimulatedAnnealingOptimizer(
        Function<T, Double> fitness,
        Function<T, T> neighborFunction,
        double initTemp,
        double coolingRate
    ) {
        this.fitness         = fitness;
        this.neighborFunction = neighborFunction;
        this.initTemp        = initTemp;
        this.coolingRate     = coolingRate;
    }

    public T optimize(T initial, int iterations) {
        T best      = initial;
        double bestFit = fitness.apply(best);
        T current   = initial;
        double temp = initTemp;

        for (int i = 0; i < iterations; i++) {
            T next    = neighborFunction.apply(current);
            double fitNext = fitness.apply(next);
            if (fitNext > bestFit) {
                best      = next;
                bestFit   = fitNext;
            }
            double delta = fitNext - fitness.apply(current);
            if (delta > 0 || Math.exp(delta / temp) > rng.nextDouble()) {
                current = next;
            }
            temp *= 1 - coolingRate;
        }
        return best;
    }
}