package com.boulderspace3d.ai;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Framework génétique générique.
 */
public class GeneticAlgorithm<T> {
    private final Supplier<List<T>> initialPopulation;
    private final Function<T, Double> fitnessFunction;
    private final BiFunction<T, T, T> crossoverFunction;
    private final Function<T, T> mutateFunction;
    private final int populationSize;
    private final Random rng = new Random();

    public GeneticAlgorithm(
        Supplier<List<T>> initialPopulation,
        Function<T, Double> fitnessFunction,
        BiFunction<T, T, T> crossoverFunction,
        Function<T, T> mutateFunction,
        int populationSize
    ) {
        this.initialPopulation = initialPopulation;
        this.fitnessFunction   = fitnessFunction;
        this.crossoverFunction = crossoverFunction;
        this.mutateFunction    = mutateFunction;
        this.populationSize    = populationSize;
    }

    public T evolve(int generations) {
        List<T> population = new ArrayList<>(initialPopulation.get());
        for (int gen = 0; gen < generations; gen++) {
            population.sort(Comparator.comparingDouble(fitnessFunction::apply).reversed());
            List<T> next = new ArrayList<>(population.subList(0, populationSize/2));
            while (next.size() < populationSize) {
                T a = next.get(rng.nextInt(next.size()));
                T b = next.get(rng.nextInt(next.size()));
                T child = crossoverFunction.apply(a, b);
                next.add(mutateFunction.apply(child));
            }
            population = next;
        }
        return Collections.max(population, Comparator.comparingDouble(fitnessFunction::apply));
    }
}