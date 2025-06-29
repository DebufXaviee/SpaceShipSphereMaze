package com.boulderspace3d.di;
import com.boulderspace3d.textures.TextureManager;
import com.boulderspace3d.weapons.ShootingSystem;
import com.boulderspace3d.enemies.EnemyManager;
import com.boulderspace3d.player.PlayerShip;
import com.boulderspace3d.ai.ProceduralBossGenerator;
public class Injection {
    private static TextureManager textureManager;
    private static ShootingSystem shootingSystem;
    private static EnemyManager enemyManager;
    private static PlayerShip playerShip;
    private static ProceduralBossGenerator bossGenerator;
    public static void setup() {
        textureManager = new TextureManager();
        shootingSystem = new ShootingSystem("assets/crosshair/hud_glow_albedo.png");
        enemyManager   = new EnemyManager();
        playerShip     = new PlayerShip();
        bossGenerator  = new ProceduralBossGenerator();
    }
    public static TextureManager provideTextureManager(){return textureManager;}
    public static ShootingSystem provideShootingSystem(){return shootingSystem;}
    public static EnemyManager provideEnemyManager(){return enemyManager;}
    public static PlayerShip providePlayerShip(){return playerShip;}
    public static ProceduralBossGenerator provideBossGenerator(){return bossGenerator;}
}
