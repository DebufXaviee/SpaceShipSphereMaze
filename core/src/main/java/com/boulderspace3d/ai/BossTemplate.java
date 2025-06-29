package com.boulderspace3d.ai;
import com.badlogic.gdx.graphics.Color;import com.boulderspace3d.data.DatabaseHelper;import com.boulderspace3d.data.GameRecord;import com.boulderspace3d.enemies.EnemyManager;import java.util.*;import java.util.function.*;
public class ProceduralBossGenerator {
    private final List<GameRecord> history; private final int currentLevel;
    public ProceduralBossGenerator(List<GameRecord> history,int currentLevel){this.history=history;this.currentLevel=currentLevel;}
    public BossTemplate generate(boolean isBigBoss){
        float[] base={0.5f,1f,0.5f,0.5f,1f}; if(isBigBoss)base[4]=2f;
        Function<float[],Double> fitness=chrom->{double diff=chrom[1]*chrom[4]*(1+chrom[3]);return diff;};
        Function<float[],float[]> mutate=chrom->{float[] c=chrom.clone();for(int i=0;i<c.length;i++){c[i]+=Math.random()*0.2-0.1;c[i]=Math.max(0f,Math.min(1f,c[i]));}return c;};
        BiFunction<float[],float[],float[]> crossover=(a,b)->{float[] c=new float[5];for(int i=0;i<5;i++)c[i]=Math.random()<0.5?a[i]:b[i];return c;};
        GeneticAlgorithm<float[]> ga=new GeneticAlgorithm<>(()->IntStream.range(0,20).mapToObj(i->mutate.apply(base)).toList(),fitness,crossover,mutate,20);
        float[] best=ga.evolve(isBigBoss?50:20);
        Color color=new Color().fromHsv(best[0]*360,0.8f,0.9f);
        float size=0.5f+best[1]*1.5f;int cannon=1+Math.round(best[2]*4);float aggr=best[3];float health=0.5f+best[4]*2.5f;
        return new BossTemplate(color,size,cannon,aggr,health);
    }
    public void spawn(EnemyManager mgr,boolean isBigBoss){
        BossTemplate tpl=generate(isBigBoss);
        mgr.spawnBossWithTemplate(tpl);
    }
}