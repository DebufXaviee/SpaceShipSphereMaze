package com.boulderspace3d.ai;

import com.boulderspace3d.enemies.EnemyManager;

/**
 * Gère la génération de miniboss et big boss.
 */
public class ProceduralBossGenerator {
    public void generate(EnemyManager manager, boolean isBoss) {
        if (isBoss)    manager.spawnBoss();
        else           manager.spawnMiniboss();
    }
}