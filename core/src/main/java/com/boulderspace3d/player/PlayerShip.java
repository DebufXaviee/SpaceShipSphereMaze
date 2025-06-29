package com.boulderspace3d.player;
import com.badlogic.gdx.graphics.Texture;import com.badlogic.gdx.graphics.g2d.SpriteBatch;import com.badlogic.gdx.math.MathUtils;import com.badlogic.gdx.math.Vector3;import com.boulderspace3d.input.InputController;import com.boulderspace3d.di.Injection;
public class PlayerShip {
    private final Texture texture; private Vector3 position; private float yaw,pitch,roll;
    private final float regenRate=0.2f, regenCooldown=5f; private float health=1f,timeSinceHit=0f;
    public PlayerShip(){ texture=new Texture("assets/playerShip/hull_panels_albedo.png"); position=new Vector3(); }
    public void update(InputController input,float dt){ /* movement & regen */ }
    public void render(SpriteBatch b){ b.draw(texture,position.x,position.y); }
    public void fireLeft(){ Injection.provideShootingSystem().shoot(position.x,position.y); }
    public void fireRight(){ Injection.provideShootingSystem().shoot(position.x,position.y); }
    public void takeDamage(float d){ health=Math.max(0,health-d); timeSinceHit=0; }
    public float getHealth(){return health;} public int getLevel(){return 1;} public Vector3 getPosition(){return position;}
    public void dispose(){ texture.dispose(); }
}