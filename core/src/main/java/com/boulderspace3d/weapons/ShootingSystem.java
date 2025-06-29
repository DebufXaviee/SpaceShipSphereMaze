package com.boulderspace3d.weapons;
import com.badlogic.gdx.graphics.Texture;import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class ShootingSystem {
    private final Texture crosshair;
    public ShootingSystem(String path){crosshair=new Texture(path);}
    public void shoot(float x,float y){ /* beam logic */ }
    public void render(SpriteBatch b){ /* draw crosshair */ }
    public void dispose(){crosshair.dispose();}
}