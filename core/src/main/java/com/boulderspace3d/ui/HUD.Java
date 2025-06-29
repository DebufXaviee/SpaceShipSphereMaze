package com.boulderspace3d.ui;
import com.badlogic.gdx.*;import com.badlogic.gdx.graphics.glutils.ShapeRenderer;import com.badlogic.gdx.graphics.g2d.SpriteBatch;import com.boulderspace3d.data.*;import com.boulderspace3d.enemies.EnemyManager;import com.boulderspace3d.player.PlayerShip;import com.boulderspace3d.ai.*;
import java.util.*;
public class HUD {
    private final PlayerShip player; private final EnemyManager manager; private final ShapeRenderer shape;
    public HUD(PlayerShip p,EnemyManager m){player=p;manager=m;shape=new ShapeRenderer();}
    public void updateElo(){
        List<GameRecord> h=DatabaseHelper.fetchAll();float[] old=manager.getSpawnRates();
        GeneticAlgorithm<float[]> ga=new GeneticAlgorithm<>(()->List.of(old),EloOptimizer.fitnessFunction(h,player.getLevel(),player.getLevel()*10),(a,b)->a,EloOptimizer.neighborFunction(),20);
        float[] gaR=ga.evolve(30);
        SimulatedAnnealingOptimizer<float[]> sa=new SimulatedAnnealingOptimizer<>(EloOptimizer.fitnessFunction(h,player.getLevel(),player.getLevel()*10),EloOptimizer.neighborFunction(),1.0,0.03);
        float[] saR=sa.optimize(gaR,50);
        double avg=h.stream().filter(r->r.getLevel()==player.getLevel()).mapToDouble(GameRecord::getSurvivalTime).average().orElse(0);
        double tgt=player.getLevel()*10;float[] fr=new float[3];
        for(int i=0;i<3;i++)fr[i]=(float)(avg>tgt?saR[i]*1.1:saR[i]*0.8);
        manager.updateEloFromParameters(fr);
    }
    public void render(SpriteBatch b){
        float x=10,y=Gdx.graphics.getHeight()-20,w=200*player.getHealth();
        shape.begin(ShapeRenderer.ShapeType.Filled);shape.setColor(1,0,0,1);shape.rect(x,y,w,10);shape.end();
    }
    public void dispose(){shape.dispose();}
}
