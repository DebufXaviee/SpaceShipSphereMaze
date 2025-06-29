package com.boulderspace3d.ai;
import com.boulderspace3d.data.GameRecord;import java.util.*;import java.util.function.*;
public class EloOptimizer{
    private static final Random RNG=new Random();
    public static Function<float[],Double> fitnessFunction(List<GameRecord> history,int currentLevel,double targetTime){
        return chrom->{double score=0;for(GameRecord rec:history){
            if(rec.getLevel()!=currentLevel)continue;
            double gap=Math.abs(rec.getSurvivalTime()-targetTime);
            double norm=Math.max(0,1-gap/targetTime);
            score+=0.4*norm+0.3*rec.getDodgeRatio()+0.3*rec.getColorTargetScore();
        }return score;};
    }
    public static Function<float[],float[]> neighborFunction(){
        return chrom->{float[] c=chrom.clone();int idx=RNG.nextInt(c.length);float d=(float)(RNG.nextGaussian()*0.05);c[idx]=Math.max(0f,c[idx]+d);return c;};
    }
}
