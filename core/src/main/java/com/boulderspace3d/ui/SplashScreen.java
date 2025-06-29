package com.boulderspace3d.ui;
import com.badlogic.gdx.*;import com.badlogic.gdx.graphics.*;import com.badlogic.gdx.graphics.g2d.SpriteBatch;import com.boulderspace3d.app.GameMain;
public class SplashScreen extends ScreenAdapter {
    private final GameMain game; private SpriteBatch batch; private Texture splash; private float elapsed;
    public SplashScreen(GameMain g){game=g;}
    @Override public void show(){batch=new SpriteBatch();splash=new Texture("assets/splash_logo.png");}
    @Override public void render(float dt){
        elapsed+=dt;Gdx.gl.glClearColor(0,0,0,1);Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();batch.draw(splash,Gdx.graphics.getWidth()/2f-splash.getWidth()/2f,Gdx.graphics.getHeight()/2f-splash.getHeight()/2f);batch.end();
        if(elapsed>2f)game.setScreen(new MainMenuScreen(game));
    }
    @Override public void dispose(){batch.dispose();splash.dispose();}
}
