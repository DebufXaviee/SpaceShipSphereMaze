package com.boulderspace3d.ui;
import com.badlogic.gdx.*;import com.badlogic.gdx.graphics.GL20;import com.badlogic.gdx.graphics.g2d.SpriteBatch;import com.badlogic.gdx.graphics.glutils.ShapeRenderer;import com.badlogic.gdx.math.Vector3;import com.boulderspace3d.player.PlayerShip;
public class Minimap {
    private final ShapeRenderer shape; private final float mapR,revR,centerX,centerY; private final PlayerShip player;
    public Minimap(PlayerShip p,float cx,float cy,float mapR){
        this.player=p;centerX=cx;centerY=cy;this.mapR=mapR;revR=(float)Math.sqrt(0.3)*mapR;shape=new ShapeRenderer();
    }
    public void render(SpriteBatch b){
        shape.setProjectionMatrix(b.getProjectionMatrix());shape.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.gl.glEnable(GL20.GL_BLEND);shape.setColor(0,0,0,0.7f);shape.circle(centerX,centerY,mapR);
        Gdx.gl.glBlendFunc(GL20.GL_ZERO,GL20.GL_ONE_MINUS_SRC_ALPHA);shape.setColor(0,0,0,0);
        Vector3 pos=player.getPosition();float vx=centerX+(pos.x/100f)*mapR;float vy=centerY+(pos.z/100f)*mapR;shape.circle(vx,vy,revR);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);Gdx.gl.glDisable(GL20.GL_BLEND);shape.end();
    }
    public void dispose(){shape.dispose();}
}
