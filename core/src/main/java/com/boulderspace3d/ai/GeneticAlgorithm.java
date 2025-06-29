package com.boulderspace3d.ai;
import java.util.*;import java.util.function.*;
public class GeneticAlgorithm<T>{
    private final Supplier<List<T>> initialPopulation;
    private final Function<T,Double> fitnessFunction;
    private final BiFunction<T,T,T> crossoverFunction;
    private final Function<T,T> mutateFunction;
    private final int populationSize; private final Random rng=new Random();
    public GeneticAlgorithm(Supplier<List<T>> initialPopulation,Function<T,Double> fitnessFunction,BiFunction<T,T,T> crossoverFunction,Function<T,T> mutateFunction,int populationSize){
        this.initialPopulation=initialPopulation;this.fitnessFunction=fitnessFunction;this.crossoverFunction=crossoverFunction;this.mutateFunction=mutateFunction;this.populationSize=populationSize;
    }
    public T evolve(int generations){
        List<T> pop=new ArrayList<>(initialPopulation.get());
        for(int g=0;g<generations;g++){
            pop.sort(Comparator.comparingDouble(fitnessFunction::apply).reversed());
            List<T> next=new ArrayList<>(pop.subList(0,populationSize/2));
            while(next.size()<populationSize){
                T a=next.get(rng.nextInt(next.size())),b=next.get(rng.nextInt(next.size()));
                next.add(mutateFunction.apply(crossoverFunction.apply(a,b)));
            }
            pop=next;
        }
        return Collections.max(pop,Comparator.comparingDouble(fitnessFunction::apply));
    }
}
