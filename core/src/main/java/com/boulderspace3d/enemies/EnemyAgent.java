package com.boulderspace3d.enemies;
import com.badlogic.gdx.graphics.Texture;import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class EnemyAgent {
    protected final Texture texture; protected float x,y,health;
    public EnemyAgent(float h){ texture=new Texture("assets/enemyShip/dark_alloy_albedo.png");health=h; x=100;y=100; }
    public void update(float dt){} public void render(SpriteBatch b){b.draw(texture,x,y);} public void takeDamage(float d){health=Math.max(0,health-d);} public void dispose(){texture.dispose();}
}