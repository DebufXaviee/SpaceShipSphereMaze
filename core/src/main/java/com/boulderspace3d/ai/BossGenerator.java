package com.boulderspace3d.ai;
import com.boulderspace3d.enemies.EnemyManager;
public class BossGenerator {
    public void generate(EnemyManager mgr,boolean isBoss){ if(isBoss)mgr.spawnBoss(); else mgr.spawnMiniboss(); }
}