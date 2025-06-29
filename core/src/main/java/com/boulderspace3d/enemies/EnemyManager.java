package com.boulderspace3d.enemies;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;import java.util.*;
public class EnemyManager {
    private final List<EnemyAgent> enemies=new ArrayList<>();
    private float spawnBasicRate=0.1f, spawnMiniRate=0.3f, spawnBossRate=0.5f;
    public void updateEloFromParameters(float[] r){spawnBasicRate=r[0];spawnMiniRate=r[1];spawnBossRate=r[2];}
    public float[] getSpawnRates(){return new float[]{spawnBasicRate,spawnMiniRate,spawnBossRate};}
    public void spawnBasic(){enemies.add(new EnemyAgent(spawnBasicRate));}
    public void spawnMiniboss(){enemies.add(new EnemyAgent(spawnMiniRate));}
    public void spawnBoss(){enemies.add(new EnemyAgent(spawnBossRate));}
    public void spawnBossWithTemplate(BossTemplate t){enemies.add(new EnemyAgentBoss(t));}
    public void updateAll(float dt){ for(EnemyAgent e: enemies) e.update(dt); }
    public void renderAll(SpriteBatch b){ for(EnemyAgent e: enemies) e.render(b); }
    public void disposeAll(){ for(EnemyAgent e: enemies) e.dispose(); enemies.clear(); }
}