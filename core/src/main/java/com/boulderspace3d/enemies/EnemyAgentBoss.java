package com.boulderspace3d.enemies;
import com.badlogic.gdx.graphics.Color;import com.badlogic.gdx.graphics.g2d.SpriteBatch;import com.boulderspace3d.ai.BossTemplate;
public class EnemyAgentBoss extends EnemyAgent {
    private final int cannonCount; private final float aggressiveness,size; private final Color color;
    public EnemyAgentBoss(BossTemplate t){
        super(t.health); this.color=t.color; this.size=t.size; this.cannonCount=t.cannonCount; this.aggressiveness=t.aggressiveness;
    }
    @Override public void update(float dt){ /* AI & movement scaled by aggressiveness */ }
    @Override public void render(SpriteBatch b){
        b.setColor(color); /* draw scaled */ b.setColor(Color.WHITE);
    }
}