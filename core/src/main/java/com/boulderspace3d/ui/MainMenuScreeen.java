package com.boulderspace3d.ui;
import com.badlogic.gdx.*;import com.badlogic.gdx.graphics.*;import com.badlogic.gdx.graphics.g2d.*;import com.boulderspace3d.app.GameMain;import com.boulderspace3d.data.DatabaseHelper;import com.boulderspace3d.data.DatabaseHelper.PlayerStats;
public class MainMenuScreen extends ScreenAdapter {
    private final GameMain game; private SpriteBatch batch; private BitmapFont font; private PlayerStats stats;
    public MainMenuScreen(GameMain gm){game=gm;}
    @Override public void show(){
        batch=new SpriteBatch(); font=new BitmapFont();
        stats=DatabaseHelper.fetchPlayerStats("iexid").orElse(new PlayerStats("iexid",1,0));
    }
    @Override public void render(float dt){
        Gdx.gl.glClearColor(0.1f,0.1f,0.2f,1);Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        float y=Gdx.graphics.getHeight()-40;
        font.draw(batch,"Bienvenue, "+stats.username,40,y);
        font.draw(batch,"Classement ELO : "+stats.elo,40,y-=30);
        font.draw(batch,"Big Boss tués : "+stats.bigBossKills,40,y-=30);
        int next=((stats.elo/100)+1)*100;float pct=(stats.elo%100)/100f*100f;
        font.draw(batch,String.format("Progression vers %d ELO : %.0f%%",next,pct),40,y-=30);
        font.draw(batch,"Appuyez pour démarrer",40,y-=60);
        batch.end();
        if(Gdx.input.justTouched())game.startLevel();
    }
    @Override public void dispose(){batch.dispose();font.dispose();}
}
