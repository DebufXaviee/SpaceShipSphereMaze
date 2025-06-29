package com.boulderspace3d.app;
import com.badlogic.gdx.Game;import com.boulderspace3d.ui.SplashScreen;
public class GameMain extends Game {
    @Override public void create(){ setScreen(new SplashScreen(this)); }
    public void startLevel(){ /* switch to game screen */ }
}